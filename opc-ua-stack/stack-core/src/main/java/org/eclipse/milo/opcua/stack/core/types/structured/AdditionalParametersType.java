package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructuredType;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/7.1">https://reference.opcfoundation.org/v105/Core/docs/Part4/7.1</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class AdditionalParametersType extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=16313");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=17537");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=17541");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=17547");

    private final KeyValuePair[] parameters;

    public AdditionalParametersType(KeyValuePair[] parameters) {
        this.parameters = parameters;
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

    public KeyValuePair[] getParameters() {
        return parameters;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 17537),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("Parameters", LocalizedText.NULL_VALUE, new NodeId(0, 14533), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<AdditionalParametersType> {
        @Override
        public Class<AdditionalParametersType> getType() {
            return AdditionalParametersType.class;
        }

        @Override
        public AdditionalParametersType decodeType(SerializationContext context, UaDecoder decoder) {
            KeyValuePair[] parameters = (KeyValuePair[]) decoder.readStructArray("Parameters", KeyValuePair.TYPE_ID);
            return new AdditionalParametersType(parameters);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               AdditionalParametersType value) {
            encoder.writeStructArray("Parameters", value.getParameters(), KeyValuePair.TYPE_ID);
        }
    }
}
