package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.server.model.types.variables.StateVariableType;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.TransitionVariableType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;

public interface StateMachineType extends BaseObjectType {
    StateVariableType getCurrentStateNode();

    LocalizedText getCurrentState();

    void setCurrentState(LocalizedText value);

    TransitionVariableType getLastTransitionNode();

    LocalizedText getLastTransition();

    void setLastTransition(LocalizedText value);
}
