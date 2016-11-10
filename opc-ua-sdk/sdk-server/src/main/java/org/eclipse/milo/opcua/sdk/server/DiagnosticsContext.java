/*
 * Copyright (c) 2016 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.sdk.server;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;

public class DiagnosticsContext<T> {

    private final Map<T, DiagnosticInfo> diagnosticsMap = Maps.newConcurrentMap();

    public EnumSet<OperationDiagnostic> getRequestedOperationDiagnostics(T t) {
        return null;
    }

    public void setOperationDiagnostic(T t, DiagnosticInfo diagnosticInfo) {

    }

    public Map<T, DiagnosticInfo> getDiagnosticsMap() {
        return diagnosticsMap;
    }

    public DiagnosticInfo[] getDiagnosticInfos(T[] ts) {
        return getDiagnosticInfos(Arrays.asList(ts));
    }

    public DiagnosticInfo[] getDiagnosticInfos(List<T> ts) {
        if (diagnosticsMap.isEmpty()) {
            return new DiagnosticInfo[0];
        } else {
            DiagnosticInfo[] diagnostics = new DiagnosticInfo[ts.size()];

            for (int i = 0; i < ts.size(); i++) {
                DiagnosticInfo diagnosticInfo = diagnosticsMap.getOrDefault(
                    ts.get(i), DiagnosticInfo.NULL_VALUE);

                diagnostics[i] = diagnosticInfo;
            }

            return diagnostics;
        }
    }

    public enum OperationDiagnostic {
        SymbolicId,
        LocalizedText,
        InnerStatusCode,
        InnerDiagnostics
    }

}
