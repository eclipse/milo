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
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;

@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class HistoryModifiedData extends HistoryData implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=11217");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=11227");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=11219");

    private final ModificationInfo[] modificationInfos;

    public HistoryModifiedData(DataValue[] dataValues, ModificationInfo[] modificationInfos) {
        super(dataValues);
        this.modificationInfos = modificationInfos;
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

    public ModificationInfo[] getModificationInfos() {
        return modificationInfos;
    }

    public static final class Codec extends GenericDataTypeCodec<HistoryModifiedData> {
        @Override
        public Class<HistoryModifiedData> getType() {
            return HistoryModifiedData.class;
        }

        @Override
        public HistoryModifiedData decode(SerializationContext context, UaDecoder decoder) {
            DataValue[] dataValues = decoder.readDataValueArray("DataValues");
            ModificationInfo[] modificationInfos = (ModificationInfo[]) decoder.readStructArray("ModificationInfos", ModificationInfo.TYPE_ID);
            return new HistoryModifiedData(dataValues, modificationInfos);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, HistoryModifiedData value) {
            encoder.writeDataValueArray("DataValues", value.getDataValues());
            encoder.writeStructArray("ModificationInfos", value.getModificationInfos(), ModificationInfo.TYPE_ID);
        }
    }
}
