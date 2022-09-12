package org.eclipse.milo.opcua.stack.core.types;

import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaBinaryStreamDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaBinaryStreamEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.OpcUaBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.OpcUaXmlDataTypeCodec;

public abstract class AbstractDataTypeDictionaryInitializer {

    private static final String BINARY_DICTIONARY_URI = "http://opcfoundation.org/UA/";

    private static final String XML_DICTIONARY_URI = "http://opcfoundation.org/UA/2008/02/Types.xsd";

    /**
     * Package-private method to initialize the built-in OPC UA namespace binary and XML dictionaries.
     */
    void initialize(NamespaceTable namespaceTable, DataTypeManager dataTypeManager) {
        OpcUaBinaryDataTypeDictionary binaryDictionary =
            dataTypeManager.getBinaryDataTypeDictionary(BINARY_DICTIONARY_URI);
        if (binaryDictionary == null) {
            binaryDictionary = new OpcUaBinaryDataTypeDictionary(BINARY_DICTIONARY_URI);
        }

        OpcUaXmlDataTypeDictionary xmlDictionary =
            dataTypeManager.getXmlDataTypeDictionary(XML_DICTIONARY_URI);
        if (xmlDictionary == null) {
            xmlDictionary = new OpcUaXmlDataTypeDictionary(XML_DICTIONARY_URI);
        }

        initialize(namespaceTable, dataTypeManager, binaryDictionary, xmlDictionary);
    }

    public void initialize(
        NamespaceTable namespaceTable,
        DataTypeManager dataTypeManager,
        OpcUaBinaryDataTypeDictionary binaryDictionary,
        OpcUaXmlDataTypeDictionary xmlDictionary
    ) {

        try {
            initializeEnums(namespaceTable, binaryDictionary, xmlDictionary);
            initializeStructs(namespaceTable, binaryDictionary, xmlDictionary);
        } catch (Exception e) {
            throw new RuntimeException("DataTypeDictionary initialization failed", e);
        }

        dataTypeManager.registerBinaryTypeDictionary(binaryDictionary);
        dataTypeManager.registerXmlTypeDictionary(xmlDictionary);
    }

    protected abstract void initializeEnums(
        NamespaceTable namespaceTable,
        DataTypeDictionary<OpcUaBinaryDataTypeCodec> binaryDictionary,
        DataTypeDictionary<OpcUaXmlDataTypeCodec> xmlDictionary
    ) throws Exception;

    protected abstract void initializeStructs(
        NamespaceTable namespaceTable,
        DataTypeDictionary<OpcUaBinaryDataTypeCodec> binaryDictionary,
        DataTypeDictionary<OpcUaXmlDataTypeCodec> xmlDictionary
    ) throws Exception;

    protected static class BinaryEnumCodec implements OpcUaBinaryDataTypeCodec {

        @Override
        public Class<?> getType() {
            return Number.class;
        }

        @Override
        public Object decode(SerializationContext context, OpcUaBinaryStreamDecoder decoder) throws UaSerializationException {
            return decoder.readInt32();
        }

        @Override
        public void encode(SerializationContext context, OpcUaBinaryStreamEncoder encoder, Object value) throws UaSerializationException {
            encoder.writeInt32((Integer) value);
        }

    }

}
