/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.client.UaStackClient;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseNextRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseNextResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseResult;
import org.eclipse.milo.opcua.stack.core.types.structured.ReferenceDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.ViewDescription;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;

/**
 * "Helper" functions for doing a Browse followed by as many BrowseNext calls as are necessary
 * to retrieve all the references.
 */
public class BrowseHelper {

    private BrowseHelper() {}

    public static CompletableFuture<List<ReferenceDescription>> browse(
        OpcUaClient client,
        BrowseDescription browseDescription
    ) {

        return client.getSession().thenCompose(
            session ->
                browse(client.getStackClient(), session, browseDescription)
        );
    }

    public static CompletableFuture<List<ReferenceDescription>> browse(
        UaStackClient client,
        OpcUaSession session,
        BrowseDescription browseDescription
    ) {

        BrowseRequest browseRequest = new BrowseRequest(
            client.newRequestHeader(
                session.getAuthenticationToken(),
                client.getConfig().getRequestTimeout()
            ),
            new ViewDescription(
                NodeId.NULL_VALUE,
                DateTime.MIN_VALUE,
                uint(0)
            ),
            uint(0),
            new BrowseDescription[]{browseDescription}
        );

        return client.sendRequest(browseRequest).thenApply(BrowseResponse.class::cast).thenCompose(response -> {
            BrowseResult result = response.getResults()[0];

            List<ReferenceDescription> references =
                Collections.synchronizedList(new ArrayList<>());

            return maybeBrowseNext(client, session, references, result);
        });
    }

    private static CompletableFuture<List<ReferenceDescription>> maybeBrowseNext(
        UaStackClient client,
        OpcUaSession session,
        List<ReferenceDescription> references,
        BrowseResult result
    ) {

        if (result.getStatusCode().isGood()) {
            references.addAll(l(result.getReferences()));

            ByteString nextContinuationPoint = result.getContinuationPoint();

            if (nextContinuationPoint == null || nextContinuationPoint.isNull()) {
                return CompletableFuture.completedFuture(references);
            } else {
                return browseNext(client, session, nextContinuationPoint, references);
            }
        } else {
            return CompletableFuture.completedFuture(references);
        }
    }

    private static CompletableFuture<List<ReferenceDescription>> browseNext(
        UaStackClient client,
        OpcUaSession session,
        ByteString continuationPoint,
        List<ReferenceDescription> references
    ) {

        BrowseNextRequest browseNextRequest = new BrowseNextRequest(
            client.newRequestHeader(
                session.getAuthenticationToken(),
                client.getConfig().getRequestTimeout()
            ),
            false,
            new ByteString[]{continuationPoint}
        );

        return client.sendRequest(browseNextRequest).thenApply(BrowseNextResponse.class::cast).thenCompose(response -> {
            BrowseResult result = response.getResults()[0];

            return maybeBrowseNext(client, session, references, result);
        });
    }

}
