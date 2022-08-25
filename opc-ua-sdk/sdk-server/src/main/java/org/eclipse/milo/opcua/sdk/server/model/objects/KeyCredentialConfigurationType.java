package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.nodes.MethodNode;
import org.eclipse.milo.opcua.sdk.server.model.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

/**
 * @see <a href="https://reference.opcfoundation.org/v104/Core/docs/Amendment4/8.5.7">https://reference.opcfoundation.org/v104/Core/docs/Amendment4/8.5.7</a>
 */
public interface KeyCredentialConfigurationType extends BaseObjectType {
    QualifiedProperty<String> RESOURCE_URI = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ResourceUri",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        -1,
        String.class
    );

    QualifiedProperty<String> PROFILE_URI = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ProfileUri",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        -1,
        String.class
    );

    QualifiedProperty<String[]> ENDPOINT_URLS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "EndpointUrls",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        1,
        String[].class
    );

    QualifiedProperty<StatusCode> SERVICE_STATUS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ServiceStatus",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=19"),
        -1,
        StatusCode.class
    );

    String getResourceUri();

    void setResourceUri(String value);

    PropertyType getResourceUriNode();

    String getProfileUri();

    void setProfileUri(String value);

    PropertyType getProfileUriNode();

    String[] getEndpointUrls();

    void setEndpointUrls(String[] value);

    PropertyType getEndpointUrlsNode();

    StatusCode getServiceStatus();

    void setServiceStatus(StatusCode value);

    PropertyType getServiceStatusNode();

    MethodNode getGetEncryptingKeyMethodNode();

    MethodNode getUpdateCredentialMethodNode();

    MethodNode getDeleteCredentialMethodNode();
}
