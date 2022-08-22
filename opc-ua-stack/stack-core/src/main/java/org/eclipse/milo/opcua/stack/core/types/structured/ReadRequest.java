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
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/5.10.2/#5.10.2.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/5.10.2/#5.10.2.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class ReadRequest extends Structure implements UaRequestMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=629");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=631");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=630");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15257");

    private final RequestHeader requestHeader;

    private final Double maxAge;

    private final TimestampsToReturn timestampsToReturn;

    private final ReadValueId[] nodesToRead;

    public ReadRequest(RequestHeader requestHeader, Double maxAge,
                       TimestampsToReturn timestampsToReturn, ReadValueId[] nodesToRead) {
        this.requestHeader = requestHeader;
        this.maxAge = maxAge;
        this.timestampsToReturn = timestampsToReturn;
        this.nodesToRead = nodesToRead;
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

    public Double getMaxAge() {
        return maxAge;
    }

    public TimestampsToReturn getTimestampsToReturn() {
        return timestampsToReturn;
    }

    public ReadValueId[] getNodesToRead() {
        return nodesToRead;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 631),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("RequestHeader", LocalizedText.NULL_VALUE, new NodeId(0, 389), -1, null, UInteger.valueOf(0), false),
                new StructureField("MaxAge", LocalizedText.NULL_VALUE, new NodeId(0, 290), -1, null, UInteger.valueOf(0), false),
                new StructureField("TimestampsToReturn", LocalizedText.NULL_VALUE, new NodeId(0, 625), -1, null, UInteger.valueOf(0), false),
                new StructureField("NodesToRead", LocalizedText.NULL_VALUE, new NodeId(0, 626), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<ReadRequest> {
        @Override
        public Class<ReadRequest> getType() {
            return ReadRequest.class;
        }

        @Override
        public ReadRequest decode(SerializationContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.readStruct("RequestHeader", RequestHeader.TYPE_ID);
            Double maxAge = decoder.readDouble("MaxAge");
            TimestampsToReturn timestampsToReturn = (TimestampsToReturn) decoder.readEnum("TimestampsToReturn", TimestampsToReturn.class);
            ReadValueId[] nodesToRead = (ReadValueId[]) decoder.readStructArray("NodesToRead", ReadValueId.TYPE_ID);
            return new ReadRequest(requestHeader, maxAge, timestampsToReturn, nodesToRead);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, ReadRequest value) {
            encoder.writeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.writeDouble("MaxAge", value.getMaxAge());
            encoder.writeEnum("TimestampsToReturn", value.getTimestampsToReturn());
            encoder.writeStructArray("NodesToRead", value.getNodesToRead(), ReadValueId.TYPE_ID);
        }
    }
}
