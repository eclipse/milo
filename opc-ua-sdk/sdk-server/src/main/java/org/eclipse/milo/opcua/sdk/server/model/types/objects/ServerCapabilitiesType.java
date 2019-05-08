/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.structured.SignedSoftwareCertificate;

public interface ServerCapabilitiesType extends BaseObjectType {
    QualifiedProperty<String[]> SERVER_PROFILE_ARRAY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ServerProfileArray",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12"),
        ValueRanks.OneDimension,
        String[].class
    );

    QualifiedProperty<String[]> LOCALE_ID_ARRAY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "LocaleIdArray",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=295"),
        ValueRanks.OneDimension,
        String[].class
    );

    QualifiedProperty<Double> MIN_SUPPORTED_SAMPLE_RATE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MinSupportedSampleRate",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=290"),
        ValueRanks.Scalar,
        Double.class
    );

    QualifiedProperty<UShort> MAX_BROWSE_CONTINUATION_POINTS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxBrowseContinuationPoints",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=5"),
        ValueRanks.Scalar,
        UShort.class
    );

    QualifiedProperty<UShort> MAX_QUERY_CONTINUATION_POINTS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxQueryContinuationPoints",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=5"),
        ValueRanks.Scalar,
        UShort.class
    );

    QualifiedProperty<UShort> MAX_HISTORY_CONTINUATION_POINTS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxHistoryContinuationPoints",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=5"),
        ValueRanks.Scalar,
        UShort.class
    );

    QualifiedProperty<SignedSoftwareCertificate[]> SOFTWARE_CERTIFICATES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "SoftwareCertificates",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=344"),
        ValueRanks.OneDimension,
        SignedSoftwareCertificate[].class
    );

    QualifiedProperty<UInteger> MAX_ARRAY_LENGTH = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxArrayLength",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_STRING_LENGTH = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxStringLength",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_BYTE_STRING_LENGTH = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxByteStringLength",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    PropertyType getServerProfileArrayNode();

    String[] getServerProfileArray();

    void setServerProfileArray(String[] value);

    PropertyType getLocaleIdArrayNode();

    String[] getLocaleIdArray();

    void setLocaleIdArray(String[] value);

    PropertyType getMinSupportedSampleRateNode();

    Double getMinSupportedSampleRate();

    void setMinSupportedSampleRate(Double value);

    PropertyType getMaxBrowseContinuationPointsNode();

    UShort getMaxBrowseContinuationPoints();

    void setMaxBrowseContinuationPoints(UShort value);

    PropertyType getMaxQueryContinuationPointsNode();

    UShort getMaxQueryContinuationPoints();

    void setMaxQueryContinuationPoints(UShort value);

    PropertyType getMaxHistoryContinuationPointsNode();

    UShort getMaxHistoryContinuationPoints();

    void setMaxHistoryContinuationPoints(UShort value);

    PropertyType getSoftwareCertificatesNode();

    SignedSoftwareCertificate[] getSoftwareCertificates();

    void setSoftwareCertificates(SignedSoftwareCertificate[] value);

    PropertyType getMaxArrayLengthNode();

    UInteger getMaxArrayLength();

    void setMaxArrayLength(UInteger value);

    PropertyType getMaxStringLengthNode();

    UInteger getMaxStringLength();

    void setMaxStringLength(UInteger value);

    PropertyType getMaxByteStringLengthNode();

    UInteger getMaxByteStringLength();

    void setMaxByteStringLength(UInteger value);

    OperationLimitsType getOperationLimitsNode();

    FolderType getModellingRulesNode();

    FolderType getAggregateFunctionsNode();
}
