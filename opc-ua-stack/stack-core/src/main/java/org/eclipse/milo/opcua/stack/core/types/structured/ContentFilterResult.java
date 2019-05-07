/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class ContentFilterResult extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=607");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=609");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=608");

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

    public ContentFilterElementResult[] getElementResults() {
        return elementResults;
    }

    public DiagnosticInfo[] getElementDiagnosticInfos() {
        return elementDiagnosticInfos;
    }

    public static final class Codec extends GenericDataTypeCodec<ContentFilterResult> {
        @Override
        public Class<ContentFilterResult> getType() {
            return ContentFilterResult.class;
        }

        @Override
        public ContentFilterResult decode(SerializationContext context, UaDecoder decoder) {
            ContentFilterElementResult[] elementResults = (ContentFilterElementResult[]) decoder.readStructArray("ElementResults", ContentFilterElementResult.TYPE_ID);
            DiagnosticInfo[] elementDiagnosticInfos = decoder.readDiagnosticInfoArray("ElementDiagnosticInfos");
            return new ContentFilterResult(elementResults, elementDiagnosticInfos);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, ContentFilterResult value) {
            encoder.writeStructArray("ElementResults", value.getElementResults(), ContentFilterElementResult.TYPE_ID);
            encoder.writeDiagnosticInfoArray("ElementDiagnosticInfos", value.getElementDiagnosticInfos());
        }
    }
}
