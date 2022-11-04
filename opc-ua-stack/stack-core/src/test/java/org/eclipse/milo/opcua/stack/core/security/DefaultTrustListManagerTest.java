/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.security;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

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

        Files.list(rejectedDir.toPath()).forEach(p -> {
            try {
                Files.delete(p);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        for (int i = 0; i < DefaultTrustListManager.MAX_REJECTED_CERTIFICATES; i++) {
            File tmp = File.createTempFile("foo", "bar", rejectedDir);
            tmp.deleteOnExit();
            // sleep so some of the last modified attributes are different
            Thread.sleep(25);
        }

        File[] rejectedFiles = rejectedDir.listFiles();
        assertNotNull(rejectedFiles);
        assertEquals(rejectedFiles.length, DefaultTrustListManager.MAX_REJECTED_CERTIFICATES);

        trustListManager.pruneOldRejectedCertificates();
        rejectedFiles = rejectedDir.listFiles();
        assertNotNull(rejectedFiles);
        assertEquals(rejectedFiles.length, DefaultTrustListManager.MAX_REJECTED_CERTIFICATES - 1);
    }

    @Test
    public void testSanitizeForUseInFilename_Wildcard() throws Exception {
        String sanitized = DefaultTrustListManager.sanitizeForUseInFilename("*.digitalpetri.com");

        assertEquals(sanitized, "_.digitalpetri.com");
    }

    @Test
    public void testSanitizeForUseInFilename_WindowsReserved() throws Exception {
        /*
        Reserved characters from https://docs.microsoft.com/en-us/windows/win32/fileio/naming-a-file
            < (less than)
            > (greater than)
            : (colon)
            " (double quote)
            / (forward slash)
            \ (backslash)
            | (vertical bar or pipe)
            ? (question mark)
            * (asterisk)
         */
        String sanitized = DefaultTrustListManager.sanitizeForUseInFilename("<>:\"/\\|?*");

        assertEquals(sanitized, "%3C%3E%3A%22%2F%5C%7C%3F_");
    }

}
