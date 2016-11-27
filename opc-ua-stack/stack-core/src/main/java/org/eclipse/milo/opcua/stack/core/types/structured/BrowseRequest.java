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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("BrowseRequest")
public class BrowseRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.BrowseRequest;
    public static final NodeId BinaryEncodingId = Identifiers.BrowseRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.BrowseRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final ViewDescription _view;
    protected final UInteger _requestedMaxReferencesPerNode;
    protected final BrowseDescription[] _nodesToBrowse;

    public BrowseRequest() {
        this._requestHeader = null;
        this._view = null;
        this._requestedMaxReferencesPerNode = null;
        this._nodesToBrowse = null;
    }

    public BrowseRequest(RequestHeader _requestHeader, ViewDescription _view, UInteger _requestedMaxReferencesPerNode, BrowseDescription[] _nodesToBrowse) {
        this._requestHeader = _requestHeader;
        this._view = _view;
        this._requestedMaxReferencesPerNode = _requestedMaxReferencesPerNode;
        this._nodesToBrowse = _nodesToBrowse;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public ViewDescription getView() { return _view; }

    public UInteger getRequestedMaxReferencesPerNode() { return _requestedMaxReferencesPerNode; }

    @Nullable
    public BrowseDescription[] getNodesToBrowse() { return _nodesToBrowse; }

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
            .add("View", _view)
            .add("RequestedMaxReferencesPerNode", _requestedMaxReferencesPerNode)
            .add("NodesToBrowse", _nodesToBrowse)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<BrowseRequest> {
        @Override
        public BrowseRequest decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "RequestHeader", reader);
            ViewDescription _view = (ViewDescription) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "ViewDescription", reader);
            UInteger _requestedMaxReferencesPerNode = reader.readUInt32();
            BrowseDescription[] _nodesToBrowse =
                reader.readArray(
                    () -> (BrowseDescription) context.decode(
                        OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "BrowseDescription", reader),
                    BrowseDescription.class
                );

            return new BrowseRequest(_requestHeader, _view, _requestedMaxReferencesPerNode, _nodesToBrowse);
        }

        @Override
        public void encode(SerializationContext context, BrowseRequest encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "RequestHeader", encodable._requestHeader, writer);
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "ViewDescription", encodable._view, writer);
            writer.writeUInt32(encodable._requestedMaxReferencesPerNode);
            writer.writeArray(
                encodable._nodesToBrowse,
                e -> context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "BrowseDescription", e, writer)
            );
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<BrowseRequest> {
        @Override
        public BrowseRequest decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "RequestHeader", reader);
            ViewDescription _view = (ViewDescription) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "ViewDescription", reader);
            UInteger _requestedMaxReferencesPerNode = reader.readUInt32("RequestedMaxReferencesPerNode");
            BrowseDescription[] _nodesToBrowse =
                reader.readArray(
                    "NodesToBrowse",
                    f -> (BrowseDescription) context.decode(
                        OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "BrowseDescription", reader),
                    BrowseDescription.class
                );

            return new BrowseRequest(_requestHeader, _view, _requestedMaxReferencesPerNode, _nodesToBrowse);
        }

        @Override
        public void encode(SerializationContext context, BrowseRequest encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "RequestHeader", encodable._requestHeader, writer);
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "ViewDescription", encodable._view, writer);
            writer.writeUInt32("RequestedMaxReferencesPerNode", encodable._requestedMaxReferencesPerNode);
            writer.writeArray(
                "NodesToBrowse",
                encodable._nodesToBrowse,
                (f, e) -> context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "BrowseDescription", e, writer)
            );
        }
    }

}
