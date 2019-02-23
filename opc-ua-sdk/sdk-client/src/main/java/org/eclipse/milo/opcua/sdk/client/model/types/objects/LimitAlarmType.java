/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public interface LimitAlarmType extends AlarmConditionType {
    QualifiedProperty<Double> HIGH_HIGH_LIMIT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "HighHighLimit",
        NodeId.parse("ns=0;i=11"),
        ValueRanks.Scalar,
        Double.class
    );

    QualifiedProperty<Double> HIGH_LIMIT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "HighLimit",
        NodeId.parse("ns=0;i=11"),
        ValueRanks.Scalar,
        Double.class
    );

    QualifiedProperty<Double> LOW_LIMIT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "LowLimit",
        NodeId.parse("ns=0;i=11"),
        ValueRanks.Scalar,
        Double.class
    );

    QualifiedProperty<Double> LOW_LOW_LIMIT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "LowLowLimit",
        NodeId.parse("ns=0;i=11"),
        ValueRanks.Scalar,
        Double.class
    );

    CompletableFuture<? extends PropertyType> getHighHighLimitNode();

    CompletableFuture<Double> getHighHighLimit();

    CompletableFuture<StatusCode> setHighHighLimit(Double value);

    CompletableFuture<? extends PropertyType> getHighLimitNode();

    CompletableFuture<Double> getHighLimit();

    CompletableFuture<StatusCode> setHighLimit(Double value);

    CompletableFuture<? extends PropertyType> getLowLimitNode();

    CompletableFuture<Double> getLowLimit();

    CompletableFuture<StatusCode> setLowLimit(Double value);

    CompletableFuture<? extends PropertyType> getLowLowLimitNode();

    CompletableFuture<Double> getLowLowLimit();

    CompletableFuture<StatusCode> setLowLowLimit(Double value);
}
