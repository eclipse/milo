package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface NonTransparentRedundancyType extends ServerRedundancyType {
    QualifiedProperty<String[]> SERVER_URI_ARRAY = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ServerUriArray",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.OneDimension,
        String[].class
    );

    PropertyType getServerUriArrayNode();

    String[] getServerUriArray();

    void setServerUriArray(String[] value);
}
