package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.server.model.variables.BaseDataVariableType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part10/5.2.5/#5.2.5.2">https://reference.opcfoundation.org/v105/Core/docs/Part10/5.2.5/#5.2.5.2</a>
 */
public interface ProgramTransitionEventType extends TransitionEventType {
    BaseDataVariableType getIntermediateResultNode();

    Object getIntermediateResult();

    void setIntermediateResult(Object value);
}
