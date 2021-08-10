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
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class AliasNameDataType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=23468");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=23499");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=23505");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=23511");

    private final QualifiedName aliasName;

    private final ExpandedNodeId[] referencedNodes;

    public AliasNameDataType(QualifiedName aliasName, ExpandedNodeId[] referencedNodes) {
        this.aliasName = aliasName;
        this.referencedNodes = referencedNodes;
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

    public QualifiedName getAliasName() {
        return aliasName;
    }

    public ExpandedNodeId[] getReferencedNodes() {
        return referencedNodes;
    }

    public static final class Codec extends GenericDataTypeCodec<AliasNameDataType> {
        @Override
        public Class<AliasNameDataType> getType() {
            return AliasNameDataType.class;
        }

        @Override
        public AliasNameDataType decode(SerializationContext context, UaDecoder decoder) {
            QualifiedName aliasName = decoder.readQualifiedName("AliasName");
            ExpandedNodeId[] referencedNodes = decoder.readExpandedNodeIdArray("ReferencedNodes");
            return new AliasNameDataType(aliasName, referencedNodes);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, AliasNameDataType value) {
            encoder.writeQualifiedName("AliasName", value.getAliasName());
            encoder.writeExpandedNodeIdArray("ReferencedNodes", value.getReferencedNodes());
        }
    }
}
