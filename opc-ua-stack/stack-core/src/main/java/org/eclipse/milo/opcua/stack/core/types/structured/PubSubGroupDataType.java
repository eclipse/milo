/*
 * Copyright (c) 2021 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public abstract class PubSubGroupDataType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15609");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15689");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15988");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=16159");

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
