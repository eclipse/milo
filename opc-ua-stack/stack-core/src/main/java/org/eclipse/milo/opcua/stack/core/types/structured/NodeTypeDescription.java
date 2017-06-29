/*
 * Copyright (c) 2017 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class NodeTypeDescription implements UaStructure {

    public static final NodeId TypeId = Identifiers.NodeTypeDescription;
    public static final NodeId BinaryEncodingId = Identifiers.NodeTypeDescription_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.NodeTypeDescription_Encoding_DefaultXml;

    protected final ExpandedNodeId typeDefinitionNode;
    protected final Boolean includeSubTypes;
    protected final QueryDataDescription[] dataToReturn;

    public NodeTypeDescription() {
        this.typeDefinitionNode = null;
        this.includeSubTypes = null;
        this.dataToReturn = null;
    }

    public NodeTypeDescription(ExpandedNodeId typeDefinitionNode, Boolean includeSubTypes, QueryDataDescription[] dataToReturn) {
        this.typeDefinitionNode = typeDefinitionNode;
        this.includeSubTypes = includeSubTypes;
        this.dataToReturn = dataToReturn;
    }

    public ExpandedNodeId getTypeDefinitionNode() { return typeDefinitionNode; }

    public Boolean getIncludeSubTypes() { return includeSubTypes; }

    @Nullable
    public QueryDataDescription[] getDataToReturn() { return dataToReturn; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("TypeDefinitionNode", typeDefinitionNode)
            .add("IncludeSubTypes", includeSubTypes)
            .add("DataToReturn", dataToReturn)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<NodeTypeDescription> {

        @Override
        public Class<NodeTypeDescription> getType() {
            return NodeTypeDescription.class;
        }

        @Override
        public NodeTypeDescription decode(UaDecoder decoder) throws UaSerializationException {
            ExpandedNodeId typeDefinitionNode = decoder.readExpandedNodeId("TypeDefinitionNode");
            Boolean includeSubTypes = decoder.readBoolean("IncludeSubTypes");
            QueryDataDescription[] dataToReturn =
                decoder.readBuiltinStructArray(
                    "DataToReturn",
                    QueryDataDescription.class
                );

            return new NodeTypeDescription(typeDefinitionNode, includeSubTypes, dataToReturn);
        }

        @Override
        public void encode(NodeTypeDescription value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeExpandedNodeId("TypeDefinitionNode", value.typeDefinitionNode);
            encoder.writeBoolean("IncludeSubTypes", value.includeSubTypes);
            encoder.writeBuiltinStructArray(
                "DataToReturn",
                value.dataToReturn,
                QueryDataDescription.class
            );
        }
    }

}
