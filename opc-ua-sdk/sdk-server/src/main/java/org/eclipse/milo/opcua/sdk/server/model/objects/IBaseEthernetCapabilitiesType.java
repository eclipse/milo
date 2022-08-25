package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.server.model.variables.BaseDataVariableType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part22/5.2.4">https://reference.opcfoundation.org/v105/Core/docs/Part22/5.2.4</a>
 */
public interface IBaseEthernetCapabilitiesType extends BaseInterfaceType {
    BaseDataVariableType getVlanTagCapableNode();

    Boolean getVlanTagCapable();

    void setVlanTagCapable(Boolean value);
}
