package org.eclipse.milo.opcua.sdk.server.events;

import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;

public class ValidationException extends UaException {

    private final DiagnosticInfo diagnosticInfo;

    public ValidationException(long statusCode) {
        this(statusCode, DiagnosticInfo.NULL_VALUE);
    }

    public ValidationException(long statusCode, DiagnosticInfo diagnosticInfo) {
        super(statusCode);
        this.diagnosticInfo = diagnosticInfo;
    }

    public DiagnosticInfo getDiagnosticInfo() {
        return diagnosticInfo;
    }

}
