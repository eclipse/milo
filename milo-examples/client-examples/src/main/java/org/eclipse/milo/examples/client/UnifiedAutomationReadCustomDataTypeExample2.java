/*
 * Copyright (c) 2024 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.examples.client;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.core.types.DynamicEnum;
import org.eclipse.milo.opcua.sdk.core.types.DynamicOptionSet;
import org.eclipse.milo.opcua.sdk.core.types.DynamicStruct;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.Objects.requireNonNull;

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
        client.connect();

        // Read datatype information and register codecs needed to decode/encode custom types.
        client.registerDataTypeCodecs();

        readWriteReadPerson(client);
        readWriteReadWorkOrder(client);
        readWriteCarExtras(client);

        future.complete(client);
    }

    private void readWriteReadPerson(OpcUaClient client) throws Exception {
        NodeId nodeId = NodeId.parse("ns=2;s=Person1");

        DynamicStruct value = readValue(client, nodeId);
        logger.info("Person1: {}", value);

        Random r = new Random();
        DynamicEnum gender = (DynamicEnum) value.getMembers().get("Gender");
        value.getMembers().put("Name", "Fat Boy" + r.nextInt(100));
        value.getMembers().put("Gender", new DynamicEnum(gender.getDataType(), r.nextInt(2)));

        StatusCode status = writeValue(client, nodeId, value);
        System.out.println("write status: " + status);

        value = readValue(client, nodeId);
        logger.info("Person1': {}", value);
    }

    private void readWriteReadWorkOrder(OpcUaClient client) throws Exception {
        NodeId nodeId = NodeId.parse("ns=2;s=Demo.Static.Scalar.WorkOrder");

        DynamicStruct value = readValue(client, nodeId);
        logger.info("WorkOrder: {}", value);

        value.getMembers().put("ID", UUID.randomUUID());

        StatusCode status = writeValue(client, nodeId, value);
        System.out.println("write status: " + status);

        value = readValue(client, nodeId);
        logger.info("WorkOrder': {}", value);
    }

    private void readWriteCarExtras(OpcUaClient client) throws Exception {
        NodeId nodeId = NodeId.parse("ns=2;s=Demo.Static.Scalar.CarExtras");

        DynamicOptionSet value = (DynamicOptionSet) readValue(client, nodeId);
        logger.info("CarExtras: {}", value);

        byte b = requireNonNull(value.getValue().bytes())[0];
        value.setValue(ByteString.of(new byte[]{(byte) ~b}));

        StatusCode status = writeValue(client, nodeId, value);
        System.out.println("write status: " + status);

        value = (DynamicOptionSet) readValue(client, nodeId);
        logger.info("CarExtras': {}", value);
    }

    private static DynamicStruct readValue(OpcUaClient client, NodeId nodeId) throws Exception {
        DataValue dataValue = client.readValues(
            0.0,
            TimestampsToReturn.Neither,
            List.of(nodeId)
        ).get(0);

        ExtensionObject xo = (ExtensionObject) dataValue.getValue().getValue();
        assert xo != null;

        return (DynamicStruct) xo.decode(client.getDynamicEncodingContext());
    }

    private static StatusCode writeValue(OpcUaClient client, NodeId nodeId, DynamicStruct value) throws Exception {
        ExtensionObject xo = ExtensionObject.encodeDefaultBinary(
            client.getDynamicEncodingContext(),
            value,
            value.getDataType().getBinaryEncodingId()
        );

        return client.writeValues(List.of(nodeId), List.of(DataValue.valueOnly(new Variant(xo)))).get(0);
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
