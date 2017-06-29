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

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class Annotation implements UaStructure {

    public static final NodeId TypeId = Identifiers.Annotation;
    public static final NodeId BinaryEncodingId = Identifiers.Annotation_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.Annotation_Encoding_DefaultXml;

    protected final String message;
    protected final String userName;
    protected final DateTime annotationTime;

    public Annotation() {
        this.message = null;
        this.userName = null;
        this.annotationTime = null;
    }

    public Annotation(String message, String userName, DateTime annotationTime) {
        this.message = message;
        this.userName = userName;
        this.annotationTime = annotationTime;
    }

    public String getMessage() { return message; }

    public String getUserName() { return userName; }

    public DateTime getAnnotationTime() { return annotationTime; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("Message", message)
            .add("UserName", userName)
            .add("AnnotationTime", annotationTime)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<Annotation> {

        @Override
        public Class<Annotation> getType() {
            return Annotation.class;
        }

        @Override
        public Annotation decode(UaDecoder decoder) throws UaSerializationException {
            String message = decoder.readString("Message");
            String userName = decoder.readString("UserName");
            DateTime annotationTime = decoder.readDateTime("AnnotationTime");

            return new Annotation(message, userName, annotationTime);
        }

        @Override
        public void encode(Annotation value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeString("Message", value.message);
            encoder.writeString("UserName", value.userName);
            encoder.writeDateTime("AnnotationTime", value.annotationTime);
        }
    }

}
