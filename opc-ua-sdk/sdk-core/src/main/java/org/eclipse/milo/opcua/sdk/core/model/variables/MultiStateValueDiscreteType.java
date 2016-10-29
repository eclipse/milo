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

package org.eclipse.milo.opcua.sdk.core.model.variables;

import org.eclipse.milo.opcua.sdk.core.model.BasicProperty;
import org.eclipse.milo.opcua.sdk.core.model.Property;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.structured.EnumValueType;


public interface MultiStateValueDiscreteType extends DiscreteItemType {

    Property<EnumValueType[]> ENUM_VALUES = new BasicProperty<>(
        QualifiedName.parse("0:EnumValues"),
        NodeId.parse("ns=0;i=7594"),
        1,
        EnumValueType[].class
    );

    Property<LocalizedText> VALUE_AS_TEXT = new BasicProperty<>(
        QualifiedName.parse("0:ValueAsText"),
        NodeId.parse("ns=0;i=21"),
        -1,
        LocalizedText.class
    );


    EnumValueType[] getEnumValues();

    PropertyType getEnumValuesNode();

    void setEnumValues(EnumValueType[] value);

    LocalizedText getValueAsText();

    PropertyType getValueAsTextNode();

    void setValueAsText(LocalizedText value);

}
