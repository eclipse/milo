package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

public interface ProgressEventType extends BaseEventType {
    QualifiedProperty<Object> CONTEXT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Context",
        NodeId.parse("ns=0;i=24"),
        ValueRanks.Scalar,
        Object.class
    );

    QualifiedProperty<UShort> PROGRESS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Progress",
        NodeId.parse("ns=0;i=5"),
        ValueRanks.Scalar,
        UShort.class
    );

    PropertyType getContextNode();

    Object getContext();

    void setContext(Object value);

    PropertyType getProgressNode();

    UShort getProgress();

    void setProgress(UShort value);
}
