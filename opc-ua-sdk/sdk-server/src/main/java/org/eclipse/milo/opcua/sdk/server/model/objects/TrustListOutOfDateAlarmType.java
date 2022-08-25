package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface TrustListOutOfDateAlarmType extends SystemOffNormalAlarmType {
    QualifiedProperty<NodeId> TRUST_LIST_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "TrustListId",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=17"),
        -1,
        NodeId.class
    );

    QualifiedProperty<DateTime> LAST_UPDATE_TIME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "LastUpdateTime",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=294"),
        -1,
        DateTime.class
    );

    QualifiedProperty<Double> UPDATE_FREQUENCY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "UpdateFrequency",
        ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=290"),
        -1,
        Double.class
    );

    NodeId getTrustListId();

    void setTrustListId(NodeId value);

    PropertyType getTrustListIdNode();

    DateTime getLastUpdateTime();

    void setLastUpdateTime(DateTime value);

    PropertyType getLastUpdateTimeNode();

    Double getUpdateFrequency();

    void setUpdateFrequency(Double value);

    PropertyType getUpdateFrequencyNode();
}
