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

public class UpdateStructureDataDetails extends HistoryUpdateDetails {

    public static final NodeId TypeId = Identifiers.UpdateStructureDataDetails;
    public static final NodeId BinaryEncodingId = Identifiers.UpdateStructureDataDetails_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.UpdateStructureDataDetails_Encoding_DefaultXml;

    protected final PerformUpdateType performInsertReplace;
    protected final DataValue[] updateValues;

    public UpdateStructureDataDetails() {
        super(null);
        this.performInsertReplace = null;
        this.updateValues = null;
    }

    public UpdateStructureDataDetails(NodeId nodeId, PerformUpdateType performInsertReplace, DataValue[] updateValues) {
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

    public static class Codec extends BuiltinDataTypeCodec<UpdateStructureDataDetails> {

        @Override
        public Class<UpdateStructureDataDetails> getType() {
            return UpdateStructureDataDetails.class;
        }

        @Override
        public UpdateStructureDataDetails decode(UaDecoder decoder) throws UaSerializationException {
            NodeId nodeId = decoder.readNodeId("NodeId");
            PerformUpdateType performInsertReplace = PerformUpdateType.from(decoder.readInt32("PerformInsertReplace"));
            DataValue[] updateValues = decoder.readArray("UpdateValues", decoder::readDataValue, DataValue.class);

            return new UpdateStructureDataDetails(nodeId, performInsertReplace, updateValues);
        }

        @Override
        public void encode(UpdateStructureDataDetails value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeNodeId("NodeId", value.nodeId);
            encoder.writeInt32("PerformInsertReplace", value.performInsertReplace != null ? value.performInsertReplace.getValue() : 0);
            encoder.writeArray("UpdateValues", value.updateValues, encoder::writeDataValue);
        }
    }

}
