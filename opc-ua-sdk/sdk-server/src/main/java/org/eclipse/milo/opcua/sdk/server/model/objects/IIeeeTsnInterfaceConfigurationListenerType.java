package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.server.model.variables.BaseDataVariableType;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part22/5.2.12">https://reference.opcfoundation.org/v105/Core/docs/Part22/5.2.12</a>
 */
public interface IIeeeTsnInterfaceConfigurationListenerType extends IIeeeTsnInterfaceConfigurationType {
    BaseDataVariableType getReceiveOffsetNode();

    UInteger getReceiveOffset();

    void setReceiveOffset(UInteger value);
}
