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
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class SoftwareCertificate implements UaStructure {

    public static final NodeId TypeId = Identifiers.SoftwareCertificate;
    public static final NodeId BinaryEncodingId = Identifiers.SoftwareCertificate_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.SoftwareCertificate_Encoding_DefaultXml;

    protected final String productName;
    protected final String productUri;
    protected final String vendorName;
    protected final ByteString vendorProductCertificate;
    protected final String softwareVersion;
    protected final String buildNumber;
    protected final DateTime buildDate;
    protected final String issuedBy;
    protected final DateTime issueDate;
    protected final SupportedProfile[] supportedProfiles;

    public SoftwareCertificate() {
        this.productName = null;
        this.productUri = null;
        this.vendorName = null;
        this.vendorProductCertificate = null;
        this.softwareVersion = null;
        this.buildNumber = null;
        this.buildDate = null;
        this.issuedBy = null;
        this.issueDate = null;
        this.supportedProfiles = null;
    }

    public SoftwareCertificate(String productName, String productUri, String vendorName, ByteString vendorProductCertificate, String softwareVersion, String buildNumber, DateTime buildDate, String issuedBy, DateTime issueDate, SupportedProfile[] supportedProfiles) {
        this.productName = productName;
        this.productUri = productUri;
        this.vendorName = vendorName;
        this.vendorProductCertificate = vendorProductCertificate;
        this.softwareVersion = softwareVersion;
        this.buildNumber = buildNumber;
        this.buildDate = buildDate;
        this.issuedBy = issuedBy;
        this.issueDate = issueDate;
        this.supportedProfiles = supportedProfiles;
    }

    public String getProductName() { return productName; }

    public String getProductUri() { return productUri; }

    public String getVendorName() { return vendorName; }

    public ByteString getVendorProductCertificate() { return vendorProductCertificate; }

    public String getSoftwareVersion() { return softwareVersion; }

    public String getBuildNumber() { return buildNumber; }

    public DateTime getBuildDate() { return buildDate; }

    public String getIssuedBy() { return issuedBy; }

    public DateTime getIssueDate() { return issueDate; }

    @Nullable
    public SupportedProfile[] getSupportedProfiles() { return supportedProfiles; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ProductName", productName)
            .add("ProductUri", productUri)
            .add("VendorName", vendorName)
            .add("VendorProductCertificate", vendorProductCertificate)
            .add("SoftwareVersion", softwareVersion)
            .add("BuildNumber", buildNumber)
            .add("BuildDate", buildDate)
            .add("IssuedBy", issuedBy)
            .add("IssueDate", issueDate)
            .add("SupportedProfiles", supportedProfiles)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<SoftwareCertificate> {

        @Override
        public Class<SoftwareCertificate> getType() {
            return SoftwareCertificate.class;
        }

        @Override
        public SoftwareCertificate decode(UaDecoder decoder) throws UaSerializationException {
            String productName = decoder.readString("ProductName");
            String productUri = decoder.readString("ProductUri");
            String vendorName = decoder.readString("VendorName");
            ByteString vendorProductCertificate = decoder.readByteString("VendorProductCertificate");
            String softwareVersion = decoder.readString("SoftwareVersion");
            String buildNumber = decoder.readString("BuildNumber");
            DateTime buildDate = decoder.readDateTime("BuildDate");
            String issuedBy = decoder.readString("IssuedBy");
            DateTime issueDate = decoder.readDateTime("IssueDate");
            SupportedProfile[] supportedProfiles =
                decoder.readBuiltinStructArray(
                    "SupportedProfiles",
                    SupportedProfile.class
                );

            return new SoftwareCertificate(productName, productUri, vendorName, vendorProductCertificate, softwareVersion, buildNumber, buildDate, issuedBy, issueDate, supportedProfiles);
        }

        @Override
        public void encode(SoftwareCertificate value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeString("ProductName", value.productName);
            encoder.writeString("ProductUri", value.productUri);
            encoder.writeString("VendorName", value.vendorName);
            encoder.writeByteString("VendorProductCertificate", value.vendorProductCertificate);
            encoder.writeString("SoftwareVersion", value.softwareVersion);
            encoder.writeString("BuildNumber", value.buildNumber);
            encoder.writeDateTime("BuildDate", value.buildDate);
            encoder.writeString("IssuedBy", value.issuedBy);
            encoder.writeDateTime("IssueDate", value.issueDate);
            encoder.writeBuiltinStructArray(
                "SupportedProfiles",
                value.supportedProfiles,
                SupportedProfile.class
            );
        }
    }

}
