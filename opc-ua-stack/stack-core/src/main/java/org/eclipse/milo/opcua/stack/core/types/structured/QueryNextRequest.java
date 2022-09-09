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
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/5.9.4/#5.9.4.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/5.9.4/#5.9.4.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class QueryNextRequest extends Structure implements UaRequestMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=619");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=621");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=620");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15254");

    private final RequestHeader requestHeader;

    private final Boolean releaseContinuationPoint;

    private final ByteString continuationPoint;

    public QueryNextRequest(RequestHeader requestHeader, Boolean releaseContinuationPoint,
                            ByteString continuationPoint) {
        this.requestHeader = requestHeader;
        this.releaseContinuationPoint = releaseContinuationPoint;
        this.continuationPoint = continuationPoint;
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

    public Boolean getReleaseContinuationPoint() {
        return releaseContinuationPoint;
    }

    public ByteString getContinuationPoint() {
        return continuationPoint;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 621),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("RequestHeader", LocalizedText.NULL_VALUE, new NodeId(0, 389), -1, null, UInteger.valueOf(0), false),
                new StructureField("ReleaseContinuationPoint", LocalizedText.NULL_VALUE, new NodeId(0, 1), -1, null, UInteger.valueOf(0), false),
                new StructureField("ContinuationPoint", LocalizedText.NULL_VALUE, new NodeId(0, 521), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<QueryNextRequest> {
        @Override
        public Class<QueryNextRequest> getType() {
            return QueryNextRequest.class;
        }

        @Override
        public QueryNextRequest decodeType(SerializationContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.readStruct("RequestHeader", RequestHeader.TYPE_ID);
            Boolean releaseContinuationPoint = decoder.readBoolean("ReleaseContinuationPoint");
            ByteString continuationPoint = decoder.readByteString("ContinuationPoint");
            return new QueryNextRequest(requestHeader, releaseContinuationPoint, continuationPoint);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               QueryNextRequest value) {
            encoder.writeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.writeBoolean("ReleaseContinuationPoint", value.getReleaseContinuationPoint());
            encoder.writeByteString("ContinuationPoint", value.getContinuationPoint());
        }
    }
}
