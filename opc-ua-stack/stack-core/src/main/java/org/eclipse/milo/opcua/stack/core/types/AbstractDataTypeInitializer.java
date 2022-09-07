package org.eclipse.milo.opcua.stack.core.types;

import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.OpcUaBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.OpcUaXmlDataTypeCodec;

public abstract class AbstractDataTypeInitializer {

    private static final String BINARY_DICTIONARY_URI = "http://opcfoundation.org/UA/";

    private static final String XML_DICTIONARY_URI = "http://opcfoundation.org/UA/2008/02/Types.xsd";

    /**
     * Package-private method to initialize the built-in OPC UA namespace binary and XML dictionaries.
     */
    void initialize(NamespaceTable namespaceTable, DataTypeManager dataTypeManager) {
        @SuppressWarnings("unchecked")
        DataTypeDictionary<OpcUaBinaryDataTypeCodec<?>> binaryDictionary =
            (DataTypeDictionary<OpcUaBinaryDataTypeCodec<?>>)
                dataTypeManager.getDataTypeDictionary(BINARY_DICTIONARY_URI);
        if (binaryDictionary == null) {
            binaryDictionary = new OpcUaBinaryDataTypeDictionary(BINARY_DICTIONARY_URI);
        }

        @SuppressWarnings("unchecked")
        DataTypeDictionary<OpcUaXmlDataTypeCodec<?>> xmlDictionary =
            (DataTypeDictionary<OpcUaXmlDataTypeCodec<?>>)
                dataTypeManager.getDataTypeDictionary(XML_DICTIONARY_URI);
        if (xmlDictionary == null) {
            xmlDictionary = new OpcUaXmlDataTypeDictionary(XML_DICTIONARY_URI);
        }

        initialize(namespaceTable, dataTypeManager, binaryDictionary, xmlDictionary);
    }

    public void initialize(
        NamespaceTable namespaceTable,
        DataTypeManager dataTypeManager,
        DataTypeDictionary<OpcUaBinaryDataTypeCodec<?>> binaryDictionary,
        DataTypeDictionary<OpcUaXmlDataTypeCodec<?>> xmlDictionary
    ) {

        try {
            initializeEnums(namespaceTable, binaryDictionary, xmlDictionary);
            initializeStructs(namespaceTable, binaryDictionary, xmlDictionary);
        } catch (Exception e) {
            throw new RuntimeException("DataType initialization failed", e);
        }

        dataTypeManager.registerTypeDictionary(binaryDictionary);
        dataTypeManager.registerTypeDictionary(xmlDictionary);
    }

    protected abstract void initializeEnums(
        NamespaceTable namespaceTable,
        DataTypeDictionary<OpcUaBinaryDataTypeCodec<?>> binaryDictionary,
        DataTypeDictionary<OpcUaXmlDataTypeCodec<?>> xmlDictionary
    ) throws Exception;

    protected abstract void initializeStructs(
        NamespaceTable namespaceTable,
        DataTypeDictionary<OpcUaBinaryDataTypeCodec<?>> binaryDictionary,
        DataTypeDictionary<OpcUaXmlDataTypeCodec<?>> xmlDictionary
    ) throws Exception;

}
