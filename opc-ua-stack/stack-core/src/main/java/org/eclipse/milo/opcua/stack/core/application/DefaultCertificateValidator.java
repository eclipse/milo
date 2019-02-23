/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.ClosedWatchServiceException;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.google.common.util.concurrent.Uninterruptibles;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.util.CertificateUtil;
import org.eclipse.milo.opcua.stack.core.util.CertificateValidationUtil;
import org.eclipse.milo.opcua.stack.core.util.DigestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.stream.Collectors.toSet;

public class DefaultCertificateValidator implements CertificateValidator, AutoCloseable {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Set<X509Certificate> trustedCertificates = Sets.newConcurrentHashSet();
    private final Set<X509Certificate> authorityCertificates = Sets.newConcurrentHashSet();

    private final File trustedDir;
    private final File rejectedDir;
    private final File revocationDir;

    private WatchService watchService;
    private Thread thread;

    public DefaultCertificateValidator(File certificatesBaseDir) {
        trustedDir = new File(certificatesBaseDir.getAbsolutePath() + File.separator + "trusted");
        if (!trustedDir.exists() && !trustedDir.mkdirs()) {
            logger.warn("Could not create trusted certificate dir: {}", trustedDir);
        }

        rejectedDir = new File(certificatesBaseDir.getAbsolutePath() + File.separator + "rejected");
        if (!rejectedDir.exists() && !rejectedDir.mkdirs()) {
            logger.warn("Could not create rejected certificate dir: {}", rejectedDir);
        }

        revocationDir = new File(certificatesBaseDir.getAbsolutePath() + File.separator + "revocation");
        if (!revocationDir.exists() && !revocationDir.mkdirs()) {
            logger.warn("Could not create revocation certificate dir: {}", revocationDir);
        }

        createWatchService();

        synchronizeTrustedCertificates();
    }

    /**
     * Create a new instance specifying explicit directories
     * <br/>
     * If the parameter {@code rejectedDir} is {@code null}, then the validator will not write out
     * the rejected certificates
     * <br/>
     * In contrast to the constructor {@link #DefaultCertificateValidator(File)}, this constructor
     * expects all directories to be already existing.
     *
     * @param trustedDir    the directory of trusted certificates
     * @param rejectedDir   the optional directory of rejected certificates, may be {@code null}
     * @param revocationDir the optional directory of revoked certificates, may be {@code null}
     */
    public DefaultCertificateValidator(
        @Nonnull File trustedDir,
        @Nullable File rejectedDir,
        @Nullable File revocationDir) {

        Objects.requireNonNull(trustedDir);

        this.trustedDir = trustedDir.getAbsoluteFile();
        this.rejectedDir = rejectedDir != null ? rejectedDir.getAbsoluteFile() : null;
        this.revocationDir = revocationDir != null ? revocationDir.getAbsoluteFile() : null;

        if (!trustedDir.isDirectory()) {
            throw new IllegalArgumentException(
                String.format("Directory of trusted certificates could not be found: %s", trustedDir.getAbsolutePath())
            );
        }

        if (rejectedDir != null && !rejectedDir.isDirectory()) {
            throw new IllegalArgumentException(
                String.format(
                    "Directory of rejected certificates must be an existing, writable directory: %s",
                    rejectedDir.getAbsolutePath()
                )
            );
        }

        if (revocationDir != null && !revocationDir.isDirectory()) {
            throw new IllegalArgumentException(
                String.format(
                    "Directory of revoked certificates must be an existing, writable directory: %s",
                    revocationDir.getAbsolutePath()
                )
            );
        }

        createWatchService();
        synchronizeTrustedCertificates();
    }

    /**
     * This will stop the validator and free all resources.
     * <br/>
     * After calling this method the method
     * {@link CertificateValidator#verifyTrustChain(List)} will report all
     * certificates as invalid.
     */
    @Override
    public void close() throws IOException {
        final WatchService watchService;
        final Thread thread;

        // get and reset
        synchronized (this) {
            logger.info("Closing default certificate validator");

            watchService = this.watchService;
            thread = this.thread;

            this.watchService = null;
            this.thread = null;

            this.trustedCertificates.clear();
            this.authorityCertificates.clear();
        }

        // dispose
        if (watchService != null) {
            watchService.close();
        }

        if (thread != null) {
            Uninterruptibles.joinUninterruptibly(thread);
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
    public synchronized void verifyTrustChain(List<X509Certificate> certificateChain) throws UaException {
        try {
            CertificateValidationUtil.verifyTrustChain(
                certificateChain, trustedCertificates, authorityCertificates);
        } catch (UaException e) {
            X509Certificate certificate = certificateChain.get(0);
            certificateRejected(certificate);
            throw e;
        }
    }

    /**
     * @return an immutable copy of the current trusted certificates.
     */
    public synchronized ImmutableSet<X509Certificate> getTrustedCertificates() {
        return ImmutableSet.copyOf(trustedCertificates);
    }

    /**
     * @return an immutable copy of the current trusted authority certificates.
     */
    public synchronized ImmutableSet<X509Certificate> getAuthorityCertificates() {
        return ImmutableSet.copyOf(authorityCertificates);
    }

    private void createWatchService() {
        try {
            this.watchService = trustedDir.toPath().getFileSystem().newWatchService();

            WatchKey trustedKey = trustedDir.toPath().register(
                watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY
            );

            this.thread = new Thread(new Watcher(watchService, trustedKey));
            this.thread.setName("ua-certificate-directory-watcher");
            this.thread.setDaemon(true);
            this.thread.start();
        } catch (IOException e) {
            logger.error("Error creating WatchService.", e);
        }
    }

    private void synchronizeTrustedCertificates() {
        logger.debug("Synchronizing trusted certificates...");

        Set<X509Certificate> certificates = certificatesFromDir(trustedDir);

        Set<X509Certificate> trusted = certificates.stream()
            .filter(c -> c.getBasicConstraints() == -1)
            .collect(toSet());

        Set<X509Certificate> authority = certificates.stream()
            .filter(c -> c.getBasicConstraints() != -1)
            .collect(toSet());

        synchronized (DefaultCertificateValidator.this) {
            trustedCertificates.clear();
            trustedCertificates.addAll(trusted);

            authorityCertificates.clear();
            authorityCertificates.addAll(authority);
        }

        logger.debug("trustedCertificates.size()={}, authorityCertificates.size()={}",
            trustedCertificates.size(), authorityCertificates.size());
    }

    private Set<X509Certificate> certificatesFromDir(File dir) {
        File[] files = dir.listFiles();
        if (files == null) files = new File[0];

        return Arrays.stream(files)
            .map(this::file2certificate)
            .filter(c -> c != null)
            .collect(toSet());
    }


    private X509Certificate file2certificate(File f) {
        try {
            try (FileInputStream inputStream = new FileInputStream(f)) {
                return CertificateUtil.decodeCertificate(inputStream);
            }
        } catch (Throwable ignored) {
            return null;
        }
    }

    private void certificateRejected(X509Certificate certificate) {
        if (rejectedDir == null) {
            return;
        }

        try {
            String[] ss = certificate.getSubjectX500Principal().getName().split(",");
            String name = ss.length > 0 ? ss[0] : certificate.getSubjectX500Principal().getName();
            String thumbprint = ByteBufUtil.hexDump(Unpooled.wrappedBuffer(DigestUtil.sha1(certificate.getEncoded())));

            String filename = String.format("%s [%s].der", URLEncoder.encode(name, "UTF-8"), thumbprint);

            File f = new File(rejectedDir.getAbsolutePath() + File.separator + filename);

            try (FileOutputStream fos = new FileOutputStream(f)) {
                fos.write(certificate.getEncoded());
                fos.flush();
            }

            logger.debug("Added rejected certificate entry: {}", filename);
        } catch (CertificateEncodingException | IOException e) {
            logger.error("Error adding rejected certificate entry.", e);
        }
    }

    private class Watcher implements Runnable {

        private final WatchService watchService;
        private final WatchKey trustedKey;

        Watcher(WatchService watchService, WatchKey trustedKey) {
            this.watchService = watchService;
            this.trustedKey = trustedKey;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    WatchKey key = watchService.take();

                    if (key == trustedKey) {
                        for (WatchEvent<?> watchEvent : key.pollEvents()) {
                            WatchEvent.Kind<?> kind = watchEvent.kind();

                            if (kind != StandardWatchEventKinds.OVERFLOW) {
                                synchronizeTrustedCertificates();
                            }
                        }
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
