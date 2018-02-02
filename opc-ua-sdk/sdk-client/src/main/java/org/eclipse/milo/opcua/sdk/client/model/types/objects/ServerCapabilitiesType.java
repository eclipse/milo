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

package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.structured.SignedSoftwareCertificate;

public interface ServerCapabilitiesType extends BaseObjectType {
    QualifiedProperty<String[]> SERVER_PROFILE_ARRAY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ServerProfileArray",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.OneDimension,
        String[].class
    );

    QualifiedProperty<String[]> LOCALE_ID_ARRAY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "LocaleIdArray",
        NodeId.parse("ns=0;i=295"),
        ValueRanks.OneDimension,
        String[].class
    );

    QualifiedProperty<Double> MIN_SUPPORTED_SAMPLE_RATE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MinSupportedSampleRate",
        NodeId.parse("ns=0;i=290"),
        ValueRanks.Scalar,
        Double.class
    );

    QualifiedProperty<UShort> MAX_BROWSE_CONTINUATION_POINTS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxBrowseContinuationPoints",
        NodeId.parse("ns=0;i=5"),
        ValueRanks.Scalar,
        UShort.class
    );

    QualifiedProperty<UShort> MAX_QUERY_CONTINUATION_POINTS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxQueryContinuationPoints",
        NodeId.parse("ns=0;i=5"),
        ValueRanks.Scalar,
        UShort.class
    );

    QualifiedProperty<UShort> MAX_HISTORY_CONTINUATION_POINTS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxHistoryContinuationPoints",
        NodeId.parse("ns=0;i=5"),
        ValueRanks.Scalar,
        UShort.class
    );

    QualifiedProperty<SignedSoftwareCertificate[]> SOFTWARE_CERTIFICATES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "SoftwareCertificates",
        NodeId.parse("ns=0;i=344"),
        ValueRanks.OneDimension,
        SignedSoftwareCertificate[].class
    );

    QualifiedProperty<UInteger> MAX_ARRAY_LENGTH = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxArrayLength",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_STRING_LENGTH = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxStringLength",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_BYTE_STRING_LENGTH = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxByteStringLength",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    CompletableFuture<? extends PropertyType> getServerProfileArrayNode();

    CompletableFuture<String[]> getServerProfileArray();

    CompletableFuture<StatusCode> setServerProfileArray(String[] value);

    CompletableFuture<? extends PropertyType> getLocaleIdArrayNode();

    CompletableFuture<String[]> getLocaleIdArray();

    CompletableFuture<StatusCode> setLocaleIdArray(String[] value);

    CompletableFuture<? extends PropertyType> getMinSupportedSampleRateNode();

    CompletableFuture<Double> getMinSupportedSampleRate();

    CompletableFuture<StatusCode> setMinSupportedSampleRate(Double value);

    CompletableFuture<? extends PropertyType> getMaxBrowseContinuationPointsNode();

    CompletableFuture<UShort> getMaxBrowseContinuationPoints();

    CompletableFuture<StatusCode> setMaxBrowseContinuationPoints(UShort value);

    CompletableFuture<? extends PropertyType> getMaxQueryContinuationPointsNode();

    CompletableFuture<UShort> getMaxQueryContinuationPoints();

    CompletableFuture<StatusCode> setMaxQueryContinuationPoints(UShort value);

    CompletableFuture<? extends PropertyType> getMaxHistoryContinuationPointsNode();

    CompletableFuture<UShort> getMaxHistoryContinuationPoints();

    CompletableFuture<StatusCode> setMaxHistoryContinuationPoints(UShort value);

    CompletableFuture<? extends PropertyType> getSoftwareCertificatesNode();

    CompletableFuture<SignedSoftwareCertificate[]> getSoftwareCertificates();

    CompletableFuture<StatusCode> setSoftwareCertificates(SignedSoftwareCertificate[] value);

    CompletableFuture<? extends PropertyType> getMaxArrayLengthNode();

    CompletableFuture<UInteger> getMaxArrayLength();

    CompletableFuture<StatusCode> setMaxArrayLength(UInteger value);

    CompletableFuture<? extends PropertyType> getMaxStringLengthNode();

    CompletableFuture<UInteger> getMaxStringLength();

    CompletableFuture<StatusCode> setMaxStringLength(UInteger value);

    CompletableFuture<? extends PropertyType> getMaxByteStringLengthNode();

    CompletableFuture<UInteger> getMaxByteStringLength();

    CompletableFuture<StatusCode> setMaxByteStringLength(UInteger value);

    CompletableFuture<? extends OperationLimitsType> getOperationLimitsNode();

    CompletableFuture<? extends FolderType> getModellingRulesNode();

    CompletableFuture<? extends FolderType> getAggregateFunctionsNode();
}
