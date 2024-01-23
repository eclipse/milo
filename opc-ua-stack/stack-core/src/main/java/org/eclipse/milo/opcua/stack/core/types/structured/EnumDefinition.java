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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/12.2.12/#12.2.12.4">https://reference.opcfoundation.org/v105/Core/docs/Part5/12.2.12/#12.2.12.4</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
public class EnumDefinition extends DataTypeDefinition implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=100");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=123");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=14799");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15067");

    private final EnumField @Nullable [] fields;

    public EnumDefinition(EnumField @Nullable [] fields) {
        this.fields = fields;
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

    public EnumField @Nullable [] getFields() {
        return fields;
    }

    @Override
    public String toString() {
        var joiner = new StringJoiner(", ", EnumDefinition.class.getSimpleName() + "[", "]");
        joiner.add("fields=" + java.util.Arrays.toString(getFields()));
        return joiner.toString();
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 123),
            new NodeId(0, 97),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("Fields", LocalizedText.NULL_VALUE, new NodeId(0, 102), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<EnumDefinition> {
        @Override
        public Class<EnumDefinition> getType() {
            return EnumDefinition.class;
        }

        @Override
        public EnumDefinition decodeType(EncodingContext context, UaDecoder decoder) {
            EnumField[] fields = (EnumField[]) decoder.decodeStructArray("Fields", EnumField.TYPE_ID);
            return new EnumDefinition(fields);
        }

        @Override
        public void encodeType(EncodingContext context, UaEncoder encoder, EnumDefinition value) {
            encoder.encodeStructArray("Fields", value.getFields(), EnumField.TYPE_ID);
        }
    }
}
