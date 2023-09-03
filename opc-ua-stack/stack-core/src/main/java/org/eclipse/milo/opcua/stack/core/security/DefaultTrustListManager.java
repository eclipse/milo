/*
 * Copyright (c) 2022 the Eclipse Milo Authors
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
import java.nio.charset.StandardCharsets;
import java.nio.file.ClosedWatchServiceException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import io.netty.buffer.ByteBufUtil;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultTrustListManager.class);

    private final AtomicReference<DateTime> lastUpdateTime = new AtomicReference<>(DateTime.MIN_VALUE);

    private final Set<X509Certificate> issuerCertificates = ConcurrentHashMap.newKeySet();
    private final Set<X509CRL> issuerCrls = ConcurrentHashMap.newKeySet();

    private final Set<X509Certificate> trustedCertificates = ConcurrentHashMap.newKeySet();
    private final Set<X509CRL> trustedCrls = ConcurrentHashMap.newKeySet();

    private final WatchService watchService;
    private final Thread watchThread;

    private final File baseDir;

    private final File issuerDir;
    private final File issuerCertsDir;
    private final File issuerCrlDir;

    private final File trustedDir;
    private final File trustedCertsDir;
    private final File trustedCrlDir;

    private final File rejectedDir;

    public DefaultTrustListManager(Path baseDir) throws IOException {
        this(baseDir.toFile());
    }

    public DefaultTrustListManager(File baseDir) throws IOException {
        this.baseDir = baseDir;
        ensureDirectoryExists(baseDir);

        issuerDir = baseDir.toPath().resolve("issuers").toFile();
        ensureDirectoryExists(issuerDir);

        issuerCertsDir = issuerDir.toPath().resolve("certs").toFile();
        ensureDirectoryExists(issuerCertsDir);

        issuerCrlDir = issuerDir.toPath().resolve("crl").toFile();
        ensureDirectoryExists(issuerCrlDir);

        trustedDir = baseDir.toPath().resolve("trusted").toFile();
        ensureDirectoryExists(trustedDir);

        trustedCertsDir = trustedDir.toPath().resolve("certs").toFile();
        ensureDirectoryExists(trustedCertsDir);

        trustedCrlDir = trustedDir.toPath().resolve("crl").toFile();
        ensureDirectoryExists(trustedCrlDir);

        rejectedDir = baseDir.toPath().resolve("rejected").toFile();
        ensureDirectoryExists(rejectedDir);

        watchService = FileSystems.getDefault().newWatchService();

        Map<WatchKey, Runnable> watchKeys = new ConcurrentHashMap<>();

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
            issuerCrlDir.toPath().register(
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
            trustedCrlDir.toPath().register(
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
        synchronizeTrustedCrls();

        lastUpdateTime.set(DateTime.now());
    }

    /**
     * Stop the certificate store watcher and free all resources.
     * <p>
     * After calling closing {@link CertificateValidator#validateCertificateChain(List)} will fail for all inputs.
     */
    @Override
    public synchronized void close() throws IOException {
        LOGGER.debug("Closing DefaultCertificateStore at {}", baseDir.getAbsolutePath());

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
    public synchronized List<X509CRL> getIssuerCrls() {
        return List.copyOf(issuerCrls);
    }

    @Override
    public synchronized List<X509CRL> getTrustedCrls() {
        return List.copyOf(trustedCrls);
    }

    @Override
    public synchronized List<X509Certificate> getIssuerCertificates() {
        return List.copyOf(issuerCertificates);
    }

    @Override
    public synchronized List<X509Certificate> getTrustedCertificates() {
        return List.copyOf(trustedCertificates);
    }

    @Override
    public synchronized void setIssuerCrls(List<X509CRL> issuerCrls) {
        replaceCrlsInDir(issuerCrls, issuerCrlDir);

        synchronizeIssuerCrls();

        lastUpdateTime.set(DateTime.now());
    }

    @Override
    public synchronized void setTrustedCrls(List<X509CRL> trustedCrls) {
        replaceCrlsInDir(trustedCrls, trustedCrlDir);

        synchronizeTrustedCrls();

        lastUpdateTime.set(DateTime.now());
    }

    @Override
    public synchronized void setIssuerCertificates(List<X509Certificate> issuerCertificates) {
        replaceCertificatesInDir(issuerCertificates, issuerCertsDir);

        synchronizeIssuerCerts();

        lastUpdateTime.set(DateTime.now());
    }

    @Override
    public synchronized void setTrustedCertificates(List<X509Certificate> trustedCertificates) {
        replaceCertificatesInDir(trustedCertificates, trustedCertsDir);

        synchronizeTrustedCerts();

        lastUpdateTime.set(DateTime.now());
    }

    @Override
    public synchronized void addIssuerCertificate(X509Certificate certificate) {
        issuerCertificates.add(certificate);

        writeCertificateToDir(certificate, issuerCertsDir);

        lastUpdateTime.set(DateTime.now());
    }

    @Override
    public synchronized void addTrustedCertificate(X509Certificate certificate) {
        trustedCertificates.add(certificate);

        writeCertificateToDir(certificate, trustedCertsDir);

        lastUpdateTime.set(DateTime.now());
    }

    @Override
    public synchronized boolean removeIssuerCertificate(ByteString thumbprint) {
        boolean found = deleteCertificateFile(issuerCertsDir, thumbprint);

        synchronizeIssuerCerts();

        lastUpdateTime.set(DateTime.now());

        return found;
    }

    @Override
    public synchronized boolean removeTrustedCertificate(ByteString thumbprint) {
        boolean found = deleteCertificateFile(trustedCertsDir, thumbprint);

        synchronizeTrustedCerts();

        lastUpdateTime.set(DateTime.now());

        return found;
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

    public File getIssuerCrlDir() {
        return issuerCrlDir;
    }

    public File getTrustedDir() {
        return trustedDir;
    }

    public File getTrustedCertsDir() {
        return trustedCertsDir;
    }

    public File getTrustedCrlDir() {
        return trustedCrlDir;
    }

    public File getRejectedDir() {
        return rejectedDir;
    }

    @Override
    public DateTime getLastUpdateTime() {
        return lastUpdateTime.get();
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
                    LOGGER.warn("Failed to delete issuer certificate: {}", file);
                }
                return true;
            }
        }

        return false;
    }

    private synchronized void replaceCrlsInDir(List<X509CRL> crls, File dir) {
        deleteDirectoryContents(dir);

        crls.forEach(crl -> writeCrlToDir(crl, dir));
    }

    private synchronized void replaceCertificatesInDir(List<X509Certificate> certificates, File dir) {
        deleteDirectoryContents(dir);

        certificates.forEach(certificate -> writeCertificateToDir(certificate, dir));
    }

    private static void deleteDirectoryContents(File dir) {
        File[] files = dir.listFiles();
        if (files == null) files = new File[0];

        Arrays.stream(files).forEach(file -> {
            try {
                Files.delete(file.toPath());
            } catch (IOException e) {
                LOGGER.warn("Failed to delete file: {}", file, e);
            }
        });
    }

    /**
     * If {@code rejectedDir} contains more than {@code MAX_REJECTED_CERTIFICATES}, delete older certificates until
     * there are {@code MAX_REJECTED_CERTIFICATES} - 1 certificates (thus creating room to add a new one).
     */
    synchronized void pruneOldRejectedCertificates() {
        File[] files = rejectedDir.listFiles();

        if (files != null && files.length >= MAX_REJECTED_CERTIFICATES) {
            int excessCount = files.length - MAX_REJECTED_CERTIFICATES;

            // If last modified of any file changes during the sort it can lead
            // to "IllegalArgumentException: Comparison method violates its general contract!"
            // thrown from Java's TimSort implementation.
            Map<File, Long> stableLastModified = new HashMap<>();
            Arrays.stream(files).forEach(f -> stableLastModified.put(f, f.lastModified()));

            Arrays.stream(files)
                .sorted((f1, f2) -> Long.compareUnsigned(stableLastModified.get(f1), stableLastModified.get(f2)))
                .limit(excessCount + 1)
                .forEach(file -> {
                    if (!file.delete()) {
                        LOGGER.warn("Unable to delete rejected certificate: {}", file);
                    }
                });
        }
    }

    private synchronized void synchronizeIssuerCerts() {
        LOGGER.debug("Synchronizing issuer certs...");

        File[] files = issuerCertsDir.listFiles();
        if (files == null) files = new File[0];

        issuerCertificates.clear();

        Arrays.stream(files)
            .flatMap(cert -> decodeCertificateFile(cert).stream())
            .forEach(issuerCertificates::add);
    }

    private synchronized void synchronizeIssuerCrls() {
        LOGGER.debug("Synchronizing issuer CRLs...");

        File[] files = issuerCrlDir.listFiles();
        if (files == null) files = new File[0];

        issuerCrls.clear();

        Arrays.stream(files)
            .flatMap(crl -> decodeCrlFile(crl).stream())
            .flatMap(List::stream)
            .forEach(issuerCrls::add);
    }

    private synchronized void synchronizeTrustedCerts() {
        LOGGER.debug("Synchronizing trusted certs...");

        File[] files = trustedCertsDir.listFiles();
        if (files == null) files = new File[0];

        trustedCertificates.clear();

        Arrays.stream(files)
            .flatMap(cert -> decodeCertificateFile(cert).stream())
            .forEach(trustedCertificates::add);
    }

    private synchronized void synchronizeTrustedCrls() {
        LOGGER.debug("Synchronizing trusted CRLs...");

        File[] files = trustedCrlDir.listFiles();
        if (files == null) files = new File[0];

        trustedCrls.clear();

        Arrays.stream(files)
            .flatMap(crl -> decodeCrlFile(crl).stream())
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

            LOGGER.debug("Wrote CRL entry: {}", f.getAbsolutePath());
        } catch (Exception e) {
            LOGGER.error("Error writing CRL", e);
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

            LOGGER.debug("Wrote certificate entry: {}", f.getAbsolutePath());
        } catch (Exception e) {
            LOGGER.error("Error writing certificate", e);
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
            LOGGER.debug("Error decoding CRL file: {}", f.toString(), e);

            return Optional.empty();
        }
    }

    private static Optional<X509Certificate> decodeCertificateFile(File f) {
        try {
            try (FileInputStream inputStream = new FileInputStream(f)) {
                return Optional.of(CertificateUtil.decodeCertificate(inputStream));
            }
        } catch (Throwable t) {
            LOGGER.debug("Error decoding certificate file: {}", f.toString(), t);

            return Optional.empty();
        }
    }

    private static String getFilename(X509CRL crl) throws Exception {
        String thumbprint = ByteBufUtil.hexDump(sha1(crl.getEncoded()));

        return String.format("%s.crl", thumbprint);
    }

    private static String getFilename(X509Certificate certificate) throws Exception {
        String[] ss = certificate.getSubjectX500Principal().getName().split(",");
        String thumbprint = ByteBufUtil.hexDump(sha1(certificate.getSignature()));
        String name = ss.length > 0 ? ss[0] : certificate.getSubjectX500Principal().getName();

        return String.format("%s [%s].der", thumbprint, sanitizeForUseInFilename(name));
    }

    static String sanitizeForUseInFilename(String name) {
        String encoded = URLEncoder.encode(name, StandardCharsets.UTF_8);

        // '*' is excluded from escaping by URLEncoder
        return encoded.replaceAll("\\*", "_");
    }

    private static void ensureDirectoryExists(File dir) throws IOException {
        if (!dir.exists() && !dir.mkdirs()) {
            throw new IOException("unable to create directory at " + dir.getAbsolutePath());
        }
    }

    private static class Watcher implements Runnable {

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
                        LOGGER.warn("Failed to reset watch key");
                        break;
                    }
                } catch (ClosedWatchServiceException e) {
                    LOGGER.info("Watcher got closed");
                    return;
                } catch (InterruptedException e) {
                    LOGGER.error("Watcher interrupted.", e);
                }
            }
        }

    }

}
