package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public interface OperationLimitsType extends FolderType {
    QualifiedProperty<UInteger> MAX_NODES_PER_READ = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxNodesPerRead",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_NODES_PER_HISTORY_READ_DATA = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxNodesPerHistoryReadData",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_NODES_PER_HISTORY_READ_EVENTS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxNodesPerHistoryReadEvents",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_NODES_PER_WRITE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxNodesPerWrite",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_NODES_PER_HISTORY_UPDATE_DATA = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxNodesPerHistoryUpdateData",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_NODES_PER_HISTORY_UPDATE_EVENTS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxNodesPerHistoryUpdateEvents",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_NODES_PER_METHOD_CALL = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxNodesPerMethodCall",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_NODES_PER_BROWSE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxNodesPerBrowse",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_NODES_PER_REGISTER_NODES = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxNodesPerRegisterNodes",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_NODES_PER_TRANSLATE_BROWSE_PATHS_TO_NODE_IDS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxNodesPerTranslateBrowsePathsToNodeIds",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_NODES_PER_NODE_MANAGEMENT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxNodesPerNodeManagement",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    QualifiedProperty<UInteger> MAX_MONITORED_ITEMS_PER_CALL = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxMonitoredItemsPerCall",
        NodeId.parse("ns=0;i=7"),
        ValueRanks.Scalar,
        UInteger.class
    );

    PropertyType getMaxNodesPerReadNode();

    UInteger getMaxNodesPerRead();

    void setMaxNodesPerRead(UInteger value);

    PropertyType getMaxNodesPerHistoryReadDataNode();

    UInteger getMaxNodesPerHistoryReadData();

    void setMaxNodesPerHistoryReadData(UInteger value);

    PropertyType getMaxNodesPerHistoryReadEventsNode();

    UInteger getMaxNodesPerHistoryReadEvents();

    void setMaxNodesPerHistoryReadEvents(UInteger value);

    PropertyType getMaxNodesPerWriteNode();

    UInteger getMaxNodesPerWrite();

    void setMaxNodesPerWrite(UInteger value);

    PropertyType getMaxNodesPerHistoryUpdateDataNode();

    UInteger getMaxNodesPerHistoryUpdateData();

    void setMaxNodesPerHistoryUpdateData(UInteger value);

    PropertyType getMaxNodesPerHistoryUpdateEventsNode();

    UInteger getMaxNodesPerHistoryUpdateEvents();

    void setMaxNodesPerHistoryUpdateEvents(UInteger value);

    PropertyType getMaxNodesPerMethodCallNode();

    UInteger getMaxNodesPerMethodCall();

    void setMaxNodesPerMethodCall(UInteger value);

    PropertyType getMaxNodesPerBrowseNode();

    UInteger getMaxNodesPerBrowse();

    void setMaxNodesPerBrowse(UInteger value);

    PropertyType getMaxNodesPerRegisterNodesNode();

    UInteger getMaxNodesPerRegisterNodes();

    void setMaxNodesPerRegisterNodes(UInteger value);

    PropertyType getMaxNodesPerTranslateBrowsePathsToNodeIdsNode();

    UInteger getMaxNodesPerTranslateBrowsePathsToNodeIds();

    void setMaxNodesPerTranslateBrowsePathsToNodeIds(UInteger value);

    PropertyType getMaxNodesPerNodeManagementNode();

    UInteger getMaxNodesPerNodeManagement();

    void setMaxNodesPerNodeManagement(UInteger value);

    PropertyType getMaxMonitoredItemsPerCallNode();

    UInteger getMaxMonitoredItemsPerCall();

    void setMaxMonitoredItemsPerCall(UInteger value);
}
