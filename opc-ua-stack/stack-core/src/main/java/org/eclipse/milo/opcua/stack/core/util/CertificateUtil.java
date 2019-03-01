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

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.security.KeyPair;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.common.base.Preconditions;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.ExtensionsGenerator;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.openssl.MiscPEMGenerator;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCS10CertificationRequestBuilder;
import org.bouncycastle.pkcs.jcajce.JcaPKCS10CertificationRequestBuilder;
import org.bouncycastle.util.io.pem.PemWriter;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;

public class CertificateUtil {

    public static final int SUBJECT_ALT_NAME_DNS_NAME = GeneralName.dNSName;
    public static final int SUBJECT_ALT_NAME_IP_ADDRESS = GeneralName.iPAddress;
    public static final int SUBJECT_ALT_NAME_URI = GeneralName.uniformResourceIdentifier;

    /**
     * Decode a DER-encoded X.509 certificate.
     *
     * @param certificateBytes DER-encoded certificate bytes.
     * @return an {@link X509Certificate}
     * @throws UaException if decoding the certificate fails.
     */
    public static X509Certificate decodeCertificate(byte[] certificateBytes) throws UaException {
        Preconditions.checkNotNull(certificateBytes, "certificateBytes cannot be null");

        return decodeCertificate(new ByteArrayInputStream(certificateBytes));
    }

    /**
     * Decode a DER-encoded X.509 certificate.
     *
     * @param inputStream {@link InputStream} containing DER-encoded certificate bytes.
     * @return an {@link X509Certificate}
     * @throws UaException if decoding the certificate fails.
     */
    public static X509Certificate decodeCertificate(InputStream inputStream) throws UaException {
        return decodeCertificates(inputStream).get(0);
    }

    /**
     * Decode either a sequence of DER-encoded X.509 certificates or a PKCS#7 certificate chain.
     *
     * @param certificateBytes the byte[] to decode from.
     * @return a {@link List} of certificates deocded from {@code certificateBytes}.
     * @throws UaException if decoding fails.
     */
    public static List<X509Certificate> decodeCertificates(byte[] certificateBytes) throws UaException {
        return decodeCertificates(new ByteArrayInputStream(certificateBytes));
    }

    /**
     * Decode either a sequence of DER-encoded X.509 certificates or a PKCS#7 certificate chain.
     *
     * @param inputStream the {@link InputStream} to decode from.
     * @return a {@link List} of certificates decoded from {@code inputStream}.
     * @throws UaException if decoding fails.
     */
    public static List<X509Certificate> decodeCertificates(InputStream inputStream) throws UaException {
        Preconditions.checkNotNull(inputStream, "inputStream cannot be null");

        CertificateFactory factory;

        try {
            factory = CertificateFactory.getInstance("X.509");
        } catch (CertificateException e) {
            throw new UaException(StatusCodes.Bad_InternalError, e);
        }

        try {
            Collection<? extends Certificate> certificates =
                factory.generateCertificates(inputStream);

            return certificates.stream()
                .map(X509Certificate.class::cast)
                .collect(Collectors.toList());
        } catch (CertificateException e) {
            throw new UaException(StatusCodes.Bad_CertificateInvalid, e);
        }
    }

    /**
     * Generate a {@link PKCS10CertificationRequest} for the provided {@code certificate} and {@code keyPair}.
     *
     * @param certificate the {@link X509Certificate} to request signing for.
     * @param keyPair     the {@link KeyPair} for {@code certificate}.
     * @return a {@link PKCS10CertificationRequest}.
     * @throws Exception if creating the signing request fails for any reason.
     */
    public static PKCS10CertificationRequest generateCsr(
        X509Certificate certificate,
        KeyPair keyPair
    ) throws Exception {

        PKCS10CertificationRequestBuilder builder = new JcaPKCS10CertificationRequestBuilder(
            certificate.getSubjectX500Principal(),
            certificate.getPublicKey()
        );

        GeneralNames subjectAltNames = new GeneralNames(
            getSubjectAltNames(certificate)
                .toArray(new GeneralName[0])
        );

        ExtensionsGenerator extGen = new ExtensionsGenerator();
        extGen.addExtension(Extension.subjectAlternativeName, false, subjectAltNames);
        builder.addAttribute(PKCSObjectIdentifiers.pkcs_9_at_extensionRequest, extGen.generate());

        JcaContentSignerBuilder signerBuilder = new JcaContentSignerBuilder(
            certificate.getSigAlgName()
        );

        ContentSigner signer = signerBuilder.build(keyPair.getPrivate());

        return builder.build(signer);
    }

    /**
     * Generate a PEM-encoded PKCS #10 certificate signing request for the provided {@code certificate} and
     * {@code keyPair}.
     *
     * @param certificate the {@link X509Certificate} to request signing for.
     * @param keyPair     the {@link KeyPair} for {@code certificate}.
     * @return a PEM-encoded PKCS #10 CSR.
     * @throws Exception if creating the signing request fails for any reason.
     */
    public static String generateCsrPem(
        X509Certificate certificate,
        KeyPair keyPair
    ) throws Exception {

        PKCS10CertificationRequest csr = generateCsr(certificate, keyPair);

        StringWriter stringWriter = new StringWriter();

        try (PemWriter pemWriter = new PemWriter(stringWriter)) {
            pemWriter.writeObject(new MiscPEMGenerator(csr));
            pemWriter.flush();
        }

        return stringWriter.toString();
    }

    private static List<GeneralName> getSubjectAltNames(X509Certificate certificate) {
        try {
            List<GeneralName> generalNames = new ArrayList<>();

            Collection<List<?>> subjectAltNames = certificate.getSubjectAlternativeNames();
            if (subjectAltNames == null) subjectAltNames = Collections.emptyList();

            for (List<?> idAndValue : subjectAltNames) {
                if (idAndValue != null && idAndValue.size() == 2) {
                    Object id = idAndValue.get(0);
                    String value = Objects.toString(idAndValue.get(1));

                    if (Objects.equals(id, SUBJECT_ALT_NAME_DNS_NAME)) {
                        generalNames.add(new GeneralName(SUBJECT_ALT_NAME_DNS_NAME, value));
                    } else if (Objects.equals(id, SUBJECT_ALT_NAME_IP_ADDRESS)) {
                        generalNames.add(new GeneralName(SUBJECT_ALT_NAME_IP_ADDRESS, value));
                    } else if (Objects.equals(id, SUBJECT_ALT_NAME_URI)) {
                        generalNames.add(new GeneralName(SUBJECT_ALT_NAME_URI, value));
                    }
                }
            }

            return generalNames;
        } catch (CertificateParsingException e) {
            return Collections.emptyList();
        }
    }

    /**
     * Extract the value of a given SubjectAltName field from a {@link X509Certificate}.
     *
     * @param certificate the certificate.
     * @param field       the field number.
     * @return an {@link Optional} containing the value in the field.
     * @see #SUBJECT_ALT_NAME_IP_ADDRESS
     * @see #SUBJECT_ALT_NAME_DNS_NAME
     * @see #SUBJECT_ALT_NAME_URI
     */
    public static Optional<Object> getSubjectAltNameField(X509Certificate certificate, int field) {
        try {
            Collection<List<?>> subjectAltNames = certificate.getSubjectAlternativeNames();
            if (subjectAltNames == null) subjectAltNames = Collections.emptyList();

            for (List<?> idAndValue : subjectAltNames) {
                if (idAndValue != null && idAndValue.size() == 2) {
                    if (idAndValue.get(0).equals(field)) {
                        return Optional.ofNullable(idAndValue.get(1));
                    }
                }
            }

            return Optional.empty();
        } catch (CertificateParsingException e) {
            return Optional.empty();
        }
    }

}
