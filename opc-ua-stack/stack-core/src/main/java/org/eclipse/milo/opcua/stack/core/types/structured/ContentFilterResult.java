/*
 * Copyright (c) 2016 Kevin Herron
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

import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.serialization.DelegateRegistry;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
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

    public ContentFilterElementResult[] getElementResults() { return _elementResults; }

    public DiagnosticInfo[] getElementDiagnosticInfos() { return _elementDiagnosticInfos; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(ContentFilterResult contentFilterResult, UaEncoder encoder) {
        encoder.encodeArray("ElementResults", contentFilterResult._elementResults, encoder::encodeSerializable);
        encoder.encodeArray("ElementDiagnosticInfos", contentFilterResult._elementDiagnosticInfos, encoder::encodeDiagnosticInfo);
    }

    public static ContentFilterResult decode(UaDecoder decoder) {
        ContentFilterElementResult[] _elementResults = decoder.decodeArray("ElementResults", decoder::decodeSerializable, ContentFilterElementResult.class);
        DiagnosticInfo[] _elementDiagnosticInfos = decoder.decodeArray("ElementDiagnosticInfos", decoder::decodeDiagnosticInfo, DiagnosticInfo.class);

        return new ContentFilterResult(_elementResults, _elementDiagnosticInfos);
    }

    static {
        DelegateRegistry.registerEncoder(ContentFilterResult::encode, ContentFilterResult.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(ContentFilterResult::decode, ContentFilterResult.class, BinaryEncodingId, XmlEncodingId);
    }

}
