package org.eclipse.milo.opcua.sdk.server.model.types.variables;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface TwoStateVariableType extends StateVariableType {
    QualifiedProperty<Boolean> ID = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Id",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<DateTime> TRANSITION_TIME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "TransitionTime",
        NodeId.parse("ns=0;i=294"),
        ValueRanks.Scalar,
        DateTime.class
    );

    QualifiedProperty<DateTime> EFFECTIVE_TRANSITION_TIME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "EffectiveTransitionTime",
        NodeId.parse("ns=0;i=294"),
        ValueRanks.Scalar,
        DateTime.class
    );

    QualifiedProperty<LocalizedText> TRUE_STATE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "TrueState",
        NodeId.parse("ns=0;i=21"),
        ValueRanks.Scalar,
        LocalizedText.class
    );

    QualifiedProperty<LocalizedText> FALSE_STATE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "FalseState",
        NodeId.parse("ns=0;i=21"),
        ValueRanks.Scalar,
        LocalizedText.class
    );

    PropertyType getIdNode();

    Boolean getId();

    void setId(Boolean value);

    PropertyType getTransitionTimeNode();

    DateTime getTransitionTime();

    void setTransitionTime(DateTime value);

    PropertyType getEffectiveTransitionTimeNode();

    DateTime getEffectiveTransitionTime();

    void setEffectiveTransitionTime(DateTime value);

    PropertyType getTrueStateNode();

    LocalizedText getTrueState();

    void setTrueState(LocalizedText value);

    PropertyType getFalseStateNode();

    LocalizedText getFalseState();

    void setFalseState(LocalizedText value);
}
