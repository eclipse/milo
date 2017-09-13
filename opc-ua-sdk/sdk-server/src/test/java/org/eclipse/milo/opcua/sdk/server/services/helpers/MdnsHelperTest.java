package org.eclipse.milo.opcua.sdk.server.services.helpers;

import java.net.InetAddress;
import java.util.Set;

import org.testng.annotations.Test;
import org.testng.collections.Sets;

import static org.testng.Assert.assertEquals;

public class MdnsHelperTest {

    @Test
    public void testGetMostPublicAddress() throws Exception {
        InetAddress loopbackAddress = InetAddress.getLoopbackAddress();
        InetAddress wildcardAddress = InetAddress.getByName("0.0.0.0");

        Set<String> bindAddresses = Sets.newHashSet();
        bindAddresses.add("not_a_valid_host");
        bindAddresses.add(loopbackAddress.getHostAddress());
        bindAddresses.add(wildcardAddress.getHostAddress());

        InetAddress mostPublicAddress = MdnsHelper
            .getMostPublicAddress(bindAddresses)
            .orElseThrow(() -> new Exception("no address returned!"));

        System.out.println("Most public address: " + mostPublicAddress);

        assertEquals(mostPublicAddress, wildcardAddress);
    }

}