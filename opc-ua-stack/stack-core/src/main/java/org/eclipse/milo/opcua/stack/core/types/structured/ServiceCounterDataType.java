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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class ServiceCounterDataType implements UaStructure {

    public static final NodeId TypeId = Identifiers.ServiceCounterDataType;
    public static final NodeId BinaryEncodingId = Identifiers.ServiceCounterDataType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ServiceCounterDataType_Encoding_DefaultXml;

    protected final UInteger totalCount;
    protected final UInteger errorCount;

    public ServiceCounterDataType() {
        this.totalCount = null;
        this.errorCount = null;
    }

    public ServiceCounterDataType(UInteger totalCount, UInteger errorCount) {
        this.totalCount = totalCount;
        this.errorCount = errorCount;
    }

    public UInteger getTotalCount() { return totalCount; }

    public UInteger getErrorCount() { return errorCount; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("TotalCount", totalCount)
            .add("ErrorCount", errorCount)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<ServiceCounterDataType> {

        @Override
        public Class<ServiceCounterDataType> getType() {
            return ServiceCounterDataType.class;
        }

        @Override
        public ServiceCounterDataType decode(UaDecoder decoder) throws UaSerializationException {
            UInteger totalCount = decoder.readUInt32("TotalCount");
            UInteger errorCount = decoder.readUInt32("ErrorCount");

            return new ServiceCounterDataType(totalCount, errorCount);
        }

        @Override
        public void encode(ServiceCounterDataType value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeUInt32("TotalCount", value.totalCount);
            encoder.writeUInt32("ErrorCount", value.errorCount);
        }
    }

}
