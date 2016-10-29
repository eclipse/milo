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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public interface AuditWriteUpdateEventType extends AuditUpdateEventType {

    Property<UInteger> ATTRIBUTE_ID = new BasicProperty<>(
        QualifiedName.parse("0:AttributeId"),
        NodeId.parse("ns=0;i=7"),
        -1,
        UInteger.class
    );

    Property<String> INDEX_RANGE = new BasicProperty<>(
        QualifiedName.parse("0:IndexRange"),
        NodeId.parse("ns=0;i=291"),
        -1,
        String.class
    );

    Property<Object> OLD_VALUE = new BasicProperty<>(
        QualifiedName.parse("0:OldValue"),
        NodeId.parse("ns=0;i=24"),
        -1,
        Object.class
    );

    Property<Object> NEW_VALUE = new BasicProperty<>(
        QualifiedName.parse("0:NewValue"),
        NodeId.parse("ns=0;i=24"),
        -1,
        Object.class
    );

    UInteger getAttributeId();

    PropertyType getAttributeIdNode();

    void setAttributeId(UInteger value);

    String getIndexRange();

    PropertyType getIndexRangeNode();

    void setIndexRange(String value);

    Object getOldValue();

    PropertyType getOldValueNode();

    void setOldValue(Object value);

    Object getNewValue();

    PropertyType getNewValueNode();

    void setNewValue(Object value);
}
