/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.util.validation;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static java.util.Collections.emptySet;
import static org.eclipse.milo.opcua.stack.core.util.validation.CertificateValidationUtil.buildTrustedCertPath;
import static org.eclipse.milo.opcua.stack.core.util.validation.CertificateValidationUtil.validateTrustedCertPath;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.PKIXCertPathBuilderResult;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.CRLReason;
import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.cert.X509v2CRLBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CRLConverter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CertificateValidationUtilTest {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private static final String ALIAS_CA_INTERMEDIATE = "ca-intermediate";
    private static final String ALIAS_CA_ROOT = "ca-root";
    private static final String ALIAS_LEAF_INTERMEDIATE_SIGNED = "leaf-intermediate-signed";
    private static final String ALIAS_LEAF_SELF_SIGNED = "leaf-self-signed";
    private static final String ALIAS_URI_WITH_SPACES = "uri-with-spaces";

    private KeyStore keyStore;
    private X509Certificate caIntermediate;
    private X509Certificate caRoot;
    private X509Certificate leafSelfSigned;
    private X509Certificate leafIntermediateSigned;
    private X509Certificate uriWithSpaces;

    @BeforeAll
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
        uriWithSpaces = getCertificate(ALIAS_URI_WITH_SPACES);
    }

    @Test
    public void testBuildTrustedCertPath_LeafSelfSigned() throws Exception {
        List<X509Certificate> certificateChain = newArrayList(leafSelfSigned);

        buildTrustedCertPath(
            certificateChain,
            newHashSet(leafSelfSigned),
            emptySet()
        );
    }

    @Test
    public void testBuildTrustedCertPath_LeafSelfSigned_NotTrusted() {
        List<X509Certificate> certificateChain = newArrayList(leafSelfSigned);

        assertThrows(
            UaException.class,
            () -> buildTrustedCertPath(
                certificateChain,
                emptySet(),
                emptySet()
            )
        );
    }

    @Test
    public void testBuildTrustedCertPath_LeafIntermediateSigned() throws Exception {
        // chain: leaf
        // trusted: ca-intermedate
        // issuers: ca-root
        {
            List<X509Certificate> certificateChain = newArrayList(leafIntermediateSigned);

            buildTrustedCertPath(
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

            buildTrustedCertPath(
                certificateChain,
                newHashSet(caIntermediate),
                newHashSet(caRoot)
            );
        }

        // chain: leaf, ca-intermediate
        // trusted: ca-root
        {
            List<X509Certificate> certificateChain = newArrayList(leafIntermediateSigned, caIntermediate);

            buildTrustedCertPath(
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

            buildTrustedCertPath(
                certificateChain,
                newHashSet(caIntermediate),
                newHashSet(caRoot)
            );
        }

        // chain: leaf, ca-intermediate, ca-root
        // trusted: ca-root
        {
            List<X509Certificate> certificateChain = newArrayList(leafIntermediateSigned, caIntermediate, caRoot);

            buildTrustedCertPath(
                certificateChain,
                newHashSet(caRoot),
                emptySet()
            );
        }

        // chain: leaf, ca-intermediate, ca-root
        // trusted: ca-intermediate, ca-root
        {
            List<X509Certificate> certificateChain = newArrayList(leafIntermediateSigned, caIntermediate, caRoot);

            buildTrustedCertPath(
                certificateChain,
                newHashSet(caIntermediate, caRoot),
                emptySet()
            );
        }
    }

    @Test
    public void testBuildAndValidate_LeafIntermediateSigned_Revoked() {
        // chain: leaf
        // trusted: ca-intermediate
        // issuers: ca-root
        // crls: ca-intermediate revokes leaf
        {
            List<X509Certificate> certificateChain = newArrayList(leafIntermediateSigned);

            UaException e = assertThrows(UaException.class, () -> {
                HashSet<X509CRL> x509CRLS = newHashSet(
                    generateCrl(
                        caIntermediate,
                        (PrivateKey) keyStore.getKey(
                            ALIAS_CA_INTERMEDIATE, "password".toCharArray()),
                        leafIntermediateSigned
                    )
                );

                PKIXCertPathBuilderResult pathBuilderResult = buildTrustedCertPath(
                    certificateChain,
                    newHashSet(caIntermediate),
                    newHashSet(caRoot)
                );

                validateTrustedCertPath(
                    pathBuilderResult.getCertPath(),
                    pathBuilderResult.getTrustAnchor(),
                    x509CRLS,
                    EnumSet.of(ValidationCheck.REVOCATION),
                    true
                );

                validateTrustedCertPath(
                    pathBuilderResult.getCertPath(),
                    pathBuilderResult.getTrustAnchor(),
                    x509CRLS,
                    EnumSet.of(ValidationCheck.REVOCATION),
                    false
                );
            });

            assertEquals(new StatusCode(StatusCodes.Bad_CertificateRevoked), e.getStatusCode());
        }

        // chain: leaf
        // trusted: ca-intermediate
        // issuers: ca-root
        // crls: ca-root revokes ca-intermediate
        {
            List<X509Certificate> certificateChain = newArrayList(leafIntermediateSigned);

            UaException e = assertThrows(UaException.class, () -> {
                HashSet<X509CRL> x509CRLS = newHashSet(
                    generateCrl(
                        caRoot,
                        (PrivateKey) keyStore.getKey(
                            ALIAS_CA_ROOT, "password".toCharArray()),
                        caIntermediate)
                );

                PKIXCertPathBuilderResult pathBuilderResult = buildTrustedCertPath(
                    certificateChain,
                    newHashSet(caIntermediate),
                    newHashSet(caRoot)
                );

                validateTrustedCertPath(
                    pathBuilderResult.getCertPath(),
                    pathBuilderResult.getTrustAnchor(),
                    x509CRLS,
                    EnumSet.of(ValidationCheck.REVOCATION),
                    true
                );

                validateTrustedCertPath(
                    pathBuilderResult.getCertPath(),
                    pathBuilderResult.getTrustAnchor(),
                    x509CRLS,
                    EnumSet.of(ValidationCheck.REVOCATION),
                    false
                );
            });

            assertEquals(new StatusCode(StatusCodes.Bad_CertificateIssuerRevoked), e.getStatusCode());
        }
    }

    @Test
    public void testBuildTrustedCertPath_NoTrusted_NoIssuers() {
    	assertThrows(UaException.class, () ->
            buildTrustedCertPath(
                newArrayList(leafSelfSigned),
                emptySet(),
                emptySet()
            )
        );

    	assertThrows(UaException.class, () ->
            buildTrustedCertPath(
                newArrayList(leafIntermediateSigned),
                emptySet(),
                emptySet()
            )
        );

    	assertThrows(UaException.class, () ->
            buildTrustedCertPath(
                newArrayList(leafIntermediateSigned, caIntermediate),
                emptySet(),
                emptySet()
            )
        );

    	assertThrows(UaException.class, () ->
            buildTrustedCertPath(
                newArrayList(leafIntermediateSigned, caIntermediate, caRoot),
                emptySet(),
                emptySet()
            )
        );
    }

    @Test
    public void testBuildTrustedCertPath_IntermediateIssuer() throws Exception {
        // chain: leaf
        // trusted: ca-root
        // issuers: ca-intermediate
        {
            List<X509Certificate> certificateChain = newArrayList(leafIntermediateSigned);

            buildTrustedCertPath(
                certificateChain,
                newHashSet(caRoot),
                newHashSet(caIntermediate)
            );
        }

        // chain: leaf, ca-intermediate
        // trusted: ca-root
        // issuers: ca-intermediate
        {
            List<X509Certificate> certificateChain = newArrayList(leafIntermediateSigned, caIntermediate);

            buildTrustedCertPath(
                certificateChain,
                newHashSet(caRoot),
                newHashSet(caIntermediate)
            );
        }

        // chain: leaf, ca-intermediate, ca-root
        // trusted: ca-root
        // issuers: ca-intermediate
        {
            List<X509Certificate> certificateChain = newArrayList(leafIntermediateSigned, caIntermediate, caRoot);

            buildTrustedCertPath(
                certificateChain,
                newHashSet(caRoot),
                newHashSet(caIntermediate)
            );
        }
    }

    @Test
    public void testBuildAndValidate_IssuerRevoked() {
        // chain: leaf
        // trusted: ca-root
        // issuers: ca-intermediate
        // crls: ca-root revokes ca-intermediate
        {
            List<X509Certificate> certificateChain = newArrayList(leafIntermediateSigned);

            UaException e = assertThrows(UaException.class, () -> {
                HashSet<X509CRL> x509CRLS = newHashSet(
                    generateCrl(
                        caRoot,
                        (PrivateKey) keyStore.getKey(
                            ALIAS_CA_ROOT, "password".toCharArray()),
                        caIntermediate)
                );

                PKIXCertPathBuilderResult pathBuilderResult = buildTrustedCertPath(
                    certificateChain,
                    newHashSet(caRoot),
                    newHashSet(caIntermediate)
                );

                CertificateValidationUtil.validateTrustedCertPath(
                    pathBuilderResult.getCertPath(),
                    pathBuilderResult.getTrustAnchor(),
                    x509CRLS,
                    EnumSet.of(ValidationCheck.REVOCATION),
                    true
                );

                CertificateValidationUtil.validateTrustedCertPath(
                    pathBuilderResult.getCertPath(),
                    pathBuilderResult.getTrustAnchor(),
                    x509CRLS,
                    EnumSet.of(ValidationCheck.REVOCATION),
                    false
                );
            });

            assertEquals(new StatusCode(StatusCodes.Bad_CertificateIssuerRevoked), e.getStatusCode());
        }
    }

    @Test
    public void testCertificateIsCa() throws KeyStoreException {
        assertTrue(CertificateValidationUtil.certificateIsCa(getCertificate("yes-key-usage-yes-ca")));
        assertTrue(CertificateValidationUtil.certificateIsCa(getCertificate("no-key-usage-yes-ca")));
        assertTrue(CertificateValidationUtil.certificateIsCa(getCertificate("yes-key-usage-no-ca")));
        assertFalse(CertificateValidationUtil.certificateIsCa(getCertificate("no-key-usage-no-ca")));
    }

    @Test
    public void testUriWithSpaces() throws Exception {
        CertificateValidationUtil.checkApplicationUri(
            uriWithSpaces,
            "this URI has spaces"
        );
    }

    private X509CRL generateCrl(X509Certificate ca, PrivateKey caPrivateKey, X509Certificate... revoked) throws Exception {
        X509v2CRLBuilder builder = new X509v2CRLBuilder(
            new X500Name(ca.getSubjectDN().getName()),
            new Date()
        );

        builder.setNextUpdate(new Date(System.currentTimeMillis() + 60_000));

        for (X509Certificate certificate : revoked) {
            builder.addCRLEntry(
                certificate.getSerialNumber(),
                new Date(System.currentTimeMillis() - 60_000),
                CRLReason.privilegeWithdrawn
            );
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
