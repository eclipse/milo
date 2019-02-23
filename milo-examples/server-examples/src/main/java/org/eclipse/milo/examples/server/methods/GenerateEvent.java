/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.examples.server.methods;

import java.util.UUID;

import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.annotations.UaInputArgument;
import org.eclipse.milo.opcua.sdk.server.annotations.UaMethod;
import org.eclipse.milo.opcua.sdk.server.api.nodes.MethodNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.BaseEventNode;
import org.eclipse.milo.opcua.sdk.server.nodes.factories.EventFactory;
import org.eclipse.milo.opcua.sdk.server.util.AnnotationBasedInvocationHandler;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;

public class GenerateEvent {

    private final OpcUaServer server;
    private final MethodNode methodNode;

    public GenerateEvent(OpcUaServer server, MethodNode methodNode) {
        this.server = server;
        this.methodNode = methodNode;
    }

    @UaMethod
    public void invoke(
        AnnotationBasedInvocationHandler.InvocationContext context,
        @UaInputArgument(
            name = "eventTypeId",
            description = "NodeId of the TypeDefinition of the event to generate.")
            NodeId eventTypeId) {

        EventFactory eventFactory = server.getEventFactory();

        try {
            BaseEventNode eventNode = eventFactory.createEvent(
                new NodeId(1, UUID.randomUUID()),
                eventTypeId
            );

            eventNode.setBrowseName(new QualifiedName(1, "foo"));
            eventNode.setDisplayName(LocalizedText.english("foo"));

            eventNode.setEventId(ByteString.of(new byte[]{0, 1, 2, 3}));
            eventNode.setEventType(Identifiers.BaseEventType);
            eventNode.setSourceNode(methodNode.getNodeId());
            eventNode.setSourceName(methodNode.getDisplayName().getText());
            eventNode.setTime(DateTime.now());
            eventNode.setReceiveTime(DateTime.NULL_VALUE);
            eventNode.setMessage(LocalizedText.english("event message!"));
            eventNode.setSeverity(ushort(2));

            server.getEventBus().post(eventNode);
        } catch (UaException e) {
            context.setFailure(new UaException(e.getStatusCode()));
        }
    }

}
