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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/7.31">https://reference.opcfoundation.org/v105/Core/docs/Part4/7.31</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class RelativePath extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=540");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=542");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=541");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15189");

    private final RelativePathElement[] elements;

    public RelativePath(RelativePathElement[] elements) {
        this.elements = elements;
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

    public RelativePathElement[] getElements() {
        return elements;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 542),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("Elements", LocalizedText.NULL_VALUE, new NodeId(0, 537), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<RelativePath> {
        @Override
        public Class<RelativePath> getType() {
            return RelativePath.class;
        }

        @Override
        public RelativePath decodeType(SerializationContext context, UaDecoder decoder) {
            RelativePathElement[] elements = (RelativePathElement[]) decoder.readStructArray("Elements", RelativePathElement.TYPE_ID);
            return new RelativePath(elements);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder, RelativePath value) {
            encoder.writeStructArray("Elements", value.getElements(), RelativePathElement.TYPE_ID);
        }
    }
}
