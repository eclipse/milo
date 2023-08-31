package org.eclipse.milo.opcua.stack.core.security;

import java.security.PrivateKey;
import java.security.cert.X509Certificate;

public interface KeyManager {

    KeyRecord get(String alias, String password) throws Exception;

    KeyRecord remove(String alias, String password) throws Exception;

    void set(String alias, String password, KeyRecord keyRecord) throws Exception;

    class KeyRecord {
        public final PrivateKey privateKey;
        public final X509Certificate[] certificateChain;

        public KeyRecord(PrivateKey privateKey, X509Certificate[] certificateChain) {
            this.privateKey = privateKey;
            this.certificateChain = certificateChain;
        }
    }

}
