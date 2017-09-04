package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.PerformUpdateType;

public interface AuditHistoryValueUpdateEventType extends AuditHistoryUpdateEventType {
    QualifiedProperty<NodeId> UPDATED_NODE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "UpdatedNode",
        NodeId.parse("ns=0;i=17"),
        ValueRanks.Scalar,
        NodeId.class
    );

    QualifiedProperty<PerformUpdateType> PERFORM_INSERT_REPLACE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "PerformInsertReplace",
        NodeId.parse("ns=0;i=11293"),
        ValueRanks.Scalar,
        PerformUpdateType.class
    );

    QualifiedProperty<DataValue[]> NEW_VALUES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "NewValues",
        NodeId.parse("ns=0;i=23"),
        ValueRanks.OneDimension,
        DataValue[].class
    );

    QualifiedProperty<DataValue[]> OLD_VALUES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "OldValues",
        NodeId.parse("ns=0;i=23"),
        ValueRanks.OneDimension,
        DataValue[].class
    );

    PropertyType getUpdatedNodeNode();

    NodeId getUpdatedNode();

    void setUpdatedNode(NodeId value);

    PropertyType getPerformInsertReplaceNode();

    PerformUpdateType getPerformInsertReplace();

    void setPerformInsertReplace(PerformUpdateType value);

    PropertyType getNewValuesNode();

    DataValue[] getNewValues();

    void setNewValues(DataValue[] value);

    PropertyType getOldValuesNode();

    DataValue[] getOldValues();

    void setOldValues(DataValue[] value);
}
