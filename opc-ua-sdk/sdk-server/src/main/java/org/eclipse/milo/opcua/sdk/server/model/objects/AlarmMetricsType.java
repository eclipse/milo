package org.eclipse.milo.opcua.sdk.server.model.objects;

import org.eclipse.milo.opcua.sdk.core.nodes.MethodNode;
import org.eclipse.milo.opcua.sdk.server.model.variables.AlarmRateVariableType;
import org.eclipse.milo.opcua.sdk.server.model.variables.BaseDataVariableType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part9/8.2">https://reference.opcfoundation.org/v105/Core/docs/Part9/8.2</a>
 */
public interface AlarmMetricsType extends BaseObjectType {
    BaseDataVariableType getAlarmCountNode();

    UInteger getAlarmCount();

    void setAlarmCount(UInteger value);

    BaseDataVariableType getStartTimeNode();

    DateTime getStartTime();

    void setStartTime(DateTime value);

    BaseDataVariableType getMaximumActiveStateNode();

    Double getMaximumActiveState();

    void setMaximumActiveState(Double value);

    BaseDataVariableType getMaximumUnAckNode();

    Double getMaximumUnAck();

    void setMaximumUnAck(Double value);

    AlarmRateVariableType getCurrentAlarmRateNode();

    Double getCurrentAlarmRate();

    void setCurrentAlarmRate(Double value);

    AlarmRateVariableType getMaximumAlarmRateNode();

    Double getMaximumAlarmRate();

    void setMaximumAlarmRate(Double value);

    BaseDataVariableType getMaximumReAlarmCountNode();

    UInteger getMaximumReAlarmCount();

    void setMaximumReAlarmCount(UInteger value);

    AlarmRateVariableType getAverageAlarmRateNode();

    Double getAverageAlarmRate();

    void setAverageAlarmRate(Double value);

    MethodNode getResetMethodNode();
}
