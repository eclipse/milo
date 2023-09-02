package org.eclipse.milo.opcua.stack.core.security;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.eclipse.milo.opcua.stack.core.security.KeyStoreKeyManager.KeyStoreSettings;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KeyStoreKeyManagerTest {

    private final Path testPath = Files.createTempDirectory("KeyStoreKeyManagerTest");
    private final Path keyStorePath = testPath.resolve("testKeyStore.pfx");

    KeyStoreKeyManagerTest() throws IOException {}

    @AfterEach
    void deleteTestFiles() throws Exception {
        assert Files.deleteIfExists(keyStorePath);
        assert Files.deleteIfExists(testPath);
    }

    @Test
    void createAndInitialize() throws Exception {
        Path keyStorePath = testPath.resolve("testKeyStore.pfx");

        KeyStoreKeyManager.createAndInitialize(
            new KeyStoreSettings(keyStorePath, () -> "password")
        );

        assertTrue(keyStorePath.toFile().exists());
    }

    @Test
    void contains() throws Exception {
        Path keyStorePath = testPath.resolve("testKeyStore.pfx");

        var keyManager = KeyStoreKeyManager.createAndInitialize(
            new KeyStoreSettings(keyStorePath, () -> "password")
        );

        assertFalse(keyManager.contains("foo"));
    }

    @Test
    void get() throws Exception {
        Path keyStorePath = testPath.resolve("testKeyStore.pfx");

        var keyManager = KeyStoreKeyManager.createAndInitialize(
            new KeyStoreSettings(keyStorePath, () -> "password")
        );

        //TODO keyManager.get()
    }

    @Test
    void remove() throws Exception {
        Path keyStorePath = testPath.resolve("testKeyStore.pfx");

        var keyManager = KeyStoreKeyManager.createAndInitialize(
            new KeyStoreSettings(keyStorePath, () -> "password")
        );

        //TODO keyManager.remove()
    }

    @Test
    void set() throws Exception {
        Path keyStorePath = testPath.resolve("testKeyStore.pfx");

        var keyManager = KeyStoreKeyManager.createAndInitialize(
            new KeyStoreSettings(keyStorePath, () -> "password")
        );

        //TODO keyManager.set()
    }

}