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
        KeyPair keyPair = factory.createRsaSha256KeyPair();
        X509Certificate[] certificateChain = factory.createRsaSha256CertificateChain(keyPair);

        keyManager = newKeyManager();

        keyManager.set(
            "test",
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
        assertNotNull(keyManager.get("test"));
        assertNull(keyManager.get("foo"));
    }

    @Test
    void remove() throws Exception {
        assertNotNull(keyManager.remove("test"));
        assertNull(keyManager.remove("test"));
        assertFalse(keyManager.contains("test"));
    }

    @Test
    void set() throws Exception {
        var factory = new TestCertificateFactory();
        KeyPair keyPair = factory.createRsaSha256KeyPair();
        X509Certificate[] certificateChain = factory.createRsaSha256CertificateChain(keyPair);

        keyManager.set(
            "test2",
            new KeyManager.KeyRecord(keyPair.getPrivate(), certificateChain)
        );

        assertTrue(keyManager.contains("test2"));
    }

}
