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
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;

@UaDataType("HistoryReadRequest")
public class HistoryReadRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.HistoryReadRequest;
    public static final NodeId BinaryEncodingId = Identifiers.HistoryReadRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.HistoryReadRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final ExtensionObject _historyReadDetails;
    protected final TimestampsToReturn _timestampsToReturn;
    protected final Boolean _releaseContinuationPoints;
    protected final HistoryReadValueId[] _nodesToRead;

    public HistoryReadRequest() {
        this._requestHeader = null;
        this._historyReadDetails = null;
        this._timestampsToReturn = null;
        this._releaseContinuationPoints = null;
        this._nodesToRead = null;
    }

    public HistoryReadRequest(RequestHeader _requestHeader, ExtensionObject _historyReadDetails, TimestampsToReturn _timestampsToReturn, Boolean _releaseContinuationPoints, HistoryReadValueId[] _nodesToRead) {
        this._requestHeader = _requestHeader;
        this._historyReadDetails = _historyReadDetails;
        this._timestampsToReturn = _timestampsToReturn;
        this._releaseContinuationPoints = _releaseContinuationPoints;
        this._nodesToRead = _nodesToRead;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public ExtensionObject getHistoryReadDetails() { return _historyReadDetails; }

    public TimestampsToReturn getTimestampsToReturn() { return _timestampsToReturn; }

    public Boolean getReleaseContinuationPoints() { return _releaseContinuationPoints; }

    @Nullable
    public HistoryReadValueId[] getNodesToRead() { return _nodesToRead; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("RequestHeader", _requestHeader)
            .add("HistoryReadDetails", _historyReadDetails)
            .add("TimestampsToReturn", _timestampsToReturn)
            .add("ReleaseContinuationPoints", _releaseContinuationPoints)
            .add("NodesToRead", _nodesToRead)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<HistoryReadRequest> {
        @Override
        public HistoryReadRequest decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "RequestHeader", reader);
            ExtensionObject _historyReadDetails = reader.readExtensionObject();
            TimestampsToReturn _timestampsToReturn = TimestampsToReturn.from(reader.readInt32());
            Boolean _releaseContinuationPoints = reader.readBoolean();
            HistoryReadValueId[] _nodesToRead =
                reader.readArray(
                    () -> (HistoryReadValueId) context.decode(
                        OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "HistoryReadValueId", reader),
                    HistoryReadValueId.class
                );

            return new HistoryReadRequest(_requestHeader, _historyReadDetails, _timestampsToReturn, _releaseContinuationPoints, _nodesToRead);
        }

        @Override
        public void encode(SerializationContext context, HistoryReadRequest encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "RequestHeader", encodable._requestHeader, writer);
            writer.writeExtensionObject(encodable._historyReadDetails);
            writer.writeInt32(encodable._timestampsToReturn != null ? encodable._timestampsToReturn.getValue() : 0);
            writer.writeBoolean(encodable._releaseContinuationPoints);
            writer.writeArray(
                encodable._nodesToRead,
                e -> context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "HistoryReadValueId", e, writer)
            );
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<HistoryReadRequest> {
        @Override
        public HistoryReadRequest decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "RequestHeader", reader);
            ExtensionObject _historyReadDetails = reader.readExtensionObject("HistoryReadDetails");
            TimestampsToReturn _timestampsToReturn = TimestampsToReturn.from(reader.readInt32("TimestampsToReturn"));
            Boolean _releaseContinuationPoints = reader.readBoolean("ReleaseContinuationPoints");
            HistoryReadValueId[] _nodesToRead =
                reader.readArray(
                    "NodesToRead",
                    f -> (HistoryReadValueId) context.decode(
                        OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "HistoryReadValueId", reader),
                    HistoryReadValueId.class
                );

            return new HistoryReadRequest(_requestHeader, _historyReadDetails, _timestampsToReturn, _releaseContinuationPoints, _nodesToRead);
        }

        @Override
        public void encode(SerializationContext context, HistoryReadRequest encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "RequestHeader", encodable._requestHeader, writer);
            writer.writeExtensionObject("HistoryReadDetails", encodable._historyReadDetails);
            writer.writeInt32("TimestampsToReturn", encodable._timestampsToReturn != null ? encodable._timestampsToReturn.getValue() : 0);
            writer.writeBoolean("ReleaseContinuationPoints", encodable._releaseContinuationPoints);
            writer.writeArray(
                "NodesToRead",
                encodable._nodesToRead,
                (f, e) -> context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "HistoryReadValueId", e, writer)
            );
        }
    }

}
