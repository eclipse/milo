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

import org.eclipse.milo.opcua.stack.core.channel.EncodingLimits;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class OpcTcpServerTransportConfigBuilder {

    private EncodingLimits encodingLimits = EncodingLimits.DEFAULT;

    private UInteger helloDeadline = uint(10_000);
    private UInteger minimumSecureChannelLifetime = uint(60_000);
    private UInteger maximumSecureChannelLifetime = uint(60_000 * 60 * 24);

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
            encodingLimits,
            helloDeadline,
            minimumSecureChannelLifetime,
            maximumSecureChannelLifetime
        );
    }

    static class OpcTcpServerTransportConfigImpl implements OpcTcpServerTransportConfig {

        private final EncodingLimits encodingLimits;
        private final UInteger helloDeadline;
        private final UInteger minimumSecureChannelLifetime;
        private final UInteger maximumSecureChannelLifetime;

        public OpcTcpServerTransportConfigImpl(
            EncodingLimits encodingLimits,
            UInteger helloDeadline,
            UInteger minimumSecureChannelLifetime,
            UInteger maximumSecureChannelLifetime
        ) {

            this.encodingLimits = encodingLimits;
            this.helloDeadline = helloDeadline;
            this.minimumSecureChannelLifetime = minimumSecureChannelLifetime;
            this.maximumSecureChannelLifetime = maximumSecureChannelLifetime;
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
