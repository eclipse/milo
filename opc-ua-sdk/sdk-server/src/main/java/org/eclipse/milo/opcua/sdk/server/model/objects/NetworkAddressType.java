package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.server.model.variables.SelectionListType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.5/#9.1.5.6">https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.5/#9.1.5.6</a>
 */
public interface NetworkAddressType extends BaseObjectType {
    SelectionListType getNetworkInterfaceNode();

    String getNetworkInterface();

    void setNetworkInterface(String value);
}
