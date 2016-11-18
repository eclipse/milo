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

import com.google.common.base.Objects;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.DataTypeEncoding;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;

public abstract class ExtensionObject {

    public static ExtensionObject NULL_BINARY = ExtensionObject.fromByteString(
        ByteString.NULL_VALUE,
        NodeId.NULL_VALUE
    );

    public static ExtensionObject NULL_XML = ExtensionObject.fromXmlElement(
        XmlElement.NULL_VALUE,
        NodeId.NULL_VALUE
    );

    public enum BodyType {
        ByteString,
        XmlElement
    }

    /**
     * @return the {@link BodyType} of the encoded body.
     */
    public abstract BodyType getBodyType();

    /**
     * Get the encoded body for this ExtensionObject.
     * <p>
     * Depending on the {@link BodyType}, either {@link ByteString} or {@link XmlElement}.
     *
     * @return the encoded by for this ExtensionObject.
     */
    public abstract Object getEncodedBody(DataTypeEncoding context);

    /**
     * @return the {@link NodeId} of the type node for the object contained within.
     */
    public abstract NodeId getEncodingTypeId();

    /**
     * Decode the body and return the decoded Object.
     *
     * @return the Object returned by the decoding the encoded body.
     * @throws UaSerializationException if decoding fails for any reason.
     */
    public <T> T getObject() throws UaSerializationException {
        return getObject(DataTypeEncoding.OPC_UA);
    }

    /**
     * Decode the body and return the decoded Object.
     *
     * @param context the {@link DataTypeEncoding} to use for decoding.
     * @return the Object returned by the decoding the encoded body.
     * @throws UaSerializationException if decoding fails for any reason.
     */
    public abstract <T> T getObject(DataTypeEncoding context) throws UaSerializationException;

    public abstract boolean isNull();

    public final boolean isNotNull() {
        return !isNull();
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(getEncodingTypeId(), getObject());
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || !(obj instanceof ExtensionObject)) {
            return false;
        }

        final ExtensionObject other = (ExtensionObject) obj;

        return Objects.equal(getEncodingTypeId(), other.getEncodingTypeId()) &&
            Objects.equal(getObject(), other.getObject());
    }

    /**
     * Create an {@link ExtensionObject} from an Object instance.
     * <p>
     * The encoded body will be created on demand.
     *
     * @param object         the Object instance.
     * @param encodingTypeId the {@link NodeId} of the encoding type.
     * @param bodyType       the {@link BodyType} to use when creating the body.
     * @return an {@link ExtensionObject}.
     */
    public static ExtensionObject fromObject(Object object, NodeId encodingTypeId, BodyType bodyType) {
        return new LazilyEncodedExtensionObject(object, encodingTypeId, bodyType);
    }

    public static ExtensionObject fromStructure(UaStructure structure) {
        return fromStructure(structure, BodyType.ByteString);
    }

    public static ExtensionObject fromStructure(UaStructure structure, BodyType bodyType) {
        NodeId encodingTypeId = bodyType == BodyType.ByteString ?
            structure.getBinaryEncodingId() :
            structure.getXmlEncodingId();

        return new LazilyEncodedExtensionObject(structure, encodingTypeId, bodyType);
    }

    /**
     * Create an {@link ExtensionObject} from a {@link ByteString} representing the encoded body.
     *
     * @param byteString     a {@link ByteString} representing the encoded body.
     * @param encodingTypeId the {@link NodeId} of the encoding type.
     * @return an {@link ExtensionObject}.
     */
    public static ExtensionObject fromByteString(ByteString byteString, NodeId encodingTypeId) {
        return new LazilyDecodedExtensionObject(byteString, encodingTypeId);
    }

    /**
     * Create an {@link ExtensionObject} from an {@link XmlElement} representing the encoded body.
     *
     * @param xmlElement     an {@link XmlElement} representing the encoded body.
     * @param encodingTypeId the {@link NodeId} of the encoding type.
     * @return an {@link ExtensionObject}.
     */
    public static ExtensionObject fromXmlElement(XmlElement xmlElement, NodeId encodingTypeId) {
        return new LazilyDecodedExtensionObject(xmlElement, encodingTypeId);
    }

    private static final class LazilyDecodedExtensionObject extends ExtensionObject {

        private volatile Object decoded;

        private final BodyType bodyType;

        private final Object encoded;
        private final NodeId encodingTypeId;

        private LazilyDecodedExtensionObject(ByteString encoded, NodeId encodingTypeId) {
            this.encoded = encoded;
            this.encodingTypeId = encodingTypeId;

            bodyType = BodyType.ByteString;
        }

        private LazilyDecodedExtensionObject(XmlElement encoded, NodeId encodingTypeId) {
            this.encoded = encoded;
            this.encodingTypeId = encodingTypeId;

            bodyType = BodyType.XmlElement;
        }

        @Override
        public BodyType getBodyType() {
            return bodyType;
        }

        @Override
        public Object getEncodedBody(DataTypeEncoding context) {
            return encoded;
        }

        @Override
        public NodeId getEncodingTypeId() {
            return encodingTypeId;
        }

        @Override
        public <T> T getObject(DataTypeEncoding context) throws UaSerializationException {
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

        @Override
        public boolean isNull() {
            return encoded == null;
        }

    }

    private static final class LazilyEncodedExtensionObject extends ExtensionObject {

        private volatile ByteString encodedByteString;
        private volatile XmlElement encodedXmlElement;

        private final Object object;
        private final NodeId encodingTypeId;
        private final BodyType bodyType;

        private LazilyEncodedExtensionObject(Object object, NodeId encodingTypeId, BodyType bodyType) {
            this.object = object;
            this.encodingTypeId = encodingTypeId;
            this.bodyType = bodyType;
        }

        @Override
        public BodyType getBodyType() {
            return bodyType;
        }

        @Override
        public Object getEncodedBody(DataTypeEncoding context) {
            switch (bodyType) {
                case ByteString:
                    if (object == null) {
                        return ByteString.NULL_VALUE;
                    }
                    if (encodedByteString == null) {
                        encodedByteString = context.encodeToByteString(object, encodingTypeId);
                    }
                    return encodedByteString;

                case XmlElement:
                    if (object == null) {
                        return XmlElement.NULL_VALUE;
                    }
                    if (encodedXmlElement == null) {
                        encodedXmlElement = context.encodeToXmlElement(object, encodingTypeId);
                    }
                    return encodedXmlElement;

                default:
                    throw new IllegalStateException("unknown body type: " + bodyType);
            }
        }

        @Override
        public NodeId getEncodingTypeId() {
            return encodingTypeId;
        }

        @Override
        @SuppressWarnings("unchecked")
        public <T> T getObject(DataTypeEncoding context) throws UaSerializationException {
            return (T) object;
        }

        @Override
        public boolean isNull() {
            return object == null;
        }

    }

}
