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
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class RelativePathElement extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=537");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=538");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=539");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15188");

    private final NodeId referenceTypeId;

    private final Boolean isInverse;

    private final Boolean includeSubtypes;

    private final QualifiedName targetName;

    public RelativePathElement(NodeId referenceTypeId, Boolean isInverse, Boolean includeSubtypes,
                               QualifiedName targetName) {
        this.referenceTypeId = referenceTypeId;
        this.isInverse = isInverse;
        this.includeSubtypes = includeSubtypes;
        this.targetName = targetName;
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

    public NodeId getReferenceTypeId() {
        return referenceTypeId;
    }

    public Boolean getIsInverse() {
        return isInverse;
    }

    public Boolean getIncludeSubtypes() {
        return includeSubtypes;
    }

    public QualifiedName getTargetName() {
        return targetName;
    }

    public static final class Codec extends GenericDataTypeCodec<RelativePathElement> {
        @Override
        public Class<RelativePathElement> getType() {
            return RelativePathElement.class;
        }

        @Override
        public RelativePathElement decode(SerializationContext context, UaDecoder decoder) {
            NodeId referenceTypeId = decoder.readNodeId("ReferenceTypeId");
            Boolean isInverse = decoder.readBoolean("IsInverse");
            Boolean includeSubtypes = decoder.readBoolean("IncludeSubtypes");
            QualifiedName targetName = decoder.readQualifiedName("TargetName");
            return new RelativePathElement(referenceTypeId, isInverse, includeSubtypes, targetName);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, RelativePathElement value) {
            encoder.writeNodeId("ReferenceTypeId", value.getReferenceTypeId());
            encoder.writeBoolean("IsInverse", value.getIsInverse());
            encoder.writeBoolean("IncludeSubtypes", value.getIncludeSubtypes());
            encoder.writeQualifiedName("TargetName", value.getTargetName());
        }
    }
}
