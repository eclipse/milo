package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.server.model.variables.AnalogUnitType;
import org.eclipse.milo.opcua.sdk.server.model.variables.BaseDataVariableType;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.ULong;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.enumerated.Duplex;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part22/5.2.2">https://reference.opcfoundation.org/v105/Core/docs/Part22/5.2.2</a>
 */
public interface IIeeeBaseEthernetPortType extends BaseInterfaceType {
    AnalogUnitType getSpeedNode();

    ULong getSpeed();

    void setSpeed(ULong value);

    BaseDataVariableType getDuplexNode();

    Duplex getDuplex();

    void setDuplex(Duplex value);

    BaseDataVariableType getMaxFrameLengthNode();

    UShort getMaxFrameLength();

    void setMaxFrameLength(UShort value);
}
