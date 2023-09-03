package org.eclipse.milo.opcua.stack.core.security;

import java.security.KeyPair;
import java.security.cert.X509Certificate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

abstract class KeyManagerTest {

    protected KeyManager keyManager;

    @BeforeEach
    void setUpKeyManager() throws Exception {
        var factory = new TestCertificateFactory();
        KeyPair keyPair = factory.createRsaKeyPair();
        X509Certificate[] certificateChain = factory.createRsaCertificateChain(keyPair);

        keyManager = newKeyManager();

        keyManager.set(
            "test",
            "password",
            new KeyManager.KeyRecord(keyPair.getPrivate(), certificateChain)
        );
    }

    protected abstract KeyManager newKeyManager() throws Exception;

    @Test
    void contains() throws Exception {
        assertTrue(keyManager.contains("test"));
        assertFalse(keyManager.contains("foo"));
    }

    @Test
    void get() throws Exception {
        assertNotNull(keyManager.get("test", "password"));
        assertNull(keyManager.get("foo", "password"));
    }

    @Test
    void remove() throws Exception {
        assertNotNull(keyManager.remove("test", "password"));
        assertNull(keyManager.remove("test", "password"));
        assertFalse(keyManager.contains("test"));
    }

    @Test
    void set() throws Exception {
        var factory = new TestCertificateFactory();
        KeyPair keyPair = factory.createRsaKeyPair();
        X509Certificate[] certificateChain = factory.createRsaCertificateChain(keyPair);

        keyManager.set(
            "test2",
            "password",
            new KeyManager.KeyRecord(keyPair.getPrivate(), certificateChain)
        );

        assertTrue(keyManager.contains("test2"));
    }

}
