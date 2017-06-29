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
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class ViewDescription implements UaStructure {

    public static final NodeId TypeId = Identifiers.ViewDescription;
    public static final NodeId BinaryEncodingId = Identifiers.ViewDescription_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ViewDescription_Encoding_DefaultXml;

    protected final NodeId viewId;
    protected final DateTime timestamp;
    protected final UInteger viewVersion;

    public ViewDescription() {
        this.viewId = null;
        this.timestamp = null;
        this.viewVersion = null;
    }

    public ViewDescription(NodeId viewId, DateTime timestamp, UInteger viewVersion) {
        this.viewId = viewId;
        this.timestamp = timestamp;
        this.viewVersion = viewVersion;
    }

    public NodeId getViewId() { return viewId; }

    public DateTime getTimestamp() { return timestamp; }

    public UInteger getViewVersion() { return viewVersion; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ViewId", viewId)
            .add("Timestamp", timestamp)
            .add("ViewVersion", viewVersion)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<ViewDescription> {

        @Override
        public Class<ViewDescription> getType() {
            return ViewDescription.class;
        }

        @Override
        public ViewDescription decode(UaDecoder decoder) throws UaSerializationException {
            NodeId viewId = decoder.readNodeId("ViewId");
            DateTime timestamp = decoder.readDateTime("Timestamp");
            UInteger viewVersion = decoder.readUInt32("ViewVersion");

            return new ViewDescription(viewId, timestamp, viewVersion);
        }

        @Override
        public void encode(ViewDescription value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeNodeId("ViewId", value.viewId);
            encoder.writeDateTime("Timestamp", value.timestamp);
            encoder.writeUInt32("ViewVersion", value.viewVersion);
        }
    }

}
