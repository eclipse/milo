/*
 * Copyright (c) 2023 the Eclipse Milo Authors
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
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Function;
import java.util.function.Supplier;

import org.eclipse.milo.opcua.stack.core.NodeIds;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KeyStoreCertificateStore implements CertificateStore {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final AtomicBoolean initialized = new AtomicBoolean(false);

    private final ReadWriteLock keyStoreLock = new ReentrantReadWriteLock(true);

    private final Map<String, Entry> cache = new HashMap<>();

    private KeyStore keyStore;

    private final KeyStoreSettings settings;

    public KeyStoreCertificateStore(KeyStoreSettings settings) {
        this.settings = settings;
    }

    public void initialize() throws Exception {
        if (initialized.compareAndSet(false, true)) {
            logger.info("Loading KeyStore at {}", settings.keyStorePath);

            keyStore = KeyStore.getInstance("pkcs12");

            File keyStoreFile = settings.keyStorePath.toFile();

            if (keyStoreFile.exists()) {
                keyStore.load(
                    new FileInputStream(keyStoreFile),
                    settings.getKeyStorePassword.get()
                );
            } else {
                keyStore.load(
                    null,
                    settings.getKeyStorePassword.get()
                );

                keyStore.store(
                    new FileOutputStream(keyStoreFile),
                    settings.getKeyStorePassword.get()
                );
            }
        }
    }

    @Override
    public boolean contains(NodeId certificateTypeId) throws Exception {
        if (!initialized.get()) {
            throw new IllegalStateException("not initialized");
        }
        try {
            keyStoreLock.readLock().lock();

            String alias = getAlias(certificateTypeId);

            return alias != null && (cache.containsKey(alias) || keyStore.containsAlias(alias));
        } finally {
            keyStoreLock.readLock().unlock();
        }
    }

    @Override
    public Entry get(NodeId certificateTypeId) throws Exception {
        if (!initialized.get()) {
            throw new IllegalStateException("not initialized");
        }

        try {
            keyStoreLock.readLock().lock();

            String alias = getAlias(certificateTypeId);

            if (alias != null) {
                if (cache.containsKey(alias)) {
                    return cache.get(alias);
                }

                Key key = keyStore.getKey(alias, settings.getAliasPassword.apply(alias));
                Certificate[] certificateChain = keyStore.getCertificateChain(alias);

                if (key instanceof PrivateKey && certificateChain != null) {
                    X509Certificate[] x509CertificateChain = Arrays.stream(certificateChain)
                        .map(c -> (X509Certificate) c)
                        .toArray(X509Certificate[]::new);

                    var entry = new Entry((PrivateKey) key, x509CertificateChain);

                    cache.putIfAbsent(alias, entry);

                    return entry;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } finally {
            keyStoreLock.readLock().unlock();
        }
    }

    @Override
    public synchronized Entry remove(NodeId certificateTypeId) throws Exception {
        if (!initialized.get()) {
            throw new IllegalStateException("not initialized");
        }

        try {
            keyStoreLock.writeLock().lock();

            String alias = getAlias(certificateTypeId);

            if (alias != null) {
                KeyStore.Entry entry = keyStore.getEntry(
                    alias,
                    new KeyStore.PasswordProtection(settings.getAliasPassword.apply(alias))
                );

                if (entry instanceof KeyStore.PrivateKeyEntry) {
                    KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) entry;
                    keyStore.deleteEntry(alias);
                    cache.remove(alias);

                    keyStore.store(
                        new FileOutputStream(settings.keyStorePath.toFile()),
                        settings.getKeyStorePassword.get()
                    );

                    return new Entry(
                        privateKeyEntry.getPrivateKey(),
                        (X509Certificate[]) privateKeyEntry.getCertificateChain()
                    );
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } finally {
            keyStoreLock.writeLock().unlock();
        }
    }

    @Override
    public void set(NodeId certificateTypeId, Entry entry) throws Exception {
        if (!initialized.get()) {
            throw new IllegalStateException("not initialized");
        }

        try {
            keyStoreLock.writeLock().lock();

            String alias = getAlias(certificateTypeId);

            keyStore.setKeyEntry(
                alias,
                entry.privateKey,
                settings.getAliasPassword.apply(alias),
                entry.certificateChain
            );

            keyStore.store(
                new FileOutputStream(settings.keyStorePath.toFile()),
                settings.getKeyStorePassword.get()
            );

            cache.put(alias, entry);
        } finally {
            keyStoreLock.writeLock().unlock();
        }
    }

    protected String getAlias(NodeId certificateTypeId) {
        if (certificateTypeId.equals(NodeIds.RsaSha256ApplicationCertificateType)) {
            return "server-rsa-sha256";
        } else {
            return certificateTypeId.toParseableString();
        }
    }

    /**
     * Create and {@link #initialize()} a new {@link KeyStoreCertificateStore} instance.
     *
     * @param settings the {@link KeyStoreSettings} to use.
     * @return an initialized {@link KeyStoreCertificateStore} instance.
     * @throws Exception if an error occurs while initializing the {@link KeyStoreCertificateStore}.
     */
    public static KeyStoreCertificateStore createAndInitialize(KeyStoreSettings settings) throws Exception {
        var store = new KeyStoreCertificateStore(settings);
        store.initialize();
        return store;
    }

    public static class KeyStoreSettings {
        public final Path keyStorePath;
        public final Supplier<char[]> getKeyStorePassword;
        public final Function<String, char[]> getAliasPassword;

        public KeyStoreSettings(
            Path keyStorePath,
            Supplier<char[]> getKeyStorePassword,
            Function<String, char[]> getAliasPassword
        ) {

            this.keyStorePath = keyStorePath;
            this.getKeyStorePassword = getKeyStorePassword;
            this.getAliasPassword = getAliasPassword;
        }
    }

}
