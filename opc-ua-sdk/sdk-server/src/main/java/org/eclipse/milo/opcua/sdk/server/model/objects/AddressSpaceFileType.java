package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.nodes.MethodNode;
import org.eclipse.milo.opcua.sdk.server.api.methods.AbstractMethodInvocationHandler;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/6.3.12">https://reference.opcfoundation.org/v105/Core/docs/Part5/6.3.12</a>
 */
public interface AddressSpaceFileType extends FileType {
    MethodNode getExportNamespaceMethodNode();

    abstract class ExportNamespaceMethod extends AbstractMethodInvocationHandler {
        public ExportNamespaceMethod(UaMethodNode node) {
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
