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


public interface LimitAlarmType extends AlarmConditionType {

    Property<Double> HIGH_HIGH_LIMIT = new BasicProperty<>(
        QualifiedName.parse("0:HighHighLimit"),
        NodeId.parse("ns=0;i=11"),
        -1,
        Double.class
    );

    Property<Double> HIGH_LIMIT = new BasicProperty<>(
        QualifiedName.parse("0:HighLimit"),
        NodeId.parse("ns=0;i=11"),
        -1,
        Double.class
    );

    Property<Double> LOW_LIMIT = new BasicProperty<>(
        QualifiedName.parse("0:LowLimit"),
        NodeId.parse("ns=0;i=11"),
        -1,
        Double.class
    );

    Property<Double> LOW_LOW_LIMIT = new BasicProperty<>(
        QualifiedName.parse("0:LowLowLimit"),
        NodeId.parse("ns=0;i=11"),
        -1,
        Double.class
    );


    CompletableFuture<? extends PropertyType> highHighLimit();

    CompletableFuture<Double> getHighHighLimit();

    CompletableFuture<StatusCode> setHighHighLimit(Double value);

    CompletableFuture<? extends PropertyType> highLimit();

    CompletableFuture<Double> getHighLimit();

    CompletableFuture<StatusCode> setHighLimit(Double value);

    CompletableFuture<? extends PropertyType> lowLimit();

    CompletableFuture<Double> getLowLimit();

    CompletableFuture<StatusCode> setLowLimit(Double value);

    CompletableFuture<? extends PropertyType> lowLowLimit();

    CompletableFuture<Double> getLowLowLimit();

    CompletableFuture<StatusCode> setLowLowLimit(Double value);


}