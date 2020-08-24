/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.diagnostics.wrappers.objects;

import org.eclipse.milo.opcua.sdk.server.AbstractLifecycle;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.diagnostics.wrappers.variables.SessionDiagnosticsVariable;
import org.eclipse.milo.opcua.sdk.server.diagnostics.wrappers.variables.SessionSecurityDiagnosticsVariable;
import org.eclipse.milo.opcua.sdk.server.diagnostics.wrappers.variables.SubscriptionDiagnosticsVariableArray;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.SessionDiagnosticsObjectTypeNode;

public class SessionDiagnosticsObject extends AbstractLifecycle {

    private SessionDiagnosticsVariable sessionDiagnosticsVariable;
    private SessionSecurityDiagnosticsVariable sessionSecurityDiagnosticsVariable;
    private SubscriptionDiagnosticsVariableArray subscriptionDiagnosticsVariableArray;


    private final SessionDiagnosticsObjectTypeNode node;
    private final Session session;

    public SessionDiagnosticsObject(SessionDiagnosticsObjectTypeNode node, Session session) {
        this.node = node;
        this.session = session;
    }

    @Override
    protected void onStartup() {
        sessionDiagnosticsVariable = new SessionDiagnosticsVariable(
            node.getSessionDiagnosticsNode(),
            session
        );
        sessionDiagnosticsVariable.startup();

        sessionSecurityDiagnosticsVariable = new SessionSecurityDiagnosticsVariable(
            node.getSessionSecurityDiagnosticsNode(), session
        );
        sessionSecurityDiagnosticsVariable.startup();

        subscriptionDiagnosticsVariableArray = new SubscriptionDiagnosticsVariableArray(
            node.getSubscriptionDiagnosticsArrayNode()
        );
        subscriptionDiagnosticsVariableArray.startup();
    }

    @Override
    protected void onShutdown() {
        sessionDiagnosticsVariable.shutdown();
        sessionSecurityDiagnosticsVariable.shutdown();
        subscriptionDiagnosticsVariableArray.shutdown();

        node.delete();
    }

}
