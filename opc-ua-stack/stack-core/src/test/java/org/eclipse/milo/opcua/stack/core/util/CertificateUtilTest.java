/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.util;

import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.util.NoSuchElementException;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class CertificateUtilTest {

    private final KeyPair keyPair;
    private final X509Certificate certificate;

    public CertificateUtilTest() throws Exception {
        keyPair = SelfSignedCertificateGenerator.generateRsaKeyPair(2048);

        SelfSignedCertificateBuilder builder = new SelfSignedCertificateBuilder(keyPair)
            .setApplicationUri("urn:eclipse:milo:test")
            .addDnsName("localhost")
            .addIpAddress("127.0.0.1");

        certificate = builder.build();
    }

    @Test
    public void testGenerateCsr() throws Exception {
        assertNotNull(CertificateUtil.generateCsr(certificate, keyPair));
    }

    @Test
    public void testGenerateCsrPem() throws Exception {
        assertNotNull(CertificateUtil.generateCsrPem(certificate, keyPair));
    }

    @Test
    public void testGetSubjectAltNameField() {
        Object uri = CertificateUtil
            .getSubjectAltNameField(certificate, CertificateUtil.SUBJECT_ALT_NAME_URI)
            .orElseThrow(() -> new NoSuchElementException("uri"));

        Object dnsName = CertificateUtil
            .getSubjectAltNameField(certificate, CertificateUtil.SUBJECT_ALT_NAME_DNS_NAME)
            .orElseThrow(() -> new NoSuchElementException("dnsName"));

        Object ipAddress = CertificateUtil
            .getSubjectAltNameField(certificate, CertificateUtil.SUBJECT_ALT_NAME_IP_ADDRESS)
            .orElseThrow(() -> new NoSuchElementException("ipAddress"));

        assertEquals(uri, "urn:eclipse:milo:test");
        assertEquals(dnsName, "localhost");
        assertEquals(ipAddress, "127.0.0.1");
    }

}
