package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/5.8.5/#5.8.5.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/5.8.5/#5.8.5.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class RegisterNodesResponse extends Structure implements UaResponseMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=561");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=563");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=562");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15196");

    private final ResponseHeader responseHeader;

    private final NodeId[] registeredNodeIds;

    public RegisterNodesResponse(ResponseHeader responseHeader, NodeId[] registeredNodeIds) {
        this.responseHeader = responseHeader;
        this.registeredNodeIds = registeredNodeIds;
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

    public ResponseHeader getResponseHeader() {
        return responseHeader;
    }

    public NodeId[] getRegisteredNodeIds() {
        return registeredNodeIds;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 563),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("ResponseHeader", LocalizedText.NULL_VALUE, new NodeId(0, 392), -1, null, UInteger.valueOf(0), false),
                new StructureField("RegisteredNodeIds", LocalizedText.NULL_VALUE, new NodeId(0, 17), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<RegisterNodesResponse> {
        @Override
        public Class<RegisterNodesResponse> getType() {
            return RegisterNodesResponse.class;
        }

        @Override
        public RegisterNodesResponse decode(SerializationContext context, UaDecoder decoder) {
            ResponseHeader responseHeader = (ResponseHeader) decoder.readStruct("ResponseHeader", ResponseHeader.TYPE_ID);
            NodeId[] registeredNodeIds = decoder.readNodeIdArray("RegisteredNodeIds");
            return new RegisterNodesResponse(responseHeader, registeredNodeIds);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           RegisterNodesResponse value) {
            encoder.writeStruct("ResponseHeader", value.getResponseHeader(), ResponseHeader.TYPE_ID);
            encoder.writeNodeIdArray("RegisteredNodeIds", value.getRegisteredNodeIds());
        }
    }
}
