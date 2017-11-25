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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;

public interface AggregateConfigurationType extends BaseObjectType {
    QualifiedProperty<Boolean> TREAT_UNCERTAIN_AS_BAD = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "TreatUncertainAsBad",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<UByte> PERCENT_DATA_BAD = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "PercentDataBad",
        NodeId.parse("ns=0;i=3"),
        ValueRanks.Scalar,
        UByte.class
    );

    QualifiedProperty<UByte> PERCENT_DATA_GOOD = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "PercentDataGood",
        NodeId.parse("ns=0;i=3"),
        ValueRanks.Scalar,
        UByte.class
    );

    QualifiedProperty<Boolean> USE_SLOPED_EXTRAPOLATION = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "UseSlopedExtrapolation",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    CompletableFuture<? extends PropertyType> getTreatUncertainAsBadNode();

    CompletableFuture<Boolean> getTreatUncertainAsBad();

    CompletableFuture<StatusCode> setTreatUncertainAsBad(Boolean value);

    CompletableFuture<? extends PropertyType> getPercentDataBadNode();

    CompletableFuture<UByte> getPercentDataBad();

    CompletableFuture<StatusCode> setPercentDataBad(UByte value);

    CompletableFuture<? extends PropertyType> getPercentDataGoodNode();

    CompletableFuture<UByte> getPercentDataGood();

    CompletableFuture<StatusCode> setPercentDataGood(UByte value);

    CompletableFuture<? extends PropertyType> getUseSlopedExtrapolationNode();

    CompletableFuture<Boolean> getUseSlopedExtrapolation();

    CompletableFuture<StatusCode> setUseSlopedExtrapolation(Boolean value);
}
