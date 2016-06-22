package org.eclipse.milo.opcua.stack.core.util;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class CryptoRestrictionsTest {

    @Test
    public void testRemove() {
        assertTrue(CryptoRestrictions.remove());
    }

}
