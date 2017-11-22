/*
 * Copyright (c) 2017 Kevin Herron
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

package org.eclipse.milo.opcua.sdk.server.model.nodes.objects;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.api.ServerNodeMap;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.SessionDiagnosticsArrayNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.SessionSecurityDiagnosticsArrayNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.SessionsDiagnosticsSummaryType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionSecurityDiagnosticsDataType;

public class SessionsDiagnosticsSummaryNode extends BaseObjectNode implements SessionsDiagnosticsSummaryType {
    public SessionsDiagnosticsSummaryNode(ServerNodeMap nodeMap, NodeId nodeId,
                                          QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                          UInteger writeMask, UInteger userWriteMask) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public SessionsDiagnosticsSummaryNode(ServerNodeMap nodeMap, NodeId nodeId,
                                          QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                          UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    public SessionDiagnosticsArrayNode getSessionDiagnosticsArrayNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "SessionDiagnosticsArray");
        return component.map(node -> (SessionDiagnosticsArrayNode) node).orElse(null);
    }

    public SessionDiagnosticsDataType[] getSessionDiagnosticsArray() {
        Optional<VariableNode> component = getVariableComponent("SessionDiagnosticsArray");
        return component.map(node -> (SessionDiagnosticsDataType[]) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setSessionDiagnosticsArray(SessionDiagnosticsDataType[] value) {
        getVariableComponent("SessionDiagnosticsArray").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public SessionSecurityDiagnosticsArrayNode getSessionSecurityDiagnosticsArrayNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "SessionSecurityDiagnosticsArray");
        return component.map(node -> (SessionSecurityDiagnosticsArrayNode) node).orElse(null);
    }

    public SessionSecurityDiagnosticsDataType[] getSessionSecurityDiagnosticsArray() {
        Optional<VariableNode> component = getVariableComponent("SessionSecurityDiagnosticsArray");
        return component.map(node -> (SessionSecurityDiagnosticsDataType[]) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setSessionSecurityDiagnosticsArray(SessionSecurityDiagnosticsDataType[] value) {
        getVariableComponent("SessionSecurityDiagnosticsArray").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }
}
