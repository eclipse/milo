/*
 * Copyright (c) 2017 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class ContentFilterResult implements UaStructure {

    public static final NodeId TypeId = Identifiers.ContentFilterResult;
    public static final NodeId BinaryEncodingId = Identifiers.ContentFilterResult_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ContentFilterResult_Encoding_DefaultXml;

    protected final ContentFilterElementResult[] elementResults;
    protected final DiagnosticInfo[] elementDiagnosticInfos;

    public ContentFilterResult() {
        this.elementResults = null;
        this.elementDiagnosticInfos = null;
    }

    public ContentFilterResult(ContentFilterElementResult[] elementResults, DiagnosticInfo[] elementDiagnosticInfos) {
        this.elementResults = elementResults;
        this.elementDiagnosticInfos = elementDiagnosticInfos;
    }

    @Nullable
    public ContentFilterElementResult[] getElementResults() { return elementResults; }

    @Nullable
    public DiagnosticInfo[] getElementDiagnosticInfos() { return elementDiagnosticInfos; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ElementResults", elementResults)
            .add("ElementDiagnosticInfos", elementDiagnosticInfos)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<ContentFilterResult> {

        @Override
        public Class<ContentFilterResult> getType() {
            return ContentFilterResult.class;
        }

        @Override
        public ContentFilterResult decode(UaDecoder decoder) throws UaSerializationException {
            ContentFilterElementResult[] elementResults =
                decoder.readBuiltinStructArray(
                    "ElementResults",
                    ContentFilterElementResult.class
                );
            DiagnosticInfo[] elementDiagnosticInfos = decoder.readArray("ElementDiagnosticInfos", decoder::readDiagnosticInfo, DiagnosticInfo.class);

            return new ContentFilterResult(elementResults, elementDiagnosticInfos);
        }

        @Override
        public void encode(ContentFilterResult value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStructArray(
                "ElementResults",
                value.elementResults,
                ContentFilterElementResult.class
            );
            encoder.writeArray("ElementDiagnosticInfos", value.elementDiagnosticInfos, encoder::writeDiagnosticInfo);
        }
    }

}
