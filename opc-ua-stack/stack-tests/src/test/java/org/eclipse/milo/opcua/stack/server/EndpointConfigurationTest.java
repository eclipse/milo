/*
 * Copyright (c) 2018 Kevin Herron
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

package org.eclipse.milo.opcua.stack.server;

import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.transport.TransportProfile;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.testng.annotations.Test;

import static org.testng.Assert.expectThrows;

public class EndpointConfigurationTest {

    @Test
    public void securityMismatchThrows() {
        expectThrows(
            IllegalArgumentException.class,
            () ->
                // mismatch between securityPolicy and securityMode
                EndpointConfiguration.newBuilder()
                    .setSecurityPolicy(SecurityPolicy.Basic128Rsa15)
                    .setSecurityMode(MessageSecurityMode.None)
                    .build()
        );

        expectThrows(
            IllegalArgumentException.class,
            () ->
                // mismatch between securityPolicy and securityMode
                EndpointConfiguration.newBuilder()
                    .setSecurityPolicy(SecurityPolicy.None)
                    .setSecurityMode(MessageSecurityMode.SignAndEncrypt)
                    .build()
        );
    }

    @Test
    public void missingCertificateThrows() {
        expectThrows(
            IllegalStateException.class,
            () ->
                // missing certificate
                EndpointConfiguration.newBuilder()
                    .setSecurityPolicy(SecurityPolicy.Basic128Rsa15)
                    .setSecurityMode(MessageSecurityMode.SignAndEncrypt)
                    .build()
        );
    }

    @Test
    protected void missingTokenPolicyThrows() {
        expectThrows(
            IllegalStateException.class,
            () ->
                // missing UserTokenPolicy
                EndpointConfiguration.newBuilder()
                    .build()
        );
    }

    @Test
    public void unsupportedTransportThrows() {
        expectThrows(
            IllegalStateException.class,
            () ->
                EndpointConfiguration.newBuilder()
                    .setTransportProfile(TransportProfile.HTTPS_UAXML)
                    .build()
        );

        expectThrows(
            IllegalStateException.class,
            () ->
                EndpointConfiguration.newBuilder()
                    .setTransportProfile(TransportProfile.HTTPS_UAJSON)
                    .build()
        );

        expectThrows(
            IllegalStateException.class,
            () ->
                EndpointConfiguration.newBuilder()
                    .setTransportProfile(TransportProfile.WSS_UAJSON)
                    .build()
        );

        expectThrows(
            IllegalStateException.class,
            () ->
                EndpointConfiguration.newBuilder()
                    .setTransportProfile(TransportProfile.WSS_UASC_UABINARY)
                    .build()
        );
    }

}
