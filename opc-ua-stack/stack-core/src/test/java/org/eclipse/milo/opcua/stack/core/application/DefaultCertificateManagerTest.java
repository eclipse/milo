package org.eclipse.milo.opcua.stack.core.application;

import java.security.KeyPair;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

import org.testng.annotations.Test;

import static org.testng.Assert.expectThrows;

public class DefaultCertificateManagerTest {

    @Test
    public void testNullPrivateKeyOrCertificateFails() {
        expectThrows(
            Exception.class,
            () -> new DefaultCertificateManager((KeyPair) null, (X509Certificate) null)
        );

        expectThrows(
            Exception.class,
            () -> new DefaultCertificateManager().add(null, (X509Certificate) null)
        );
    }

}