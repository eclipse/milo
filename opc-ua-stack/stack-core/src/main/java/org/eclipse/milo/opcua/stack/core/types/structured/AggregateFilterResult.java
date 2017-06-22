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
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("AggregateFilterResult")
public class AggregateFilterResult extends MonitoringFilterResult {

    public static final NodeId TypeId = Identifiers.AggregateFilterResult;
    public static final NodeId BinaryEncodingId = Identifiers.AggregateFilterResult_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.AggregateFilterResult_Encoding_DefaultXml;

    protected final DateTime _revisedStartTime;
    protected final Double _revisedProcessingInterval;
    protected final AggregateConfiguration _revisedAggregateConfiguration;

    public AggregateFilterResult() {
        super();
        this._revisedStartTime = null;
        this._revisedProcessingInterval = null;
        this._revisedAggregateConfiguration = null;
    }

    public AggregateFilterResult(DateTime _revisedStartTime, Double _revisedProcessingInterval, AggregateConfiguration _revisedAggregateConfiguration) {
        super();
        this._revisedStartTime = _revisedStartTime;
        this._revisedProcessingInterval = _revisedProcessingInterval;
        this._revisedAggregateConfiguration = _revisedAggregateConfiguration;
    }

    public DateTime getRevisedStartTime() { return _revisedStartTime; }

    public Double getRevisedProcessingInterval() { return _revisedProcessingInterval; }

    public AggregateConfiguration getRevisedAggregateConfiguration() { return _revisedAggregateConfiguration; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("RevisedStartTime", _revisedStartTime)
            .add("RevisedProcessingInterval", _revisedProcessingInterval)
            .add("RevisedAggregateConfiguration", _revisedAggregateConfiguration)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<AggregateFilterResult> {
        @Override
        public AggregateFilterResult decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            DateTime _revisedStartTime = reader.readDateTime();
            Double _revisedProcessingInterval = reader.readDouble();
            AggregateConfiguration _revisedAggregateConfiguration = (AggregateConfiguration) context.decode(AggregateConfiguration.BinaryEncodingId, reader);

            return new AggregateFilterResult(_revisedStartTime, _revisedProcessingInterval, _revisedAggregateConfiguration);
        }

        @Override
        public void encode(SerializationContext context, AggregateFilterResult value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeDateTime(value._revisedStartTime);
            writer.writeDouble(value._revisedProcessingInterval);
            context.encode(AggregateConfiguration.BinaryEncodingId, value._revisedAggregateConfiguration, writer);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<AggregateFilterResult> {
        @Override
        public AggregateFilterResult decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            DateTime _revisedStartTime = reader.readDateTime("RevisedStartTime");
            Double _revisedProcessingInterval = reader.readDouble("RevisedProcessingInterval");
            AggregateConfiguration _revisedAggregateConfiguration = (AggregateConfiguration) context.decode(AggregateConfiguration.XmlEncodingId, reader);

            return new AggregateFilterResult(_revisedStartTime, _revisedProcessingInterval, _revisedAggregateConfiguration);
        }

        @Override
        public void encode(SerializationContext context, AggregateFilterResult encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeDateTime("RevisedStartTime", encodable._revisedStartTime);
            writer.writeDouble("RevisedProcessingInterval", encodable._revisedProcessingInterval);
            context.encode(AggregateConfiguration.XmlEncodingId, encodable._revisedAggregateConfiguration, writer);
        }
    }

}
