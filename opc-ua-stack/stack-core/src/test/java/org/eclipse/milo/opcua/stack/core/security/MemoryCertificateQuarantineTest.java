package org.eclipse.milo.opcua.stack.core.security;

class MemoryCertificateQuarantineTest extends CertificateQuarantineTest {

    @Override
    protected CertificateQuarantine newCertificateQuarantine(int maxRejectedCertificates) {
        return new MemoryCertificateQuarantine(maxRejectedCertificates);
    }

    @Override
    protected int getMaxRejectedCertificates() {
        return 3;
    }

}