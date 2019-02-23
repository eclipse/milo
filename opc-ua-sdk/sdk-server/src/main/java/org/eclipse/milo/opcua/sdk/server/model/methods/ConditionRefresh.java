/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.methods;

import java.util.UUID;

import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.annotations.UaInputArgument;
import org.eclipse.milo.opcua.sdk.server.annotations.UaMethod;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.BaseEventNode;
import org.eclipse.milo.opcua.sdk.server.subscriptions.Subscription;
import org.eclipse.milo.opcua.sdk.server.util.AnnotationBasedInvocationHandler;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.util.NonceUtil;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;

public class ConditionRefresh {

    private final OpcUaServer server;

    public ConditionRefresh(OpcUaServer server) {
        this.server = server;
    }

    @UaMethod
    public void invoke(
        AnnotationBasedInvocationHandler.InvocationContext context,

        @UaInputArgument(name = "subscriptionId")
            UInteger subscriptionId) throws UaException {

        Session session = context.getSession().orElse(null);

        if (session != null) {
            Subscription subscription = session.getSubscriptionManager().getSubscription(subscriptionId);

            if (subscription == null) {
                context.setFailure(new UaException(StatusCodes.Bad_SubscriptionIdInvalid));
            } else {
                BaseEventNode refreshStart = server.getEventFactory().createEvent(
                    new NodeId(1, UUID.randomUUID()),
                    Identifiers.RefreshStartEventType
                );

                refreshStart.setBrowseName(new QualifiedName(1, "RefreshStart"));
                refreshStart.setDisplayName(LocalizedText.english("RefreshStart"));
                refreshStart.setEventId(NonceUtil.generateNonce(16));
                refreshStart.setEventType(Identifiers.RefreshStartEventType);
                refreshStart.setSourceNode(Identifiers.Server);
                refreshStart.setSourceName("Server");
                refreshStart.setTime(DateTime.now());
                refreshStart.setReceiveTime(DateTime.NULL_VALUE);
                refreshStart.setMessage(LocalizedText.english("RefreshStart"));
                refreshStart.setSeverity(ushort(0));

                BaseEventNode refreshEnd = server.getEventFactory().createEvent(
                    new NodeId(1, UUID.randomUUID()),
                    Identifiers.RefreshEndEventType
                );

                refreshEnd.setBrowseName(new QualifiedName(1, "RefreshEnd"));
                refreshEnd.setDisplayName(LocalizedText.english("RefreshEnd"));
                refreshEnd.setEventId(NonceUtil.generateNonce(16));
                refreshEnd.setEventType(Identifiers.RefreshEndEventType);
                refreshEnd.setSourceNode(Identifiers.Server);
                refreshEnd.setSourceName("Server");
                refreshEnd.setTime(DateTime.now());
                refreshEnd.setReceiveTime(DateTime.NULL_VALUE);
                refreshEnd.setMessage(LocalizedText.english("RefreshEnd"));
                refreshEnd.setSeverity(ushort(0));

                server.getEventBus().post(refreshStart);
                server.getEventBus().post(refreshEnd);
            }
        } else {
            context.setFailure(new UaException(StatusCodes.Bad_UserAccessDenied));
        }
    }

}
