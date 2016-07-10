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

@UaDataType("MonitoringFilterResult")
public class MonitoringFilterResult implements UaStructure {

    public static final NodeId TypeId = Identifiers.MonitoringFilterResult;
    public static final NodeId BinaryEncodingId = Identifiers.MonitoringFilterResult_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.MonitoringFilterResult_Encoding_DefaultXml;


    public MonitoringFilterResult() {
    }


    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(MonitoringFilterResult monitoringFilterResult, UaEncoder encoder) {
    }

    public static MonitoringFilterResult decode(UaDecoder decoder) {

        return new MonitoringFilterResult();
    }

    static {
        DelegateRegistry.registerEncoder(MonitoringFilterResult::encode, MonitoringFilterResult.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(MonitoringFilterResult::decode, MonitoringFilterResult.class, BinaryEncodingId, XmlEncodingId);
    }

}
