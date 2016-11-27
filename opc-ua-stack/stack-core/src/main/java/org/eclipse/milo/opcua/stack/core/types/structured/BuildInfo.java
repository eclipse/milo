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

@UaDataType("BuildInfo")
public class BuildInfo implements UaStructure {

    public static final NodeId TypeId = Identifiers.BuildInfo;
    public static final NodeId BinaryEncodingId = Identifiers.BuildInfo_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.BuildInfo_Encoding_DefaultXml;

    protected final String _productUri;
    protected final String _manufacturerName;
    protected final String _productName;
    protected final String _softwareVersion;
    protected final String _buildNumber;
    protected final DateTime _buildDate;

    public BuildInfo() {
        this._productUri = null;
        this._manufacturerName = null;
        this._productName = null;
        this._softwareVersion = null;
        this._buildNumber = null;
        this._buildDate = null;
    }

    public BuildInfo(String _productUri, String _manufacturerName, String _productName, String _softwareVersion, String _buildNumber, DateTime _buildDate) {
        this._productUri = _productUri;
        this._manufacturerName = _manufacturerName;
        this._productName = _productName;
        this._softwareVersion = _softwareVersion;
        this._buildNumber = _buildNumber;
        this._buildDate = _buildDate;
    }

    public String getProductUri() { return _productUri; }

    public String getManufacturerName() { return _manufacturerName; }

    public String getProductName() { return _productName; }

    public String getSoftwareVersion() { return _softwareVersion; }

    public String getBuildNumber() { return _buildNumber; }

    public DateTime getBuildDate() { return _buildDate; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ProductUri", _productUri)
            .add("ManufacturerName", _manufacturerName)
            .add("ProductName", _productName)
            .add("SoftwareVersion", _softwareVersion)
            .add("BuildNumber", _buildNumber)
            .add("BuildDate", _buildDate)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<BuildInfo> {
        @Override
        public BuildInfo decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            String _productUri = reader.readString();
            String _manufacturerName = reader.readString();
            String _productName = reader.readString();
            String _softwareVersion = reader.readString();
            String _buildNumber = reader.readString();
            DateTime _buildDate = reader.readDateTime();

            return new BuildInfo(_productUri, _manufacturerName, _productName, _softwareVersion, _buildNumber, _buildDate);
        }

        @Override
        public void encode(SerializationContext context, BuildInfo encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeString(encodable._productUri);
            writer.writeString(encodable._manufacturerName);
            writer.writeString(encodable._productName);
            writer.writeString(encodable._softwareVersion);
            writer.writeString(encodable._buildNumber);
            writer.writeDateTime(encodable._buildDate);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<BuildInfo> {
        @Override
        public BuildInfo decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            String _productUri = reader.readString("ProductUri");
            String _manufacturerName = reader.readString("ManufacturerName");
            String _productName = reader.readString("ProductName");
            String _softwareVersion = reader.readString("SoftwareVersion");
            String _buildNumber = reader.readString("BuildNumber");
            DateTime _buildDate = reader.readDateTime("BuildDate");

            return new BuildInfo(_productUri, _manufacturerName, _productName, _softwareVersion, _buildNumber, _buildDate);
        }

        @Override
        public void encode(SerializationContext context, BuildInfo encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeString("ProductUri", encodable._productUri);
            writer.writeString("ManufacturerName", encodable._manufacturerName);
            writer.writeString("ProductName", encodable._productName);
            writer.writeString("SoftwareVersion", encodable._softwareVersion);
            writer.writeString("BuildNumber", encodable._buildNumber);
            writer.writeDateTime("BuildDate", encodable._buildDate);
        }
    }

}
