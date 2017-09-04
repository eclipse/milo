package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.RedundantServerDataType;

public interface TransparentRedundancyType extends ServerRedundancyType {
    QualifiedProperty<String> CURRENT_SERVER_ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "CurrentServerId",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    QualifiedProperty<RedundantServerDataType[]> REDUNDANT_SERVER_ARRAY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "RedundantServerArray",
        NodeId.parse("ns=0;i=853"),
        ValueRanks.OneDimension,
        RedundantServerDataType[].class
    );

    PropertyType getCurrentServerIdNode();

    String getCurrentServerId();

    void setCurrentServerId(String value);

    PropertyType getRedundantServerArrayNode();

    RedundantServerDataType[] getRedundantServerArray();

    void setRedundantServerArray(RedundantServerDataType[] value);
}
