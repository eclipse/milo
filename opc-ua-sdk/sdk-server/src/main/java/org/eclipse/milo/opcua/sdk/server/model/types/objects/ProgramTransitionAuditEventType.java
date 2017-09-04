package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.server.model.types.variables.FiniteTransitionVariableType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;

public interface ProgramTransitionAuditEventType extends AuditUpdateStateEventType {
    FiniteTransitionVariableType getTransitionNode();

    LocalizedText getTransition();

    void setTransition(LocalizedText value);
}
