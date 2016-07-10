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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("ServiceCounterDataType")
public class ServiceCounterDataType implements UaStructure {

    public static final NodeId TypeId = Identifiers.ServiceCounterDataType;
    public static final NodeId BinaryEncodingId = Identifiers.ServiceCounterDataType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ServiceCounterDataType_Encoding_DefaultXml;

    protected final UInteger _totalCount;
    protected final UInteger _errorCount;

    public ServiceCounterDataType() {
        this._totalCount = null;
        this._errorCount = null;
    }

    public ServiceCounterDataType(UInteger _totalCount, UInteger _errorCount) {
        this._totalCount = _totalCount;
        this._errorCount = _errorCount;
    }

    public UInteger getTotalCount() { return _totalCount; }

    public UInteger getErrorCount() { return _errorCount; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(ServiceCounterDataType serviceCounterDataType, UaEncoder encoder) {
        encoder.encodeUInt32("TotalCount", serviceCounterDataType._totalCount);
        encoder.encodeUInt32("ErrorCount", serviceCounterDataType._errorCount);
    }

    public static ServiceCounterDataType decode(UaDecoder decoder) {
        UInteger _totalCount = decoder.decodeUInt32("TotalCount");
        UInteger _errorCount = decoder.decodeUInt32("ErrorCount");

        return new ServiceCounterDataType(_totalCount, _errorCount);
    }

    static {
        DelegateRegistry.registerEncoder(ServiceCounterDataType::encode, ServiceCounterDataType.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(ServiceCounterDataType::decode, ServiceCounterDataType.class, BinaryEncodingId, XmlEncodingId);
    }

}
