/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.jetbrains.annotations.Nullable;

public class DiagnosticsContext<T> {

    private final Map<T, DiagnosticValues> diagnosticsMap = new ConcurrentHashMap<>();

    public EnumSet<OperationDiagnostic> getRequestedOperationDiagnostics(T t) {
        return null;
    }

    public void setDiagnosticValues(T operationRequest, DiagnosticValues diagnosticValues) {
        diagnosticsMap.put(operationRequest, diagnosticValues);
    }

    public AggregateDiagnosticInfos getDiagnosticInfos(List<T> ts) {
        if (diagnosticsMap.isEmpty()) {
            return new AggregateDiagnosticInfos(Collections.emptyList(), Collections.emptyList());
        } else {
            var stringSet = new LinkedHashSet<String>();

            for (T t : ts) {
                DiagnosticValues diagnosticValues = diagnosticsMap.get(t);

                if (diagnosticValues != null) {
                    String namespaceUri = diagnosticValues.getNamespaceUri();
                    if (namespaceUri != null) {
                        stringSet.add(namespaceUri);
                    }
                    String symbolicId = diagnosticValues.getSymbolicId();
                    if (symbolicId != null) {
                        stringSet.add(symbolicId);
                    }
                    LocalizedText localizedText = diagnosticValues.getLocalizedText();
                    if (localizedText != null) {
                        String locale = localizedText.getLocale();
                        if (locale != null) {
                            stringSet.add(locale);
                        }
                        String text = localizedText.getText();
                        if (text != null) {
                            stringSet.add(text);
                        }
                    }
                }
            }

            var stringTable = new ArrayList<>(stringSet);
            var diagnosticInfos = new ArrayList<DiagnosticInfo>();

            for (T t : ts) {
                DiagnosticValues diagnosticValues = diagnosticsMap.get(t);

                if (diagnosticValues == null) {
                    diagnosticInfos.add(DiagnosticInfo.NULL_VALUE);
                } else {
                    int namespaceUri = stringTable.indexOf(diagnosticValues.getNamespaceUri());
                    int symbolicId = stringTable.indexOf(diagnosticValues.getSymbolicId());

                    int locale = -1;
                    int localizedText = -1;
                    if (diagnosticValues.getLocalizedText() != null) {
                        locale = stringTable.indexOf(diagnosticValues.getLocalizedText().getLocale());
                        localizedText = stringTable.indexOf(diagnosticValues.getLocalizedText().getText());
                    }

                    var diagnosticInfo = new DiagnosticInfo(
                        namespaceUri,
                        symbolicId,
                        locale,
                        localizedText,
                        diagnosticValues.getAdditionalInfo(),
                        diagnosticValues.getInnerStatusCode(),
                        diagnosticValues.getInnerDiagnostics()
                    );

                    diagnosticInfos.add(diagnosticInfo);
                }
            }

            return new AggregateDiagnosticInfos(stringTable, diagnosticInfos);
        }
    }

    public static class DiagnosticValues {

        private final String namespaceUri;
        private final String symbolicId;
        private final LocalizedText localizedText;
        private final String additionalInfo;
        private final StatusCode innerStatusCode;
        private final DiagnosticInfo innerDiagnostics;

        public DiagnosticValues(
            @Nullable String namespaceUri,
            @Nullable String symbolicId,
            @Nullable LocalizedText localizedText,
            @Nullable String additionalInfo
        ) {

            this(namespaceUri, symbolicId, localizedText, additionalInfo, null, null);
        }

        public DiagnosticValues(
            @Nullable String namespaceUri,
            @Nullable String symbolicId,
            @Nullable LocalizedText localizedText,
            @Nullable String additionalInfo,
            @Nullable StatusCode innerStatusCode,
            @Nullable DiagnosticInfo innerDiagnostics
        ) {

            this.namespaceUri = namespaceUri;
            this.symbolicId = symbolicId;
            this.localizedText = localizedText;
            this.additionalInfo = additionalInfo;
            this.innerStatusCode = innerStatusCode;
            this.innerDiagnostics = innerDiagnostics;
        }

        public @Nullable String getNamespaceUri() {
            return namespaceUri;
        }

        public @Nullable String getSymbolicId() {
            return symbolicId;
        }

        public @Nullable LocalizedText getLocalizedText() {
            return localizedText;
        }

        public @Nullable String getAdditionalInfo() {
            return additionalInfo;
        }

        public @Nullable StatusCode getInnerStatusCode() {
            return innerStatusCode;
        }

        public @Nullable DiagnosticInfo getInnerDiagnostics() {
            return innerDiagnostics;
        }

    }

    public static class AggregateDiagnosticInfos {

        private final List<String> stringTable;
        private final List<DiagnosticInfo> diagnosticInfos;

        public AggregateDiagnosticInfos(List<String> stringTable, List<DiagnosticInfo> diagnosticInfos) {
            this.stringTable = stringTable;
            this.diagnosticInfos = diagnosticInfos;
        }

        public List<String> getStringTable() {
            return stringTable;
        }

        public List<DiagnosticInfo> getDiagnosticInfos() {
            return diagnosticInfos;
        }

    }

    public enum OperationDiagnostic {
        SymbolicId,
        LocalizedText,
        InnerStatusCode,
        InnerDiagnostics
    }

}
