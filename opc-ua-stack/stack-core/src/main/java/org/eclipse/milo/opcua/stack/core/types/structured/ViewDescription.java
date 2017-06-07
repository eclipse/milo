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
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("ViewDescription")
public class ViewDescription implements UaStructure {

    public static final NodeId TypeId = Identifiers.ViewDescription;
    public static final NodeId BinaryEncodingId = Identifiers.ViewDescription_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ViewDescription_Encoding_DefaultXml;

    protected final NodeId _viewId;
    protected final DateTime _timestamp;
    protected final UInteger _viewVersion;

    public ViewDescription() {
        this._viewId = null;
        this._timestamp = null;
        this._viewVersion = null;
    }

    public ViewDescription(NodeId _viewId, DateTime _timestamp, UInteger _viewVersion) {
        this._viewId = _viewId;
        this._timestamp = _timestamp;
        this._viewVersion = _viewVersion;
    }

    public NodeId getViewId() { return _viewId; }

    public DateTime getTimestamp() { return _timestamp; }

    public UInteger getViewVersion() { return _viewVersion; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ViewId", _viewId)
            .add("Timestamp", _timestamp)
            .add("ViewVersion", _viewVersion)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<ViewDescription> {
        @Override
        public ViewDescription decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            NodeId _viewId = reader.readNodeId();
            DateTime _timestamp = reader.readDateTime();
            UInteger _viewVersion = reader.readUInt32();

            return new ViewDescription(_viewId, _timestamp, _viewVersion);
        }

        @Override
        public void encode(SerializationContext context, ViewDescription value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId(value._viewId);
            writer.writeDateTime(value._timestamp);
            writer.writeUInt32(value._viewVersion);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<ViewDescription> {
        @Override
        public ViewDescription decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            NodeId _viewId = reader.readNodeId("ViewId");
            DateTime _timestamp = reader.readDateTime("Timestamp");
            UInteger _viewVersion = reader.readUInt32("ViewVersion");

            return new ViewDescription(_viewId, _timestamp, _viewVersion);
        }

        @Override
        public void encode(SerializationContext context, ViewDescription encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId("ViewId", encodable._viewId);
            writer.writeDateTime("Timestamp", encodable._timestamp);
            writer.writeUInt32("ViewVersion", encodable._viewVersion);
        }
    }

}
