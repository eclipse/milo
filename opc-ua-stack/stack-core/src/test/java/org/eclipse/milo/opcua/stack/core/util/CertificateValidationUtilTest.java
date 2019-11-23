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

import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.List;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.CRLReason;
import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.cert.X509v2CRLBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CRLConverter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static java.util.Collections.emptySet;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.expectThrows;

public class CertificateValidationUtilTest {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private static final String ALIAS_CA_INTERMEDIATE = "ca-intermediate";
    private static final String ALIAS_CA_ROOT = "ca-root";
    private static final String ALIAS_LEAF_INTERMEDIATE_SIGNED = "leaf-intermediate-signed";
    private static final String ALIAS_LEAF_SELF_SIGNED = "leaf-self-signed";

    private KeyStore keyStore;
    private X509Certificate caIntermediate;
    private X509Certificate caRoot;
    private X509Certificate leafSelfSigned;
    private X509Certificate leafIntermediateSigned;

    @BeforeSuite
    public void loadKeyStore() throws Exception {
        keyStore = KeyStore.getInstance("PKCS12");

        InputStream inputStream = getClass()
            .getResourceAsStream("validation-certs.pfx");

        assertNotNull(inputStream);

        keyStore.load(inputStream, "password".toCharArray());

        caIntermediate = getCertificate(ALIAS_CA_INTERMEDIATE);
        caRoot = getCertificate(ALIAS_CA_ROOT);
        leafSelfSigned = getCertificate(ALIAS_LEAF_SELF_SIGNED);
        leafIntermediateSigned = getCertificate(ALIAS_LEAF_INTERMEDIATE_SIGNED);
    }

    @Test
    public void testVerifyTrustChain_LeafSelfSigned() throws Exception {
        List<X509Certificate> certificateChain = newArrayList(leafSelfSigned);

        CertificateValidationUtil.verifyTrustChain(
            certificateChain,
            newHashSet(leafSelfSigned),
            emptySet()
        );
    }

    @Test
    public void testVerifyTrustChain_LeafSelfSigned_NotTrusted() throws Exception {
        List<X509Certificate> certificateChain = newArrayList(leafSelfSigned);

        expectThrows(
            UaException.class,
            () -> CertificateValidationUtil.verifyTrustChain(
                certificateChain,
                emptySet(),
                emptySet()
            )
        );
    }

    @Test
    public void testVerifyTrustChain_LeafIntermediateSigned() throws Exception {
        // chain: leaf
        // trusted: ca-intermedate
        // issuers: ca-root
        {
            List<X509Certificate> certificateChain = newArrayList(leafIntermediateSigned);

            CertificateValidationUtil.verifyTrustChain(
                certificateChain,
                newHashSet(caIntermediate),
                newHashSet(caRoot)
            );
        }

        // chain: leaf, ca-intermediate
        // trusted: ca-intermediate
        // issuers: ca-root
        {
            List<X509Certificate> certificateChain = newArrayList(leafIntermediateSigned, caIntermediate);

            CertificateValidationUtil.verifyTrustChain(
                certificateChain,
                newHashSet(caIntermediate),
                newHashSet(caRoot)
            );
        }

        // chain: leaf, ca-intermediate
        // trusted: ca-root
        {
            List<X509Certificate> certificateChain = newArrayList(leafIntermediateSigned, caIntermediate);

            CertificateValidationUtil.verifyTrustChain(
                certificateChain,
                newHashSet(caRoot),
                emptySet()
            );
        }

        // chain: leaf, ca-intermediate, ca-root
        // trusted: ca-intermediate
        // issuers: ca-root
        {
            List<X509Certificate> certificateChain = newArrayList(leafIntermediateSigned, caIntermediate, caRoot);

            CertificateValidationUtil.verifyTrustChain(
                certificateChain,
                newHashSet(caIntermediate),
                newHashSet(caRoot)
            );
        }

        // chain: leaf, ca-intermediate, ca-root
        // trusted: ca-root
        {
            List<X509Certificate> certificateChain = newArrayList(leafIntermediateSigned, caIntermediate, caRoot);

            CertificateValidationUtil.verifyTrustChain(
                certificateChain,
                newHashSet(caRoot),
                emptySet()
            );
        }

        // chain: leaf, ca-intermediate, ca-root
        // trusted: ca-intermediate, ca-root
        {
            List<X509Certificate> certificateChain = newArrayList(leafIntermediateSigned, caIntermediate, caRoot);

            CertificateValidationUtil.verifyTrustChain(
                certificateChain,
                newHashSet(caIntermediate, caRoot),
                emptySet()
            );
        }
    }

    @Test
    public void testVerifyTrustChain_LeafIntermediateSigned_Revoked() throws Exception {
        // chain: leaf
        // trusted: ca-intermediate
        // issuers: ca--root
        // crls: ca-intermediate revokes leaf
        {
            List<X509Certificate> certificateChain = newArrayList(leafIntermediateSigned);

            expectThrows(UaException.class, () ->
                CertificateValidationUtil.verifyTrustChain(
                    certificateChain,
                    newHashSet(caIntermediate),
                    newHashSet(
                        generateCrl(
                            caIntermediate,
                            (PrivateKey) keyStore.getKey(
                                ALIAS_CA_INTERMEDIATE, "password".toCharArray()),
                            leafIntermediateSigned)
                    ),
                    newHashSet(caRoot),
                    emptySet()
                )
            );
        }

        // chain: leaf
        // trusted: ca-intermediate
        // issuers: ca-root
        // crls: ca-root revokes ca-intermediate
        {
            List<X509Certificate> certificateChain = newArrayList(leafIntermediateSigned);

            expectThrows(UaException.class, () ->
                CertificateValidationUtil.verifyTrustChain(
                    certificateChain,
                    newHashSet(caIntermediate),
                    newHashSet(
                        generateCrl(
                            caRoot,
                            (PrivateKey) keyStore.getKey(
                                ALIAS_CA_ROOT, "password".toCharArray()),
                            caIntermediate)
                    ),
                    newHashSet(caRoot),
                    emptySet()
                )
            );
        }

        // chain: leaf
        // trusted: ca-intermediate
        // issuers: ca-root
        // crls: ca-root revokes leaf
        {
            List<X509Certificate> certificateChain = newArrayList(leafIntermediateSigned);

            expectThrows(UaException.class, () ->
                CertificateValidationUtil.verifyTrustChain(
                    certificateChain,
                    newHashSet(caIntermediate),
                    newHashSet(
                        generateCrl(
                            caRoot,
                            (PrivateKey) keyStore.getKey(
                                ALIAS_CA_ROOT, "password".toCharArray()),
                            leafIntermediateSigned)
                    ),
                    newHashSet(caRoot),
                    emptySet()
                )
            );
        }
    }

    @Test
    public void testVerifyTrustChain_NoTrusted_NoIssuers() throws Exception {
        expectThrows(UaException.class, () ->
            CertificateValidationUtil.verifyTrustChain(
                newArrayList(leafSelfSigned),
                emptySet(),
                emptySet(),
                emptySet(),
                emptySet()
            )
        );

        expectThrows(UaException.class, () ->
            CertificateValidationUtil.verifyTrustChain(
                newArrayList(leafIntermediateSigned),
                emptySet(),
                emptySet(),
                emptySet(),
                emptySet()
            )
        );

        expectThrows(UaException.class, () ->
            CertificateValidationUtil.verifyTrustChain(
                newArrayList(leafIntermediateSigned, caIntermediate),
                emptySet(),
                emptySet(),
                emptySet(),
                emptySet()
            )
        );

        expectThrows(UaException.class, () ->
            CertificateValidationUtil.verifyTrustChain(
                newArrayList(leafIntermediateSigned, caIntermediate, caRoot),
                emptySet(),
                emptySet(),
                emptySet(),
                emptySet()
            )
        );
    }

    @Test
    public void testVerifyTrustChain_IntermediateIssuer() throws Exception {
        // chain: leaf
        // trusted: ca-root
        // issuers: ca-intermediate
        {
            List<X509Certificate> certificateChain = newArrayList(leafIntermediateSigned);

            CertificateValidationUtil.verifyTrustChain(
                certificateChain,
                newHashSet(caRoot),
                emptySet(),
                newHashSet(caIntermediate),
                emptySet()
            );
        }

        // chain: leaf, ca-intermediate
        // trusted: ca-root
        // issuers: ca-intermediate
        {
            List<X509Certificate> certificateChain = newArrayList(leafIntermediateSigned, caIntermediate);

            CertificateValidationUtil.verifyTrustChain(
                certificateChain,
                newHashSet(caRoot),
                emptySet(),
                newHashSet(caIntermediate),
                emptySet()
            );
        }

        // chain: leaf, ca-intermediate, ca-root
        // trusted: ca-root
        // issuers: ca-intermediate
        {
            List<X509Certificate> certificateChain = newArrayList(leafIntermediateSigned, caIntermediate, caRoot);

            CertificateValidationUtil.verifyTrustChain(
                certificateChain,
                newHashSet(caRoot),
                emptySet(),
                newHashSet(caIntermediate),
                emptySet()
            );
        }
    }

    @Test
    public void testVerifyTrustChain_IssuerRevoked() {
        // chain: leaf
        // trusted: ca-root
        // issuers: ca-intermediate
        // crls: ca-root revokes ca-intermediate
        {
            List<X509Certificate> certificateChain = newArrayList(leafIntermediateSigned);

            expectThrows(UaException.class, () ->
                CertificateValidationUtil.verifyTrustChain(
                    certificateChain,
                    newHashSet(caRoot),
                    newHashSet(
                        generateCrl(
                            caRoot,
                            (PrivateKey) keyStore.getKey(
                                ALIAS_CA_ROOT, "password".toCharArray()),
                            caIntermediate)
                    ),
                    newHashSet(caIntermediate),
                    emptySet()
                )
            );
        }
    }

    @Test
    public void testCertificateIsCa() throws KeyStoreException {
        assertTrue(CertificateValidationUtil.certificateIsCa(getCertificate("yes-key-usage-yes-ca")));
        assertTrue(CertificateValidationUtil.certificateIsCa(getCertificate("no-key-usage-yes-ca")));
        assertTrue(CertificateValidationUtil.certificateIsCa(getCertificate("yes-key-usage-no-ca")));
        assertFalse(CertificateValidationUtil.certificateIsCa(getCertificate("no-key-usage-no-ca")));
    }

    private X509CRL generateCrl(X509Certificate ca, PrivateKey caPrivateKey, X509Certificate... revoked) throws Exception {
        X509v2CRLBuilder builder = new X509v2CRLBuilder(
            new X500Name(ca.getSubjectDN().getName()),
            new Date()
        );

        for (X509Certificate certificate : revoked) {
            builder.addCRLEntry(certificate.getSerialNumber(), new Date(), CRLReason.privilegeWithdrawn);
        }

        JcaContentSignerBuilder contentSignerBuilder =
            new JcaContentSignerBuilder("SHA256WithRSAEncryption");

        contentSignerBuilder.setProvider("BC");

        X509CRLHolder crlHolder = builder.build(contentSignerBuilder.build(caPrivateKey));

        JcaX509CRLConverter converter = new JcaX509CRLConverter();

        converter.setProvider("BC");

        return converter.getCRL(crlHolder);
    }

    private X509Certificate getCertificate(String alias) throws KeyStoreException {
        X509Certificate certificate = (X509Certificate) keyStore.getCertificate(alias);
        assertNotNull(certificate);
        return certificate;
    }

}
