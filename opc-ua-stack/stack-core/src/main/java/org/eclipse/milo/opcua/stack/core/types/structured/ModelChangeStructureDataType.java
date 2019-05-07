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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class ModelChangeStructureDataType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=877");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=878");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=879");

    private final NodeId affected;

    private final NodeId affectedType;

    private final UByte verb;

    public ModelChangeStructureDataType(NodeId affected, NodeId affectedType, UByte verb) {
        this.affected = affected;
        this.affectedType = affectedType;
        this.verb = verb;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    public NodeId getAffected() {
        return affected;
    }

    public NodeId getAffectedType() {
        return affectedType;
    }

    public UByte getVerb() {
        return verb;
    }

    public static final class Codec extends GenericDataTypeCodec<ModelChangeStructureDataType> {
        @Override
        public Class<ModelChangeStructureDataType> getType() {
            return ModelChangeStructureDataType.class;
        }

        @Override
        public ModelChangeStructureDataType decode(SerializationContext context, UaDecoder decoder) {
            NodeId affected = decoder.readNodeId("Affected");
            NodeId affectedType = decoder.readNodeId("AffectedType");
            UByte verb = decoder.readByte("Verb");
            return new ModelChangeStructureDataType(affected, affectedType, verb);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           ModelChangeStructureDataType value) {
            encoder.writeNodeId("Affected", value.getAffected());
            encoder.writeNodeId("AffectedType", value.getAffectedType());
            encoder.writeByte("Verb", value.getVerb());
        }
    }
}
