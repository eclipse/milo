/*
 * Copyright (c) 2017 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.stack.core.application;

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
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.util.CertificateUtil;
import org.eclipse.milo.opcua.stack.core.util.CertificateValidationUtil;
import org.eclipse.milo.opcua.stack.core.util.DigestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.collect.Lists.newArrayList;

public class DirectoryCertificateValidator implements CertificateValidator, AutoCloseable {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Set<X509Certificate> trustedCertificates = Sets.newConcurrentHashSet();
    private final Set<CRL> trustedCrls = Sets.newConcurrentHashSet();

    private final Set<X509Certificate> issuerCertificates = Sets.newConcurrentHashSet();
    private final Set<CRL> issuerCrls = Sets.newConcurrentHashSet();

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

    public DirectoryCertificateValidator(File baseDir) throws IOException {
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
        synchronizeTrustedCrls();
    }

    /**
     * Stop the certificate store watcher and free all resources.
     * <p>
     * After calling closing {@link #verifyTrustChain(X509Certificate, List)} will fail for all inputs.
     */
    @Override
    public void close() throws IOException {
        synchronized (this) {
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
            trustedCrls.clear();
        }
    }

    @Override
    public void validate(X509Certificate certificate) throws UaException {
        try {
            CertificateValidationUtil.validateCertificateValidity(certificate);
        } catch (UaException e) {
            certificateRejected(certificate);
            throw e;
        }
    }

    @Override
    public void verifyTrustChain(X509Certificate certificate, List<X509Certificate> chain) throws UaException {
        try {
            // TODO pass CRLs to validateTrustChain once supported
            CertificateValidationUtil.validateTrustChain(
                certificate,
                chain,
                trustedCertificates,
                issuerCertificates
            );
        } catch (UaException e) {
            certificateRejected(certificate);
            throw e;
        }
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

    private void certificateRejected(X509Certificate certificate) {
        try {
            String[] ss = certificate.getSubjectX500Principal().getName().split(",");
            String name = ss.length > 0 ? ss[0] : certificate.getSubjectX500Principal().getName();
            String thumbprint = ByteBufUtil.hexDump(Unpooled.wrappedBuffer(DigestUtil.sha1(certificate.getEncoded())));

            String filename = String.format("%s [%s].der", thumbprint, URLEncoder.encode(name, "UTF-8"));

            File f = new File(rejectedDir.getAbsolutePath() + File.separator + filename);

            try (FileOutputStream fos = new FileOutputStream(f)) {
                fos.write(certificate.getEncoded());
                fos.flush();
            }

            logger.info("Added rejected certificate entry: {}", filename);
        } catch (CertificateEncodingException | IOException e) {
            logger.error("Error adding rejected certificate entry.", e);
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

    private Optional<X509Certificate> decodeCertificateFile(File f) {
        try {
            try (FileInputStream inputStream = new FileInputStream(f)) {
                return Optional.of(CertificateUtil.decodeCertificate(inputStream));
            }
        } catch (Throwable ignored) {
            return Optional.empty();
        }
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

    private Optional<List<CRL>> decodeCrlFile(File f) {
        try {
            CertificateFactory factory = CertificateFactory.getInstance("X.509");
            Collection<? extends CRL> crls = factory.generateCRLs(new FileInputStream(f));
            return Optional.of(newArrayList(crls));
        } catch (CertificateException | FileNotFoundException | CRLException e) {
            return Optional.empty();
        }
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
