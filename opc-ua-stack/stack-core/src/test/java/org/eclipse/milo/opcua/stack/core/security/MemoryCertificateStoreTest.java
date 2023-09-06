package org.eclipse.milo.opcua.stack.core.security;

class MemoryCertificateStoreTest extends CertificateStoreTest {

    @Override
    protected CertificateStore newCertificateStore() {
        return new MemoryCertificateStore();
    }

}
