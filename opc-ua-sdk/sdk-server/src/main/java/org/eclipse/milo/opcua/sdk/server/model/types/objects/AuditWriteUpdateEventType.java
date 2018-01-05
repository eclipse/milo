package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public interface AuditWriteUpdateEventType extends AuditUpdateEventType {
    QualifiedProperty<UInteger> ATTRIBUTE_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "AttributeId",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<String> INDEX_RANGE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "IndexRange",
        NodeId.parse("ns=0;i=291"),
        ValueRanks.Scalar,
        String.class
    );

    QualifiedProperty<Object> OLD_VALUE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "OldValue",
        NodeId.parse("ns=0;i=24"),
        ValueRanks.Scalar,
        Object.class
    );

    QualifiedProperty<Object> NEW_VALUE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "NewValue",
        NodeId.parse("ns=0;i=24"),
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
