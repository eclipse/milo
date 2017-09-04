package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.NetworkGroupDataType;

public interface NonTransparentNetworkRedundancyType extends NonTransparentRedundancyType {
    QualifiedProperty<NetworkGroupDataType[]> SERVER_NETWORK_GROUPS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ServerNetworkGroups",
        NodeId.parse("ns=0;i=11944"),
        ValueRanks.OneDimension,
        NetworkGroupDataType[].class
    );

    PropertyType getServerNetworkGroupsNode();

    NetworkGroupDataType[] getServerNetworkGroups();

    void setServerNetworkGroups(NetworkGroupDataType[] value);
}
