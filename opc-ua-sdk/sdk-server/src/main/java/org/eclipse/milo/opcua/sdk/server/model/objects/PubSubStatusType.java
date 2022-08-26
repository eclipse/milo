package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.nodes.MethodNode;
import org.eclipse.milo.opcua.sdk.server.api.methods.AbstractMethodInvocationHandler;
import org.eclipse.milo.opcua.sdk.server.model.variables.BaseDataVariableType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.enumerated.PubSubState;
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.10/#9.1.10.1">https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.10/#9.1.10.1</a>
 */
public interface PubSubStatusType extends BaseObjectType {
    BaseDataVariableType getStateNode();

    PubSubState getState();

    void setState(PubSubState value);

    MethodNode getEnableMethodNode();

    MethodNode getDisableMethodNode();

    abstract class EnableMethod extends AbstractMethodInvocationHandler {
        public EnableMethod(UaMethodNode node) {
            super(node);
        }

        @Override
        public Argument[] getInputArguments() {
            return new Argument[]{};
        }

        @Override
        public Argument[] getOutputArguments() {
            return new Argument[]{};
        }

        @Override
        protected Variant[] invoke(InvocationContext context,
                                   Variant[] inputValues) throws UaException {
            invoke(context);
            return new Variant[]{};
        }

        protected abstract void invoke(InvocationContext context) throws
            UaException;
    }

    abstract class DisableMethod extends AbstractMethodInvocationHandler {
        public DisableMethod(UaMethodNode node) {
            super(node);
        }

        @Override
        public Argument[] getInputArguments() {
            return new Argument[]{};
        }

        @Override
        public Argument[] getOutputArguments() {
            return new Argument[]{};
        }

        @Override
        protected Variant[] invoke(InvocationContext context,
                                   Variant[] inputValues) throws UaException {
            invoke(context);
            return new Variant[]{};
        }

        protected abstract void invoke(InvocationContext context) throws
            UaException;
    }
}
