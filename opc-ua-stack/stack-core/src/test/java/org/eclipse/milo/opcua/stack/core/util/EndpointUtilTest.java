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

package org.eclipse.milo.opcua.stack.core.util;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class EndpointUtilTest {

    @Test
    public void testGetPath() throws Exception {
        assertEquals(EndpointUtil.getPath("opc.tcp://localhost:4840/foo"), "/foo");
        assertEquals(EndpointUtil.getPath("opc.tcp://localhost:4840/foo/"), "/foo");
    }

    @Test
    public void testGetPath_EmptyAndSlash() throws Exception {
        assertEquals(EndpointUtil.getPath("opc.tcp://localhost:4840"), "/");
        assertEquals(EndpointUtil.getPath("opc.tcp://localhost:4840/"), "/");
    }


    @Test
    public void testGetPath_Invalid() throws Exception {
        assertEquals(EndpointUtil.getPath("opc.tcp://localhost:4840/no spaces allowed"),
            "opc.tcp://localhost:4840/no spaces allowed");
    }

    @Test
    public void testReplaceUrlHostname() {
        assertEquals(
            EndpointUtil.replaceUrlHostname("opc.tcp://localhost:4840", "localhost2"),
            "opc.tcp://localhost2:4840");

        assertEquals(
            EndpointUtil.replaceUrlHostname("opc.tcp://localhost:4840/", "localhost2"),
            "opc.tcp://localhost2:4840/");

        assertEquals(
            EndpointUtil.replaceUrlHostname("opc.tcp://localhost:4840/foo", "localhost2"),
            "opc.tcp://localhost2:4840/foo");

        assertEquals(
            EndpointUtil.replaceUrlHostname("opc.tcp://localhost:4840/foo/bar", "localhost2"),
            "opc.tcp://localhost2:4840/foo/bar");

        assertEquals(
            EndpointUtil.replaceUrlHostname("opc.tcp://localhost", "localhost2"),
            "opc.tcp://localhost2");

        assertEquals(
            EndpointUtil.replaceUrlHostname("opc.tcp://localhost/", "localhost2"),
            "opc.tcp://localhost2/");

        assertEquals(
            EndpointUtil.replaceUrlHostname("opc.tcp://localhost/foo", "localhost2"),
            "opc.tcp://localhost2/foo");

        assertEquals(
            EndpointUtil.replaceUrlHostname("opc.tcp://localhost/foo/bar", "localhost2"),
            "opc.tcp://localhost2/foo/bar");

        assertEquals(
            EndpointUtil.replaceUrlHostname("opc.tcp://example.com", "example2.com"),
            "opc.tcp://example2.com");

        assertEquals(
            EndpointUtil.replaceUrlHostname("opc.tcp://example.com/", "example2.com"),
            "opc.tcp://example2.com/");

        assertEquals(
            EndpointUtil.replaceUrlHostname("opc.tcp://example.com/foo", "example2.com"),
            "opc.tcp://example2.com/foo");

        assertEquals(
            EndpointUtil.replaceUrlHostname("opc.tcp://example.com/foo/bar", "example2.com"),
            "opc.tcp://example2.com/foo/bar");

        assertEquals(
            EndpointUtil.replaceUrlHostname("opc.tcp://127.0.0.1", "192.168.0.1"),
            "opc.tcp://192.168.0.1");

        assertEquals(
            EndpointUtil.replaceUrlHostname("opc.tcp://127.0.0.1/", "192.168.0.1"),
            "opc.tcp://192.168.0.1/");

        assertEquals(
            EndpointUtil.replaceUrlHostname("opc.tcp://127.0.0.1/foo", "192.168.0.1"),
            "opc.tcp://192.168.0.1/foo");

        assertEquals(
            EndpointUtil.replaceUrlHostname("opc.tcp://127.0.0.1/foo/bar", "192.168.0.1"),
            "opc.tcp://192.168.0.1/foo/bar");

        assertEquals(
            EndpointUtil.replaceUrlHostname("opc.tcp://127.0.0.1:4840", "192.168.0.1"),
            "opc.tcp://192.168.0.1:4840");

        assertEquals(
            EndpointUtil.replaceUrlHostname("opc.tcp://127.0.0.1:4840/", "192.168.0.1"),
            "opc.tcp://192.168.0.1:4840/");

        assertEquals(
            EndpointUtil.replaceUrlHostname("opc.tcp://127.0.0.1:4840/foo", "192.168.0.1"),
            "opc.tcp://192.168.0.1:4840/foo");

        assertEquals(
            EndpointUtil.replaceUrlHostname("opc.tcp://127.0.0.1:4840/foo/bar", "192.168.0.1"),
            "opc.tcp://192.168.0.1:4840/foo/bar");
    }

}
