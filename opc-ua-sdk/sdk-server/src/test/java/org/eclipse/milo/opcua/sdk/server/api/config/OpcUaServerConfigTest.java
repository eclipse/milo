/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.api.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.eclipse.milo.opcua.sdk.server.identity.AnonymousIdentityValidator;
import org.eclipse.milo.opcua.stack.core.security.DefaultCertificateManager;
import org.eclipse.milo.opcua.stack.core.security.DefaultTrustListManager;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.structured.BuildInfo;
import org.eclipse.milo.opcua.stack.server.security.DefaultServerCertificateValidator;
import org.junit.jupiter.api.Test;

import com.google.common.io.Files;

public class OpcUaServerConfigTest {

    @Test
    public void testCopy() throws IOException {
        DefaultTrustListManager trustListManager = new DefaultTrustListManager(Files.createTempDir());

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        OpcUaServerConfig original = OpcUaServerConfig.builder()
            .setCertificateManager(new DefaultCertificateManager())
            .setTrustListManager(trustListManager)
            .setCertificateValidator(new DefaultServerCertificateValidator(trustListManager))
            .setIdentityValidator(AnonymousIdentityValidator.INSTANCE)
            .setBuildInfo(new BuildInfo("a", "b", "c", "d", "e", DateTime.MIN_VALUE))
            .setLimits(new OpcUaServerConfigLimits() {})
            .setScheduledExecutorService(scheduledExecutorService)
            .build();

        OpcUaServerConfig copy = OpcUaServerConfig.copy(original).build();

        assertEquals(original.getIdentityValidator(), copy.getIdentityValidator());
        assertEquals(original.getBuildInfo(), copy.getBuildInfo());
        assertEquals(original.getLimits(), copy.getLimits());
        assertEquals(original.getScheduledExecutorService(), copy.getScheduledExecutorService());
    }

}
