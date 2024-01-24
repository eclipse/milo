package org.eclipse.milo.opcua.stack.core.security;

import java.security.KeyPair;
import java.security.cert.X509Certificate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

abstract class CertificateQuarantineTest {

    protected final TestCertificateFactory certificateFactory = new TestCertificateFactory();

    protected CertificateQuarantine certificateQuarantine;

    @BeforeEach
    void setUpQuarantine() throws Exception {
        certificateQuarantine = newCertificateQuarantine(getMaxRejectedCertificates());
    }

    protected abstract CertificateQuarantine newCertificateQuarantine(int maxRejectedCertificates) throws Exception;

    protected abstract int getMaxRejectedCertificates();

    @Test
    void addRejectedCertificate() {
        KeyPair keyPair = certificateFactory.createRsaSha256KeyPair();
        X509Certificate[] certificateChain = certificateFactory.createRsaSha256CertificateChain(keyPair);
        X509Certificate certificate = certificateChain[0];

        certificateQuarantine.addRejectedCertificate(certificate);

        assertTrue(certificateQuarantine.getRejectedCertificates().contains(certificate));
    }

    @Test
    void removeRejectedCertificate() {
        KeyPair keyPair = certificateFactory.createRsaSha256KeyPair();
        X509Certificate[] certificateChain = certificateFactory.createRsaSha256CertificateChain(keyPair);
        X509Certificate certificate = certificateChain[0];

        certificateQuarantine.addRejectedCertificate(certificate);

        assertTrue(certificateQuarantine.getRejectedCertificates().contains(certificate));

        certificateQuarantine.removeRejectedCertificate(certificate);

        assertFalse(certificateQuarantine.getRejectedCertificates().contains(certificate));
        assertEquals(0, certificateQuarantine.getRejectedCertificates().size());
    }

    @Test
    void getRejectedCertificates() {
        for (int i = 0; i < 3; i++) {
            KeyPair keyPair = certificateFactory.createRsaSha256KeyPair();
            X509Certificate[] certificateChain = certificateFactory.createRsaSha256CertificateChain(keyPair);
            X509Certificate certificate = certificateChain[0];

            certificateQuarantine.addRejectedCertificate(certificate);
        }

        assertEquals(3, certificateQuarantine.getRejectedCertificates().size());
    }

    @Test
    void maxRejectedCertificates() {
        for (int i = 0; i < 3; i++) {
            KeyPair keyPair = certificateFactory.createRsaSha256KeyPair();
            X509Certificate[] certificateChain = certificateFactory.createRsaSha256CertificateChain(keyPair);
            X509Certificate certificate = certificateChain[0];

            certificateQuarantine.addRejectedCertificate(certificate);
        }

        assertEquals(3, certificateQuarantine.getRejectedCertificates().size());

        KeyPair keyPair = certificateFactory.createRsaSha256KeyPair();
        X509Certificate[] certificateChain = certificateFactory.createRsaSha256CertificateChain(keyPair);
        X509Certificate certificate = certificateChain[0];

        certificateQuarantine.addRejectedCertificate(certificate);

        assertEquals(3, certificateQuarantine.getRejectedCertificates().size());
    }

}
