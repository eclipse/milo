/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.security;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class DefaultTrustListManagerTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testPruneRejectedCertificateDir() throws Exception {
        File securityTestDir = new File(System.getProperty("java.io.tmpdir"), "securityTest");
        if (!securityTestDir.exists() && !securityTestDir.mkdirs()) {
            throw new Exception("unable to create security temp dir: " + securityTestDir);
        }
        logger.info("using {}", securityTestDir);

        DefaultTrustListManager trustListManager = new DefaultTrustListManager(securityTestDir);

        File rejectedDir = trustListManager.getRejectedDir();

        for (int i = 0; i < DefaultTrustListManager.MAX_REJECTED_CERTIFICATES; i++) {
            File tmp = File.createTempFile("foo", "bar", rejectedDir);
            tmp.deleteOnExit();
        }

        File[] rejectedFiles = rejectedDir.listFiles();
        assertNotNull(rejectedFiles);
        assertEquals(rejectedFiles.length, DefaultTrustListManager.MAX_REJECTED_CERTIFICATES);

        trustListManager.pruneOldRejectedCertificates();
        rejectedFiles = rejectedDir.listFiles();
        assertNotNull(rejectedFiles);
        assertEquals(rejectedFiles.length, DefaultTrustListManager.MAX_REJECTED_CERTIFICATES - 1);
    }

}
