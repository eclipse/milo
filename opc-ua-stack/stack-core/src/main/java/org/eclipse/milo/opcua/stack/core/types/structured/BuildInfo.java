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

public class BuildInfo implements UaStructure {

    public static final NodeId TypeId = Identifiers.BuildInfo;
    public static final NodeId BinaryEncodingId = Identifiers.BuildInfo_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.BuildInfo_Encoding_DefaultXml;

    protected final String productUri;
    protected final String manufacturerName;
    protected final String productName;
    protected final String softwareVersion;
    protected final String buildNumber;
    protected final DateTime buildDate;

    public BuildInfo() {
        this.productUri = null;
        this.manufacturerName = null;
        this.productName = null;
        this.softwareVersion = null;
        this.buildNumber = null;
        this.buildDate = null;
    }

    public BuildInfo(String productUri, String manufacturerName, String productName, String softwareVersion, String buildNumber, DateTime buildDate) {
        this.productUri = productUri;
        this.manufacturerName = manufacturerName;
        this.productName = productName;
        this.softwareVersion = softwareVersion;
        this.buildNumber = buildNumber;
        this.buildDate = buildDate;
    }

    public String getProductUri() { return productUri; }

    public String getManufacturerName() { return manufacturerName; }

    public String getProductName() { return productName; }

    public String getSoftwareVersion() { return softwareVersion; }

    public String getBuildNumber() { return buildNumber; }

    public DateTime getBuildDate() { return buildDate; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ProductUri", productUri)
            .add("ManufacturerName", manufacturerName)
            .add("ProductName", productName)
            .add("SoftwareVersion", softwareVersion)
            .add("BuildNumber", buildNumber)
            .add("BuildDate", buildDate)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<BuildInfo> {

        @Override
        public Class<BuildInfo> getType() {
            return BuildInfo.class;
        }

        @Override
        public BuildInfo decode(UaDecoder decoder) throws UaSerializationException {
            String productUri = decoder.readString("ProductUri");
            String manufacturerName = decoder.readString("ManufacturerName");
            String productName = decoder.readString("ProductName");
            String softwareVersion = decoder.readString("SoftwareVersion");
            String buildNumber = decoder.readString("BuildNumber");
            DateTime buildDate = decoder.readDateTime("BuildDate");

            return new BuildInfo(productUri, manufacturerName, productName, softwareVersion, buildNumber, buildDate);
        }

        @Override
        public void encode(BuildInfo value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeString("ProductUri", value.productUri);
            encoder.writeString("ManufacturerName", value.manufacturerName);
            encoder.writeString("ProductName", value.productName);
            encoder.writeString("SoftwareVersion", value.softwareVersion);
            encoder.writeString("BuildNumber", value.buildNumber);
            encoder.writeDateTime("BuildDate", value.buildDate);
        }
    }

}
