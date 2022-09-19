/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.transport.uasc;

import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.util.Optional;

import io.netty.channel.EventLoopGroup;
import io.netty.util.HashedWheelTimer;
import org.eclipse.milo.opcua.stack.core.channel.EncodingLimits;
import org.eclipse.milo.opcua.stack.core.security.CertificateValidator;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;

public interface UascClientConfig {

    EndpointDescription getEndpoint();

    Optional<KeyPair> getKeyPair();

    Optional<X509Certificate> getCertificate();

    Optional<X509Certificate[]> getCertificateChain();

    CertificateValidator getCertificateValidator();

    EncodingLimits getEncodingLimits();

    UInteger getAcknowledgeTimeout();

    UInteger getRequestTimeout();

    UInteger getChannelLifetime();

    EventLoopGroup getEventLoop();

    HashedWheelTimer getWheelTimer();

}
