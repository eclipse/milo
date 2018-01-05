package org.eclipse.milo.opcua.sdk.server.model.types.variables;

import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public interface SamplingIntervalDiagnosticsType extends BaseDataVariableType {
    BaseDataVariableType getSamplingIntervalNode();

    Double getSamplingInterval();

    void setSamplingInterval(Double value);

    BaseDataVariableType getSampledMonitoredItemsCountNode();

    UInteger getSampledMonitoredItemsCount();

    void setSampledMonitoredItemsCount(UInteger value);

    BaseDataVariableType getMaxSampledMonitoredItemsCountNode();

    UInteger getMaxSampledMonitoredItemsCount();

    void setMaxSampledMonitoredItemsCount(UInteger value);

    BaseDataVariableType getDisabledMonitoredItemsSamplingCountNode();

    UInteger getDisabledMonitoredItemsSamplingCount();

    void setDisabledMonitoredItemsSamplingCount(UInteger value);
}
