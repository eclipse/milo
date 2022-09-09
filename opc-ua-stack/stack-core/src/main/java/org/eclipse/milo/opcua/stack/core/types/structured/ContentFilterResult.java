package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/7.7.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/7.7.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class ContentFilterResult extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=607");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=609");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=608");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15228");

    private final ContentFilterElementResult[] elementResults;

    private final DiagnosticInfo[] elementDiagnosticInfos;

    public ContentFilterResult(ContentFilterElementResult[] elementResults,
                               DiagnosticInfo[] elementDiagnosticInfos) {
        this.elementResults = elementResults;
        this.elementDiagnosticInfos = elementDiagnosticInfos;
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

    public ContentFilterElementResult[] getElementResults() {
        return elementResults;
    }

    public DiagnosticInfo[] getElementDiagnosticInfos() {
        return elementDiagnosticInfos;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 609),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("ElementResults", LocalizedText.NULL_VALUE, new NodeId(0, 604), 1, null, UInteger.valueOf(0), false),
                new StructureField("ElementDiagnosticInfos", LocalizedText.NULL_VALUE, new NodeId(0, 25), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<ContentFilterResult> {
        @Override
        public Class<ContentFilterResult> getType() {
            return ContentFilterResult.class;
        }

        @Override
        public ContentFilterResult decodeType(SerializationContext context, UaDecoder decoder) {
            ContentFilterElementResult[] elementResults = (ContentFilterElementResult[]) decoder.readStructArray("ElementResults", ContentFilterElementResult.TYPE_ID);
            DiagnosticInfo[] elementDiagnosticInfos = decoder.readDiagnosticInfoArray("ElementDiagnosticInfos");
            return new ContentFilterResult(elementResults, elementDiagnosticInfos);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               ContentFilterResult value) {
            encoder.writeStructArray("ElementResults", value.getElementResults(), ContentFilterElementResult.TYPE_ID);
            encoder.writeDiagnosticInfoArray("ElementDiagnosticInfos", value.getElementDiagnosticInfos());
        }
    }
}
