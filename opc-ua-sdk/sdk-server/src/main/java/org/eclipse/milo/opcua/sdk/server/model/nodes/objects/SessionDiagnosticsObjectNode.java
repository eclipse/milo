/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.nodes.objects;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.SessionDiagnosticsVariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.SessionSecurityDiagnosticsNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.SubscriptionDiagnosticsArrayNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.SessionDiagnosticsObjectType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
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
    public SessionDiagnosticsObjectNode(UaNodeContext context, NodeId nodeId,
                                        QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                        UInteger writeMask, UInteger userWriteMask) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public SessionDiagnosticsObjectNode(UaNodeContext context, NodeId nodeId,
                                        QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                        UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public SubscriptionDiagnosticsArrayNode getSubscriptionDiagnosticsArrayNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "SubscriptionDiagnosticsArray");
        return (SubscriptionDiagnosticsArrayNode) component.orElse(null);
    }

    @Override
    public SubscriptionDiagnosticsDataType[] getSubscriptionDiagnosticsArray() {
        Optional<VariableNode> component = getVariableComponent("SubscriptionDiagnosticsArray");
        return component.map(node -> (SubscriptionDiagnosticsDataType[]) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setSubscriptionDiagnosticsArray(SubscriptionDiagnosticsDataType[] value) {
        getVariableComponent("SubscriptionDiagnosticsArray").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public SessionDiagnosticsVariableNode getSessionDiagnosticsNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "SessionDiagnostics");
        return (SessionDiagnosticsVariableNode) component.orElse(null);
    }

    @Override
    public SessionDiagnosticsDataType getSessionDiagnostics() {
        Optional<VariableNode> component = getVariableComponent("SessionDiagnostics");
        return component.map(node -> (SessionDiagnosticsDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setSessionDiagnostics(SessionDiagnosticsDataType value) {
        getVariableComponent("SessionDiagnostics").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public SessionSecurityDiagnosticsNode getSessionSecurityDiagnosticsNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "SessionSecurityDiagnostics");
        return (SessionSecurityDiagnosticsNode) component.orElse(null);
    }

    @Override
    public SessionSecurityDiagnosticsDataType getSessionSecurityDiagnostics() {
        Optional<VariableNode> component = getVariableComponent("SessionSecurityDiagnostics");
        return component.map(node -> (SessionSecurityDiagnosticsDataType) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setSessionSecurityDiagnostics(SessionSecurityDiagnosticsDataType value) {
        getVariableComponent("SessionSecurityDiagnostics").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }
}
