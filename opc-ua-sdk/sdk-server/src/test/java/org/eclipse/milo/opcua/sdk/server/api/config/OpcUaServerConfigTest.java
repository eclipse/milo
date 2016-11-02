package org.eclipse.milo.opcua.sdk.server.api.config;

import java.util.EnumSet;

import com.google.common.collect.Lists;
import com.google.common.io.Files;
import org.eclipse.milo.opcua.sdk.server.identity.AnonymousIdentityValidator;
import org.eclipse.milo.opcua.stack.core.application.DefaultCertificateManager;
import org.eclipse.milo.opcua.stack.core.application.DefaultCertificateValidator;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.structured.BuildInfo;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class OpcUaServerConfigTest {

    @Test
    public void testCopy() {
        OpcUaServerConfig original = OpcUaServerConfig.builder()
            .setCertificateManager(new DefaultCertificateManager())
            .setCertificateValidator(new DefaultCertificateValidator(Files.createTempDir()))
            .setSecurityPolicies(EnumSet.of(SecurityPolicy.None, SecurityPolicy.Basic128Rsa15))
            .setHostname("testHostname")
            .setBindAddresses(Lists.newArrayList("127.0.0.1", "0.0.0.0"))
            .setBindPort(12345)
            .setIdentityValidator(AnonymousIdentityValidator.INSTANCE)
            .setBuildInfo(new BuildInfo("a", "b", "c", "d", "e", DateTime.MIN_VALUE))
            .setLimits(new OpcUaServerConfigLimits() {})
            .build();

        OpcUaServerConfig copy = OpcUaServerConfig.copy(original).build();

        assertEquals(copy.getSecurityPolicies(), original.getSecurityPolicies());
        assertEquals(copy.getHostname(), original.getHostname());
        assertEquals(copy.getBindAddresses(), original.getBindAddresses());
        assertEquals(copy.getIdentityValidator(), original.getIdentityValidator());
        assertEquals(copy.getBuildInfo(), original.getBuildInfo());
        assertEquals(copy.getLimits(), original.getLimits());
    }

}