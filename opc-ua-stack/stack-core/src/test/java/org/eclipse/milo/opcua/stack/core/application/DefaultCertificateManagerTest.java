package org.eclipse.milo.opcua.stack.core.application;

import java.security.KeyPair;

import org.testng.annotations.Test;

import static org.testng.Assert.expectThrows;

public class DefaultCertificateManagerTest {

    @Test
    public void testNullPrivateKeyOrCertificateFails() {
        expectThrows(
            Exception.class,
            () -> new DefaultCertificateManager((KeyPair) null, null)
        );
    }

}