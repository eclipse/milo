/*
 * Copyright (c) 2016 Kevin Herron
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
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaDataTypeManager;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

@UaDataType("BrowsePathResult")
public class BrowsePathResult implements UaStructure {

    public static final NodeId TypeId = Identifiers.BrowsePathResult;
    public static final NodeId BinaryEncodingId = Identifiers.BrowsePathResult_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.BrowsePathResult_Encoding_DefaultXml;

    protected final StatusCode _statusCode;
    protected final BrowsePathTarget[] _targets;

    public BrowsePathResult() {
        this._statusCode = null;
        this._targets = null;
    }

    public BrowsePathResult(StatusCode _statusCode, BrowsePathTarget[] _targets) {
        this._statusCode = _statusCode;
        this._targets = _targets;
    }

    public StatusCode getStatusCode() { return _statusCode; }

    @Nullable
    public BrowsePathTarget[] getTargets() { return _targets; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("StatusCode", _statusCode)
            .add("Targets", _targets)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<BrowsePathResult> {
        @Override
        public BrowsePathResult decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            StatusCode _statusCode = reader.readStatusCode();
            BrowsePathTarget[] _targets =
                reader.readArray(
                    () -> (BrowsePathTarget) context.decode(
                        OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "BrowsePathTarget", reader),
                    BrowsePathTarget.class
                );

            return new BrowsePathResult(_statusCode, _targets);
        }

        @Override
        public void encode(SerializationContext context, BrowsePathResult encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeStatusCode(encodable._statusCode);
            writer.writeArray(
                encodable._targets,
                e -> context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "BrowsePathTarget", e, writer)
            );
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<BrowsePathResult> {
        @Override
        public BrowsePathResult decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            StatusCode _statusCode = reader.readStatusCode("StatusCode");
            BrowsePathTarget[] _targets =
                reader.readArray(
                    "Targets",
                    f -> (BrowsePathTarget) context.decode(
                        OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "BrowsePathTarget", reader),
                    BrowsePathTarget.class
                );

            return new BrowsePathResult(_statusCode, _targets);
        }

        @Override
        public void encode(SerializationContext context, BrowsePathResult encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeStatusCode("StatusCode", encodable._statusCode);
            writer.writeArray(
                "Targets",
                encodable._targets,
                (f, e) -> context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "BrowsePathTarget", e, writer)
            );
        }
    }

}
