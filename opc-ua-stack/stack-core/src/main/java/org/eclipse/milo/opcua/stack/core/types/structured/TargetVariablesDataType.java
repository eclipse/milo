/*
 * Copyright (c) 2021 the Eclipse Milo Authors
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

@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class TargetVariablesDataType extends SubscribedDataSetDataType implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15631");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15712");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=16011");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=16310");

    private final FieldTargetDataType[] targetVariables;

    public TargetVariablesDataType(FieldTargetDataType[] targetVariables) {
        this.targetVariables = targetVariables;
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

    @Override
    public ExpandedNodeId getJsonEncodingId() {
        return JSON_ENCODING_ID;
    }

    public FieldTargetDataType[] getTargetVariables() {
        return targetVariables;
    }

    public static final class Codec extends GenericDataTypeCodec<TargetVariablesDataType> {
        @Override
        public Class<TargetVariablesDataType> getType() {
            return TargetVariablesDataType.class;
        }

        @Override
        public TargetVariablesDataType decode(SerializationContext context, UaDecoder decoder) {
            FieldTargetDataType[] targetVariables = (FieldTargetDataType[]) decoder.readStructArray("TargetVariables", FieldTargetDataType.TYPE_ID);
            return new TargetVariablesDataType(targetVariables);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           TargetVariablesDataType value) {
            encoder.writeStructArray("TargetVariables", value.getTargetVariables(), FieldTargetDataType.TYPE_ID);
        }
    }
}
