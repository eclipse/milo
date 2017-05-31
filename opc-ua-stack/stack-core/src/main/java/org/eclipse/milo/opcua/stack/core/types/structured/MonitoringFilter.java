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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("MonitoringFilter")
public class MonitoringFilter implements UaStructure {

    public static final NodeId TypeId = Identifiers.MonitoringFilter;
    public static final NodeId BinaryEncodingId = Identifiers.MonitoringFilter_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.MonitoringFilter_Encoding_DefaultXml;


    public MonitoringFilter() {
    }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<MonitoringFilter> {
        @Override
        public MonitoringFilter decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {

            return new MonitoringFilter();
        }

        @Override
        public void encode(SerializationContext context, MonitoringFilter encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<MonitoringFilter> {
        @Override
        public MonitoringFilter decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {

            return new MonitoringFilter();
        }

        @Override
        public void encode(SerializationContext context, MonitoringFilter encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
        }
    }

}
