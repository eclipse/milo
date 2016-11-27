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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("Message", _message)
            .add("UserName", _userName)
            .add("AnnotationTime", _annotationTime)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<Annotation> {
        @Override
        public Annotation decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            String _message = reader.readString();
            String _userName = reader.readString();
            DateTime _annotationTime = reader.readDateTime();

            return new Annotation(_message, _userName, _annotationTime);
        }

        @Override
        public void encode(SerializationContext context, Annotation encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeString(encodable._message);
            writer.writeString(encodable._userName);
            writer.writeDateTime(encodable._annotationTime);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<Annotation> {
        @Override
        public Annotation decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            String _message = reader.readString("Message");
            String _userName = reader.readString("UserName");
            DateTime _annotationTime = reader.readDateTime("AnnotationTime");

            return new Annotation(_message, _userName, _annotationTime);
        }

        @Override
        public void encode(SerializationContext context, Annotation encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeString("Message", encodable._message);
            writer.writeString("UserName", encodable._userName);
            writer.writeDateTime("AnnotationTime", encodable._annotationTime);
        }
    }

}
