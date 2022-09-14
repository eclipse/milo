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
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/7.31">https://reference.opcfoundation.org/v105/Core/docs/Part4/7.31</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class RelativePathElement extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=537");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=539");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=538");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15188");

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

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 539),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("ReferenceTypeId", LocalizedText.NULL_VALUE, new NodeId(0, 17), -1, null, UInteger.valueOf(0), false),
                new StructureField("IsInverse", LocalizedText.NULL_VALUE, new NodeId(0, 1), -1, null, UInteger.valueOf(0), false),
                new StructureField("IncludeSubtypes", LocalizedText.NULL_VALUE, new NodeId(0, 1), -1, null, UInteger.valueOf(0), false),
                new StructureField("TargetName", LocalizedText.NULL_VALUE, new NodeId(0, 20), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<RelativePathElement> {
        @Override
        public Class<RelativePathElement> getType() {
            return RelativePathElement.class;
        }

        @Override
        public RelativePathElement decodeType(SerializationContext context, UaDecoder decoder) {
            NodeId referenceTypeId = decoder.decodeNodeId("ReferenceTypeId");
            Boolean isInverse = decoder.decodeBoolean("IsInverse");
            Boolean includeSubtypes = decoder.decodeBoolean("IncludeSubtypes");
            QualifiedName targetName = decoder.decodeQualifiedName("TargetName");
            return new RelativePathElement(referenceTypeId, isInverse, includeSubtypes, targetName);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               RelativePathElement value) {
            encoder.encodeNodeId("ReferenceTypeId", value.getReferenceTypeId());
            encoder.encodeBoolean("IsInverse", value.getIsInverse());
            encoder.encodeBoolean("IncludeSubtypes", value.getIncludeSubtypes());
            encoder.encodeQualifiedName("TargetName", value.getTargetName());
        }
    }
}
