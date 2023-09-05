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
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Function;
import java.util.function.Supplier;

import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KeyStoreKeyManager implements KeyManager {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final AtomicBoolean initialized = new AtomicBoolean(false);

    private final ReadWriteLock keyStoreLock = new ReentrantReadWriteLock(true);

    private KeyStore keyStore;

    private final KeyStoreSettings settings;

    public KeyStoreKeyManager(KeyStoreSettings settings) {
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
    public boolean contains(String alias) throws Exception {
        if (!initialized.get()) {
            throw new IllegalStateException("KeyStoreKeyManager not initialized");
        }
        try {
            keyStoreLock.readLock().lock();

            return keyStore.containsAlias(alias);
        } finally {
            keyStoreLock.readLock().unlock();
        }
    }

    @Override
    public @Nullable KeyRecord get(String alias) throws Exception {
        if (!initialized.get()) {
            throw new IllegalStateException("KeyStoreKeyManager not initialized");
        }

        try {
            keyStoreLock.readLock().lock();

            Key key = keyStore.getKey(alias, settings.getAliasPassword.apply(alias));
            Certificate[] certificateChain = keyStore.getCertificateChain(alias);

            if (key instanceof PrivateKey && certificateChain != null) {
                X509Certificate[] x509CertificateChain = Arrays.stream(certificateChain)
                    .map(c -> (X509Certificate) c)
                    .toArray(X509Certificate[]::new);

                return new KeyRecord((PrivateKey) key, x509CertificateChain);
            } else {
                return null;
            }
        } finally {
            keyStoreLock.readLock().unlock();
        }
    }

    @Override
    public synchronized @Nullable KeyRecord remove(String alias) throws Exception {
        if (!initialized.get()) {
            throw new IllegalStateException("KeyStoreKeyManager not initialized");
        }

        try {
            keyStoreLock.writeLock().lock();

            KeyStore.Entry entry = keyStore.getEntry(
                alias,
                new KeyStore.PasswordProtection(settings.getAliasPassword.apply(alias))
            );

            if (entry instanceof KeyStore.PrivateKeyEntry) {
                KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) entry;
                keyStore.deleteEntry(alias);

                keyStore.store(
                    new FileOutputStream(settings.keyStorePath.toFile()),
                    settings.getKeyStorePassword.get()
                );

                return new KeyRecord(
                    privateKeyEntry.getPrivateKey(),
                    (X509Certificate[]) privateKeyEntry.getCertificateChain()
                );
            } else {
                return null;
            }
        } finally {
            keyStoreLock.writeLock().unlock();
        }
    }

    @Override
    public void set(String alias, KeyRecord keyRecord) throws Exception {
        if (!initialized.get()) {
            throw new IllegalStateException("KeyStoreKeyManager not initialized");
        }

        try {
            keyStoreLock.writeLock().lock();

            keyStore.setKeyEntry(
                alias,
                keyRecord.privateKey,
                settings.getAliasPassword.apply(alias),
                keyRecord.certificateChain
            );

            keyStore.store(
                new FileOutputStream(settings.keyStorePath.toFile()),
                settings.getKeyStorePassword.get()
            );
        } finally {
            keyStoreLock.writeLock().unlock();
        }
    }

    /**
     * Create and {@link #initialize()} a new {@link KeyStoreKeyManager} instance.
     *
     * @param settings the {@link KeyStoreSettings} to use.
     * @return an initialized {@link KeyStoreKeyManager} instance.
     * @throws Exception if an error occurs while initializing the {@link KeyStoreKeyManager}.
     */
    public static KeyStoreKeyManager createAndInitialize(KeyStoreSettings settings) throws Exception {
        var keyManager = new KeyStoreKeyManager(settings);
        keyManager.initialize();
        return keyManager;
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
