package org.eclipse.milo.opcua.sdk.core.dtd;

import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.types.DataTypeDictionary2;
import org.eclipse.milo.opcua.stack.core.types.DataTypeManager;

public abstract class DataTypeDictionaryInitializer {

    private static final String BINARY_DICTIONARY_URI = "http://opcfoundation.org/UA/";

    public void initialize(NamespaceTable namespaceTable, DataTypeManager dataTypeManager) {
        DataTypeDictionary2 binaryDictionary =
            dataTypeManager.getTypeDictionary(BINARY_DICTIONARY_URI);

        if (binaryDictionary == null) {
            binaryDictionary = new BinaryDataTypeDictionary(BINARY_DICTIONARY_URI);
        }

        initialize(namespaceTable, dataTypeManager, binaryDictionary);
    }

    public void initialize(
        NamespaceTable namespaceTable,
        DataTypeManager dataTypeManager,
        DataTypeDictionary2 binaryDictionary
    ) {

        try {
            initializeStructs(namespaceTable, binaryDictionary);
        } catch (Exception e) {
            throw new RuntimeException("DataTypeDictionary initialization failed", e);
        }

        dataTypeManager.registerTypeDictionary(binaryDictionary);
    }

    protected abstract void initializeStructs(
        NamespaceTable namespaceTable,
        DataTypeDictionary2 binaryDictionary
    ) throws Exception;

}
