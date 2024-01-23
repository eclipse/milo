/*
 * Copyright (c) 2024 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.util.StringJoiner;

import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.encoding.EncodingContext;
import org.eclipse.milo.opcua.stack.core.encoding.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.encoding.UaDecoder;
import org.eclipse.milo.opcua.stack.core.encoding.UaEncoder;
import org.eclipse.milo.opcua.stack.core.types.UaStructuredType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;
import org.jetbrains.annotations.Nullable;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/7.7.2">https://reference.opcfoundation.org/v105/Core/docs/Part4/7.7.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
public class ContentFilterResult extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=607");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=609");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=608");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15228");

    private final ContentFilterElementResult @Nullable [] elementResults;

    private final DiagnosticInfo @Nullable [] elementDiagnosticInfos;

    public ContentFilterResult(ContentFilterElementResult @Nullable [] elementResults,
                               DiagnosticInfo @Nullable [] elementDiagnosticInfos) {
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

    public ContentFilterElementResult @Nullable [] getElementResults() {
        return elementResults;
    }

    public DiagnosticInfo @Nullable [] getElementDiagnosticInfos() {
        return elementDiagnosticInfos;
    }

    @Override
    public String toString() {
        var joiner = new StringJoiner(", ", ContentFilterResult.class.getSimpleName() + "[", "]");
        joiner.add("elementResults=" + java.util.Arrays.toString(getElementResults()));
        joiner.add("elementDiagnosticInfos=" + java.util.Arrays.toString(getElementDiagnosticInfos()));
        return joiner.toString();
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
        public ContentFilterResult decodeType(EncodingContext context, UaDecoder decoder) {
            ContentFilterElementResult[] elementResults = (ContentFilterElementResult[]) decoder.decodeStructArray("ElementResults", ContentFilterElementResult.TYPE_ID);
            DiagnosticInfo[] elementDiagnosticInfos = decoder.decodeDiagnosticInfoArray("ElementDiagnosticInfos");
            return new ContentFilterResult(elementResults, elementDiagnosticInfos);
        }

        @Override
        public void encodeType(EncodingContext context, UaEncoder encoder, ContentFilterResult value) {
            encoder.encodeStructArray("ElementResults", value.getElementResults(), ContentFilterElementResult.TYPE_ID);
            encoder.encodeDiagnosticInfoArray("ElementDiagnosticInfos", value.getElementDiagnosticInfos());
        }
    }
}
