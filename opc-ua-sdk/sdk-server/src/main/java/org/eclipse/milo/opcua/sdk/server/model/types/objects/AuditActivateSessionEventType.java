package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.SignedSoftwareCertificate;
import org.eclipse.milo.opcua.stack.core.types.structured.UserIdentityToken;

public interface AuditActivateSessionEventType extends AuditSessionEventType {
    QualifiedProperty<SignedSoftwareCertificate[]> CLIENT_SOFTWARE_CERTIFICATES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ClientSoftwareCertificates",
        NodeId.parse("ns=0;i=344"),
        ValueRanks.OneDimension,
        SignedSoftwareCertificate[].class
    );

    QualifiedProperty<UserIdentityToken> USER_IDENTITY_TOKEN = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "UserIdentityToken",
        NodeId.parse("ns=0;i=316"),
        ValueRanks.Scalar,
        UserIdentityToken.class
    );

    QualifiedProperty<String> SECURE_CHANNEL_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "SecureChannelId",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    PropertyType getClientSoftwareCertificatesNode();

    SignedSoftwareCertificate[] getClientSoftwareCertificates();

    void setClientSoftwareCertificates(SignedSoftwareCertificate[] value);

    PropertyType getUserIdentityTokenNode();

    UserIdentityToken getUserIdentityToken();

    void setUserIdentityToken(UserIdentityToken value);

    PropertyType getSecureChannelIdNode();

    String getSecureChannelId();

    void setSecureChannelId(String value);
}
