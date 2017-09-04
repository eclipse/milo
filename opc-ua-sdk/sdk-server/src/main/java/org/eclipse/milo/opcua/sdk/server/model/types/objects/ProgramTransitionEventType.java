package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface ProgramTransitionEventType extends TransitionEventType {
    QualifiedProperty<Object> INTERMEDIATE_RESULT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "IntermediateResult",
        NodeId.parse("ns=0;i=24"),
        ValueRanks.Scalar,
        Object.class
    );

    PropertyType getIntermediateResultNode();

    Object getIntermediateResult();

    void setIntermediateResult(Object value);
}
