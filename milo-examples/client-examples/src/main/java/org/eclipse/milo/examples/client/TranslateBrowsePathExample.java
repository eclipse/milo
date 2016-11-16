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

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowsePath;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowsePathResult;
import org.eclipse.milo.opcua.stack.core.types.structured.RelativePath;
import org.eclipse.milo.opcua.stack.core.types.structured.RelativePathElement;
import org.eclipse.milo.opcua.stack.core.types.structured.TranslateBrowsePathsToNodeIdsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.collect.Lists.newArrayList;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;

public class TranslateBrowsePathExample implements ClientExample {

    public static void main(String[] args) throws Exception {
        TranslateBrowsePathExample example = new TranslateBrowsePathExample();

        new ClientExampleRunner(example).run();
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void run(OpcUaClient client, CompletableFuture<OpcUaClient> future) throws Exception {
        // synchronous connect
        client.connect().get();

        TranslateBrowsePathsToNodeIdsResponse response = client.translateBrowsePaths(newArrayList(new BrowsePath(
            Identifiers.ObjectsFolder,
            new RelativePath(new RelativePathElement[]{
                new RelativePathElement(
                    Identifiers.HierarchicalReferences,
                    false,
                    true,
                    new QualifiedName(2, "HelloWorld")
                ),
                new RelativePathElement(
                    Identifiers.HierarchicalReferences,
                    false,
                    true,
                    new QualifiedName(2, "ScalarTypes")
                ),
                new RelativePathElement(
                    Identifiers.HierarchicalReferences,
                    false,
                    true,
                    new QualifiedName(2, "UInt64")
                )
            })
        ))).get();

        BrowsePathResult result = l(response.getResults()).get(0);
        StatusCode statusCode = result.getStatusCode();
        logger.info("Status={}", statusCode);

        l(result.getTargets()).forEach(target -> logger.info("TargetId={}", target.getTargetId()));

        future.complete(client);
    }

}
