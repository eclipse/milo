/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.examples.client;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.DataTypeTreeSessionInitializer;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An example that shows reading the value of a node whose DataType is a custom structure type.
 * <p>
 * Requires the Unified Automation CPP Demo server be running and the endpoint URL be pointing to it.
 */
public class UnifiedAutomationReadCustomDataTypeExample2 implements ClientExample {

    public static void main(String[] args) throws Exception {
        UnifiedAutomationReadCustomDataTypeExample2 example =
            new UnifiedAutomationReadCustomDataTypeExample2();

        new ClientExampleRunner(example, false).run();
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void run(OpcUaClient client, CompletableFuture<OpcUaClient> future) throws Exception {
        // DataTypeTree reads DataTypeDefinition attributes while traversing the type hierarchy
        // and, by default, registers codecs with the client's dynamic DataTypeManager.
        client.addSessionInitializer(new DataTypeTreeSessionInitializer());

        client.connect().get();

        DataValue dataValue = client.readValue(
            0.0,
            TimestampsToReturn.Neither,
            NodeId.parse("ns=2;s=Demo.Static.Scalar.WorkOrder")
        ).get();

        ExtensionObject xo = (ExtensionObject) dataValue.getValue().getValue();

        Object value = xo.decode(client.getDynamicSerializationContext());

        logger.info("value: {}", value);

        future.complete(client);
    }

    @Override
    public String getEndpointUrl() {
        // Change this if UaCPPServer is running somewhere other than localhost.
        return "opc.tcp://localhost:48010";
    }

    @Override
    public SecurityPolicy getSecurityPolicy() {
        return SecurityPolicy.None;
    }

}
