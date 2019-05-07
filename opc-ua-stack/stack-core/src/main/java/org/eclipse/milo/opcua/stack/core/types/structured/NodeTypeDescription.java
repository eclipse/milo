/*
 * Copyright (c) 2019 the Eclipse Milo Authors
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
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class NodeTypeDescription extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=573");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=575");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=574");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15201");

    private final ExpandedNodeId typeDefinitionNode;

    private final Boolean includeSubTypes;

    private final QueryDataDescription[] dataToReturn;

    public NodeTypeDescription(ExpandedNodeId typeDefinitionNode, Boolean includeSubTypes,
                               QueryDataDescription[] dataToReturn) {
        this.typeDefinitionNode = typeDefinitionNode;
        this.includeSubTypes = includeSubTypes;
        this.dataToReturn = dataToReturn;
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

    public ExpandedNodeId getTypeDefinitionNode() {
        return typeDefinitionNode;
    }

    public Boolean getIncludeSubTypes() {
        return includeSubTypes;
    }

    public QueryDataDescription[] getDataToReturn() {
        return dataToReturn;
    }

    public static final class Codec extends GenericDataTypeCodec<NodeTypeDescription> {
        @Override
        public Class<NodeTypeDescription> getType() {
            return NodeTypeDescription.class;
        }

        @Override
        public NodeTypeDescription decode(SerializationContext context, UaDecoder decoder) {
            ExpandedNodeId typeDefinitionNode = decoder.readExpandedNodeId("TypeDefinitionNode");
            Boolean includeSubTypes = decoder.readBoolean("IncludeSubTypes");
            QueryDataDescription[] dataToReturn = (QueryDataDescription[]) decoder.readStructArray("DataToReturn", QueryDataDescription.TYPE_ID);
            return new NodeTypeDescription(typeDefinitionNode, includeSubTypes, dataToReturn);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, NodeTypeDescription value) {
            encoder.writeExpandedNodeId("TypeDefinitionNode", value.getTypeDefinitionNode());
            encoder.writeBoolean("IncludeSubTypes", value.getIncludeSubTypes());
            encoder.writeStructArray("DataToReturn", value.getDataToReturn(), QueryDataDescription.TYPE_ID);
        }
    }
}
