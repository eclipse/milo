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
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("SoftwareCertificate")
public class SoftwareCertificate implements UaStructure {

    public static final NodeId TypeId = Identifiers.SoftwareCertificate;
    public static final NodeId BinaryEncodingId = Identifiers.SoftwareCertificate_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.SoftwareCertificate_Encoding_DefaultXml;

    protected final String _productName;
    protected final String _productUri;
    protected final String _vendorName;
    protected final ByteString _vendorProductCertificate;
    protected final String _softwareVersion;
    protected final String _buildNumber;
    protected final DateTime _buildDate;
    protected final String _issuedBy;
    protected final DateTime _issueDate;
    protected final SupportedProfile[] _supportedProfiles;

    public SoftwareCertificate() {
        this._productName = null;
        this._productUri = null;
        this._vendorName = null;
        this._vendorProductCertificate = null;
        this._softwareVersion = null;
        this._buildNumber = null;
        this._buildDate = null;
        this._issuedBy = null;
        this._issueDate = null;
        this._supportedProfiles = null;
    }

    public SoftwareCertificate(String _productName, String _productUri, String _vendorName, ByteString _vendorProductCertificate, String _softwareVersion, String _buildNumber, DateTime _buildDate, String _issuedBy, DateTime _issueDate, SupportedProfile[] _supportedProfiles) {
        this._productName = _productName;
        this._productUri = _productUri;
        this._vendorName = _vendorName;
        this._vendorProductCertificate = _vendorProductCertificate;
        this._softwareVersion = _softwareVersion;
        this._buildNumber = _buildNumber;
        this._buildDate = _buildDate;
        this._issuedBy = _issuedBy;
        this._issueDate = _issueDate;
        this._supportedProfiles = _supportedProfiles;
    }

    public String getProductName() { return _productName; }

    public String getProductUri() { return _productUri; }

    public String getVendorName() { return _vendorName; }

    public ByteString getVendorProductCertificate() { return _vendorProductCertificate; }

    public String getSoftwareVersion() { return _softwareVersion; }

    public String getBuildNumber() { return _buildNumber; }

    public DateTime getBuildDate() { return _buildDate; }

    public String getIssuedBy() { return _issuedBy; }

    public DateTime getIssueDate() { return _issueDate; }

    @Nullable
    public SupportedProfile[] getSupportedProfiles() { return _supportedProfiles; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ProductName", _productName)
            .add("ProductUri", _productUri)
            .add("VendorName", _vendorName)
            .add("VendorProductCertificate", _vendorProductCertificate)
            .add("SoftwareVersion", _softwareVersion)
            .add("BuildNumber", _buildNumber)
            .add("BuildDate", _buildDate)
            .add("IssuedBy", _issuedBy)
            .add("IssueDate", _issueDate)
            .add("SupportedProfiles", _supportedProfiles)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<SoftwareCertificate> {
        @Override
        public SoftwareCertificate decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            String _productName = reader.readString();
            String _productUri = reader.readString();
            String _vendorName = reader.readString();
            ByteString _vendorProductCertificate = reader.readByteString();
            String _softwareVersion = reader.readString();
            String _buildNumber = reader.readString();
            DateTime _buildDate = reader.readDateTime();
            String _issuedBy = reader.readString();
            DateTime _issueDate = reader.readDateTime();
            SupportedProfile[] _supportedProfiles =
                reader.readArray(
                    () -> (SupportedProfile) context.decode(
                        SupportedProfile.BinaryEncodingId, reader),
                    SupportedProfile.class
                );

            return new SoftwareCertificate(_productName, _productUri, _vendorName, _vendorProductCertificate, _softwareVersion, _buildNumber, _buildDate, _issuedBy, _issueDate, _supportedProfiles);
        }

        @Override
        public void encode(SerializationContext context, SoftwareCertificate value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeString(value._productName);
            writer.writeString(value._productUri);
            writer.writeString(value._vendorName);
            writer.writeByteString(value._vendorProductCertificate);
            writer.writeString(value._softwareVersion);
            writer.writeString(value._buildNumber);
            writer.writeDateTime(value._buildDate);
            writer.writeString(value._issuedBy);
            writer.writeDateTime(value._issueDate);
            writer.writeArray(
                value._supportedProfiles,
                e -> context.encode(SupportedProfile.BinaryEncodingId, e, writer)
            );
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<SoftwareCertificate> {
        @Override
        public SoftwareCertificate decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            String _productName = reader.readString("ProductName");
            String _productUri = reader.readString("ProductUri");
            String _vendorName = reader.readString("VendorName");
            ByteString _vendorProductCertificate = reader.readByteString("VendorProductCertificate");
            String _softwareVersion = reader.readString("SoftwareVersion");
            String _buildNumber = reader.readString("BuildNumber");
            DateTime _buildDate = reader.readDateTime("BuildDate");
            String _issuedBy = reader.readString("IssuedBy");
            DateTime _issueDate = reader.readDateTime("IssueDate");
            SupportedProfile[] _supportedProfiles =
                reader.readArray(
                    "SupportedProfiles",
                    f -> (SupportedProfile) context.decode(
                        SupportedProfile.XmlEncodingId, reader),
                    SupportedProfile.class
                );

            return new SoftwareCertificate(_productName, _productUri, _vendorName, _vendorProductCertificate, _softwareVersion, _buildNumber, _buildDate, _issuedBy, _issueDate, _supportedProfiles);
        }

        @Override
        public void encode(SerializationContext context, SoftwareCertificate encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeString("ProductName", encodable._productName);
            writer.writeString("ProductUri", encodable._productUri);
            writer.writeString("VendorName", encodable._vendorName);
            writer.writeByteString("VendorProductCertificate", encodable._vendorProductCertificate);
            writer.writeString("SoftwareVersion", encodable._softwareVersion);
            writer.writeString("BuildNumber", encodable._buildNumber);
            writer.writeDateTime("BuildDate", encodable._buildDate);
            writer.writeString("IssuedBy", encodable._issuedBy);
            writer.writeDateTime("IssueDate", encodable._issueDate);
            writer.writeArray(
                "SupportedProfiles",
                encodable._supportedProfiles,
                (f, e) -> context.encode(SupportedProfile.XmlEncodingId, e, writer)
            );
        }
    }

}
