package org.eclipse.milo.opcua.stack.core.security;

import com.google.common.collect.Sets;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.util.CertificateUtil;

import java.io.File;
import java.io.IOException;
import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class DefaultCertificateManager2 implements CertificateManager2 {

    private final Set<X509Certificate> rejectedCertificates = Sets.newConcurrentHashSet();

    private final Map<NodeId, CertificateGroup> certificateGroups = new ConcurrentHashMap<>();

    public DefaultCertificateManager2() {}

    /**
     * Create a {@link DefaultCertificateManager2} with a {@link KeyPair} and
     * {@link X509Certificate} belonging to the DefaultApplicationGroup instance.
     *
     * @param trustListDir the directory to manage the Trust List in.
     * @param keyPair the {@link KeyPair}.
     * @param certificate the {@link X509Certificate}.
     */
    public DefaultCertificateManager2(
        File trustListDir,
        KeyPair keyPair,
        X509Certificate certificate
    ) throws IOException {

        this(trustListDir, keyPair, new X509Certificate[]{certificate});
    }

    /**
     * Create a {@link DefaultCertificateManager2} with a {@link KeyPair} and
     * {@link X509Certificate} chain belonging to the DefaultApplicationGroup instance.
     *
     * @param trustListDir the directory to manage the Trust List in.
     * @param keyPair the {@link KeyPair}.
     * @param certificateChain the {@link X509Certificate} chain.
     */
    public DefaultCertificateManager2(
        File trustListDir,
        KeyPair keyPair,
        X509Certificate[] certificateChain
    ) throws IOException {

        checkNotNull(keyPair, "keyPair must be non-null");
        checkNotNull(certificateChain, "certificateChain must be non-null");
        checkArgument(certificateChain.length > 0, "certificateChain must be non-empty");

        var defaultGroup = new DefaultApplicationCertificateGroup(trustListDir, keyPair, certificateChain);
        certificateGroups.put(defaultGroup.getCertificateGroupId(), defaultGroup);
    }

    @Override
    public Optional<KeyPair> getKeyPair(ByteString thumbprint) {
        return firstMatchingCertificate(thumbprint).map(c -> c.keyPair);
    }

    @Override
    public Optional<X509Certificate> getCertificate(ByteString thumbprint) {
        return firstMatchingCertificate(thumbprint).map(c -> c.certificate);
    }

    @Override
    public Optional<X509Certificate[]> getCertificateChain(ByteString thumbprint) {
        return firstMatchingCertificate(thumbprint).map(c -> c.certificateChain);
    }

    @Override
    public Optional<CertificateGroup> getCertificateGroup(NodeId certificateGroupId) {
        return Optional.ofNullable(certificateGroups.get(certificateGroupId));
    }

    @Override
    public List<X509Certificate> getRejectedCertificates() {
        return List.copyOf(rejectedCertificates);
    }

    @Override
    public void addRejectedCertificate(X509Certificate certificate) {
        rejectedCertificates.add(certificate);
    }

    @Override
    public boolean removeRejectedCertificate(X509Certificate certificate) {
        return rejectedCertificates.remove(certificate);
    }

    private Optional<CertificateGroup.Certificate> firstMatchingCertificate(ByteString thumbprint) {
        return certificateGroups.values().stream()
            .flatMap(g -> g.getCertificates().stream())
            .filter(c -> {
                try {
                    return CertificateUtil.thumbprint(c.certificate).equals(thumbprint);
                } catch (UaException e) {
                    return false;
                }
            })
            .findFirst();
    }

}
