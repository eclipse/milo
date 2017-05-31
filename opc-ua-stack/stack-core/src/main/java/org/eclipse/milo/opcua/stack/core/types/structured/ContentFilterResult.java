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
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("ContentFilterResult")
public class ContentFilterResult implements UaStructure {

    public static final NodeId TypeId = Identifiers.ContentFilterResult;
    public static final NodeId BinaryEncodingId = Identifiers.ContentFilterResult_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ContentFilterResult_Encoding_DefaultXml;

    protected final ContentFilterElementResult[] _elementResults;
    protected final DiagnosticInfo[] _elementDiagnosticInfos;

    public ContentFilterResult() {
        this._elementResults = null;
        this._elementDiagnosticInfos = null;
    }

    public ContentFilterResult(ContentFilterElementResult[] _elementResults, DiagnosticInfo[] _elementDiagnosticInfos) {
        this._elementResults = _elementResults;
        this._elementDiagnosticInfos = _elementDiagnosticInfos;
    }

    @Nullable
    public ContentFilterElementResult[] getElementResults() { return _elementResults; }

    @Nullable
    public DiagnosticInfo[] getElementDiagnosticInfos() { return _elementDiagnosticInfos; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ElementResults", _elementResults)
            .add("ElementDiagnosticInfos", _elementDiagnosticInfos)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<ContentFilterResult> {
        @Override
        public ContentFilterResult decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            ContentFilterElementResult[] _elementResults =
                reader.readArray(
                    () -> (ContentFilterElementResult) context.decode(
                        ContentFilterElementResult.BinaryEncodingId, reader),
                    ContentFilterElementResult.class
                );
            DiagnosticInfo[] _elementDiagnosticInfos = reader.readArray(reader::readDiagnosticInfo, DiagnosticInfo.class);

            return new ContentFilterResult(_elementResults, _elementDiagnosticInfos);
        }

        @Override
        public void encode(SerializationContext context, ContentFilterResult encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeArray(
                encodable._elementResults,
                e -> context.encode(ContentFilterElementResult.BinaryEncodingId, e, writer)
            );
            writer.writeArray(encodable._elementDiagnosticInfos, writer::writeDiagnosticInfo);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<ContentFilterResult> {
        @Override
        public ContentFilterResult decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            ContentFilterElementResult[] _elementResults =
                reader.readArray(
                    "ElementResults",
                    f -> (ContentFilterElementResult) context.decode(
                        ContentFilterElementResult.XmlEncodingId, reader),
                    ContentFilterElementResult.class
                );
            DiagnosticInfo[] _elementDiagnosticInfos = reader.readArray("ElementDiagnosticInfos", reader::readDiagnosticInfo, DiagnosticInfo.class);

            return new ContentFilterResult(_elementResults, _elementDiagnosticInfos);
        }

        @Override
        public void encode(SerializationContext context, ContentFilterResult encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeArray(
                "ElementResults",
                encodable._elementResults,
                (f, e) -> context.encode(ContentFilterElementResult.XmlEncodingId, e, writer)
            );
            writer.writeArray("ElementDiagnosticInfos", encodable._elementDiagnosticInfos, writer::writeDiagnosticInfo);
        }
    }

}
