package org.eclipse.milo.opcua.stack.core.security;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import org.eclipse.milo.opcua.stack.core.NodeIds;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.util.SelfSignedCertificateBuilder;
import org.eclipse.milo.opcua.stack.core.util.SelfSignedCertificateGenerator;

public class TestCertificateFactory implements CertificateManager.CertificateFactory {

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
