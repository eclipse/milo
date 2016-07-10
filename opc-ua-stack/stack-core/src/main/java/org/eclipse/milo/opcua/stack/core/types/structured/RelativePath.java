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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("RelativePath")
public class RelativePath implements UaStructure {

    public static final NodeId TypeId = Identifiers.RelativePath;
    public static final NodeId BinaryEncodingId = Identifiers.RelativePath_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.RelativePath_Encoding_DefaultXml;

    protected final RelativePathElement[] _elements;

    public RelativePath() {
        this._elements = null;
    }

    public RelativePath(RelativePathElement[] _elements) {
        this._elements = _elements;
    }

    public RelativePathElement[] getElements() { return _elements; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(RelativePath relativePath, UaEncoder encoder) {
        encoder.encodeArray("Elements", relativePath._elements, encoder::encodeSerializable);
    }

    public static RelativePath decode(UaDecoder decoder) {
        RelativePathElement[] _elements = decoder.decodeArray("Elements", decoder::decodeSerializable, RelativePathElement.class);

        return new RelativePath(_elements);
    }

    static {
        DelegateRegistry.registerEncoder(RelativePath::encode, RelativePath.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(RelativePath::decode, RelativePath.class, BinaryEncodingId, XmlEncodingId);
    }

}
