package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.server.model.variables.BaseDataVariableType;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part22/5.2.14">https://reference.opcfoundation.org/v105/Core/docs/Part22/5.2.14</a>
 */
public interface IIeeeTsnVlanTagType extends BaseInterfaceType {
    BaseDataVariableType getVlanIdNode();

    UShort getVlanId();

    void setVlanId(UShort value);

    BaseDataVariableType getPriorityCodePointNode();

    UByte getPriorityCodePoint();

    void setPriorityCodePoint(UByte value);
}
