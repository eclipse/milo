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
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("Annotation")
public class Annotation implements UaStructure {

    public static final NodeId TypeId = Identifiers.Annotation;
    public static final NodeId BinaryEncodingId = Identifiers.Annotation_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.Annotation_Encoding_DefaultXml;

    protected final String _message;
    protected final String _userName;
    protected final DateTime _annotationTime;

    public Annotation() {
        this._message = null;
        this._userName = null;
        this._annotationTime = null;
    }

    public Annotation(String _message, String _userName, DateTime _annotationTime) {
        this._message = _message;
        this._userName = _userName;
        this._annotationTime = _annotationTime;
    }

    public String getMessage() { return _message; }

    public String getUserName() { return _userName; }

    public DateTime getAnnotationTime() { return _annotationTime; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(Annotation annotation, UaEncoder encoder) {
        encoder.encodeString("Message", annotation._message);
        encoder.encodeString("UserName", annotation._userName);
        encoder.encodeDateTime("AnnotationTime", annotation._annotationTime);
    }

    public static Annotation decode(UaDecoder decoder) {
        String _message = decoder.decodeString("Message");
        String _userName = decoder.decodeString("UserName");
        DateTime _annotationTime = decoder.decodeDateTime("AnnotationTime");

        return new Annotation(_message, _userName, _annotationTime);
    }

    static {
        DelegateRegistry.registerEncoder(Annotation::encode, Annotation.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(Annotation::decode, Annotation.class, BinaryEncodingId, XmlEncodingId);
    }

}
