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
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("NodeTypeDescription")
public class NodeTypeDescription implements UaStructure {

    public static final NodeId TypeId = Identifiers.NodeTypeDescription;
    public static final NodeId BinaryEncodingId = Identifiers.NodeTypeDescription_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.NodeTypeDescription_Encoding_DefaultXml;

    protected final ExpandedNodeId _typeDefinitionNode;
    protected final Boolean _includeSubTypes;
    protected final QueryDataDescription[] _dataToReturn;

    public NodeTypeDescription() {
        this._typeDefinitionNode = null;
        this._includeSubTypes = null;
        this._dataToReturn = null;
    }

    public NodeTypeDescription(ExpandedNodeId _typeDefinitionNode, Boolean _includeSubTypes, QueryDataDescription[] _dataToReturn) {
        this._typeDefinitionNode = _typeDefinitionNode;
        this._includeSubTypes = _includeSubTypes;
        this._dataToReturn = _dataToReturn;
    }

    public ExpandedNodeId getTypeDefinitionNode() { return _typeDefinitionNode; }

    public Boolean getIncludeSubTypes() { return _includeSubTypes; }

    @Nullable
    public QueryDataDescription[] getDataToReturn() { return _dataToReturn; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("TypeDefinitionNode", _typeDefinitionNode)
            .add("IncludeSubTypes", _includeSubTypes)
            .add("DataToReturn", _dataToReturn)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<NodeTypeDescription> {
        @Override
        public NodeTypeDescription decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            ExpandedNodeId _typeDefinitionNode = reader.readExpandedNodeId();
            Boolean _includeSubTypes = reader.readBoolean();
            QueryDataDescription[] _dataToReturn =
                reader.readArray(
                    () -> (QueryDataDescription) context.decode(
                        QueryDataDescription.BinaryEncodingId, reader),
                    QueryDataDescription.class
                );

            return new NodeTypeDescription(_typeDefinitionNode, _includeSubTypes, _dataToReturn);
        }

        @Override
        public void encode(SerializationContext context, NodeTypeDescription encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeExpandedNodeId(encodable._typeDefinitionNode);
            writer.writeBoolean(encodable._includeSubTypes);
            writer.writeArray(
                encodable._dataToReturn,
                e -> context.encode(QueryDataDescription.BinaryEncodingId, e, writer)
            );
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<NodeTypeDescription> {
        @Override
        public NodeTypeDescription decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            ExpandedNodeId _typeDefinitionNode = reader.readExpandedNodeId("TypeDefinitionNode");
            Boolean _includeSubTypes = reader.readBoolean("IncludeSubTypes");
            QueryDataDescription[] _dataToReturn =
                reader.readArray(
                    "DataToReturn",
                    f -> (QueryDataDescription) context.decode(
                        QueryDataDescription.XmlEncodingId, reader),
                    QueryDataDescription.class
                );

            return new NodeTypeDescription(_typeDefinitionNode, _includeSubTypes, _dataToReturn);
        }

        @Override
        public void encode(SerializationContext context, NodeTypeDescription encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeExpandedNodeId("TypeDefinitionNode", encodable._typeDefinitionNode);
            writer.writeBoolean("IncludeSubTypes", encodable._includeSubTypes);
            writer.writeArray(
                "DataToReturn",
                encodable._dataToReturn,
                (f, e) -> context.encode(QueryDataDescription.XmlEncodingId, e, writer)
            );
        }
    }

}
