package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
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
public class ContentFilterElement extends Structure implements UaStructure {
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
        public ContentFilterElement decodeType(SerializationContext context, UaDecoder decoder) {
            FilterOperator filterOperator = (FilterOperator) decoder.readEnum("FilterOperator", FilterOperator.class);
            ExtensionObject[] filterOperands = decoder.readExtensionObjectArray("FilterOperands");
            return new ContentFilterElement(filterOperator, filterOperands);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               ContentFilterElement value) {
            encoder.writeEnum("FilterOperator", value.getFilterOperator());
            encoder.writeExtensionObjectArray("FilterOperands", value.getFilterOperands());
        }
    }
}
