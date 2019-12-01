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

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.CertPath;
import java.security.cert.CertPathBuilder;
import java.security.cert.CertPathChecker;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.CertificateParsingException;
import java.security.cert.CollectionCertStoreParameters;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.PKIXCertPathBuilderResult;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.PKIXParameters;
import java.security.cert.PKIXReason;
import java.security.cert.PKIXRevocationChecker;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CRL;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.google.common.base.Preconditions;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.security.provider.certpath.PKIXCertPathValidator;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;

public class CertificateValidationUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(CertificateValidationUtil.class);

    private static final String KEY_USAGE_OID = "2.5.29.15";
    private static final String EXTENDED_KEY_USAGE_OID = "2.5.29.37";
    private static final String SERVER_AUTH_OID = "1.3.6.1.5.5.7.3.1";
    private static final String CLIENT_AUTH_OID = "1.3.6.1.5.5.7.3.2";

    private static final int SUBJECT_ALT_NAME_URI = 6;
    private static final int SUBJECT_ALT_NAME_DNS_NAME = 2;
    private static final int SUBJECT_ALT_NAME_IP_ADDRESS = 7;

    public static PKIXCertPathBuilderResult buildTrustedCertPath(
        List<X509Certificate> certificateChain,
        Collection<X509Certificate> trustedCertificates,
        Collection<X509Certificate> issuerCertificates
    ) throws UaException {

        Preconditions.checkArgument(!certificateChain.isEmpty(), "certificateChain must not be empty");

        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("certificateChain: {}", certificateChain);
            LOGGER.trace("trustedCertificates: {}", trustedCertificates);
            LOGGER.trace("issuerCertificates: {}", issuerCertificates);
        }

        Set<TrustAnchor> trustAnchors = new HashSet<>();
        for (X509Certificate c : trustedCertificates) {
            if (certificateIsCa(c) && certificateIsSelfSigned(c)) {
                trustAnchors.add(new TrustAnchor(c, null));
            }
        }
        for (X509Certificate c : issuerCertificates) {
            if (certificateIsCa(c) && certificateIsSelfSigned(c)) {
                trustAnchors.add(new TrustAnchor(c, null));
            }
        }

        PKIXCertPathBuilderResult certPathResult = buildCertPath(
            certificateChain,
            trustedCertificates,
            issuerCertificates,
            trustAnchors
        );

        CertPath certPath = certPathResult.getCertPath();
        TrustAnchor trustAnchor = certPathResult.getTrustAnchor();

        List<X509Certificate> certificates = new ArrayList<>();
        certPath.getCertificates().stream()
            .map(X509Certificate.class::cast)
            .forEach(certificates::add);
        certificates.add(trustAnchor.getTrustedCert());

        if (LOGGER.isTraceEnabled()) {
            List<String> path = certificates.stream()
                .map(c -> c.getSubjectX500Principal().getName())
                .collect(Collectors.toList());
            LOGGER.trace("certificate path: {}", path);
        }

        if (certificates.stream().noneMatch(trustedCertificates::contains)) {
            throw new UaException(
                StatusCodes.Bad_CertificateUntrusted,
                "certificate chain did not contain a trusted certificate"
            );
        }

        return certPathResult;
    }

    public static void validateTrustedCertPath(
        CertPath certPath,
        TrustAnchor trustAnchor,
        Collection<X509CRL> crls,
        boolean revocationCheckingEnabled
    ) throws UaException {

        X509Certificate anchorCert = trustAnchor.getTrustedCert();

        if (certPath.getCertificates().isEmpty()) {
            // self-signed; anchor is the end entity cert
            checkValidity(anchorCert, true);
            checkCertificateUsage(anchorCert);
        } else {
            checkValidity(anchorCert, false);
            checkIssuerCertificateUsage(anchorCert);

            // TODO add a CertPathChecker for keyUsage and extendedKeyUsage
            try {
                PKIXParameters parameters = new PKIXParameters(newHashSet(trustAnchor));

                if (revocationCheckingEnabled) {
                    parameters.setRevocationEnabled(true);

                    if (!crls.isEmpty()) {
                        parameters.addCertStore(CertStore.getInstance(
                            "Collection",
                            new CollectionCertStoreParameters(crls)
                        ));
                    }
                } else {
                    parameters.setRevocationEnabled(false);
                }

                parameters.addCertPathChecker(new OpcUaCertificateUsageChecker(certPath));

                PKIXCertPathValidator certPathValidator = new PKIXCertPathValidator();

                CertPathChecker revocationChecker = certPathValidator.engineGetRevocationChecker();

                if (revocationCheckingEnabled && revocationChecker instanceof PKIXRevocationChecker) {
                    PKIXRevocationChecker pkixRevocationChecker = (PKIXRevocationChecker) revocationChecker;

                    pkixRevocationChecker.setOptions(newHashSet(
                        PKIXRevocationChecker.Option.NO_FALLBACK,
                        PKIXRevocationChecker.Option.PREFER_CRLS,
                        PKIXRevocationChecker.Option.SOFT_FAIL
                    ));
                }

                certPathValidator.engineValidate(certPath, parameters);
            } catch (CertPathValidatorException e) {
                CertPath path = e.getCertPath();
                CertPathValidatorException.Reason reason = e.getReason();

                int failedAtIndex = e.getIndex();

                if (failedAtIndex < 0) {
                    throw new UaException(StatusCodes.Bad_SecurityChecksFailed, e);
                } else {
                    X509Certificate failed = (X509Certificate) path.getCertificates().get(failedAtIndex);

                    LOGGER.debug(
                        "cert path validation failed at index={} reason={}, certificate={}",
                        failedAtIndex,
                        reason,
                        failed.getSubjectX500Principal().getName()
                    );

                    if (reason == CertPathValidatorException.BasicReason.REVOKED) {
                        if (failedAtIndex == 0) {
                            throw new UaException(StatusCodes.Bad_CertificateRevoked, e);
                        } else {
                            throw new UaException(StatusCodes.Bad_CertificateIssuerRevoked, e);
                        }
                    } else if (reason == CertPathValidatorException.BasicReason.UNDETERMINED_REVOCATION_STATUS) {
                        if (failedAtIndex == 0) {
                            throw new UaException(StatusCodes.Bad_CertificateRevocationUnknown, e);
                        } else {
                            throw new UaException(StatusCodes.Bad_CertificateIssuerRevocationUnknown, e);
                        }
                    } else if (reason == CertPathValidatorException.BasicReason.EXPIRED ||
                        reason == CertPathValidatorException.BasicReason.NOT_YET_VALID) {

                        if (failedAtIndex == 0) {
                            throw new UaException(StatusCodes.Bad_CertificateTimeInvalid, e);
                        } else {
                            throw new UaException(StatusCodes.Bad_CertificateIssuerTimeInvalid, e);
                        }
                    } else {
                        throw new UaException(StatusCodes.Bad_SecurityChecksFailed, e);
                    }
                }
            } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException e) {
                throw new UaException(StatusCodes.Bad_SecurityChecksFailed, e);
            }
        }
    }

    private static PKIXCertPathBuilderResult buildCertPath(
        List<X509Certificate> certificateChain,
        Collection<X509Certificate> trustedCertificates,
        Collection<X509Certificate> issuerCertificates,
        Set<TrustAnchor> trustAnchors
    ) throws UaException {

        X509Certificate certificate = certificateChain.get(0);

        X509CertSelector selector = new X509CertSelector();
        selector.setCertificate(certificate);

        try {
            PKIXBuilderParameters builderParams = new PKIXBuilderParameters(trustAnchors, selector);

            // Add a CertStore containing potential intermediate certs
            Collection<Object> intermediates = newArrayList();
            intermediates.addAll(certificateChain.subList(1, certificateChain.size()));
            for (X509Certificate c : trustedCertificates) {
                if (certificateIsCa(c) && !certificateIsSelfSigned(c)) {
                    intermediates.add(c);
                }
            }
            for (X509Certificate c : issuerCertificates) {
                if (certificateIsCa(c) && !certificateIsSelfSigned(c)) {
                    intermediates.add(c);
                }
            }

            if (intermediates.size() > 0) {
                CertStore certStore = CertStore.getInstance(
                    "Collection",
                    new CollectionCertStoreParameters(intermediates)
                );

                builderParams.addCertStore(certStore);
            }

            // Disable revocation checking in the CertPathBuilder; it will be
            // checked by a PKIXCertPathValidator after the CertPath is built.
            builderParams.setRevocationEnabled(false);

            CertPathBuilder builder = CertPathBuilder.getInstance("PKIX");

            return (PKIXCertPathBuilderResult) builder.build(builderParams);
        } catch (GeneralSecurityException e) {
            throw new UaException(StatusCodes.Bad_SecurityChecksFailed, e);
        }
    }

    /**
     * Check if {@code certificate}'s KeyUsage and/or BasicConstraints extensions indicate it is a CA.
     *
     * @param certificate the {@link X509Certificate} to check.
     * @return {@code true} if {@code certificate}'s KeyUsage and/or BasicConstraints extensions indicates it is a CA.
     */
    static boolean certificateIsCa(X509Certificate certificate) {
        boolean[] keyUsage = certificate.getKeyUsage();
        int basicConstraints = certificate.getBasicConstraints();

        if (keyUsage == null) {
            // no KeyUsage, just check if the cA BasicConstraint is set.
            return basicConstraints >= 0;
        } else {
            if (keyUsage[5] && basicConstraints == -1) {
                // KeyUsage is present and the keyCertSign bit is set.
                // According to RFC 5280 the BasicConstraint cA bit must also
                // be set, but it's not!
                LOGGER.warn(
                    "'{}' violates RFC 5280: KeyUsage keyCertSign " +
                        "bit set without BasicConstraint cA bit set",
                    certificate.getSubjectX500Principal().getName()
                );
            }

            return keyUsage[5] || basicConstraints >= 0;
        }
    }

    /**
     * Return {@code true} if a given {@link X509Certificate} is self-signed.
     *
     * @return {@code true} if a given {@link X509Certificate} is self-signed.
     */
    private static boolean certificateIsSelfSigned(X509Certificate cert) throws UaException {
        try {
            // Verify certificate signature with its own public key
            PublicKey key = cert.getPublicKey();
            cert.verify(key);

            // Check that subject and issuer are the same
            return Objects.equals(cert.getSubjectX500Principal(), cert.getIssuerX500Principal());
        } catch (SignatureException | InvalidKeyException sigEx) {
            // Invalid signature or key: not self-signed
            return false;
        } catch (Exception e) {
            throw new UaException(StatusCodes.Bad_CertificateInvalid, e);
        }
    }

    /**
     * Check that {@code certificate} is valid (the current date and time are within the validity period).
     *
     * @param certificate the {@link X509Certificate} to check.
     * @param endEntity   {@code true} if the certificate is the end entity, {@code false} if it's an issuer.
     * @throws UaException if the certificate is not valid.
     */
    public static void checkValidity(X509Certificate certificate, boolean endEntity) throws UaException {
        try {
            certificate.checkValidity();
        } catch (CertificateExpiredException e) {
            throw new UaException(
                endEntity ?
                    StatusCodes.Bad_CertificateTimeInvalid :
                    StatusCodes.Bad_CertificateIssuerTimeInvalid,
                String.format(
                    "certificate is expired: %s - %s",
                    certificate.getNotBefore(), certificate.getNotAfter())
            );
        } catch (CertificateNotYetValidException e) {
            throw new UaException(
                endEntity ?
                    StatusCodes.Bad_CertificateTimeInvalid :
                    StatusCodes.Bad_CertificateIssuerTimeInvalid,
                String.format(
                    "certificate not yet valid: %s - %s",
                    certificate.getNotBefore(), certificate.getNotAfter())
            );
        }
    }

    /**
     * Validate that one of {@code hostNames} matches a SubjectAltName DNSName or IPAddress entry in the certificate.
     *
     * @param certificate the certificate to validate against.
     * @param hostNames   the host names or ip addresses to look for.
     * @throws UaException if there is no matching DNSName or IPAddress entry.
     */
    public static void checkHostnameOrIpAddress(
        X509Certificate certificate,
        String... hostNames
    ) throws UaException {

        boolean dnsNameMatch = Arrays.stream(hostNames).anyMatch(n -> {
            try {
                return checkSubjectAltNameField(
                    certificate,
                    SUBJECT_ALT_NAME_DNS_NAME,
                    n::equals
                );
            } catch (Throwable t) {
                return false;
            }
        });

        boolean ipAddressMatch = Arrays.stream(hostNames).anyMatch(n -> {
            try {
                return checkSubjectAltNameField(
                    certificate,
                    SUBJECT_ALT_NAME_IP_ADDRESS,
                    n::equals
                );
            } catch (Throwable t) {
                return false;
            }
        });

        if (!(dnsNameMatch || ipAddressMatch)) {
            throw new UaException(StatusCodes.Bad_CertificateHostNameInvalid);
        }
    }

    public static void checkCertificateUsage(X509Certificate certificate) throws UaException {
        Set<String> criticalExtensions = certificate.getCriticalExtensionOIDs();
        // TODO suppression
        // TODO if keyUsage or extendedKeyUsage are critical they cannot be suppressed

        boolean[] keyUsage = certificate.getKeyUsage();

        if (keyUsage == null) {
            throw new UaException(
                StatusCodes.Bad_CertificateUseNotAllowed,
                "KeyUsage extension not found"
            );
        }

        boolean digitalSignature = keyUsage[0];
        boolean nonRepudiation = keyUsage[1];
        boolean keyEncipherment = keyUsage[2];
        boolean dataEncipherment = keyUsage[3];

        if (!digitalSignature) {
            throw new UaException(
                StatusCodes.Bad_CertificateUseNotAllowed,
                "required KeyUsage 'digitalSignature' not found"
            );
        }

        if (!nonRepudiation) {
            throw new UaException(
                StatusCodes.Bad_CertificateUseNotAllowed,
                "required KeyUsage 'nonRepudiation' not found"
            );
        }

        if (!keyEncipherment) {
            throw new UaException(
                StatusCodes.Bad_CertificateUseNotAllowed,
                "required KeyUsage 'keyEncipherment' not found"
            );
        }

        if (!dataEncipherment) {
            throw new UaException(
                StatusCodes.Bad_CertificateUseNotAllowed,
                "required KeyUsage 'dataEncipherment' not found"
            );
        }

        try {
            List<String> extendedKeyUsage = certificate.getExtendedKeyUsage();

            if (extendedKeyUsage == null) {
                throw new UaException(
                    StatusCodes.Bad_CertificateUseNotAllowed,
                    "ExtendedKeyUsage extension not found"
                );
            }

            if (!extendedKeyUsage.contains(CLIENT_AUTH_OID)) {
                throw new UaException(
                    StatusCodes.Bad_CertificateUseNotAllowed,
                    "required ExtendedKeyUsage 'clientAuth' not found"
                );
            }

            if (!extendedKeyUsage.contains(SERVER_AUTH_OID)) {
                throw new UaException(
                    StatusCodes.Bad_CertificateUseNotAllowed,
                    "required ExtendedKeyUsage 'serverAuth' not found"
                );
            }
        } catch (CertificateParsingException e) {
            throw new UaException(StatusCodes.Bad_CertificateUseNotAllowed);
        }
    }

    /**
     * Validate that the application URI matches the SubjectAltName URI in the given certificate.
     *
     * @param certificate    the certificate to validate against.
     * @param applicationUri the URI to validate.
     * @throws UaException if the certificate is invalid, does not contain a uri, or contains a uri that does not match.
     */
    public static void checkApplicationUri(X509Certificate certificate, String applicationUri) throws UaException {
        if (!checkSubjectAltNameField(certificate, SUBJECT_ALT_NAME_URI, applicationUri::equals)) {
            throw new UaException(StatusCodes.Bad_CertificateUriInvalid);
        }
    }

    public static void checkIssuerCertificateUsage(X509Certificate certificate) throws UaException {
        // TODO
        LOGGER.info(
            "TODO: check certificate usage for issuer: {}",
            certificate.getSubjectX500Principal().getName()
        );
    }

    /**
     * Test the value of some SubjectAlternativeNames field against a predicate.
     *
     * @param certificate    an {@link X509Certificate}.
     * @param field          the field id.
     * @param fieldPredicate a predicate to test the field value.
     * @return {@code true} if the field was found and the predicate tested true.
     * @throws UaException if SubjectAlternativeNames can't be obtained from the certificate.
     */
    private static boolean checkSubjectAltNameField(
        X509Certificate certificate,
        int field,
        Predicate<Object> fieldPredicate
    ) throws UaException {

        try {
            Collection<List<?>> subjectAltNames = certificate.getSubjectAlternativeNames();
            if (subjectAltNames == null) subjectAltNames = Collections.emptyList();

            for (List<?> idAndValue : subjectAltNames) {
                if (idAndValue != null && idAndValue.size() == 2) {
                    if (idAndValue.get(0).equals(field)) {
                        if (fieldPredicate.test(idAndValue.get(1))) {
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

    enum ValidationCheck {
        KEY_USAGE,
        EXTENDED_KEY_USAGE,
        REVOCATION_LIST,
        REVOCATION_STATUS
    }

    private static class OpcUaCertificateUsageChecker extends PKIXCertPathChecker {

        private final X509Certificate endEntityCert;

        private final CertPath certPath;

        OpcUaCertificateUsageChecker(CertPath certPath) {
            this.certPath = certPath;

            endEntityCert = (X509Certificate) certPath.getCertificates().get(0);
        }

        @Override
        public void init(boolean forward) throws CertPathValidatorException {
            if (forward) {
                throw new CertPathValidatorException("forward checking not supported");
            }
        }

        @Override
        public boolean isForwardCheckingSupported() {
            return false;
        }

        @Override
        public Set<String> getSupportedExtensions() {
            return null;
        }

        @Override
        public void check(Certificate cert, Collection<String> unresolvedCritExts) throws CertPathValidatorException {
            if (endEntityCert.equals(cert)) {
                try {
                    checkCertificateUsage((X509Certificate) cert);

                    LOGGER.info(
                        "validated certificate usage for end entity: {}",
                        ((X509Certificate) cert).getSubjectX500Principal().getName()
                    );
                } catch (UaException e) {
                    throw new CertPathValidatorException(
                        e.getMessage(),
                        e,
                        certPath,
                        certPath.getCertificates().indexOf(cert),
                        PKIXReason.INVALID_KEY_USAGE
                    );
                }
            } else {
                try {
                    checkIssuerCertificateUsage((X509Certificate) cert);

                    LOGGER.info(
                        "validated certificate usage for issuer: {}",
                        ((X509Certificate) cert).getSubjectX500Principal().getName()
                    );
                } catch (UaException e) {
                    throw new CertPathValidatorException(
                        e.getMessage(),
                        e,
                        certPath,
                        certPath.getCertificates().indexOf(cert),
                        PKIXReason.INVALID_KEY_USAGE
                    );
                }
            }
        }

    }

}
