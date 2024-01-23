/*
 * Copyright (c) 2024 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import java.util.StringJoiner;

import lombok.EqualsAndHashCode;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.encoding.EncodingContext;
import org.eclipse.milo.opcua.stack.core.encoding.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.encoding.UaDecoder;
import org.eclipse.milo.opcua.stack.core.encoding.UaEncoder;
import org.eclipse.milo.opcua.stack.core.types.UaStructuredType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;
import org.jetbrains.annotations.Nullable;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/6.4.1/#6.4.1.1.4.2">https://reference.opcfoundation.org/v105/Core/docs/Part14/6.4.1/#6.4.1.1.4.2</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
public class TransmitQosPriorityDataType extends TransmitQosDataType implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=23605");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=23857");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=23925");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=23993");

    private final @Nullable String priorityLabel;

    public TransmitQosPriorityDataType(@Nullable String priorityLabel) {
        this.priorityLabel = priorityLabel;
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

    public @Nullable String getPriorityLabel() {
        return priorityLabel;
    }

    @Override
    public String toString() {
        var joiner = new StringJoiner(", ", TransmitQosPriorityDataType.class.getSimpleName() + "[", "]");
        joiner.add("priorityLabel='" + getPriorityLabel() + "'");
        return joiner.toString();
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 23857),
            new NodeId(0, 23604),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("PriorityLabel", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<TransmitQosPriorityDataType> {
        @Override
        public Class<TransmitQosPriorityDataType> getType() {
            return TransmitQosPriorityDataType.class;
        }

        @Override
        public TransmitQosPriorityDataType decodeType(EncodingContext context, UaDecoder decoder) {
            String priorityLabel = decoder.decodeString("PriorityLabel");
            return new TransmitQosPriorityDataType(priorityLabel);
        }

        @Override
        public void encodeType(EncodingContext context, UaEncoder encoder,
                               TransmitQosPriorityDataType value) {
            encoder.encodeString("PriorityLabel", value.getPriorityLabel());
        }
    }
}
