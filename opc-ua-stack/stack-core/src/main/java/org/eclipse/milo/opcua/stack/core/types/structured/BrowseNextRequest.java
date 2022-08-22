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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/5.8.3/#5.8.3.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/5.8.3/#5.8.3.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class BrowseNextRequest extends Structure implements UaRequestMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=531");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=533");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=532");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15186");

    private final RequestHeader requestHeader;

    private final Boolean releaseContinuationPoints;

    private final ByteString[] continuationPoints;

    public BrowseNextRequest(RequestHeader requestHeader, Boolean releaseContinuationPoints,
                             ByteString[] continuationPoints) {
        this.requestHeader = requestHeader;
        this.releaseContinuationPoints = releaseContinuationPoints;
        this.continuationPoints = continuationPoints;
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

    public Boolean getReleaseContinuationPoints() {
        return releaseContinuationPoints;
    }

    public ByteString[] getContinuationPoints() {
        return continuationPoints;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 533),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("RequestHeader", LocalizedText.NULL_VALUE, new NodeId(0, 389), -1, null, UInteger.valueOf(0), false),
                new StructureField("ReleaseContinuationPoints", LocalizedText.NULL_VALUE, new NodeId(0, 1), -1, null, UInteger.valueOf(0), false),
                new StructureField("ContinuationPoints", LocalizedText.NULL_VALUE, new NodeId(0, 521), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<BrowseNextRequest> {
        @Override
        public Class<BrowseNextRequest> getType() {
            return BrowseNextRequest.class;
        }

        @Override
        public BrowseNextRequest decode(SerializationContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.readStruct("RequestHeader", RequestHeader.TYPE_ID);
            Boolean releaseContinuationPoints = decoder.readBoolean("ReleaseContinuationPoints");
            ByteString[] continuationPoints = decoder.readByteStringArray("ContinuationPoints");
            return new BrowseNextRequest(requestHeader, releaseContinuationPoints, continuationPoints);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, BrowseNextRequest value) {
            encoder.writeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.writeBoolean("ReleaseContinuationPoints", value.getReleaseContinuationPoints());
            encoder.writeByteStringArray("ContinuationPoints", value.getContinuationPoints());
        }
    }
}
