package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.nodes.MethodNode;
import org.eclipse.milo.opcua.sdk.server.model.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.structured.UserTokenPolicy;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/8.6.1">https://reference.opcfoundation.org/v105/Core/docs/Part14/8.6.1</a>
 */
public interface PubSubKeyPushTargetType extends BaseObjectType {
    QualifiedProperty<String> APPLICATION_URI = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ApplicationUri",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        -1,
        String.class
    );

    QualifiedProperty<String> ENDPOINT_URL = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "EndpointUrl",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        -1,
        String.class
    );

    QualifiedProperty<String> SECURITY_POLICY_URI = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "SecurityPolicyUri",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        -1,
        String.class
    );

    QualifiedProperty<UserTokenPolicy> USER_TOKEN_TYPE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "UserTokenType",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=304"),
        -1,
        UserTokenPolicy.class
    );

    QualifiedProperty<UShort> REQUESTED_KEY_COUNT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "RequestedKeyCount",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=5"),
        -1,
        UShort.class
    );

    QualifiedProperty<Double> RETRY_INTERVAL = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "RetryInterval",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=290"),
        -1,
        Double.class
    );

    QualifiedProperty<DateTime> LAST_PUSH_EXECUTION_TIME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "LastPushExecutionTime",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=13"),
        -1,
        DateTime.class
    );

    QualifiedProperty<DateTime> LAST_PUSH_ERROR_TIME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "LastPushErrorTime",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=13"),
        -1,
        DateTime.class
    );

    String getApplicationUri();

    void setApplicationUri(String value);

    PropertyType getApplicationUriNode();

    String getEndpointUrl();

    void setEndpointUrl(String value);

    PropertyType getEndpointUrlNode();

    String getSecurityPolicyUri();

    void setSecurityPolicyUri(String value);

    PropertyType getSecurityPolicyUriNode();

    UserTokenPolicy getUserTokenType();

    void setUserTokenType(UserTokenPolicy value);

    PropertyType getUserTokenTypeNode();

    UShort getRequestedKeyCount();

    void setRequestedKeyCount(UShort value);

    PropertyType getRequestedKeyCountNode();

    Double getRetryInterval();

    void setRetryInterval(Double value);

    PropertyType getRetryIntervalNode();

    DateTime getLastPushExecutionTime();

    void setLastPushExecutionTime(DateTime value);

    PropertyType getLastPushExecutionTimeNode();

    DateTime getLastPushErrorTime();

    void setLastPushErrorTime(DateTime value);

    PropertyType getLastPushErrorTimeNode();

    MethodNode getConnectSecurityGroupsMethodNode();

    MethodNode getDisconnectSecurityGroupsMethodNode();

    MethodNode getTriggerKeyUpdateMethodNode();
}
