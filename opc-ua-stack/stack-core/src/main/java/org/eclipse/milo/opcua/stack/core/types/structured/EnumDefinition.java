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

@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class EnumDefinition extends DataTypeDefinition implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=100");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=123");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=14799");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15067");

    private final EnumField[] fields;

    public EnumDefinition(EnumField[] fields) {
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

    public EnumField[] getFields() {
        return fields;
    }

    public static final class Codec extends GenericDataTypeCodec<EnumDefinition> {
        @Override
        public Class<EnumDefinition> getType() {
            return EnumDefinition.class;
        }

        @Override
        public EnumDefinition decode(SerializationContext context, UaDecoder decoder) {
            EnumField[] fields = (EnumField[]) decoder.readStructArray("Fields", EnumField.TYPE_ID);
            return new EnumDefinition(fields);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, EnumDefinition value) {
            encoder.writeStructArray("Fields", value.getFields(), EnumField.TYPE_ID);
        }
    }
}
