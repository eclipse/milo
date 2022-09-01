package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.server.model.variables.AnalogUnitType;
import org.eclipse.milo.opcua.sdk.server.model.variables.BaseDataVariableType;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.ULong;
import org.eclipse.milo.opcua.stack.core.types.enumerated.InterfaceAdminStatus;
import org.eclipse.milo.opcua.stack.core.types.enumerated.InterfaceOperStatus;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part22/5.5.1/#5.5.1.2">https://reference.opcfoundation.org/v105/Core/docs/Part22/5.5.1/#5.5.1.2</a>
 */
public interface IetfBaseNetworkInterfaceType extends BaseObjectType {
    BaseDataVariableType getAdminStatusNode();

    InterfaceAdminStatus getAdminStatus();

    void setAdminStatus(InterfaceAdminStatus value);

    BaseDataVariableType getOperStatusNode();

    InterfaceOperStatus getOperStatus();

    void setOperStatus(InterfaceOperStatus value);

    BaseDataVariableType getPhysAddressNode();

    String getPhysAddress();

    void setPhysAddress(String value);

    AnalogUnitType getSpeedNode();

    ULong getSpeed();

    void setSpeed(ULong value);
}