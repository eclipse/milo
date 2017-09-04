package org.eclipse.milo.opcua.sdk.server.model.types.variables;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface TwoStateDiscreteType extends DiscreteItemType {
    QualifiedProperty<LocalizedText> FALSE_STATE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "FalseState",
        NodeId.parse("ns=0;i=21"),
        ValueRanks.Scalar,
        LocalizedText.class
    );

    QualifiedProperty<LocalizedText> TRUE_STATE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "TrueState",
        NodeId.parse("ns=0;i=21"),
        ValueRanks.Scalar,
        LocalizedText.class
    );

    PropertyType getFalseStateNode();

    LocalizedText getFalseState();

    void setFalseState(LocalizedText value);

    PropertyType getTrueStateNode();

    LocalizedText getTrueState();

    void setTrueState(LocalizedText value);
}
