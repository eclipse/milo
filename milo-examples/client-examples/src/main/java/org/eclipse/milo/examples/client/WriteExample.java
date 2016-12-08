/*
 * Copyright (c) 2016 Kevin Herron
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

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.google.common.collect.ImmutableList;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WriteExample implements ClientExample {

    public static void main(String[] args) throws Exception {
        WriteExample example = new WriteExample();

        new ClientExampleRunner(example).run();
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void run(OpcUaClient client, CompletableFuture<OpcUaClient> future) throws Exception {
        // synchronous connect
        client.connect().get();

        List<NodeId> nodeIds = ImmutableList.of(new NodeId(2, "HelloWorld/ScalarTypes/Int32"));

        for (int i = 0; i < 10; i++) {
            Variant v = new Variant(i);

            // don't write status or timestamps
            DataValue dv = new DataValue(v, null, null);

            // write asynchronously....
            CompletableFuture<List<StatusCode>> f =
                client.writeValues(nodeIds, ImmutableList.of(dv));

            // ...but block for the results so we write in order
            List<StatusCode> statusCodes = f.get();
            StatusCode status = statusCodes.get(0);

            if (status.isGood()) {
                logger.info("Wrote '{}' to nodeId={}", v, nodeIds.get(0));
            }
        }

        future.complete(client);
    }

}
