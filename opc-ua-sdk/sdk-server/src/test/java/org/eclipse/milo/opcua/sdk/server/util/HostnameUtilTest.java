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

package org.eclipse.milo.opcua.sdk.server.util;

import java.util.Set;

import org.testng.annotations.Test;

import static org.testng.Assert.assertNotEquals;

public class HostnameUtilTest {

    @Test
    public void testGetHostnames_ExcludeLocal() throws Exception {
        Set<String> hostnames = HostnameUtil.getHostnames("0.0.0.0", false);

        hostnames.forEach(hostname -> {
            assertNotEquals(hostname, "127.0.0.1");
            assertNotEquals(hostname, "localhost");
            System.out.println(hostname);
        });
    }

}