package org.eclipse.milo.opcua.stack.core.security;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jetbrains.annotations.Nullable;

public class MemoryKeyManager implements KeyManager {

    private final Map<String, KeyRecord> keys = new ConcurrentHashMap<>();

    @Override
    public boolean contains(String alias) throws Exception {
        return keys.containsKey(alias);
    }

    @Override
    public @Nullable KeyRecord get(String alias, String password) throws Exception {
        return keys.get(alias);
    }

    @Override
    public @Nullable KeyRecord remove(String alias, String password) throws Exception {
        return keys.remove(alias);
    }

    @Override
    public void set(String alias, String password, KeyRecord keyRecord) throws Exception {
        keys.put(alias, keyRecord);
    }

}
