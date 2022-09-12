package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessageType;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/5.5.2/#5.5.2.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/5.5.2/#5.5.2.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class OpenSecureChannelResponse extends Structure implements UaResponseMessageType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=447");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=449");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=448");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15133");

    private final ResponseHeader responseHeader;

    private final UInteger serverProtocolVersion;

    private final ChannelSecurityToken securityToken;

    private final ByteString serverNonce;

    public OpenSecureChannelResponse(ResponseHeader responseHeader, UInteger serverProtocolVersion,
                                     ChannelSecurityToken securityToken, ByteString serverNonce) {
        this.responseHeader = responseHeader;
        this.serverProtocolVersion = serverProtocolVersion;
        this.securityToken = securityToken;
        this.serverNonce = serverNonce;
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

    public UInteger getServerProtocolVersion() {
        return serverProtocolVersion;
    }

    public ChannelSecurityToken getSecurityToken() {
        return securityToken;
    }

    public ByteString getServerNonce() {
        return serverNonce;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 449),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("ResponseHeader", LocalizedText.NULL_VALUE, new NodeId(0, 392), -1, null, UInteger.valueOf(0), false),
                new StructureField("ServerProtocolVersion", LocalizedText.NULL_VALUE, new NodeId(0, 7), -1, null, UInteger.valueOf(0), false),
                new StructureField("SecurityToken", LocalizedText.NULL_VALUE, new NodeId(0, 441), -1, null, UInteger.valueOf(0), false),
                new StructureField("ServerNonce", LocalizedText.NULL_VALUE, new NodeId(0, 15), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<OpenSecureChannelResponse> {
        @Override
        public Class<OpenSecureChannelResponse> getType() {
            return OpenSecureChannelResponse.class;
        }

        @Override
        public OpenSecureChannelResponse decodeType(SerializationContext context, UaDecoder decoder) {
            ResponseHeader responseHeader = (ResponseHeader) decoder.readStruct("ResponseHeader", ResponseHeader.TYPE_ID);
            UInteger serverProtocolVersion = decoder.readUInt32("ServerProtocolVersion");
            ChannelSecurityToken securityToken = (ChannelSecurityToken) decoder.readStruct("SecurityToken", ChannelSecurityToken.TYPE_ID);
            ByteString serverNonce = decoder.readByteString("ServerNonce");
            return new OpenSecureChannelResponse(responseHeader, serverProtocolVersion, securityToken, serverNonce);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               OpenSecureChannelResponse value) {
            encoder.writeStruct("ResponseHeader", value.getResponseHeader(), ResponseHeader.TYPE_ID);
            encoder.writeUInt32("ServerProtocolVersion", value.getServerProtocolVersion());
            encoder.writeStruct("SecurityToken", value.getSecurityToken(), ChannelSecurityToken.TYPE_ID);
            encoder.writeByteString("ServerNonce", value.getServerNonce());
        }
    }
}
