package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessageType;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/5.7.5/#5.7.5.1">https://reference.opcfoundation.org/v105/Core/docs/Part4/5.7.5/#5.7.5.1</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class DeleteReferencesRequest extends Structure implements UaRequestMessageType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=504");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=506");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=505");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15176");

    private final RequestHeader requestHeader;

    private final DeleteReferencesItem[] referencesToDelete;

    public DeleteReferencesRequest(RequestHeader requestHeader,
                                   DeleteReferencesItem[] referencesToDelete) {
        this.requestHeader = requestHeader;
        this.referencesToDelete = referencesToDelete;
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

    public DeleteReferencesItem[] getReferencesToDelete() {
        return referencesToDelete;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 506),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("RequestHeader", LocalizedText.NULL_VALUE, new NodeId(0, 389), -1, null, UInteger.valueOf(0), false),
                new StructureField("ReferencesToDelete", LocalizedText.NULL_VALUE, new NodeId(0, 385), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<DeleteReferencesRequest> {
        @Override
        public Class<DeleteReferencesRequest> getType() {
            return DeleteReferencesRequest.class;
        }

        @Override
        public DeleteReferencesRequest decodeType(SerializationContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.readStruct("RequestHeader", RequestHeader.TYPE_ID);
            DeleteReferencesItem[] referencesToDelete = (DeleteReferencesItem[]) decoder.readStructArray("ReferencesToDelete", DeleteReferencesItem.TYPE_ID);
            return new DeleteReferencesRequest(requestHeader, referencesToDelete);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               DeleteReferencesRequest value) {
            encoder.writeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.writeStructArray("ReferencesToDelete", value.getReferencesToDelete(), DeleteReferencesItem.TYPE_ID);
        }
    }
}
