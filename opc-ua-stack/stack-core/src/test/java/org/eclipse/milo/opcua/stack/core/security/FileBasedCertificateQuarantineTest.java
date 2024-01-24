package org.eclipse.milo.opcua.stack.core.security;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

class FileBasedCertificateQuarantineTest extends CertificateQuarantineTest {

    @Override
    protected CertificateQuarantine newCertificateQuarantine(int maxRejectedCertificates) throws IOException {
        File tempDirectory = Files.createTempDirectory(getClass().getSimpleName()).toFile();
        tempDirectory.deleteOnExit();

        return new FileBasedCertificateQuarantine(tempDirectory, maxRejectedCertificates);
    }

    @Override
    protected int getMaxRejectedCertificates() {
        return 3;
    }

}