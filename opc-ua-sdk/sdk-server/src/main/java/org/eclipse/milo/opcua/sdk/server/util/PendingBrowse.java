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

package org.eclipse.milo.opcua.sdk.server.util;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.core.types.structured.BrowseDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseResult;

public class PendingBrowse implements Pending<BrowseDescription, BrowseResult> {

    private final CompletableFuture<BrowseResult> future = new CompletableFuture<>();

    private final BrowseDescription browseDescription;

    public PendingBrowse(BrowseDescription browseDescription) {
        this.browseDescription = browseDescription;
    }

    @Override
    public CompletableFuture<BrowseResult> getFuture() {
        return future;
    }

    @Override
    public BrowseDescription getInput() {
        return browseDescription;
    }

}
