package org.eclipse.milo.opcua.sdk.client.model.nodes.variables;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

public class PropertyTypeNode extends BaseVariableTypeNode implements PropertyType {
    public PropertyTypeNode(OpcUaClient client, NodeId nodeId, NodeClass nodeClass,
                            QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                            UInteger writeMask, UInteger userWriteMask, DataValue value, NodeId dataType,
                            Integer valueRank, UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                            Double minimumSamplingInterval, Boolean historizing) {
        super(client, nodeId, nodeClass, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }
}
