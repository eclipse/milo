package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.nodes.MethodNode;
import org.eclipse.milo.opcua.sdk.server.model.variables.BaseDataVariableType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.PubSubState;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.10/#9.1.10.1">https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.10/#9.1.10.1</a>
 */
public interface PubSubStatusType extends BaseObjectType {
    BaseDataVariableType getStateNode();

    PubSubState getState();

    void setState(PubSubState value);

    MethodNode getEnableMethodNode();

    MethodNode getDisableMethodNode();
}
