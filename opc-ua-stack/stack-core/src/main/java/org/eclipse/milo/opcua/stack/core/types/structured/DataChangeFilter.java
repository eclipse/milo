/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.DataChangeTrigger;

@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class DataChangeFilter extends MonitoringFilter implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=722");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=724");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=723");

    private final DataChangeTrigger trigger;

    private final UInteger deadbandType;

    private final Double deadbandValue;

    public DataChangeFilter(DataChangeTrigger trigger, UInteger deadbandType, Double deadbandValue) {
        this.trigger = trigger;
        this.deadbandType = deadbandType;
        this.deadbandValue = deadbandValue;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    public DataChangeTrigger getTrigger() {
        return trigger;
    }

    public UInteger getDeadbandType() {
        return deadbandType;
    }

    public Double getDeadbandValue() {
        return deadbandValue;
    }

    public static final class Codec extends GenericDataTypeCodec<DataChangeFilter> {
        @Override
        public Class<DataChangeFilter> getType() {
            return DataChangeFilter.class;
        }

        @Override
        public DataChangeFilter decode(SerializationContext context, UaDecoder decoder) {
            DataChangeTrigger trigger = decoder.readEnum("Trigger", DataChangeTrigger.class);
            UInteger deadbandType = decoder.readUInt32("DeadbandType");
            Double deadbandValue = decoder.readDouble("DeadbandValue");
            return new DataChangeFilter(trigger, deadbandType, deadbandValue);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, DataChangeFilter value) {
            encoder.writeEnum("Trigger", value.getTrigger());
            encoder.writeUInt32("DeadbandType", value.getDeadbandType());
            encoder.writeDouble("DeadbandValue", value.getDeadbandValue());
        }
    }
}
