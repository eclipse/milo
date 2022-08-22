package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/5.10.4/#5.10.4.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/5.10.4/#5.10.4.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class WriteRequest extends Structure implements UaRequestMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=671");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=673");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=672");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15277");

    private final RequestHeader requestHeader;

    private final WriteValue[] nodesToWrite;

    public WriteRequest(RequestHeader requestHeader, WriteValue[] nodesToWrite) {
        this.requestHeader = requestHeader;
        this.nodesToWrite = nodesToWrite;
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

    public RequestHeader getRequestHeader() {
        return requestHeader;
    }

    public WriteValue[] getNodesToWrite() {
        return nodesToWrite;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 673),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("RequestHeader", LocalizedText.NULL_VALUE, new NodeId(0, 389), -1, null, UInteger.valueOf(0), false),
                new StructureField("NodesToWrite", LocalizedText.NULL_VALUE, new NodeId(0, 668), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<WriteRequest> {
        @Override
        public Class<WriteRequest> getType() {
            return WriteRequest.class;
        }

        @Override
        public WriteRequest decode(SerializationContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.readStruct("RequestHeader", RequestHeader.TYPE_ID);
            WriteValue[] nodesToWrite = (WriteValue[]) decoder.readStructArray("NodesToWrite", WriteValue.TYPE_ID);
            return new WriteRequest(requestHeader, nodesToWrite);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, WriteRequest value) {
            encoder.writeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.writeStructArray("NodesToWrite", value.getNodesToWrite(), WriteValue.TYPE_ID);
        }
    }
}
