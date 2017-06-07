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
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

@UaDataType("StatusChangeNotification")
public class StatusChangeNotification extends NotificationData {

    public static final NodeId TypeId = Identifiers.StatusChangeNotification;
    public static final NodeId BinaryEncodingId = Identifiers.StatusChangeNotification_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.StatusChangeNotification_Encoding_DefaultXml;

    protected final StatusCode _status;
    protected final DiagnosticInfo _diagnosticInfo;

    public StatusChangeNotification() {
        super();
        this._status = null;
        this._diagnosticInfo = null;
    }

    public StatusChangeNotification(StatusCode _status, DiagnosticInfo _diagnosticInfo) {
        super();
        this._status = _status;
        this._diagnosticInfo = _diagnosticInfo;
    }

    public StatusCode getStatus() { return _status; }

    public DiagnosticInfo getDiagnosticInfo() { return _diagnosticInfo; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("Status", _status)
            .add("DiagnosticInfo", _diagnosticInfo)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<StatusChangeNotification> {
        @Override
        public StatusChangeNotification decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            StatusCode _status = reader.readStatusCode();
            DiagnosticInfo _diagnosticInfo = reader.readDiagnosticInfo();

            return new StatusChangeNotification(_status, _diagnosticInfo);
        }

        @Override
        public void encode(SerializationContext context, StatusChangeNotification value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeStatusCode(value._status);
            writer.writeDiagnosticInfo(value._diagnosticInfo);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<StatusChangeNotification> {
        @Override
        public StatusChangeNotification decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            StatusCode _status = reader.readStatusCode("Status");
            DiagnosticInfo _diagnosticInfo = reader.readDiagnosticInfo("DiagnosticInfo");

            return new StatusChangeNotification(_status, _diagnosticInfo);
        }

        @Override
        public void encode(SerializationContext context, StatusChangeNotification encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeStatusCode("Status", encodable._status);
            writer.writeDiagnosticInfo("DiagnosticInfo", encodable._diagnosticInfo);
        }
    }

}
