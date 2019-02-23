/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
