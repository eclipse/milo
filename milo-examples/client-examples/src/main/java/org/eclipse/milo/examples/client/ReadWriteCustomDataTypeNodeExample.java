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

import org.eclipse.milo.examples.server.types.CustomDataType;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.nodes.VariableNode;
import org.eclipse.milo.opcua.stack.core.types.OpcUaBinaryDataTypeDictionary;
import org.eclipse.milo.opcua.stack.core.types.OpcUaDataTypeManager;
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
        registerCustomCodec();

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

        CustomDataType decoded = xo.decode();
        logger.info("Decoded={}", decoded);

        // Write a modified value
        CustomDataType modified = new CustomDataType(
            decoded.getFoo() + "bar",
            uint(decoded.getBar().intValue() + 1),
            !decoded.isBaz()
        );
        ExtensionObject modifiedXo = ExtensionObject.encode(
            modified,
            xo.getEncodingTypeId()
        );

        StatusCode writeStatus = node.writeValue(new DataValue(new Variant(modifiedXo))).get();

        logger.info("writeStatus={}", writeStatus);

        // Read the modified value back
        value = node.readValue().get();
        logger.info("Value={}", value);

        variant = value.getValue();
        xo = (ExtensionObject) variant.getValue();

        decoded = xo.decode();
        logger.info("Decoded={}", decoded);

        future.complete(client);
    }

    private void registerCustomCodec() {
        // Create a dictionary, binaryEncodingId, and register the codec under that id
        OpcUaBinaryDataTypeDictionary dictionary = new OpcUaBinaryDataTypeDictionary(
            "urn:eclipse:milo:example:custom-data-type"
        );

        NodeId binaryEncodingId = new NodeId(2, "DataType.CustomDataType.BinaryEncoding");

        dictionary.registerStructCodec(
            new CustomDataType.Codec().asBinaryCodec(),
            "CustomDataType",
            binaryEncodingId
        );

        // Register dictionary with the shared DataTypeManager instance
        OpcUaDataTypeManager.getInstance().registerTypeDictionary(dictionary);
    }

}
