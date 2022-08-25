package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.server.model.variables.TwoStateVariableType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part9/5.8.18/#5.8.18.3">https://reference.opcfoundation.org/v105/Core/docs/Part9/5.8.18/#5.8.18.3</a>
 */
public interface ExclusiveLimitAlarmType extends LimitAlarmType {
    TwoStateVariableType getActiveStateNode();

    LocalizedText getActiveState();

    void setActiveState(LocalizedText value);

    ExclusiveLimitStateMachineType getLimitStateNode();
}
