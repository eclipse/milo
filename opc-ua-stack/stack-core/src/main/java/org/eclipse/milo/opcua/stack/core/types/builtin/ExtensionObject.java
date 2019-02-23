/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.builtin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.EncodingLimits;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.types.DataTypeEncoding;
import org.eclipse.milo.opcua.stack.core.types.DataTypeManager;
import org.eclipse.milo.opcua.stack.core.types.OpcUaDataTypeManager;
import org.eclipse.milo.opcua.stack.core.types.OpcUaDefaultBinaryEncoding;
import org.eclipse.milo.opcua.stack.core.types.OpcUaDefaultXmlEncoding;
import org.eclipse.milo.opcua.stack.core.util.Lazy;

public final class ExtensionObject {

    public enum BodyType {
        ByteString,
        XmlElement
    }

    private final Lazy<Object> decoded = new Lazy<>();

    private final BodyType bodyType;

    private final Object body;
    private final NodeId encodingId;

    public ExtensionObject(
        @Nonnull ByteString body,
        @Nonnull NodeId encodingId) {

        this((Object) body, encodingId);
    }

    public ExtensionObject(
        @Nonnull XmlElement body,
        @Nonnull NodeId encodingId) {

        this((Object) body, encodingId);
    }

    private ExtensionObject(
        @Nonnull Object body,
        @Nonnull NodeId encodingId) {

        this.body = body;
        this.encodingId = encodingId;

        if (body instanceof ByteString) {
            bodyType = BodyType.ByteString;
        } else if (body instanceof XmlElement) {
            bodyType = BodyType.XmlElement;
        } else {
            throw new IllegalArgumentException("body: " + body);
        }
    }

    public Object getBody() {
        return body;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public NodeId getEncodingId() {
        return encodingId;
    }

    public boolean isNull() {
        switch (bodyType) {
            case ByteString:
                return ((ByteString) body).isNull();
            case XmlElement:
                return ((XmlElement) body).isNull();
            default:
                throw new IllegalStateException("BodyType: " + bodyType);
        }
    }

    public Object decode() throws UaSerializationException {
        switch (bodyType) {
            case ByteString:
                return decode(OpcUaDefaultBinaryEncoding.getInstance(), OpcUaDataTypeManager.getInstance());
            case XmlElement:
                return decode(OpcUaDefaultXmlEncoding.getInstance(), OpcUaDataTypeManager.getInstance());
            default:
                throw new IllegalStateException("BodyType: " + bodyType);
        }
    }

    public Object decode(DataTypeManager dataTypeManager) throws UaSerializationException {
        switch (bodyType) {
            case ByteString:
                return decode(OpcUaDefaultBinaryEncoding.getInstance(), dataTypeManager);
            case XmlElement:
                return decode(OpcUaDefaultXmlEncoding.getInstance(), dataTypeManager);
            default:
                throw new IllegalStateException("BodyType: " + bodyType);
        }
    }

    public Object decode(
        EncodingLimits encodingLimits,
        DataTypeManager dataTypeManager) throws UaSerializationException {

        switch (bodyType) {
            case ByteString:
                return decode(OpcUaDefaultBinaryEncoding.getInstance(), encodingLimits, dataTypeManager);
            case XmlElement:
                return decode(OpcUaDefaultXmlEncoding.getInstance(), encodingLimits, dataTypeManager);
            default:
                throw new IllegalStateException("BodyType: " + bodyType);
        }
    }

    public Object decode(
        DataTypeEncoding encoding,
        DataTypeManager dataTypeManager) throws UaSerializationException {

        return decode(encoding, EncodingLimits.DEFAULT, dataTypeManager);
    }

    public Object decode(
        DataTypeEncoding encoding,
        EncodingLimits encodingLimits,
        DataTypeManager dataTypeManager) throws UaSerializationException {

        return decoded.getOrCompute(() -> encoding.decode(body, encodingId, encodingLimits, dataTypeManager));
    }

    @Nullable
    public Object decodeOrNull() {
        try {
            return decode();
        } catch (UaSerializationException e) {
            return null;
        }
    }

    @Nullable
    public Object decodeOrNull(DataTypeManager dataTypeManager) {
        try {
            return decode(dataTypeManager);
        } catch (UaSerializationException e) {
            return null;
        }
    }

    @Nullable
    public Object decodeOrNull(EncodingLimits encodingLimits, DataTypeManager dataTypeManager) {
        try {
            return decode(encodingLimits, dataTypeManager);
        } catch (UaSerializationException e) {
            return null;
        }
    }

    public ExtensionObject transcode(
        NodeId newEncodingId,
        DataTypeEncoding newEncoding,
        EncodingLimits encodingLimits,
        DataTypeManager dataTypeManager) {

        if (this.encodingId.equals(newEncodingId)) {
            return this;
        } else {
            // The "fast" path: body is a encoded in Default Binary or Default XML.
            // No need to look up the DataTypeEncoding.
            Object struct = decodeOrNull(encodingLimits, dataTypeManager);

            if (struct != null) {
                Object encoded = newEncoding.encode(struct, newEncodingId, encodingLimits, dataTypeManager);

                return new ExtensionObject(encoded, newEncodingId);
            } else {
                // TODO look up current DataTypeEncoding via this.encodingId, try decoding again using that.
                return this;
            }
        }
    }

    public static ExtensionObject encode(UaStructure struct) {
        NodeId encodingId = struct.getBinaryEncodingId();

        return encodeDefaultBinary(struct, encodingId, OpcUaDataTypeManager.getInstance());
    }

    public static ExtensionObject encodeDefaultBinary(
        Object object,
        NodeId encodingId,
        DataTypeManager dataTypeManager) throws UaSerializationException {

        return encode(
            object,
            encodingId,
            OpcUaDefaultBinaryEncoding.getInstance(),
            EncodingLimits.DEFAULT,
            dataTypeManager
        );
    }

    public static ExtensionObject encodeDefaultXml(
        Object object,
        NodeId encodingId,
        DataTypeManager dataTypeManager) throws UaSerializationException {

        return encode(
            object,
            encodingId,
            OpcUaDefaultXmlEncoding.getInstance(),
            EncodingLimits.DEFAULT,
            dataTypeManager
        );
    }

    public static ExtensionObject encode(
        Object object,
        NodeId encodingId,
        DataTypeEncoding encoding) throws UaSerializationException {

        return encode(object, encodingId, encoding, EncodingLimits.DEFAULT, OpcUaDataTypeManager.getInstance());
    }

    public static ExtensionObject encode(
        Object object,
        NodeId encodingId,
        DataTypeEncoding encoding,
        EncodingLimits encodingLimits,
        DataTypeManager dataTypeManager) throws UaSerializationException {

        Object body = encoding.encode(object, encodingId, encodingLimits, dataTypeManager);

        return new ExtensionObject(body, encodingId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExtensionObject that = (ExtensionObject) o;

        return Objects.equal(body, that.body) &&
            Objects.equal(encodingId, that.encodingId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(body, encodingId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("encoded", body)
            .add("encodingId", encodingId)
            .toString();
    }

}
