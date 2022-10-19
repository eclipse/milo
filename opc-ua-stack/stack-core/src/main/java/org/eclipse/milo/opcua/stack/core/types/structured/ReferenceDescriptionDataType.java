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

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part23/5.5.1">https://reference.opcfoundation.org/v105/Core/docs/Part23/5.5.1</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class ReferenceDescriptionDataType extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=32659");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=32661");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=32669");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=32677");

    private final NodeId sourceNode;

    private final NodeId referenceType;

    private final Boolean isForward;

    private final ExpandedNodeId targetNode;

    public ReferenceDescriptionDataType(NodeId sourceNode, NodeId referenceType, Boolean isForward,
                                        ExpandedNodeId targetNode) {
        this.sourceNode = sourceNode;
        this.referenceType = referenceType;
        this.isForward = isForward;
        this.targetNode = targetNode;
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

    public NodeId getSourceNode() {
        return sourceNode;
    }

    public NodeId getReferenceType() {
        return referenceType;
    }

    public Boolean getIsForward() {
        return isForward;
    }

    public ExpandedNodeId getTargetNode() {
        return targetNode;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 32661),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("SourceNode", LocalizedText.NULL_VALUE, new NodeId(0, 17), -1, null, UInteger.valueOf(0), false),
                new StructureField("ReferenceType", LocalizedText.NULL_VALUE, new NodeId(0, 17), -1, null, UInteger.valueOf(0), false),
                new StructureField("IsForward", LocalizedText.NULL_VALUE, new NodeId(0, 1), -1, null, UInteger.valueOf(0), false),
                new StructureField("TargetNode", LocalizedText.NULL_VALUE, new NodeId(0, 18), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<ReferenceDescriptionDataType> {
        @Override
        public Class<ReferenceDescriptionDataType> getType() {
            return ReferenceDescriptionDataType.class;
        }

        @Override
        public ReferenceDescriptionDataType decodeType(EncodingContext context, UaDecoder decoder) {
            NodeId sourceNode = decoder.decodeNodeId("SourceNode");
            NodeId referenceType = decoder.decodeNodeId("ReferenceType");
            Boolean isForward = decoder.decodeBoolean("IsForward");
            ExpandedNodeId targetNode = decoder.decodeExpandedNodeId("TargetNode");
            return new ReferenceDescriptionDataType(sourceNode, referenceType, isForward, targetNode);
        }

        @Override
        public void encodeType(EncodingContext context, UaEncoder encoder,
                               ReferenceDescriptionDataType value) {
            encoder.encodeNodeId("SourceNode", value.getSourceNode());
            encoder.encodeNodeId("ReferenceType", value.getReferenceType());
            encoder.encodeBoolean("IsForward", value.getIsForward());
            encoder.encodeExpandedNodeId("TargetNode", value.getTargetNode());
        }
    }
}
