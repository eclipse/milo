package org.eclipse.milo.opcua.sdk.server.model.variables;

import org.eclipse.milo.opcua.stack.core.types.structured.SamplingIntervalDiagnosticsDataType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/7.9">https://reference.opcfoundation.org/v105/Core/docs/Part5/7.9</a>
 */
public interface SamplingIntervalDiagnosticsArrayType extends BaseDataVariableType {
    SamplingIntervalDiagnosticsType getSamplingIntervalDiagnosticsNode();

    SamplingIntervalDiagnosticsDataType getSamplingIntervalDiagnostics();

    void setSamplingIntervalDiagnostics(SamplingIntervalDiagnosticsDataType value);
}
