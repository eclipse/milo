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

package org.eclipse.milo.opcua.sdk.server.api.config;

import java.util.EnumSet;

import com.google.common.io.Files;
import org.eclipse.milo.opcua.sdk.server.identity.AnonymousIdentityValidator;
import org.eclipse.milo.opcua.stack.core.application.DefaultCertificateManager;
import org.eclipse.milo.opcua.stack.core.application.DefaultCertificateValidator;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.structured.BuildInfo;
import org.testng.annotations.Test;

import static com.google.common.collect.Lists.newArrayList;
import static org.testng.Assert.assertEquals;

public class OpcUaServerConfigTest {

    @Test
    public void testCopy() {
        OpcUaServerConfig original = OpcUaServerConfig.builder()
            .setCertificateManager(new DefaultCertificateManager())
            .setCertificateValidator(new DefaultCertificateValidator(Files.createTempDir()))
            .setSecurityPolicies(EnumSet.of(SecurityPolicy.None, SecurityPolicy.Basic128Rsa15))
            .setBindPort(12345)
            .setBindAddresses(newArrayList("127.0.0.1", "0.0.0.0"))
            .setEndpointAddresses(newArrayList("testHostname"))
            .setIdentityValidator(AnonymousIdentityValidator.INSTANCE)
            .setBuildInfo(new BuildInfo("a", "b", "c", "d", "e", DateTime.MIN_VALUE))
            .setLimits(new OpcUaServerConfigLimits() {})
            .build();

        OpcUaServerConfig copy = OpcUaServerConfig.copy(original).build();

        assertEquals(copy.getSecurityPolicies(), original.getSecurityPolicies());
        assertEquals(copy.getBindPort(), original.getBindPort());
        assertEquals(copy.getBindAddresses(), original.getBindAddresses());
        assertEquals(copy.getEndpointAddresses(), original.getEndpointAddresses());
        assertEquals(copy.getIdentityValidator(), original.getIdentityValidator());
        assertEquals(copy.getBuildInfo(), original.getBuildInfo());
        assertEquals(copy.getLimits(), original.getLimits());
    }

}