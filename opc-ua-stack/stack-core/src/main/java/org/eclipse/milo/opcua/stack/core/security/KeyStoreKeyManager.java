package org.eclipse.milo.opcua.stack.core.security;

import java.nio.file.Path;

import org.jetbrains.annotations.Nullable;

public class KeyStoreKeyManager implements KeyManager {

    public KeyStoreKeyManager(Path pkiDir) {
        // TODO
    }

    public void initialize() throws Exception {
        // TODO
    }

    @Override
    public boolean contains(String alias) throws Exception {
        return false; // TODO
    }

    @Override
    public @Nullable KeyRecord get(String alias, String password) throws Exception {
        return null; // TODO
    }

    @Override
    public @Nullable KeyRecord remove(String alias, String password) throws Exception {
        return null; // TODO
    }

    @Override
    public void set(String alias, String password, KeyRecord keyRecord) throws Exception {
        // TODO
    }

}
