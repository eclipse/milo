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


    public static void encode(BuildInfo buildInfo, UaEncoder encoder) {
        encoder.encodeString("ProductUri", buildInfo._productUri);
        encoder.encodeString("ManufacturerName", buildInfo._manufacturerName);
        encoder.encodeString("ProductName", buildInfo._productName);
        encoder.encodeString("SoftwareVersion", buildInfo._softwareVersion);
        encoder.encodeString("BuildNumber", buildInfo._buildNumber);
        encoder.encodeDateTime("BuildDate", buildInfo._buildDate);
    }

    public static BuildInfo decode(UaDecoder decoder) {
        String _productUri = decoder.decodeString("ProductUri");
        String _manufacturerName = decoder.decodeString("ManufacturerName");
        String _productName = decoder.decodeString("ProductName");
        String _softwareVersion = decoder.decodeString("SoftwareVersion");
        String _buildNumber = decoder.decodeString("BuildNumber");
        DateTime _buildDate = decoder.decodeDateTime("BuildDate");

        return new BuildInfo(_productUri, _manufacturerName, _productName, _softwareVersion, _buildNumber, _buildDate);
    }

    static {
        DelegateRegistry.registerEncoder(BuildInfo::encode, BuildInfo.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(BuildInfo::decode, BuildInfo.class, BinaryEncodingId, XmlEncodingId);
    }

}
