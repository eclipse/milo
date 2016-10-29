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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;


public interface StateVariableType extends BaseDataVariableType {

    Property<Object> ID = new BasicProperty<>(
        QualifiedName.parse("0:Id"),
        NodeId.parse("ns=0;i=24"),
        -1,
        Object.class
    );

    Property<QualifiedName> NAME = new BasicProperty<>(
        QualifiedName.parse("0:Name"),
        NodeId.parse("ns=0;i=20"),
        -1,
        QualifiedName.class
    );

    Property<UInteger> NUMBER = new BasicProperty<>(
        QualifiedName.parse("0:Number"),
        NodeId.parse("ns=0;i=7"),
        -1,
        UInteger.class
    );

    Property<LocalizedText> EFFECTIVE_DISPLAY_NAME = new BasicProperty<>(
        QualifiedName.parse("0:EffectiveDisplayName"),
        NodeId.parse("ns=0;i=21"),
        -1,
        LocalizedText.class
    );


    Object getId();

    PropertyType getIdNode();

    void setId(Object value);

    QualifiedName getName();

    PropertyType getNameNode();

    void setName(QualifiedName value);

    UInteger getNumber();

    PropertyType getNumberNode();

    void setNumber(UInteger value);

    LocalizedText getEffectiveDisplayName();

    PropertyType getEffectiveDisplayNameNode();

    void setEffectiveDisplayName(LocalizedText value);

}
