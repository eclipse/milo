/*
 * Copyright (c) 2016 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.stack.core.util;

import java.security.cert.CertPathBuilder;
import java.security.cert.CertPathChecker;
import java.security.cert.CertStore;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.CertificateParsingException;
import java.security.cert.CollectionCertStoreParameters;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.PKIXCertPathBuilderResult;
import java.security.cert.PKIXRevocationChecker;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CRL;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CertificateValidationUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(CertificateValidationUtil.class);

    private static final String KEY_USAGE_OID = "2.5.29.15";

    private static final int SUBJECT_ALT_NAME_URI = 6;
    private static final int SUBJECT_ALT_NAME_DNS_NAME = 2;
    private static final int SUBJECT_ALT_NAME_IP_ADDRESS = 7;

    /**
     * Verify that a chain of trust can be established for a certificate or chain of certificates.
     * <p>
     * The chain must begin with the end-entity certificate at index 0 followed by the remaining certificates in the
     * chain, if any, in the correct order.
     * <p>
     * If the end-entity certificate is present in the {@code trustedCertificates} set then trust is immediately
     * verified. Otherwise, an attempt to build a path to a trusted anchor is made using the provided
     * {@code issuerCertificates} as the anchors.
     *
     * @param certificateChain    the certificate chain to verify.
     * @param trustedCertificates the set of known-trusted certificates.
     * @param issuerCertificates  the set of CA certificates to use as trust anchors.
     * @throws UaException if a chain of trust could not be established.
     */
    public static void verifyTrustChain(
        List<X509Certificate> certificateChain,
        Set<X509Certificate> trustedCertificates,
        Set<X509Certificate> issuerCertificates) throws UaException {

        verifyTrustChain(
            certificateChain,
            trustedCertificates,
            issuerCertificates,
            Collections.emptySet()
        );
    }

    /**
     * Verify that a chain of trust can be established for a certificate or chain of certificates.
     * <p>
     * The chain must begin with the end-entity certificate at index 0 followed by the remaining certificates in the
     * chain, if any, in the correct order.
     * <p>
     * If the end-entity certificate is present in the {@code trustedCertificates} set then trust is immediately
     * verified. Otherwise, an attempt to build a path to a trusted anchor is made using the provided
     * {@code issuerCertificates} as the anchors.
     *
     * @param certificateChain    the certificate chain to verify.
     * @param trustedCertificates the set of known-trusted certificates.
     * @param issuerCertificates  the set of CA certificates to use as trust anchors.
     * @param issuerCrls          the set of {@link X509CRL}s, if any, to use while verifying trust.
     * @throws UaException if a chain of trust could not be established.
     */
    public static void verifyTrustChain(
        List<X509Certificate> certificateChain,
        Set<X509Certificate> trustedCertificates,
        Set<X509Certificate> issuerCertificates,
        Set<X509CRL> issuerCrls) throws UaException {

        Preconditions.checkArgument(!certificateChain.isEmpty(), "certificateChain must not be empty");

        X509Certificate certificate = certificateChain.get(0);

        boolean certificateTrusted = trustedCertificates.stream()
            .anyMatch(c -> Arrays.equals(certificate.getSignature(), c.getSignature()));

        if (certificateTrusted) {
            LOGGER.debug("Found certificate in trusted certificates: {}", certificate);
            return;
        }

        try {
            Set<TrustAnchor> trustAnchors = new HashSet<>();
            issuerCertificates.forEach(ca -> trustAnchors.add(new TrustAnchor(ca, null)));

            X509CertSelector selector = new X509CertSelector();
            selector.setCertificate(certificate);

            PKIXBuilderParameters params = new PKIXBuilderParameters(trustAnchors, selector);

            // Add a CertStore containing any intermediate certs and CRLs
            if (certificateChain.size() > 0 || issuerCrls.size() > 0) {
                Collection<Object> collection = Lists.newArrayList();
                collection.addAll(certificateChain);
                collection.addAll(issuerCrls);

                CertStore certStore = CertStore.getInstance(
                    "Collection",
                    new CollectionCertStoreParameters(collection)
                );

                params.addCertStore(certStore);
            }

            // Only enable revocation checking if the CRL list is non-empty
            params.setRevocationEnabled(!issuerCrls.isEmpty());

            CertPathBuilder builder = CertPathBuilder.getInstance("PKIX");

            // Set up revocation options regardless of whether it's actually enabled
            CertPathChecker revocationChecker = builder.getRevocationChecker();

            if (revocationChecker instanceof PKIXRevocationChecker) {
                ((PKIXRevocationChecker) revocationChecker).setOptions(Sets.newHashSet(
                    PKIXRevocationChecker.Option.NO_FALLBACK,
                    PKIXRevocationChecker.Option.PREFER_CRLS,
                    PKIXRevocationChecker.Option.SOFT_FAIL
                ));
            }

            PKIXCertPathBuilderResult result = (PKIXCertPathBuilderResult) builder.build(params);

            LOGGER.debug("Validated certificate chain: {}", result.getCertPath());
        } catch (Throwable t) {
            LOGGER.debug("PKIX path validation failed: {}", t.getMessage());
            throw new UaException(StatusCodes.Bad_SecurityChecksFailed);
        }
    }

    public static void validateCertificateValidity(X509Certificate certificate) throws UaException {
        try {
            certificate.checkValidity();
        } catch (CertificateExpiredException e) {
            throw new UaException(StatusCodes.Bad_CertificateTimeInvalid,
                String.format("certificate is expired: %s - %s",
                    certificate.getNotBefore(), certificate.getNotAfter()));
        } catch (CertificateNotYetValidException e) {
            throw new UaException(StatusCodes.Bad_CertificateTimeInvalid,
                String.format("certificate not yet valid: %s - %s",
                    certificate.getNotBefore(), certificate.getNotAfter()));
        }
    }

    /**
     * Validate that one of {@code hostnames} matches a SubjectAltName DNSName or IPAddress entry in the certificate.
     *
     * @param certificate the certificate to validate against.
     * @param hostnames   the hostnames to look for.
     * @throws UaException if there is no matching DNSName or IPAddress entry.
     */
    public static void validateHostnameOrIpAddress(
        X509Certificate certificate, String... hostnames) throws UaException {

        boolean dnsNameMatch = Arrays.stream(hostnames).anyMatch(n -> {
            try {
                return validateSubjectAltNameField(
                    certificate, SUBJECT_ALT_NAME_DNS_NAME, n::equals);
            } catch (Throwable t) {
                return false;
            }
        });

        boolean ipAddressMatch = Arrays.stream(hostnames).anyMatch(n -> {
            try {
                return validateSubjectAltNameField(
                    certificate, SUBJECT_ALT_NAME_IP_ADDRESS, n::equals);
            } catch (Throwable t) {
                return false;
            }
        });

        if (!(dnsNameMatch || ipAddressMatch)) {
            throw new UaException(StatusCodes.Bad_CertificateHostNameInvalid);
        }
    }

    /**
     * Validate that the application URI matches the SubjectAltName URI in the given certificate.
     *
     * @param certificate    the certificate to validate against.
     * @param applicationUri the URI to validate.
     * @throws UaException if the certificate is invalid, does not contain a uri, or contains a uri that does not match.
     */
    public static void validateApplicationUri(X509Certificate certificate, String applicationUri) throws UaException {
        if (!validateSubjectAltNameField(certificate, SUBJECT_ALT_NAME_URI, applicationUri::equals)) {
            throw new UaException(StatusCodes.Bad_CertificateUriInvalid);
        }
    }

    public static void validateApplicationCertificateUsage(X509Certificate certificate) throws UaException {
        Set<String> criticalExtensions = certificate.getCriticalExtensionOIDs();
        if (criticalExtensions == null) criticalExtensions = new HashSet<>();

        if (criticalExtensions.contains(KEY_USAGE_OID)) {
            boolean[] keyUsage = certificate.getKeyUsage();
            boolean digitalSignature = keyUsage[0];
            boolean nonRepudiation = keyUsage[1];
            boolean keyEncipherment = keyUsage[2];
            boolean dataEncipherment = keyUsage[3];

            if (!digitalSignature) {
                throw new UaException(StatusCodes.Bad_CertificateUseNotAllowed,
                    "required KeyUsage 'digitalSignature' not found");
            }

            if (!nonRepudiation) {
                throw new UaException(StatusCodes.Bad_CertificateUseNotAllowed,
                    "required KeyUsage 'nonRepudiation' not found");
            }

            if (!keyEncipherment) {
                throw new UaException(StatusCodes.Bad_CertificateUseNotAllowed,
                    "required KeyUsage 'keyEncipherment' not found");
            }

            if (!dataEncipherment) {
                throw new UaException(StatusCodes.Bad_CertificateUseNotAllowed,
                    "required KeyUsage 'dataEncipherment' not found");
            }
        }
    }

    public static boolean validateSubjectAltNameField(X509Certificate certificate, int field,
                                                      Predicate<Object> fieldValidator) throws UaException {

        try {
            Collection<List<?>> subjectAltNames = certificate.getSubjectAlternativeNames();
            if (subjectAltNames == null) subjectAltNames = Collections.emptyList();

            for (List<?> idAndValue : subjectAltNames) {
                if (idAndValue != null && idAndValue.size() == 2) {
                    if (idAndValue.get(0).equals(field)) {
                        if (fieldValidator.test(idAndValue.get(1))) {
                            return true;
                        }
                    }
                }
            }

            return false;
        } catch (CertificateParsingException e) {
            throw new UaException(StatusCodes.Bad_CertificateInvalid, e);
        }
    }

    public static String getSubjectAltNameUri(X509Certificate certificate) throws UaException {
        try {
            Collection<List<?>> subjectAltNames = certificate.getSubjectAlternativeNames();
            if (subjectAltNames == null) subjectAltNames = Collections.emptyList();

            for (List<?> idAndValue : subjectAltNames) {
                if (idAndValue != null && idAndValue.size() == 2) {
                    if (idAndValue.get(0).equals(SUBJECT_ALT_NAME_URI)) {
                        Object uri = idAndValue.get(1);
                        return uri != null ? uri.toString() : null;
                    }
                }
            }

            return null;
        } catch (CertificateParsingException e) {
            throw new UaException(StatusCodes.Bad_CertificateInvalid, e);
        }
    }

}
