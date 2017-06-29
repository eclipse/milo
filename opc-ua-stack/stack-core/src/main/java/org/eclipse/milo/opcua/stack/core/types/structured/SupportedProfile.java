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
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ComplianceLevel;

public class SupportedProfile implements UaStructure {

    public static final NodeId TypeId = Identifiers.SupportedProfile;
    public static final NodeId BinaryEncodingId = Identifiers.SupportedProfile_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.SupportedProfile_Encoding_DefaultXml;

    protected final String organizationUri;
    protected final String profileId;
    protected final String complianceTool;
    protected final DateTime complianceDate;
    protected final ComplianceLevel complianceLevel;
    protected final String[] unsupportedUnitIds;

    public SupportedProfile() {
        this.organizationUri = null;
        this.profileId = null;
        this.complianceTool = null;
        this.complianceDate = null;
        this.complianceLevel = null;
        this.unsupportedUnitIds = null;
    }

    public SupportedProfile(String organizationUri, String profileId, String complianceTool, DateTime complianceDate, ComplianceLevel complianceLevel, String[] unsupportedUnitIds) {
        this.organizationUri = organizationUri;
        this.profileId = profileId;
        this.complianceTool = complianceTool;
        this.complianceDate = complianceDate;
        this.complianceLevel = complianceLevel;
        this.unsupportedUnitIds = unsupportedUnitIds;
    }

    public String getOrganizationUri() { return organizationUri; }

    public String getProfileId() { return profileId; }

    public String getComplianceTool() { return complianceTool; }

    public DateTime getComplianceDate() { return complianceDate; }

    public ComplianceLevel getComplianceLevel() { return complianceLevel; }

    @Nullable
    public String[] getUnsupportedUnitIds() { return unsupportedUnitIds; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("OrganizationUri", organizationUri)
            .add("ProfileId", profileId)
            .add("ComplianceTool", complianceTool)
            .add("ComplianceDate", complianceDate)
            .add("ComplianceLevel", complianceLevel)
            .add("UnsupportedUnitIds", unsupportedUnitIds)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<SupportedProfile> {

        @Override
        public Class<SupportedProfile> getType() {
            return SupportedProfile.class;
        }

        @Override
        public SupportedProfile decode(UaDecoder decoder) throws UaSerializationException {
            String organizationUri = decoder.readString("OrganizationUri");
            String profileId = decoder.readString("ProfileId");
            String complianceTool = decoder.readString("ComplianceTool");
            DateTime complianceDate = decoder.readDateTime("ComplianceDate");
            ComplianceLevel complianceLevel = ComplianceLevel.from(decoder.readInt32("ComplianceLevel"));
            String[] unsupportedUnitIds = decoder.readArray("UnsupportedUnitIds", decoder::readString, String.class);

            return new SupportedProfile(organizationUri, profileId, complianceTool, complianceDate, complianceLevel, unsupportedUnitIds);
        }

        @Override
        public void encode(SupportedProfile value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeString("OrganizationUri", value.organizationUri);
            encoder.writeString("ProfileId", value.profileId);
            encoder.writeString("ComplianceTool", value.complianceTool);
            encoder.writeDateTime("ComplianceDate", value.complianceDate);
            encoder.writeInt32("ComplianceLevel", value.complianceLevel != null ? value.complianceLevel.getValue() : 0);
            encoder.writeArray("UnsupportedUnitIds", value.unsupportedUnitIds, encoder::writeString);
        }
    }

}
