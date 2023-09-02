package org.eclipse.milo.opcua.stack.core.security;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import org.eclipse.milo.opcua.stack.core.NodeIds;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.util.SelfSignedCertificateBuilder;
import org.eclipse.milo.opcua.stack.core.util.SelfSignedCertificateGenerator;
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
        var factory = new KeyManagerTest.TestCertificateFactory();
        KeyPair keyPair = factory.createRsaKeyPair();
        X509Certificate[] certificateChain = factory.createRsaCertificateChain(keyPair);

        keyManager.set(
            "test2",
            "password",
            new KeyManager.KeyRecord(keyPair.getPrivate(), certificateChain)
        );

        assertTrue(keyManager.contains("test2"));
    }

    public static class TestCertificateFactory implements CertificateManager.CertificateFactory {
        @Override
        public KeyPair createKeyPair(NodeId nodeId) {
            assert nodeId.equals(NodeIds.RsaSha256ApplicationCertificateType);

            return createRsaKeyPair();
        }

        @Override
        public X509Certificate[] createCertificateChain(NodeId nodeId, KeyPair keyPair) {
            assert nodeId.equals(NodeIds.RsaSha256ApplicationCertificateType);

            return createRsaCertificateChain(keyPair);
        }

        public KeyPair createRsaKeyPair() {
            try {
                return SelfSignedCertificateGenerator.generateRsaKeyPair(2048);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }

        public X509Certificate[] createRsaCertificateChain(KeyPair keyPair) {
            String applicationUri = "urn:eclipse:milo:test";

            SelfSignedCertificateBuilder builder = new SelfSignedCertificateBuilder(keyPair)
                .setCommonName("Eclipse Milo OPC UA Test Server")
                .setOrganization("digitalpetri")
                .setOrganizationalUnit("dev")
                .setLocalityName("Folsom")
                .setStateName("CA")
                .setCountryCode("US")
                .setApplicationUri(applicationUri)
                .addIpAddress("127.0.0.1")
                .addDnsName("localhost");

            try {
                return new X509Certificate[]{builder.build()};
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
