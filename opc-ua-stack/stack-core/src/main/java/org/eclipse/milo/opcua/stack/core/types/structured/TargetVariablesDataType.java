/*
 * Copyright (c) 2022 the Eclipse Milo Authors
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
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.types.UaStructuredType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.10/#6.2.10.2.2">https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.10/#6.2.10.2.2</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class TargetVariablesDataType extends SubscribedDataSetDataType implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=15631");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=15712");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=16011");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=16310");

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

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 15712),
            new NodeId(0, 15630),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("TargetVariables", LocalizedText.NULL_VALUE, new NodeId(0, 14744), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<TargetVariablesDataType> {
        @Override
        public Class<TargetVariablesDataType> getType() {
            return TargetVariablesDataType.class;
        }

        @Override
        public TargetVariablesDataType decodeType(SerializationContext context, UaDecoder decoder) {
            FieldTargetDataType[] targetVariables = (FieldTargetDataType[]) decoder.decodeStructArray("TargetVariables", FieldTargetDataType.TYPE_ID);
            return new TargetVariablesDataType(targetVariables);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               TargetVariablesDataType value) {
            encoder.encodeStructArray("TargetVariables", value.getTargetVariables(), FieldTargetDataType.TYPE_ID);
        }
    }
}
