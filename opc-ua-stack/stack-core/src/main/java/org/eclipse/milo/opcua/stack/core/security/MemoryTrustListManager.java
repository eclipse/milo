package org.eclipse.milo.opcua.stack.core.security;

import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.util.CertificateUtil;

public class MemoryTrustListManager implements TrustListManager {

    private final List<X509Certificate> issuerCertificates = Collections.synchronizedList(new ArrayList<>());
    private final List<X509Certificate> trustedCertificates = Collections.synchronizedList(new ArrayList<>());

    private final List<X509CRL> issuerCrls = Collections.synchronizedList(new ArrayList<>());
    private final List<X509CRL> trustedCrls = Collections.synchronizedList(new ArrayList<>());

    @Override
    public List<X509CRL> getIssuerCrls() {
        synchronized (issuerCrls) {
            return List.copyOf(issuerCrls);
        }
    }

    @Override
    public List<X509CRL> getTrustedCrls() {
        synchronized (trustedCrls) {
            return List.copyOf(trustedCrls);
        }
    }

    @Override
    public List<X509Certificate> getIssuerCertificates() {
        synchronized (issuerCertificates) {
            return List.copyOf(issuerCertificates);
        }
    }

    @Override
    public List<X509Certificate> getTrustedCertificates() {
        synchronized (trustedCertificates) {
            return List.copyOf(trustedCertificates);
        }
    }

    @Override
    public void setIssuerCrls(List<X509CRL> issuerCrls) {
        synchronized (this.issuerCrls) {
            this.issuerCrls.clear();
            this.issuerCrls.addAll(issuerCrls);
        }
    }

    @Override
    public void setTrustedCrls(List<X509CRL> trustedCrls) {
        synchronized (this.trustedCrls) {
            this.trustedCrls.clear();
            this.trustedCrls.addAll(trustedCrls);
        }
    }

    @Override
    public void setIssuerCertificates(List<X509Certificate> issuerCertificates) {
        synchronized (this.issuerCertificates) {
            this.issuerCertificates.clear();
            this.issuerCertificates.addAll(issuerCertificates);
        }
    }

    @Override
    public void setTrustedCertificates(List<X509Certificate> trustedCertificates) {
        synchronized (this.trustedCertificates) {
            this.trustedCertificates.clear();
            this.trustedCertificates.addAll(trustedCertificates);
        }
    }

    @Override
    public void addIssuerCertificate(X509Certificate certificate) {
        issuerCertificates.add(certificate);
    }

    @Override
    public void addTrustedCertificate(X509Certificate certificate) {
        trustedCertificates.add(certificate);
    }

    @Override
    public boolean removeIssuerCertificate(ByteString thumbprint) {
        return issuerCertificates.removeIf(c -> thumbprintMatches(c, thumbprint));
    }

    @Override
    public boolean removeTrustedCertificate(ByteString thumbprint) {
        return trustedCertificates.removeIf(c -> thumbprintMatches(c, thumbprint));
    }

    @Override
    public void addRejectedCertificate(X509Certificate certificate) {
        // TODO
    }

    @Override
    public boolean removeRejectedCertificate(ByteString thumbprint) {
        return false; // TODO
    }

    @Override
    public List<X509Certificate> getRejectedCertificates() {
        return Collections.emptyList(); // TODO
    }

    @Override
    public DateTime getLastUpdateTime() {
        return DateTime.now(); // TODO
    }

    private static boolean thumbprintMatches(X509Certificate certificate, ByteString thumbprint) {
        try {
            return CertificateUtil.thumbprint(certificate).equals(thumbprint);
        } catch (UaException e) {
            return false;
        }
    }

}
