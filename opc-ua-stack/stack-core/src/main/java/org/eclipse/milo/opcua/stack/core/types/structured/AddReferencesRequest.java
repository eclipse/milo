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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/5.7.3/#5.7.3.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/5.7.3/#5.7.3.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class AddReferencesRequest extends Structure implements UaRequestMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=492");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=494");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=493");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15170");

    private final RequestHeader requestHeader;

    private final AddReferencesItem[] referencesToAdd;

    public AddReferencesRequest(RequestHeader requestHeader, AddReferencesItem[] referencesToAdd) {
        this.requestHeader = requestHeader;
        this.referencesToAdd = referencesToAdd;
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

    public AddReferencesItem[] getReferencesToAdd() {
        return referencesToAdd;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 494),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("RequestHeader", LocalizedText.NULL_VALUE, new NodeId(0, 389), -1, null, UInteger.valueOf(0), false),
                new StructureField("ReferencesToAdd", LocalizedText.NULL_VALUE, new NodeId(0, 379), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<AddReferencesRequest> {
        @Override
        public Class<AddReferencesRequest> getType() {
            return AddReferencesRequest.class;
        }

        @Override
        public AddReferencesRequest decode(SerializationContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.readStruct("RequestHeader", RequestHeader.TYPE_ID);
            AddReferencesItem[] referencesToAdd = (AddReferencesItem[]) decoder.readStructArray("ReferencesToAdd", AddReferencesItem.TYPE_ID);
            return new AddReferencesRequest(requestHeader, referencesToAdd);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           AddReferencesRequest value) {
            encoder.writeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.writeStructArray("ReferencesToAdd", value.getReferencesToAdd(), AddReferencesItem.TYPE_ID);
        }
    }
}
