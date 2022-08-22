package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.5/#6.2.5.7">https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.5/#6.2.5.7</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public abstract class PubSubGroupDataType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=15609");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=15689");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=15988");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=16159");

    private final String name;

    private final Boolean enabled;

    private final MessageSecurityMode securityMode;

    private final String securityGroupId;

    private final EndpointDescription[] securityKeyServices;

    private final UInteger maxNetworkMessageSize;

    private final KeyValuePair[] groupProperties;

    public PubSubGroupDataType(String name, Boolean enabled, MessageSecurityMode securityMode,
                               String securityGroupId, EndpointDescription[] securityKeyServices,
                               UInteger maxNetworkMessageSize, KeyValuePair[] groupProperties) {
        this.name = name;
        this.enabled = enabled;
        this.securityMode = securityMode;
        this.securityGroupId = securityGroupId;
        this.securityKeyServices = securityKeyServices;
        this.maxNetworkMessageSize = maxNetworkMessageSize;
        this.groupProperties = groupProperties;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getJsonEncodingId() {
        return JSON_ENCODING_ID;
    }

    public String getName() {
        return name;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public MessageSecurityMode getSecurityMode() {
        return securityMode;
    }

    public String getSecurityGroupId() {
        return securityGroupId;
    }

    public EndpointDescription[] getSecurityKeyServices() {
        return securityKeyServices;
    }

    public UInteger getMaxNetworkMessageSize() {
        return maxNetworkMessageSize;
    }

    public KeyValuePair[] getGroupProperties() {
        return groupProperties;
    }
}
