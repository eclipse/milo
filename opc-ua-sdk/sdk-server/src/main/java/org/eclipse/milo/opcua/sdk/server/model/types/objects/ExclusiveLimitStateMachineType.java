package org.eclipse.milo.opcua.sdk.server.model.types.objects;

public interface ExclusiveLimitStateMachineType extends FiniteStateMachineType {
    StateType getHighHighNode();

    StateType getHighNode();

    StateType getLowNode();

    StateType getLowLowNode();

    TransitionType getLowLowToLowNode();

    TransitionType getLowToLowLowNode();

    TransitionType getHighHighToHighNode();

    TransitionType getHighToHighHighNode();
}
