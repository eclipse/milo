/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.ClosedWatchServiceException;
import java.nio.file.FileSystems;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.security.cert.CRL;
import java.security.cert.CRLException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import io.netty.buffer.ByteBufUtil;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.util.CertificateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.util.DigestUtil.sha1;

public class DefaultTrustListManager implements TrustListManager, AutoCloseable {

    /**
     * The maximum number of certificates that can accumulate in the rejected
     * directory before the oldest certificates are deleted to make room for
     * newer ones.
     */
    static final int MAX_REJECTED_CERTIFICATES = 128;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Set<X509Certificate> issuerCertificates = Sets.newConcurrentHashSet();
    private final Set<X509CRL> issuerCrls = Sets.newConcurrentHashSet();

    private final Set<X509Certificate> trustedCertificates = Sets.newConcurrentHashSet();
    private final Set<X509CRL> trustedCrls = Sets.newConcurrentHashSet();

    private final WatchService watchService;
    private final Thread watchThread;

    private final File baseDir;

    private final File issuerDir;
    private final File issuerCertsDir;
    private final File issuerCrlsDir;

    private final File trustedDir;
    private final File trustedCertsDir;
    private final File trustedCrlsDir;

    private final File rejectedDir;

    public DefaultTrustListManager(File baseDir) throws IOException {
        this.baseDir = baseDir;
        ensureDirectoryExists(baseDir);

        issuerDir = baseDir.toPath().resolve("issuers").toFile();
        ensureDirectoryExists(issuerDir);

        issuerCertsDir = issuerDir.toPath().resolve("certs").toFile();
        ensureDirectoryExists(issuerCertsDir);

        issuerCrlsDir = issuerDir.toPath().resolve("crls").toFile();
        ensureDirectoryExists(issuerCrlsDir);

        trustedDir = baseDir.toPath().resolve("trusted").toFile();
        ensureDirectoryExists(trustedDir);

        trustedCertsDir = trustedDir.toPath().resolve("certs").toFile();
        ensureDirectoryExists(trustedCertsDir);

        trustedCrlsDir = trustedDir.toPath().resolve("crls").toFile();
        ensureDirectoryExists(trustedCrlsDir);

        rejectedDir = baseDir.toPath().resolve("rejected").toFile();
        ensureDirectoryExists(rejectedDir);

        watchService = FileSystems.getDefault().newWatchService();

        Map<WatchKey, Runnable> watchKeys = Maps.newConcurrentMap();

        watchKeys.put(
            issuerCertsDir.toPath().register(
                watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY
            ),
            this::synchronizeIssuerCerts
        );
        watchKeys.put(
            issuerCrlsDir.toPath().register(
                watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY
            ),
            this::synchronizeIssuerCrls
        );
        watchKeys.put(
            trustedCertsDir.toPath().register(
                watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY
            ),
            this::synchronizeTrustedCerts
        );
        watchKeys.put(
            trustedCrlsDir.toPath().register(
                watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY
            ),
            this::synchronizeTrustedCrls
        );

        watchThread = new Thread(new Watcher(watchService, watchKeys));
        watchThread.setName("certificate-store-watcher");
        watchThread.setDaemon(true);
        watchThread.start();

        synchronizeIssuerCerts();
        synchronizeIssuerCrls();
        synchronizeTrustedCerts();
    }

    /**
     * Stop the certificate store watcher and free all resources.
     * <p>
     * After calling closing {@link CertificateValidator#verifyTrustChain(List)} will fail for all inputs.
     */
    @Override
    public synchronized void close() throws IOException {
        logger.info("Closing DefaultCertificateStore at {}", baseDir.getAbsolutePath());

        watchService.close();

        try {
            watchThread.join(5000);
        } catch (InterruptedException e) {
            throw new IOException(e);
        }

        issuerCertificates.clear();
        issuerCrls.clear();

        trustedCertificates.clear();
    }

    @Override
    public synchronized ImmutableList<X509CRL> getIssuerCrls() {
        return ImmutableList.copyOf(issuerCrls);
    }

    @Override
    public synchronized ImmutableList<X509CRL> getTrustedCrls() {
        return ImmutableList.copyOf(trustedCrls);
    }

    @Override
    public synchronized ImmutableList<X509Certificate> getIssuerCertificates() {
        return ImmutableList.copyOf(issuerCertificates);
    }

    @Override
    public synchronized ImmutableList<X509Certificate> getTrustedCertificates() {
        return ImmutableList.copyOf(trustedCertificates);
    }

    @Override
    public synchronized ImmutableList<X509Certificate> getRejectedCertificates() {
        File[] files = rejectedDir.listFiles();
        if (files == null) files = new File[0];

        return ImmutableList.copyOf(
            Arrays.stream(files)
                .flatMap(cert ->
                    decodeCertificateFile(cert)
                        .map(Stream::of).orElse(Stream.empty()))
                .collect(Collectors.toList())
        );
    }

    @Override
    public synchronized void setIssuerCrls(List<X509CRL> issuerCrls) {
        replaceCrlsInDir(issuerCrls, issuerCrlsDir);

        synchronizeIssuerCrls();
    }

    @Override
    public synchronized void setTrustedCrls(List<X509CRL> trustedCrls) {
        replaceCrlsInDir(trustedCrls, trustedCrlsDir);

        synchronizeTrustedCrls();
    }

    @Override
    public synchronized void setIssuerCertificates(List<X509Certificate> issuerCertificates) {
        replaceCertificatesInDir(issuerCertificates, issuerCertsDir);

        synchronizeIssuerCerts();
    }

    @Override
    public synchronized void setTrustedCertificates(List<X509Certificate> trustedCertificates) {
        replaceCertificatesInDir(trustedCertificates, trustedCertsDir);

        synchronizeTrustedCerts();
    }

    @Override
    public synchronized void addIssuerCertificate(X509Certificate certificate) {
        issuerCertificates.add(certificate);

        writeCertificateToDir(certificate, issuerCertsDir);
    }

    @Override
    public synchronized void addTrustedCertificate(X509Certificate certificate) {
        trustedCertificates.add(certificate);

        writeCertificateToDir(certificate, trustedCertsDir);
    }

    @Override
    public synchronized void addRejectedCertificate(X509Certificate certificate) {
        pruneOldRejectedCertificates();

        writeCertificateToDir(certificate, rejectedDir);
    }

    @Override
    public synchronized boolean removeIssuerCertificate(ByteString thumbprint) {
        boolean found = deleteCertificateFile(issuerCertsDir, thumbprint);

        synchronizeIssuerCerts();

        return found;
    }

    @Override
    public synchronized boolean removeTrustedCertificate(ByteString thumbprint) {
        boolean found = deleteCertificateFile(trustedCertsDir, thumbprint);

        synchronizeTrustedCerts();

        return found;
    }

    @Override
    public synchronized boolean removeRejectedCertificate(ByteString thumbprint) {
        return deleteCertificateFile(rejectedDir, thumbprint);
    }

    public File getBaseDir() {
        return baseDir;
    }

    public File getIssuerDir() {
        return issuerDir;
    }

    public File getIssuerCertsDir() {
        return issuerCertsDir;
    }

    public File getIssuerCrlsDir() {
        return issuerCrlsDir;
    }

    public File getTrustedDir() {
        return trustedDir;
    }

    public File getTrustedCertsDir() {
        return trustedCertsDir;
    }

    public File getTrustedCrlsDir() {
        return trustedCrlsDir;
    }

    public File getRejectedDir() {
        return rejectedDir;
    }

    private synchronized boolean deleteCertificateFile(File certificateDir, ByteString thumbprint) {
        File[] files = certificateDir.listFiles();
        if (files == null) files = new File[0];

        for (File file : files) {
            boolean matchesThumbprint = decodeCertificateFile(file).map(c -> {
                try {
                    ByteString bs = CertificateUtil.thumbprint(c);

                    return bs.equals(thumbprint);
                } catch (UaException e) {
                    return false;
                }
            }).orElse(false);

            if (matchesThumbprint) {
                if (!file.delete()) {
                    logger.warn("Failed to delete issuer certificate: {}", file);
                }
                return true;
            }
        }

        return false;
    }

    private synchronized void replaceCrlsInDir(List<X509CRL> crls, File dir) {
        File[] files = dir.listFiles();
        if (files == null) files = new File[0];

        Arrays.stream(files).forEach(File::delete);

        crls.forEach(crl -> writeCrlToDir(crl, dir));
    }

    private synchronized void replaceCertificatesInDir(List<X509Certificate> certificates, File dir) {
        File[] files = dir.listFiles();
        if (files == null) files = new File[0];

        Arrays.stream(files).forEach(File::delete);

        certificates.forEach(certificate -> writeCertificateToDir(certificate, dir));
    }

    /**
     * If {@code rejectedDir} contains more than {@code MAX_REJECTED_CERTIFICATES}, delete older certificates until
     * there are {@code MAX_REJECTED_CERTIFICATES} - 1 certificates (thus creating room to add a new one).
     */
    synchronized void pruneOldRejectedCertificates() {
        File[] files = rejectedDir.listFiles();

        if (files != null && files.length >= MAX_REJECTED_CERTIFICATES) {
            int excessCount = files.length - MAX_REJECTED_CERTIFICATES;

            Arrays.stream(files)
                .sorted(
                    (o1, o2) ->
                        (int) (o1.lastModified() - o2.lastModified()))
                .limit(excessCount + 1)
                .forEach(file -> {
                    if (!file.delete()) {
                        logger.warn("Unable to delete rejected certificate: {}", file);
                    }
                });
        }
    }

    private synchronized void synchronizeIssuerCerts() {
        File[] files = issuerCertsDir.listFiles();
        if (files == null) files = new File[0];

        issuerCertificates.clear();

        Arrays.stream(files)
            .flatMap(cert ->
                decodeCertificateFile(cert)
                    .map(Stream::of).orElse(Stream.empty()))
            .forEach(issuerCertificates::add);
    }

    private synchronized void synchronizeIssuerCrls() {
        File[] files = issuerCrlsDir.listFiles();
        if (files == null) files = new File[0];

        issuerCrls.clear();

        Arrays.stream(files)
            .flatMap(crl ->
                decodeCrlFile(crl)
                    .map(Stream::of).orElse(Stream.empty()))
            .flatMap(List::stream)
            .forEach(issuerCrls::add);
    }

    private synchronized void synchronizeTrustedCerts() {
        File[] files = trustedCertsDir.listFiles();
        if (files == null) files = new File[0];

        trustedCertificates.clear();

        Arrays.stream(files)
            .flatMap(cert ->
                decodeCertificateFile(cert)
                    .map(Stream::of).orElse(Stream.empty()))
            .forEach(trustedCertificates::add);
    }

    private synchronized void synchronizeTrustedCrls() {
        File[] files = trustedCrlsDir.listFiles();
        if (files == null) files = new File[0];

        trustedCrls.clear();

        Arrays.stream(files)
            .flatMap(crl ->
                decodeCrlFile(crl)
                    .map(Stream::of).orElse(Stream.empty()))
            .flatMap(List::stream)
            .forEach(trustedCrls::add);
    }

    private static void writeCrlToDir(X509CRL crl, File dir) {
        try {
            String filename = getFilename(crl);
            File f = dir.toPath().resolve(filename).toFile();

            try (FileOutputStream fos = new FileOutputStream(f)) {
                fos.write(crl.getEncoded());
                fos.flush();
            }

            LoggerFactory.getLogger(DefaultTrustListManager.class)
                .debug("Wrote CRL entry: {}", f.getAbsolutePath());
        } catch (Exception e) {
            LoggerFactory.getLogger(DefaultTrustListManager.class)
                .error("Error writing CRL", e);
        }
    }

    private static void writeCertificateToDir(X509Certificate certificate, File dir) {
        try {
            String filename = getFilename(certificate);
            File f = dir.toPath().resolve(filename).toFile();

            try (FileOutputStream fos = new FileOutputStream(f)) {
                fos.write(certificate.getEncoded());
                fos.flush();
            }

            LoggerFactory.getLogger(DefaultTrustListManager.class)
                .debug("Wrote certificate entry: {}", f.getAbsolutePath());
        } catch (Exception e) {
            LoggerFactory.getLogger(DefaultTrustListManager.class)
                .error("Error writing certificate", e);
        }
    }

    private static Optional<List<X509CRL>> decodeCrlFile(File f) {
        try {
            CertificateFactory factory = CertificateFactory.getInstance("X.509");
            Collection<? extends CRL> crls = factory.generateCRLs(new FileInputStream(f));

            return Optional.of(
                crls.stream()
                    .filter(crl -> crl instanceof X509CRL)
                    .map(X509CRL.class::cast)
                    .collect(Collectors.toList())
            );
        } catch (CertificateException | FileNotFoundException | CRLException e) {
            return Optional.empty();
        }
    }

    private static Optional<X509Certificate> decodeCertificateFile(File f) {
        try {
            try (FileInputStream inputStream = new FileInputStream(f)) {
                return Optional.of(CertificateUtil.decodeCertificate(inputStream));
            }
        } catch (Throwable ignored) {
            return Optional.empty();
        }
    }

    private static String getFilename(X509CRL crl) throws Exception {
        String thumbprint = ByteBufUtil.hexDump(sha1(crl.getEncoded()));

        return String.format("%s.crl", thumbprint);
    }

    private static String getFilename(X509Certificate certificate) throws Exception {
        String[] ss = certificate.getSubjectX500Principal().getName().split(",");
        String name = ss.length > 0 ? ss[0] : certificate.getSubjectX500Principal().getName();
        String thumbprint = ByteBufUtil.hexDump(sha1(certificate.getSignature()));

        return String.format("%s [%s].der", thumbprint, URLEncoder.encode(name, "UTF-8"));
    }

    private static void ensureDirectoryExists(File dir) throws IOException {
        if (!dir.exists() && !dir.mkdirs()) {
            throw new IOException("unable to create directory at " + dir.getAbsolutePath());
        }
    }

    private class Watcher implements Runnable {

        private final WatchService watchService;
        private final Map<WatchKey, Runnable> watchKeys;

        Watcher(WatchService watchService, Map<WatchKey, Runnable> watchKeys) {
            this.watchService = watchService;
            this.watchKeys = watchKeys;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    WatchKey key = watchService.take();

                    if (watchKeys.containsKey(key)) {
                        boolean synchronize = key.pollEvents().stream()
                            .anyMatch(e -> e.kind() != StandardWatchEventKinds.OVERFLOW);

                        if (synchronize) watchKeys.get(key).run();
                    }

                    if (!key.reset()) {
                        logger.warn("Failed to reset watch key");
                        break;
                    }
                } catch (ClosedWatchServiceException e) {
                    logger.info("Watcher got closed");
                    return;
                } catch (InterruptedException e) {
                    logger.error("Watcher interrupted.", e);
                }
            }
        }

    }

}
