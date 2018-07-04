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

import static org.eclipse.milo.opcua.stack.core.util.EndpointUtil.updateUrl;
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
        testReplaceUrlHostnameWithScheme("opc.tcp");
        testReplaceUrlHostnameWithScheme("http");
        testReplaceUrlHostnameWithScheme("https");
    }

    @Test
    public void testReplaceUrlPort() {
        testReplaceUrlPortWithScheme("opc.tcp");
        testReplaceUrlPortWithScheme("http");
        testReplaceUrlPortWithScheme("https");
    }

    private void testReplaceUrlHostnameWithScheme(String scheme) {
        assertEquals(
            updateUrl(scheme + "://localhost:4840", "localhost2"),
            scheme + "://localhost2:4840");

        assertEquals(
            updateUrl(scheme + "://localhost:4840/", "localhost2"),
            scheme + "://localhost2:4840/");

        assertEquals(
            updateUrl(scheme + "://localhost:4840/foo", "localhost2"),
            scheme + "://localhost2:4840/foo");

        assertEquals(
            updateUrl(scheme + "://localhost:4840/foo/bar", "localhost2"),
            scheme + "://localhost2:4840/foo/bar");

        assertEquals(
            updateUrl(scheme + "://localhost", "localhost2"),
            scheme + "://localhost2");

        assertEquals(
            updateUrl(scheme + "://localhost/", "localhost2"),
            scheme + "://localhost2/");

        assertEquals(
            updateUrl(scheme + "://localhost/foo", "localhost2"),
            scheme + "://localhost2/foo");

        assertEquals(
            updateUrl(scheme + "://localhost/foo/bar", "localhost2"),
            scheme + "://localhost2/foo/bar");

        assertEquals(
            updateUrl(scheme + "://example.com", "example2.com"),
            scheme + "://example2.com");

        assertEquals(
            updateUrl(scheme + "://example.com/", "example2.com"),
            scheme + "://example2.com/");

        assertEquals(
            updateUrl(scheme + "://example.com/foo", "example2.com"),
            scheme + "://example2.com/foo");

        assertEquals(
            updateUrl(scheme + "://example.com/foo/bar", "example2.com"),
            scheme + "://example2.com/foo/bar");

        assertEquals(
            updateUrl(scheme + "://127.0.0.1", "192.168.0.1"),
            scheme + "://192.168.0.1");

        assertEquals(
            updateUrl(scheme + "://127.0.0.1/", "192.168.0.1"),
            scheme + "://192.168.0.1/");

        assertEquals(
            updateUrl(scheme + "://127.0.0.1/foo", "192.168.0.1"),
            scheme + "://192.168.0.1/foo");

        assertEquals(
            updateUrl(scheme + "://127.0.0.1/foo/bar", "192.168.0.1"),
            scheme + "://192.168.0.1/foo/bar");

        assertEquals(
            updateUrl(scheme + "://127.0.0.1:4840", "192.168.0.1"),
            scheme + "://192.168.0.1:4840");

        assertEquals(
            updateUrl(scheme + "://127.0.0.1:4840/", "192.168.0.1"),
            scheme + "://192.168.0.1:4840/");

        assertEquals(
            updateUrl(scheme + "://127.0.0.1:4840/foo", "192.168.0.1"),
            scheme + "://192.168.0.1:4840/foo");

        assertEquals(
            updateUrl(scheme + "://127.0.0.1:4840/foo/bar", "192.168.0.1"),
            scheme + "://192.168.0.1:4840/foo/bar");
    }

    private void testReplaceUrlPortWithScheme(String scheme) {
        assertEquals(
            updateUrl(scheme + "://localhost:4840", "localhost", 12685),
            scheme + "://localhost:12685");

        assertEquals(
            updateUrl(scheme + "://localhost:4840", null, 12685),
            scheme + "://localhost:12685");

        assertEquals(
            updateUrl(scheme + "://localhost:4840", null, -1),
            scheme + "://localhost:4840");
    }

}
