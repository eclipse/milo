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

package org.eclipse.milo.opcua.stack.core.channel;

public class ChannelParameters {

    private final int localMaxMessageSize;
    private final int localReceiveBufferSize;
    private final int localSendBufferSize;
    private final int localMaxChunkCount;

    private final int remoteMaxMessageSize;
    private final int remoteReceiveBufferSize;
    private final int remoteSendBufferSize;
    private final int remoteMaxChunkCount;

    public ChannelParameters(int localMaxMessageSize,
                             int localReceiveBufferSize,
                             int localSendBufferSize,
                             int localMaxChunkCount,
                             int remoteMaxMessageSize,
                             int remoteReceiveBufferSize,
                             int remoteSendBufferSize,
                             int remoteMaxChunkCount) {

        this.localMaxMessageSize = localMaxMessageSize;
        this.localReceiveBufferSize = localReceiveBufferSize;
        this.localSendBufferSize = localSendBufferSize;
        this.localMaxChunkCount = localMaxChunkCount;
        this.remoteMaxMessageSize = remoteMaxMessageSize;
        this.remoteReceiveBufferSize = remoteReceiveBufferSize;
        this.remoteSendBufferSize = remoteSendBufferSize;
        this.remoteMaxChunkCount = remoteMaxChunkCount;
    }

    public int getLocalMaxMessageSize() {
        return localMaxMessageSize;
    }

    public int getLocalReceiveBufferSize() {
        return localReceiveBufferSize;
    }

    public int getLocalSendBufferSize() {
        return localSendBufferSize;
    }

    public int getLocalMaxChunkCount() {
        return localMaxChunkCount;
    }

    public int getRemoteMaxMessageSize() {
        return remoteMaxMessageSize;
    }

    public int getRemoteReceiveBufferSize() {
        return remoteReceiveBufferSize;
    }

    public int getRemoteSendBufferSize() {
        return remoteSendBufferSize;
    }

    public int getRemoteMaxChunkCount() {
        return remoteMaxChunkCount;
    }

}
