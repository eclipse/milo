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

package org.eclipse.milo.examples.client;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
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
public class UnifiedAutomationReadCustomDataTypeExample implements ClientExample {

    public static void main(String[] args) throws Exception {
        UnifiedAutomationReadCustomDataTypeExample example =
            new UnifiedAutomationReadCustomDataTypeExample();

        new ClientExampleRunner(example).run();
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void run(OpcUaClient client, CompletableFuture<OpcUaClient> future) throws Exception {
        client.connect().get();

        DataValue dataValue = client.readValue(
            0.0,
            TimestampsToReturn.Neither,
            NodeId.parse("ns=2;s=Demo.Static.Scalar.WorkOrder")
        ).get();

        ExtensionObject xo = (ExtensionObject) dataValue.getValue().getValue();

        // Decoding a struct with custom DataType requires a DataTypeManager
        // that has the codec registered with it. OpcUaClient automatically
        // reads any DataTypeDictionary nodes present in the server upon
        // connecting and dynamically generates codecs for custom structures.
        Object value = xo.decode(client.getDataTypeManager());

        logger.info("value: {}", value);

        future.complete(client);
    }

    @Override
    public String getEndpointUrl() {
        // Change this if UaCPPServer is running somewhere other than localhost.
        return "opc.tcp://localhost:48010";
    }

}
