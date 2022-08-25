package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.server.model.variables.BaseDataVariableType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.5/#9.1.5.7">https://reference.opcfoundation.org/v105/Core/docs/Part14/9.1.5/#9.1.5.7</a>
 */
public interface NetworkAddressUrlType extends NetworkAddressType {
    BaseDataVariableType getUrlNode();

    String getUrl();

    void setUrl(String value);
}
