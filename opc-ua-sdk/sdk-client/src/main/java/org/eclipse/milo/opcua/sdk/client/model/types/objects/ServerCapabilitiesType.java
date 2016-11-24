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

package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.model.BasicProperty;
import org.eclipse.milo.opcua.sdk.core.model.Property;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.structured.SignedSoftwareCertificate;


public interface ServerCapabilitiesType extends BaseObjectType {

    Property<String[]> SERVER_PROFILE_ARRAY = new BasicProperty<>(
        QualifiedName.parse("0:ServerProfileArray"),
        NodeId.parse("ns=0;i=12"),
        1,
        String[].class
    );

    Property<String[]> LOCALE_ID_ARRAY = new BasicProperty<>(
        QualifiedName.parse("0:LocaleIdArray"),
        NodeId.parse("ns=0;i=295"),
        1,
        String[].class
    );

    Property<Double> MIN_SUPPORTED_SAMPLE_RATE = new BasicProperty<>(
        QualifiedName.parse("0:MinSupportedSampleRate"),
        NodeId.parse("ns=0;i=290"),
        -1,
        Double.class
    );

    Property<UShort> MAX_BROWSE_CONTINUATION_POINTS = new BasicProperty<>(
        QualifiedName.parse("0:MaxBrowseContinuationPoints"),
        NodeId.parse("ns=0;i=5"),
        -1,
        UShort.class
    );

    Property<UShort> MAX_QUERY_CONTINUATION_POINTS = new BasicProperty<>(
        QualifiedName.parse("0:MaxQueryContinuationPoints"),
        NodeId.parse("ns=0;i=5"),
        -1,
        UShort.class
    );

    Property<UShort> MAX_HISTORY_CONTINUATION_POINTS = new BasicProperty<>(
        QualifiedName.parse("0:MaxHistoryContinuationPoints"),
        NodeId.parse("ns=0;i=5"),
        -1,
        UShort.class
    );

    Property<SignedSoftwareCertificate[]> SOFTWARE_CERTIFICATES = new BasicProperty<>(
        QualifiedName.parse("0:SoftwareCertificates"),
        NodeId.parse("ns=0;i=344"),
        1,
        SignedSoftwareCertificate[].class
    );

    Property<UInteger> MAX_ARRAY_LENGTH = new BasicProperty<>(
        QualifiedName.parse("0:MaxArrayLength"),
        NodeId.parse("ns=0;i=7"),
        -1,
        UInteger.class
    );

    Property<UInteger> MAX_STRING_LENGTH = new BasicProperty<>(
        QualifiedName.parse("0:MaxStringLength"),
        NodeId.parse("ns=0;i=7"),
        -1,
        UInteger.class
    );

    Property<UInteger> MAX_BYTE_STRING_LENGTH = new BasicProperty<>(
        QualifiedName.parse("0:MaxByteStringLength"),
        NodeId.parse("ns=0;i=7"),
        -1,
        UInteger.class
    );


    CompletableFuture<? extends PropertyType> serverProfileArray();

    CompletableFuture<String[]> getServerProfileArray();

    CompletableFuture<StatusCode> setServerProfileArray(String[] value);

    CompletableFuture<? extends PropertyType> localeIdArray();

    CompletableFuture<String[]> getLocaleIdArray();

    CompletableFuture<StatusCode> setLocaleIdArray(String[] value);

    CompletableFuture<? extends PropertyType> minSupportedSampleRate();

    CompletableFuture<Double> getMinSupportedSampleRate();

    CompletableFuture<StatusCode> setMinSupportedSampleRate(Double value);

    CompletableFuture<? extends PropertyType> maxBrowseContinuationPoints();

    CompletableFuture<UShort> getMaxBrowseContinuationPoints();

    CompletableFuture<StatusCode> setMaxBrowseContinuationPoints(UShort value);

    CompletableFuture<? extends PropertyType> maxQueryContinuationPoints();

    CompletableFuture<UShort> getMaxQueryContinuationPoints();

    CompletableFuture<StatusCode> setMaxQueryContinuationPoints(UShort value);

    CompletableFuture<? extends PropertyType> maxHistoryContinuationPoints();

    CompletableFuture<UShort> getMaxHistoryContinuationPoints();

    CompletableFuture<StatusCode> setMaxHistoryContinuationPoints(UShort value);

    CompletableFuture<? extends PropertyType> softwareCertificates();

    CompletableFuture<SignedSoftwareCertificate[]> getSoftwareCertificates();

    CompletableFuture<StatusCode> setSoftwareCertificates(SignedSoftwareCertificate[] value);

    CompletableFuture<? extends PropertyType> maxArrayLength();

    CompletableFuture<UInteger> getMaxArrayLength();

    CompletableFuture<StatusCode> setMaxArrayLength(UInteger value);

    CompletableFuture<? extends PropertyType> maxStringLength();

    CompletableFuture<UInteger> getMaxStringLength();

    CompletableFuture<StatusCode> setMaxStringLength(UInteger value);

    CompletableFuture<? extends PropertyType> maxByteStringLength();

    CompletableFuture<UInteger> getMaxByteStringLength();

    CompletableFuture<StatusCode> setMaxByteStringLength(UInteger value);

    CompletableFuture<? extends OperationLimitsType> operationLimits();

    CompletableFuture<? extends FolderType> modellingRules();

    CompletableFuture<? extends FolderType> aggregateFunctions();


}