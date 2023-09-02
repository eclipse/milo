package org.eclipse.milo.opcua.stack.core.security;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.eclipse.milo.opcua.stack.core.security.KeyStoreKeyManager.KeyStoreSettings;
import org.junit.jupiter.api.AfterEach;

class KeyStoreKeyManagerTest extends KeyManagerTest {

    private final Path testPath = Files.createTempDirectory("KeyStoreKeyManagerTest");
    private final Path keyStorePath = testPath.resolve("testKeyStore.pfx");

    KeyStoreKeyManagerTest() throws IOException {}

    @AfterEach
    void deleteTestFiles() throws Exception {
        assert Files.deleteIfExists(keyStorePath);
        assert Files.deleteIfExists(testPath);
    }

    @Override
    protected KeyManager newKeyManager() throws Exception {
        return KeyStoreKeyManager.createAndInitialize(
            new KeyStoreSettings(keyStorePath, () -> "password")
        );
    }

}