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
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
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
                    settings.password.get().toCharArray()
                );
            } else {
                keyStore.load(
                    null,
                    settings.password.get().toCharArray()
                );

                keyStore.store(
                    new FileOutputStream(keyStoreFile),
                    settings.password.get().toCharArray()
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
    public @Nullable KeyRecord get(String alias, String password) throws Exception {
        if (!initialized.get()) {
            throw new IllegalStateException("KeyStoreKeyManager not initialized");
        }

        try {
            keyStoreLock.readLock().lock();

            Key key = keyStore.getKey(alias, password.toCharArray());
            Certificate[] certificateChain = keyStore.getCertificateChain(alias);

            if (key instanceof PrivateKey && certificateChain instanceof X509Certificate[]) {
                return new KeyRecord((PrivateKey) key, (X509Certificate[]) certificateChain);
            } else {
                return null;
            }
        } finally {
            keyStoreLock.readLock().unlock();
        }
    }

    @Override
    public synchronized @Nullable KeyRecord remove(String alias, String password) throws Exception {
        if (!initialized.get()) {
            throw new IllegalStateException("KeyStoreKeyManager not initialized");
        }

        try {
            keyStoreLock.writeLock().lock();

            KeyStore.Entry entry = keyStore.getEntry(
                alias,
                new KeyStore.PasswordProtection(password.toCharArray())
            );

            if (entry instanceof KeyStore.PrivateKeyEntry) {
                KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) entry;
                keyStore.deleteEntry(alias);

                keyStore.store(
                    new FileOutputStream(settings.keyStorePath.toFile()),
                    settings.password.get().toCharArray()
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
    public void set(String alias, String password, KeyRecord keyRecord) throws Exception {
        if (!initialized.get()) {
            throw new IllegalStateException("KeyStoreKeyManager not initialized");
        }

        try {
            keyStoreLock.writeLock().lock();

            keyStore.setKeyEntry(
                alias,
                keyRecord.privateKey,
                password.toCharArray(),
                keyRecord.certificateChain
            );

            keyStore.store(
                new FileOutputStream(settings.keyStorePath.toFile()),
                settings.password.get().toCharArray()
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
        public final Supplier<String> password;

        public KeyStoreSettings(Path keyStorePath, Supplier<String> password) {
            this.keyStorePath = keyStorePath;
            this.password = password;
        }
    }

}
