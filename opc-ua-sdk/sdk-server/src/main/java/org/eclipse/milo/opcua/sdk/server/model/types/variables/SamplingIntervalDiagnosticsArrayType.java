package org.eclipse.milo.opcua.sdk.server.model.types.variables;

import org.eclipse.milo.opcua.stack.core.types.structured.SamplingIntervalDiagnosticsDataType;

public interface SamplingIntervalDiagnosticsArrayType extends BaseDataVariableType {
    SamplingIntervalDiagnosticsType getSamplingIntervalDiagnosticsNode();

    SamplingIntervalDiagnosticsDataType getSamplingIntervalDiagnostics();

    void setSamplingIntervalDiagnostics(SamplingIntervalDiagnosticsDataType value);
}
