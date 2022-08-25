package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.nodes.MethodNode;
import org.eclipse.milo.opcua.sdk.server.model.variables.TwoStateVariableType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part9/5.7.2">https://reference.opcfoundation.org/v105/Core/docs/Part9/5.7.2</a>
 */
public interface AcknowledgeableConditionType extends ConditionType {
    TwoStateVariableType getEnabledStateNode();

    LocalizedText getEnabledState();

    void setEnabledState(LocalizedText value);

    TwoStateVariableType getAckedStateNode();

    LocalizedText getAckedState();

    void setAckedState(LocalizedText value);

    TwoStateVariableType getConfirmedStateNode();

    LocalizedText getConfirmedState();

    void setConfirmedState(LocalizedText value);

    MethodNode getAcknowledgeMethodNode();

    MethodNode getConfirmMethodNode();
}
