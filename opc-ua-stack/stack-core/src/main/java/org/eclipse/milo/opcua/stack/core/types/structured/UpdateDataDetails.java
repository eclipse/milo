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

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.PerformUpdateType;

public class UpdateDataDetails extends HistoryUpdateDetails {

    public static final NodeId TypeId = Identifiers.UpdateDataDetails;
    public static final NodeId BinaryEncodingId = Identifiers.UpdateDataDetails_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.UpdateDataDetails_Encoding_DefaultXml;

    protected final PerformUpdateType performInsertReplace;
    protected final DataValue[] updateValues;

    public UpdateDataDetails() {
        super(null);
        this.performInsertReplace = null;
        this.updateValues = null;
    }

    public UpdateDataDetails(NodeId nodeId, PerformUpdateType performInsertReplace, DataValue[] updateValues) {
        super(nodeId);
        this.performInsertReplace = performInsertReplace;
        this.updateValues = updateValues;
    }

    public PerformUpdateType getPerformInsertReplace() { return performInsertReplace; }

    @Nullable
    public DataValue[] getUpdateValues() { return updateValues; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("NodeId", nodeId)
            .add("PerformInsertReplace", performInsertReplace)
            .add("UpdateValues", updateValues)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<UpdateDataDetails> {

        @Override
        public Class<UpdateDataDetails> getType() {
            return UpdateDataDetails.class;
        }

        @Override
        public UpdateDataDetails decode(UaDecoder decoder) throws UaSerializationException {
            NodeId nodeId = decoder.readNodeId("NodeId");
            PerformUpdateType performInsertReplace = PerformUpdateType.from(decoder.readInt32("PerformInsertReplace"));
            DataValue[] updateValues = decoder.readArray("UpdateValues", decoder::readDataValue, DataValue.class);

            return new UpdateDataDetails(nodeId, performInsertReplace, updateValues);
        }

        @Override
        public void encode(UpdateDataDetails value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeNodeId("NodeId", value.nodeId);
            encoder.writeInt32("PerformInsertReplace", value.performInsertReplace != null ? value.performInsertReplace.getValue() : 0);
            encoder.writeArray("UpdateValues", value.updateValues, encoder::writeDataValue);
        }
    }

}
