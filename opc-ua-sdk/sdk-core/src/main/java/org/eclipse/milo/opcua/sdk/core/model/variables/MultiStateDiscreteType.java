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


public interface MultiStateDiscreteType extends DiscreteItemType {

    Property<LocalizedText[]> ENUM_STRINGS = new BasicProperty<>(
        QualifiedName.parse("0:EnumStrings"),
        NodeId.parse("ns=0;i=21"),
        1,
        LocalizedText[].class
    );


    LocalizedText[] getEnumStrings();

    PropertyType getEnumStringsNode();

    void setEnumStrings(LocalizedText[] value);

}
