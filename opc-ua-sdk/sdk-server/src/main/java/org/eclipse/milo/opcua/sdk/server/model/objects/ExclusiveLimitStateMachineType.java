package org.eclipse.milo.opcua.sdk.server.model.objects;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part9/5.8.18/#5.8.18.2">https://reference.opcfoundation.org/v105/Core/docs/Part9/5.8.18/#5.8.18.2</a>
 */
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
