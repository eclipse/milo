package org.eclipse.milo.opcua.sdk.server.nodes;

import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.ServerTable;
import org.eclipse.milo.opcua.stack.core.channel.EncodingLimits;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.DataTypeManager;
import org.eclipse.milo.opcua.stack.core.types.OpcUaDataTypeManager;

public class TestSerializationContext implements SerializationContext {

    @Override
    public DataTypeManager getDataTypeManager() {
        return OpcUaDataTypeManager.getInstance();
    }

    @Override
    public EncodingLimits getEncodingLimits() {
        return EncodingLimits.DEFAULT;
    }

    @Override
    public NamespaceTable getNamespaceTable() {
        return new NamespaceTable();
    }

    @Override
    public ServerTable getServerTable() {
        return new ServerTable();
    }

}
