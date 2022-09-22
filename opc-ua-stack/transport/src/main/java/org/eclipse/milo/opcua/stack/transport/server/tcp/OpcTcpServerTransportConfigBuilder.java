/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.transport.server.tcp;

import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.channel.EncodingLimits;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class OpcTcpServerTransportConfigBuilder {

    private String bindAddress = "localhost";
    private int bindPort = Stack.DEFAULT_TCP_PORT;
    private EncodingLimits encodingLimits = EncodingLimits.DEFAULT;

    private UInteger helloDeadline = uint(10_000);
    private UInteger minimumSecureChannelLifetime = uint(60_000);
    private UInteger maximumSecureChannelLifetime = uint(60_000 * 60 * 24);

    public OpcTcpServerTransportConfigBuilder setBindAddress(String bindAddress) {
        this.bindAddress = bindAddress;
        return this;
    }

    public OpcTcpServerTransportConfigBuilder setBindPort(int bindPort) {
        this.bindPort = bindPort;
        return this;
    }

    public OpcTcpServerTransportConfigBuilder setEncodingLimits(EncodingLimits encodingLimits) {
        this.encodingLimits = encodingLimits;
        return this;
    }

    public OpcTcpServerTransportConfigBuilder setHelloDeadline(UInteger helloDeadline) {
        this.helloDeadline = helloDeadline;
        return this;
    }

    public OpcTcpServerTransportConfigBuilder setMinimumSecureChannelLifetime(UInteger minimumSecureChannelLifetime) {
        this.minimumSecureChannelLifetime = minimumSecureChannelLifetime;
        return this;
    }

    public OpcTcpServerTransportConfigBuilder setMaximumSecureChannelLifetime(UInteger maximumSecureChannelLifetime) {
        this.maximumSecureChannelLifetime = maximumSecureChannelLifetime;
        return this;
    }

    public OpcTcpServerTransportConfig build() {
        return new OpcTcpServerTransportConfigImpl(
            bindAddress,
            bindPort,
            encodingLimits,
            helloDeadline,
            minimumSecureChannelLifetime,
            maximumSecureChannelLifetime
        );
    }

    static class OpcTcpServerTransportConfigImpl implements OpcTcpServerTransportConfig {

        private final String bindAddress;
        private final int bindPort;
        private final EncodingLimits encodingLimits;
        private final UInteger helloDeadline;
        private final UInteger minimumSecureChannelLifetime;
        private final UInteger maximumSecureChannelLifetime;

        public OpcTcpServerTransportConfigImpl(
            String bindAddress,
            int bindPort,
            EncodingLimits encodingLimits,
            UInteger helloDeadline,
            UInteger minimumSecureChannelLifetime,
            UInteger maximumSecureChannelLifetime
        ) {

            this.bindAddress = bindAddress;
            this.bindPort = bindPort;
            this.encodingLimits = encodingLimits;
            this.helloDeadline = helloDeadline;
            this.minimumSecureChannelLifetime = minimumSecureChannelLifetime;
            this.maximumSecureChannelLifetime = maximumSecureChannelLifetime;
        }

        @Override
        public String getBindAddress() {
            return bindAddress;
        }

        @Override
        public int getBindPort() {
            return bindPort;
        }

        @Override
        public EncodingLimits getEncodingLimits() {
            return encodingLimits;
        }

        @Override
        public UInteger getHelloDeadline() {
            return helloDeadline;
        }

        @Override
        public UInteger getMinimumSecureChannelLifetime() {
            return minimumSecureChannelLifetime;
        }

        @Override
        public UInteger getMaximumSecureChannelLifetime() {
            return maximumSecureChannelLifetime;
        }

    }

}
