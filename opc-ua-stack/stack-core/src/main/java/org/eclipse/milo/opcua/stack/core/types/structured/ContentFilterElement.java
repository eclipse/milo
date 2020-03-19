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
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.enumerated.FilterOperator;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class ContentFilterElement extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=583");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=584");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=585");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15204");

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
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    public FilterOperator getFilterOperator() {
        return filterOperator;
    }

    public ExtensionObject[] getFilterOperands() {
        return filterOperands;
    }

    public static final class Codec extends GenericDataTypeCodec<ContentFilterElement> {
        @Override
        public Class<ContentFilterElement> getType() {
            return ContentFilterElement.class;
        }

        @Override
        public ContentFilterElement decode(SerializationContext context, UaDecoder decoder) {
            FilterOperator filterOperator = decoder.readEnum("FilterOperator", FilterOperator.class);
            ExtensionObject[] filterOperands = decoder.readExtensionObjectArray("FilterOperands");
            return new ContentFilterElement(filterOperator, filterOperands);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           ContentFilterElement value) {
            encoder.writeEnum("FilterOperator", value.getFilterOperator());
            encoder.writeExtensionObjectArray("FilterOperands", value.getFilterOperands());
        }
    }
}
