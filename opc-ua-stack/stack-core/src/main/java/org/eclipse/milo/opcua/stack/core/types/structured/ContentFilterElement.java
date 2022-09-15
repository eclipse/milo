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
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.FilterOperator;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/7.7.1">https://reference.opcfoundation.org/v105/Core/docs/Part4/7.7.1</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class ContentFilterElement extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=583");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=585");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=584");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15204");

    private final FilterOperator filterOperator;

    private final ExtensionObject[] filterOperands;

    public ContentFilterElement(FilterOperator filterOperator, ExtensionObject[] filterOperands) {
        this.filterOperator = filterOperator;
        this.filterOperands = filterOperands;
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

    public FilterOperator getFilterOperator() {
        return filterOperator;
    }

    public ExtensionObject[] getFilterOperands() {
        return filterOperands;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 585),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("FilterOperator", LocalizedText.NULL_VALUE, new NodeId(0, 576), -1, null, UInteger.valueOf(0), false),
                new StructureField("FilterOperands", LocalizedText.NULL_VALUE, new NodeId(0, 22), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<ContentFilterElement> {
        @Override
        public Class<ContentFilterElement> getType() {
            return ContentFilterElement.class;
        }

        @Override
        public ContentFilterElement decodeType(EncodingContext context, UaDecoder decoder) {
            FilterOperator filterOperator = FilterOperator.from(decoder.decodeEnum("FilterOperator"));
            ExtensionObject[] filterOperands = decoder.decodeExtensionObjectArray("FilterOperands");
            return new ContentFilterElement(filterOperator, filterOperands);
        }

        @Override
        public void encodeType(EncodingContext context, UaEncoder encoder,
                               ContentFilterElement value) {
            encoder.encodeEnum("FilterOperator", value.getFilterOperator());
            encoder.encodeExtensionObjectArray("FilterOperands", value.getFilterOperands());
        }
    }
}
