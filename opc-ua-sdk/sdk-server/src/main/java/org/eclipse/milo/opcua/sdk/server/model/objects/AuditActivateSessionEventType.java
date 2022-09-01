package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.SignedSoftwareCertificate;
import org.eclipse.milo.opcua.stack.core.types.structured.UserIdentityToken;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/6.4.10">https://reference.opcfoundation.org/v105/Core/docs/Part5/6.4.10</a>
 */
public interface AuditActivateSessionEventType extends AuditSessionEventType {
    QualifiedProperty<SignedSoftwareCertificate[]> CLIENT_SOFTWARE_CERTIFICATES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ClientSoftwareCertificates",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=344"),
        1,
        SignedSoftwareCertificate[].class
    );

    QualifiedProperty<UserIdentityToken> USER_IDENTITY_TOKEN = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "UserIdentityToken",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=316"),
        -1,
        UserIdentityToken.class
    );

    QualifiedProperty<String> SECURE_CHANNEL_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "SecureChannelId",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        -1,
        String.class
    );

    SignedSoftwareCertificate[] getClientSoftwareCertificates();

    void setClientSoftwareCertificates(SignedSoftwareCertificate[] value);

    PropertyType getClientSoftwareCertificatesNode();

    UserIdentityToken getUserIdentityToken();

    void setUserIdentityToken(UserIdentityToken value);

    PropertyType getUserIdentityTokenNode();

    String getSecureChannelId();

    void setSecureChannelId(String value);

    PropertyType getSecureChannelIdNode();
}