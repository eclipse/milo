package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.server.model.variables.BaseDataVariableType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part22/5.2.10">https://reference.opcfoundation.org/v105/Core/docs/Part22/5.2.10</a>
 */
public interface IIeeeTsnInterfaceConfigurationType extends BaseInterfaceType {
    BaseDataVariableType getMacAddressNode();

    String getMacAddress();

    void setMacAddress(String value);

    BaseDataVariableType getInterfaceNameNode();

    String getInterfaceName();

    void setInterfaceName(String value);
}
