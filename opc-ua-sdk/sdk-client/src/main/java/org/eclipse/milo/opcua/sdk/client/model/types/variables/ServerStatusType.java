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

package org.eclipse.milo.opcua.sdk.client.model.types.variables;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ServerState;
import org.eclipse.milo.opcua.stack.core.types.structured.BuildInfo;

public interface ServerStatusType extends BaseDataVariableType {
    CompletableFuture<? extends BaseDataVariableType> getStartTimeNode();

    CompletableFuture<DateTime> getStartTime();

    CompletableFuture<StatusCode> setStartTime(DateTime value);

    CompletableFuture<? extends BaseDataVariableType> getCurrentTimeNode();

    CompletableFuture<DateTime> getCurrentTime();

    CompletableFuture<StatusCode> setCurrentTime(DateTime value);

    CompletableFuture<? extends BaseDataVariableType> getStateNode();

    CompletableFuture<ServerState> getState();

    CompletableFuture<StatusCode> setState(ServerState value);

    CompletableFuture<? extends BuildInfoType> getBuildInfoNode();

    CompletableFuture<BuildInfo> getBuildInfo();

    CompletableFuture<StatusCode> setBuildInfo(BuildInfo value);

    CompletableFuture<? extends BaseDataVariableType> getSecondsTillShutdownNode();

    CompletableFuture<UInteger> getSecondsTillShutdown();

    CompletableFuture<StatusCode> setSecondsTillShutdown(UInteger value);

    CompletableFuture<? extends BaseDataVariableType> getShutdownReasonNode();

    CompletableFuture<LocalizedText> getShutdownReason();

    CompletableFuture<StatusCode> setShutdownReason(LocalizedText value);
}
