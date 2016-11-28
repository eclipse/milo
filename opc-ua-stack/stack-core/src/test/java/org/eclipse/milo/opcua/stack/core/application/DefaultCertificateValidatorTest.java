/*
 * Copyright (c) 2016 Jens Reimann and others
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

package org.eclipse.milo.opcua.stack.core.application;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DefaultCertificateValidatorTest {

    private Path basePath = Paths.get("certs.test1");

    @Test(timeOut = 5_000)
    public void testLifecycleClose() throws IOException {
        int initialThreadCount = countThreads("init");
        try (DefaultCertificateValidator validator = new DefaultCertificateValidator(this.basePath.toFile())) {
            // no-op
            Assert.assertTrue(countThreads("opened") > initialThreadCount);
        }
        Assert.assertEquals(countThreads("closed"), initialThreadCount);
    }

    private int countThreads(String label) {
        System.out.format("========================= %s =========================%n", label);
        Thread[] threads = Thread.getAllStackTraces().keySet().toArray(new Thread[0]);
        Arrays.sort(threads, Comparator.comparing(Thread::getName));

        for (Thread thread : threads) {
            System.out.println(thread);
        }
        System.out.format("========================= %s =========================%n", label);
        return threads.length;
    }
    
}
