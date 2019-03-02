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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface LimitAlarmType extends AlarmConditionType {
    QualifiedProperty<Double> HIGH_LIMIT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "HighLimit",
        NodeId.parse("ns=0;i=11"),
        ValueRanks.Scalar,
        Double.class
    );

    QualifiedProperty<Double> HIGH_HIGH_LIMIT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "HighHighLimit",
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

    PropertyType getHighLimitNode();

    Double getHighLimit();

    void setHighLimit(Double value);

    PropertyType getHighHighLimitNode();

    Double getHighHighLimit();

    void setHighHighLimit(Double value);

    PropertyType getLowLimitNode();

    Double getLowLimit();

    void setLowLimit(Double value);

    PropertyType getLowLowLimitNode();

    Double getLowLowLimit();

    void setLowLowLimit(Double value);
}
