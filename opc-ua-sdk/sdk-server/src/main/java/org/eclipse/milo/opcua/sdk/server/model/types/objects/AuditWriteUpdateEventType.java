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
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public interface AuditWriteUpdateEventType extends AuditUpdateEventType {
    QualifiedProperty<UInteger> ATTRIBUTE_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "AttributeId",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<String> INDEX_RANGE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "IndexRange",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=291"),
        ValueRanks.Scalar,
        String.class
    );

    QualifiedProperty<Object> OLD_VALUE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "OldValue",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=24"),
        ValueRanks.Scalar,
        Object.class
    );

    QualifiedProperty<Object> NEW_VALUE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "NewValue",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=24"),
        ValueRanks.Scalar,
        Object.class
    );

    PropertyType getAttributeIdNode();

    UInteger getAttributeId();

    void setAttributeId(UInteger value);

    PropertyType getIndexRangeNode();

    String getIndexRange();

    void setIndexRange(String value);

    PropertyType getOldValueNode();

    Object getOldValue();

    void setOldValue(Object value);

    PropertyType getNewValueNode();

    Object getNewValue();

    void setNewValue(Object value);
}
