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

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class AddNodesResult implements UaStructure {

    public static final NodeId TypeId = Identifiers.AddNodesResult;
    public static final NodeId BinaryEncodingId = Identifiers.AddNodesResult_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.AddNodesResult_Encoding_DefaultXml;

    protected final StatusCode statusCode;
    protected final NodeId addedNodeId;

    public AddNodesResult() {
        this.statusCode = null;
        this.addedNodeId = null;
    }

    public AddNodesResult(StatusCode statusCode, NodeId addedNodeId) {
        this.statusCode = statusCode;
        this.addedNodeId = addedNodeId;
    }

    public StatusCode getStatusCode() { return statusCode; }

    public NodeId getAddedNodeId() { return addedNodeId; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("StatusCode", statusCode)
            .add("AddedNodeId", addedNodeId)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<AddNodesResult> {

        @Override
        public Class<AddNodesResult> getType() {
            return AddNodesResult.class;
        }

        @Override
        public AddNodesResult decode(UaDecoder decoder) throws UaSerializationException {
            StatusCode statusCode = decoder.readStatusCode("StatusCode");
            NodeId addedNodeId = decoder.readNodeId("AddedNodeId");

            return new AddNodesResult(statusCode, addedNodeId);
        }

        @Override
        public void encode(AddNodesResult value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeStatusCode("StatusCode", value.statusCode);
            encoder.writeNodeId("AddedNodeId", value.addedNodeId);
        }
    }

}
