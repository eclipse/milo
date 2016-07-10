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

package org.eclipse.milo.opcua.stack.core.types.builtin;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.DataTypeEncoding;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;

public final class ExtensionObject {

    public enum BodyType {
        ByteString,
        XmlElement
    }

    private volatile Object decoded;

    private final BodyType bodyType;

    private final Object encoded;
    private final NodeId encodingTypeId;

    public ExtensionObject(ByteString encoded, NodeId encodingTypeId) {
        this.encoded = encoded;
        this.encodingTypeId = encodingTypeId;

        bodyType = BodyType.ByteString;
    }

    public ExtensionObject(XmlElement encoded, NodeId encodingTypeId) {
        this.encoded = encoded;
        this.encodingTypeId = encodingTypeId;

        bodyType = BodyType.XmlElement;
    }

    public Object getEncoded() {
        return encoded;
    }

    public NodeId getEncodingTypeId() {
        return encodingTypeId;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public <T> T decode() throws UaSerializationException {
        return decode(DataTypeEncoding.OPC_UA);
    }

    public <T> T decode(DataTypeEncoding context) throws UaSerializationException {
        if (decoded != null) return (T) decoded;

        switch (bodyType) {
            case ByteString: {
                ByteString bs = (ByteString) encoded;
                if (bs == null || bs.isNull()) {
                    return null;
                } else {
                    decoded = context.decodeFromByteString((ByteString) encoded, encodingTypeId);
                    return (T) decoded;
                }
            }

            case XmlElement: {
                XmlElement e = (XmlElement) encoded;
                if (e == null || e.isNull()) {
                    return null;
                } else {
                    decoded = context.decodeFromXmlElement((XmlElement) encoded, encodingTypeId);
                    return (T) decoded;
                }
            }

            default:
                throw new IllegalStateException("unknown body type: " + bodyType);
        }
    }

    public static ExtensionObject encode(UaStructure structure) throws UaSerializationException {
        return encodeAsByteString(structure, structure.getBinaryEncodingId());
    }

    public static ExtensionObject encodeAsByteString(Object object,
                                                     NodeId encodingTypeId) throws UaSerializationException {

        return encodeAsByteString(object, encodingTypeId, DataTypeEncoding.OPC_UA);
    }

    public static ExtensionObject encodeAsByteString(Object object,
                                                     NodeId encodingTypeId,
                                                     DataTypeEncoding context) throws UaSerializationException {

        ByteString encoded = context.encodeToByteString(object, encodingTypeId);

        return new ExtensionObject(encoded, encodingTypeId);
    }

    public static ExtensionObject encodeAsXmlElement(Object object,
                                                     NodeId encodingTypeId) throws UaSerializationException {

        return encodeAsXmlElement(object, encodingTypeId, DataTypeEncoding.OPC_UA);
    }

    public static ExtensionObject encodeAsXmlElement(Object object,
                                                     NodeId encodingTypeId,
                                                     DataTypeEncoding context) throws UaSerializationException {

        XmlElement encoded = context.encodeToXmlElement(object, encodingTypeId);

        return new ExtensionObject(encoded, encodingTypeId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExtensionObject that = (ExtensionObject) o;

        return Objects.equal(encoded, that.encoded) &&
            Objects.equal(encodingTypeId, that.encodingTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(encoded, encodingTypeId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("encoded", encoded)
            .add("encodingTypeId", encodingTypeId)
            .toString();
    }

}
