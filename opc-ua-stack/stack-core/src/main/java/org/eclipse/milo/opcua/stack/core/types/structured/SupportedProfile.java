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
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ComplianceLevel;

@UaDataType("SupportedProfile")
public class SupportedProfile implements UaStructure {

    public static final NodeId TypeId = Identifiers.SupportedProfile;
    public static final NodeId BinaryEncodingId = Identifiers.SupportedProfile_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.SupportedProfile_Encoding_DefaultXml;

    protected final String _organizationUri;
    protected final String _profileId;
    protected final String _complianceTool;
    protected final DateTime _complianceDate;
    protected final ComplianceLevel _complianceLevel;
    protected final String[] _unsupportedUnitIds;

    public SupportedProfile() {
        this._organizationUri = null;
        this._profileId = null;
        this._complianceTool = null;
        this._complianceDate = null;
        this._complianceLevel = null;
        this._unsupportedUnitIds = null;
    }

    public SupportedProfile(String _organizationUri, String _profileId, String _complianceTool, DateTime _complianceDate, ComplianceLevel _complianceLevel, String[] _unsupportedUnitIds) {
        this._organizationUri = _organizationUri;
        this._profileId = _profileId;
        this._complianceTool = _complianceTool;
        this._complianceDate = _complianceDate;
        this._complianceLevel = _complianceLevel;
        this._unsupportedUnitIds = _unsupportedUnitIds;
    }

    public String getOrganizationUri() { return _organizationUri; }

    public String getProfileId() { return _profileId; }

    public String getComplianceTool() { return _complianceTool; }

    public DateTime getComplianceDate() { return _complianceDate; }

    public ComplianceLevel getComplianceLevel() { return _complianceLevel; }

    @Nullable
    public String[] getUnsupportedUnitIds() { return _unsupportedUnitIds; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("OrganizationUri", _organizationUri)
            .add("ProfileId", _profileId)
            .add("ComplianceTool", _complianceTool)
            .add("ComplianceDate", _complianceDate)
            .add("ComplianceLevel", _complianceLevel)
            .add("UnsupportedUnitIds", _unsupportedUnitIds)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<SupportedProfile> {
        @Override
        public SupportedProfile decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            String _organizationUri = reader.readString();
            String _profileId = reader.readString();
            String _complianceTool = reader.readString();
            DateTime _complianceDate = reader.readDateTime();
            ComplianceLevel _complianceLevel = ComplianceLevel.from(reader.readInt32());
            String[] _unsupportedUnitIds = reader.readArray(reader::readString, String.class);

            return new SupportedProfile(_organizationUri, _profileId, _complianceTool, _complianceDate, _complianceLevel, _unsupportedUnitIds);
        }

        @Override
        public void encode(SerializationContext context, SupportedProfile encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeString(encodable._organizationUri);
            writer.writeString(encodable._profileId);
            writer.writeString(encodable._complianceTool);
            writer.writeDateTime(encodable._complianceDate);
            writer.writeInt32(encodable._complianceLevel != null ? encodable._complianceLevel.getValue() : 0);
            writer.writeArray(encodable._unsupportedUnitIds, writer::writeString);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<SupportedProfile> {
        @Override
        public SupportedProfile decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            String _organizationUri = reader.readString("OrganizationUri");
            String _profileId = reader.readString("ProfileId");
            String _complianceTool = reader.readString("ComplianceTool");
            DateTime _complianceDate = reader.readDateTime("ComplianceDate");
            ComplianceLevel _complianceLevel = ComplianceLevel.from(reader.readInt32("ComplianceLevel"));
            String[] _unsupportedUnitIds = reader.readArray("UnsupportedUnitIds", reader::readString, String.class);

            return new SupportedProfile(_organizationUri, _profileId, _complianceTool, _complianceDate, _complianceLevel, _unsupportedUnitIds);
        }

        @Override
        public void encode(SerializationContext context, SupportedProfile encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeString("OrganizationUri", encodable._organizationUri);
            writer.writeString("ProfileId", encodable._profileId);
            writer.writeString("ComplianceTool", encodable._complianceTool);
            writer.writeDateTime("ComplianceDate", encodable._complianceDate);
            writer.writeInt32("ComplianceLevel", encodable._complianceLevel != null ? encodable._complianceLevel.getValue() : 0);
            writer.writeArray("UnsupportedUnitIds", encodable._unsupportedUnitIds, writer::writeString);
        }
    }

}
