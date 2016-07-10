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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;

public interface AggregateConfigurationType extends BaseObjectType {

    Property<Boolean> TREAT_UNCERTAIN_AS_BAD = new BasicProperty<>(
        QualifiedName.parse("0:TreatUncertainAsBad"),
        NodeId.parse("ns=0;i=1"),
        -1,
        Boolean.class
    );

    Property<UByte> PERCENT_DATA_BAD = new BasicProperty<>(
        QualifiedName.parse("0:PercentDataBad"),
        NodeId.parse("ns=0;i=3"),
        -1,
        UByte.class
    );

    Property<UByte> PERCENT_DATA_GOOD = new BasicProperty<>(
        QualifiedName.parse("0:PercentDataGood"),
        NodeId.parse("ns=0;i=3"),
        -1,
        UByte.class
    );

    Property<Boolean> USE_SLOPED_EXTRAPOLATION = new BasicProperty<>(
        QualifiedName.parse("0:UseSlopedExtrapolation"),
        NodeId.parse("ns=0;i=1"),
        -1,
        Boolean.class
    );

    Boolean getTreatUncertainAsBad();

    PropertyType getTreatUncertainAsBadNode();

    void setTreatUncertainAsBad(Boolean value);

    UByte getPercentDataBad();

    PropertyType getPercentDataBadNode();

    void setPercentDataBad(UByte value);

    UByte getPercentDataGood();

    PropertyType getPercentDataGoodNode();

    void setPercentDataGood(UByte value);

    Boolean getUseSlopedExtrapolation();

    PropertyType getUseSlopedExtrapolationNode();

    void setUseSlopedExtrapolation(Boolean value);
}
