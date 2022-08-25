package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.server.model.variables.StateVariableType;
import org.eclipse.milo.opcua.sdk.server.model.variables.TransitionVariableType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part16/4.4.16">https://reference.opcfoundation.org/v105/Core/docs/Part16/4.4.16</a>
 */
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
