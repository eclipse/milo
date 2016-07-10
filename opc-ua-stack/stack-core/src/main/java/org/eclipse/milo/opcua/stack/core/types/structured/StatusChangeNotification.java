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

import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.serialization.DelegateRegistry;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
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


    public static void encode(StatusChangeNotification statusChangeNotification, UaEncoder encoder) {
        encoder.encodeStatusCode("Status", statusChangeNotification._status);
        encoder.encodeDiagnosticInfo("DiagnosticInfo", statusChangeNotification._diagnosticInfo);
    }

    public static StatusChangeNotification decode(UaDecoder decoder) {
        StatusCode _status = decoder.decodeStatusCode("Status");
        DiagnosticInfo _diagnosticInfo = decoder.decodeDiagnosticInfo("DiagnosticInfo");

        return new StatusChangeNotification(_status, _diagnosticInfo);
    }

    static {
        DelegateRegistry.registerEncoder(StatusChangeNotification::encode, StatusChangeNotification.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(StatusChangeNotification::decode, StatusChangeNotification.class, BinaryEncodingId, XmlEncodingId);
    }

}
