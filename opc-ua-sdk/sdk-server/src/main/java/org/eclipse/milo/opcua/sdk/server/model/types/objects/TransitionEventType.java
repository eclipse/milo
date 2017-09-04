package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.server.model.types.variables.StateVariableType;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.TransitionVariableType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;

public interface TransitionEventType extends BaseEventType {
    TransitionVariableType getTransitionNode();

    LocalizedText getTransition();

    void setTransition(LocalizedText value);

    StateVariableType getFromStateNode();

    LocalizedText getFromState();

    void setFromState(LocalizedText value);

    StateVariableType getToStateNode();

    LocalizedText getToState();

    void setToState(LocalizedText value);
}
