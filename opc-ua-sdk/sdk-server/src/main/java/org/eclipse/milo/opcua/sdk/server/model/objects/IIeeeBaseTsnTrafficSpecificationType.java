package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.server.model.variables.BaseDataVariableType;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.structured.UnsignedRationalNumber;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part22/5.2.8">https://reference.opcfoundation.org/v105/Core/docs/Part22/5.2.8</a>
 */
public interface IIeeeBaseTsnTrafficSpecificationType extends BaseInterfaceType {
    BaseDataVariableType getMaxIntervalFramesNode();

    UShort getMaxIntervalFrames();

    void setMaxIntervalFrames(UShort value);

    BaseDataVariableType getMaxFrameSizeNode();

    UInteger getMaxFrameSize();

    void setMaxFrameSize(UInteger value);

    BaseDataVariableType getIntervalNode();

    UnsignedRationalNumber getInterval();

    void setInterval(UnsignedRationalNumber value);
}
