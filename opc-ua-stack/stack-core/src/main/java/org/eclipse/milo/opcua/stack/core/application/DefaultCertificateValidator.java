/*
 * Copyright (c) 2016 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 * 	http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * 	http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.stack.core.application;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.FileSystems;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.util.CertificateUtil;
import org.eclipse.milo.opcua.stack.core.util.CertificateValidationUtil;
import org.eclipse.milo.opcua.stack.core.util.DigestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.stream.Collectors.toSet;

public class DefaultCertificateValidator implements CertificateValidator {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Set<X509Certificate> trustedCertificates = Sets.newConcurrentHashSet();
    private final Set<X509Certificate> authorityCertificates = Sets.newConcurrentHashSet();

    private final File trustedDir;
    private final File rejectedDir;
    private final File revocationDir;

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
    public synchronized void verifyTrustChain(X509Certificate certificate,
                                              List<X509Certificate> chain) throws UaException {
        try {
            CertificateValidationUtil.validateTrustChain(
                certificate, chain, trustedCertificates, authorityCertificates);

        } catch (UaException e) {
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
            WatchService watchService = FileSystems.getDefault().newWatchService();

            WatchKey trustedKey = trustedDir.toPath().register(
                watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY
            );

            Thread thread = new Thread(new Watcher(watchService, trustedKey));
            thread.setName("ua-certificate-directory-watcher");
            thread.setDaemon(true);
            thread.start();
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
        try {
            String[] ss = certificate.getSubjectX500Principal().getName().split(",");
            String name = ss.length > 0 ? ss[0] : certificate.getSubjectX500Principal().getName();
            String thumbprint = ByteBufUtil.hexDump(Unpooled.wrappedBuffer(DigestUtil.sha1(certificate.getEncoded())));

            String filename = String.format("%s [%s].der", URLEncoder.encode(name, "UTF-8"), thumbprint);

            File f = new File(rejectedDir.getAbsolutePath() + File.separator + filename);

            FileOutputStream fos = new FileOutputStream(f);
            fos.write(certificate.getEncoded());
            fos.flush();
            fos.close();

            logger.debug("Added rejected certificate entry: {}", filename);
        } catch (CertificateEncodingException | IOException e) {
            logger.error("Error adding rejected certificate entry.", e);
        }
    }

    private class Watcher implements Runnable {

        private final WatchService watchService;
        private final WatchKey trustedKey;

        public Watcher(WatchService watchService, WatchKey trustedKey) {
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
                        break;
                    }
                } catch (InterruptedException e) {
                    logger.error("Watcher interrupted.", e);
                }
            }
        }

    }

}
