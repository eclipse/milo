package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface AuditCreateSessionEventType extends AuditSessionEventType {
    QualifiedProperty<String> SECURE_CHANNEL_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "SecureChannelId",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    QualifiedProperty<ByteString> CLIENT_CERTIFICATE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ClientCertificate",
        NodeId.parse("ns=0;i=15"),
        ValueRanks.Scalar,
        ByteString.class
    );

    QualifiedProperty<String> CLIENT_CERTIFICATE_THUMBPRINT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ClientCertificateThumbprint",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    QualifiedProperty<Double> REVISED_SESSION_TIMEOUT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "RevisedSessionTimeout",
        NodeId.parse("ns=0;i=290"),
        ValueRanks.Scalar,
        Double.class
    );

    PropertyType getSecureChannelIdNode();

    String getSecureChannelId();

    void setSecureChannelId(String value);

    PropertyType getClientCertificateNode();

    ByteString getClientCertificate();

    void setClientCertificate(ByteString value);

    PropertyType getClientCertificateThumbprintNode();

    String getClientCertificateThumbprint();

    void setClientCertificateThumbprint(String value);

    PropertyType getRevisedSessionTimeoutNode();

    Double getRevisedSessionTimeout();

    void setRevisedSessionTimeout(Double value);
}
