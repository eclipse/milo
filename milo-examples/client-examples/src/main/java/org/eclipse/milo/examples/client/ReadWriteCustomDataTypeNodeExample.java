/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.examples.client;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.examples.server.types.CustomDataType;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.nodes.VariableNode;
import org.eclipse.milo.opcua.stack.core.types.OpcUaDefaultBinaryEncoding;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class ReadWriteCustomDataTypeNodeExample implements ClientExample {

    public static void main(String[] args) throws Exception {
        ReadWriteCustomDataTypeNodeExample example = new ReadWriteCustomDataTypeNodeExample();

        new ClientExampleRunner(example).run();
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void run(OpcUaClient client, CompletableFuture<OpcUaClient> future) throws Exception {
        registerCustomCodec(client);

        // synchronous connect
        client.connect().get();

        // synchronous read request via VariableNode
        VariableNode node = client.getAddressSpace().createVariableNode(
            new NodeId(2, "HelloWorld/CustomDataTypeVariable"));

        logger.info("DataType={}", node.getDataType().get());

        // Read the current value
        DataValue value = node.readValue().get();
        logger.info("Value={}", value);

        Variant variant = value.getValue();
        ExtensionObject xo = (ExtensionObject) variant.getValue();

        CustomDataType decoded = (CustomDataType) xo.decode(
            client.getSerializationContext()
        );
        logger.info("Decoded={}", decoded);

        // Write a modified value
        CustomDataType modified = new CustomDataType(
            decoded.getFoo() + "bar",
            uint(decoded.getBar().intValue() + 1),
            !decoded.isBaz()
        );
        ExtensionObject modifiedXo = ExtensionObject.encode(
            client.getSerializationContext(),
            modified,
            xo.getEncodingId(),
            OpcUaDefaultBinaryEncoding.getInstance()
        );

        StatusCode writeStatus = node.writeValue(new DataValue(new Variant(modifiedXo))).get();

        logger.info("writeStatus={}", writeStatus);

        // Read the modified value back
        value = node.readValue().get();
        logger.info("Value={}", value);

        variant = value.getValue();
        xo = (ExtensionObject) variant.getValue();

        decoded = (CustomDataType) xo.decode(
            client.getSerializationContext()
        );
        logger.info("Decoded={}", decoded);

        future.complete(client);
    }

    private void registerCustomCodec(OpcUaClient client) {
        NodeId binaryEncodingId = new NodeId(2, "DataType.CustomDataType.BinaryEncoding");

        // Register codec with the client DataTypeManager instance
        client.getDataTypeManager().registerCodec(
            binaryEncodingId,
            new CustomDataType.Codec().asBinaryCodec()
        );
    }

}
