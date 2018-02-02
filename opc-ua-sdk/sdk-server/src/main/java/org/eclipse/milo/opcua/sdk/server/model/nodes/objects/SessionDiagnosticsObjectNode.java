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
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.SessionDiagnosticsVariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.SessionSecurityDiagnosticsNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.SubscriptionDiagnosticsArrayNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.SessionDiagnosticsObjectType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionSecurityDiagnosticsDataType;
import org.eclipse.milo.opcua.stack.core.types.structured.SubscriptionDiagnosticsDataType;

public class SessionDiagnosticsObjectNode extends BaseObjectNode implements SessionDiagnosticsObjectType {
    public SessionDiagnosticsObjectNode(ServerNodeMap nodeMap, NodeId nodeId,
                                        QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                        UInteger writeMask, UInteger userWriteMask) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public SessionDiagnosticsObjectNode(ServerNodeMap nodeMap, NodeId nodeId,
                                        QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                        UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    public SessionDiagnosticsVariableNode getSessionDiagnosticsNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "SessionDiagnostics");
        return component.map(node -> (SessionDiagnosticsVariableNode) node).orElse(null);
    }

    public SessionDiagnosticsDataType getSessionDiagnostics() {
        Optional<VariableNode> component = getVariableComponent("SessionDiagnostics");
        return component.map(node -> (SessionDiagnosticsDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setSessionDiagnostics(SessionDiagnosticsDataType value) {
        getVariableComponent("SessionDiagnostics").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public SessionSecurityDiagnosticsNode getSessionSecurityDiagnosticsNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "SessionSecurityDiagnostics");
        return component.map(node -> (SessionSecurityDiagnosticsNode) node).orElse(null);
    }

    public SessionSecurityDiagnosticsDataType getSessionSecurityDiagnostics() {
        Optional<VariableNode> component = getVariableComponent("SessionSecurityDiagnostics");
        return component.map(node -> (SessionSecurityDiagnosticsDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setSessionSecurityDiagnostics(SessionSecurityDiagnosticsDataType value) {
        getVariableComponent("SessionSecurityDiagnostics").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public SubscriptionDiagnosticsArrayNode getSubscriptionDiagnosticsArrayNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "SubscriptionDiagnosticsArray");
        return component.map(node -> (SubscriptionDiagnosticsArrayNode) node).orElse(null);
    }

    public SubscriptionDiagnosticsDataType[] getSubscriptionDiagnosticsArray() {
        Optional<VariableNode> component = getVariableComponent("SubscriptionDiagnosticsArray");
        return component.map(node -> (SubscriptionDiagnosticsDataType[]) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setSubscriptionDiagnosticsArray(SubscriptionDiagnosticsDataType[] value) {
        getVariableComponent("SubscriptionDiagnosticsArray").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }
}
