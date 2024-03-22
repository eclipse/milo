package org.eclipse.milo.opcua.sdk.client.dtd;

import org.eclipse.milo.opcua.sdk.core.dtd.BinaryDataTypeCodec;
import org.opcfoundation.opcua.binaryschema.StructuredType;

public interface BinaryCodecFactory {

    BinaryDataTypeCodec createCodec(StructuredType structuredType);

}
