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
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("NotificationData")
public class NotificationData implements UaStructure {

    public static final NodeId TypeId = Identifiers.NotificationData;
    public static final NodeId BinaryEncodingId = Identifiers.NotificationData_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.NotificationData_Encoding_DefaultXml;


    public NotificationData() {
    }


    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(NotificationData notificationData, UaEncoder encoder) {
    }

    public static NotificationData decode(UaDecoder decoder) {

        return new NotificationData();
    }

    static {
        DelegateRegistry.registerEncoder(NotificationData::encode, NotificationData.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(NotificationData::decode, NotificationData.class, BinaryEncodingId, XmlEncodingId);
    }

}
