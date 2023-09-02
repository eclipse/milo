package org.eclipse.milo.opcua.stack.core.security;

class MemoryKeyManagerTest extends KeyManagerTest {

    @Override
    protected KeyManager newKeyManager() {
        return new MemoryKeyManager();
    }

}