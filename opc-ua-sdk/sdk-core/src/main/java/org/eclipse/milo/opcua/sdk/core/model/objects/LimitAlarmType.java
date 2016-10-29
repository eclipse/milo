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

package org.eclipse.milo.opcua.sdk.core.model.objects;

import org.eclipse.milo.opcua.sdk.core.model.BasicProperty;
import org.eclipse.milo.opcua.sdk.core.model.Property;
import org.eclipse.milo.opcua.sdk.core.model.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;

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

    Double getHighHighLimit();

    PropertyType getHighHighLimitNode();

    void setHighHighLimit(Double value);

    Double getHighLimit();

    PropertyType getHighLimitNode();

    void setHighLimit(Double value);

    Double getLowLimit();

    PropertyType getLowLimitNode();

    void setLowLimit(Double value);

    Double getLowLowLimit();

    PropertyType getLowLowLimitNode();

    void setLowLowLimit(Double value);
}
