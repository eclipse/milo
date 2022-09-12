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
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/7.33">https://reference.opcfoundation.org/v105/Core/docs/Part4/7.33</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class RequestHeader extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=389");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=391");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=390");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15088");

    private final NodeId authenticationToken;

    private final DateTime timestamp;

    private final UInteger requestHandle;

    private final UInteger returnDiagnostics;

    private final String auditEntryId;

    private final UInteger timeoutHint;

    private final ExtensionObject additionalHeader;

    public RequestHeader(NodeId authenticationToken, DateTime timestamp, UInteger requestHandle,
                         UInteger returnDiagnostics, String auditEntryId, UInteger timeoutHint,
                         ExtensionObject additionalHeader) {
        this.authenticationToken = authenticationToken;
        this.timestamp = timestamp;
        this.requestHandle = requestHandle;
        this.returnDiagnostics = returnDiagnostics;
        this.auditEntryId = auditEntryId;
        this.timeoutHint = timeoutHint;
        this.additionalHeader = additionalHeader;
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

    public NodeId getAuthenticationToken() {
        return authenticationToken;
    }

    public DateTime getTimestamp() {
        return timestamp;
    }

    public UInteger getRequestHandle() {
        return requestHandle;
    }

    public UInteger getReturnDiagnostics() {
        return returnDiagnostics;
    }

    public String getAuditEntryId() {
        return auditEntryId;
    }

    public UInteger getTimeoutHint() {
        return timeoutHint;
    }

    public ExtensionObject getAdditionalHeader() {
        return additionalHeader;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 391),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("AuthenticationToken", LocalizedText.NULL_VALUE, new NodeId(0, 388), -1, null, UInteger.valueOf(0), false),
                new StructureField("Timestamp", LocalizedText.NULL_VALUE, new NodeId(0, 294), -1, null, UInteger.valueOf(0), false),
                new StructureField("RequestHandle", LocalizedText.NULL_VALUE, new NodeId(0, 288), -1, null, UInteger.valueOf(0), false),
                new StructureField("ReturnDiagnostics", LocalizedText.NULL_VALUE, new NodeId(0, 7), -1, null, UInteger.valueOf(0), false),
                new StructureField("AuditEntryId", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("TimeoutHint", LocalizedText.NULL_VALUE, new NodeId(0, 7), -1, null, UInteger.valueOf(0), false),
                new StructureField("AdditionalHeader", LocalizedText.NULL_VALUE, new NodeId(0, 22), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<RequestHeader> {
        @Override
        public Class<RequestHeader> getType() {
            return RequestHeader.class;
        }

        @Override
        public RequestHeader decodeType(SerializationContext context, UaDecoder decoder) {
            NodeId authenticationToken = decoder.readNodeId("AuthenticationToken");
            DateTime timestamp = decoder.readDateTime("Timestamp");
            UInteger requestHandle = decoder.readUInt32("RequestHandle");
            UInteger returnDiagnostics = decoder.readUInt32("ReturnDiagnostics");
            String auditEntryId = decoder.readString("AuditEntryId");
            UInteger timeoutHint = decoder.readUInt32("TimeoutHint");
            ExtensionObject additionalHeader = decoder.readExtensionObject("AdditionalHeader");
            return new RequestHeader(authenticationToken, timestamp, requestHandle, returnDiagnostics, auditEntryId, timeoutHint, additionalHeader);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder, RequestHeader value) {
            encoder.writeNodeId("AuthenticationToken", value.getAuthenticationToken());
            encoder.writeDateTime("Timestamp", value.getTimestamp());
            encoder.writeUInt32("RequestHandle", value.getRequestHandle());
            encoder.writeUInt32("ReturnDiagnostics", value.getReturnDiagnostics());
            encoder.writeString("AuditEntryId", value.getAuditEntryId());
            encoder.writeUInt32("TimeoutHint", value.getTimeoutHint());
            encoder.writeExtensionObject("AdditionalHeader", value.getAdditionalHeader());
        }
    }
}
