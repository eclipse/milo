package org.eclipse.milo.opcua.test;

import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.types.DataTypeManager;
import org.eclipse.milo.opcua.test.types.ConcreteTestType;
import org.eclipse.milo.opcua.test.types.ConcreteTestTypeEx;
import org.eclipse.milo.opcua.test.types.StructWithAbstractArrayFields;
import org.eclipse.milo.opcua.test.types.StructWithAbstractScalarFields;
import org.eclipse.milo.opcua.test.types.StructWithArrayFields;
import org.eclipse.milo.opcua.test.types.StructWithArrayFieldsEx;
import org.eclipse.milo.opcua.test.types.StructWithMatrixFields;
import org.eclipse.milo.opcua.test.types.StructWithOptionalArrayFields;
import org.eclipse.milo.opcua.test.types.StructWithOptionalScalarFields;
import org.eclipse.milo.opcua.test.types.StructWithScalarFields;
import org.eclipse.milo.opcua.test.types.StructWithScalarFieldsEx;
import org.eclipse.milo.opcua.test.types.UnionOfArray;
import org.eclipse.milo.opcua.test.types.UnionOfScalar;

public class DataTypeInitializer {
    public void initialize(NamespaceTable namespaceTable, DataTypeManager dataTypeManager) {
        try {
            registerStructCodecs(namespaceTable, dataTypeManager);
        } catch (Exception e) {
            throw new RuntimeException("DataType initialization failed", e);
        }
    }

    private void registerStructCodecs(NamespaceTable namespaceTable, DataTypeManager dataTypeManager)
        throws Exception {
        dataTypeManager.registerType(
            UnionOfArray.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            new UnionOfArray.Codec(),
            UnionOfArray.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            UnionOfArray.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            UnionOfArray.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerType(
            UnionOfScalar.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            new UnionOfScalar.Codec(),
            UnionOfScalar.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            UnionOfScalar.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            UnionOfScalar.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerType(
            ConcreteTestType.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            new ConcreteTestType.Codec(),
            ConcreteTestType.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ConcreteTestType.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ConcreteTestType.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerType(
            ConcreteTestTypeEx.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            new ConcreteTestTypeEx.Codec(),
            ConcreteTestTypeEx.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ConcreteTestTypeEx.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            ConcreteTestTypeEx.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerType(
            StructWithAbstractArrayFields.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            new StructWithAbstractArrayFields.Codec(),
            StructWithAbstractArrayFields.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            StructWithAbstractArrayFields.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            StructWithAbstractArrayFields.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerType(
            StructWithAbstractScalarFields.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            new StructWithAbstractScalarFields.Codec(),
            StructWithAbstractScalarFields.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            StructWithAbstractScalarFields.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            StructWithAbstractScalarFields.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerType(
            StructWithArrayFields.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            new StructWithArrayFields.Codec(),
            StructWithArrayFields.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            StructWithArrayFields.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            StructWithArrayFields.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerType(
            StructWithArrayFieldsEx.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            new StructWithArrayFieldsEx.Codec(),
            StructWithArrayFieldsEx.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            StructWithArrayFieldsEx.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            StructWithArrayFieldsEx.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerType(
            StructWithMatrixFields.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            new StructWithMatrixFields.Codec(),
            StructWithMatrixFields.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            StructWithMatrixFields.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            StructWithMatrixFields.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerType(
            StructWithOptionalArrayFields.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            new StructWithOptionalArrayFields.Codec(),
            StructWithOptionalArrayFields.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            StructWithOptionalArrayFields.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            StructWithOptionalArrayFields.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerType(
            StructWithOptionalScalarFields.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            new StructWithOptionalScalarFields.Codec(),
            StructWithOptionalScalarFields.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            StructWithOptionalScalarFields.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            StructWithOptionalScalarFields.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerType(
            StructWithScalarFields.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            new StructWithScalarFields.Codec(),
            StructWithScalarFields.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            StructWithScalarFields.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            StructWithScalarFields.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
        dataTypeManager.registerType(
            StructWithScalarFieldsEx.TYPE_ID.toNodeIdOrThrow(namespaceTable),
            new StructWithScalarFieldsEx.Codec(),
            StructWithScalarFieldsEx.BINARY_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            StructWithScalarFieldsEx.XML_ENCODING_ID.toNodeIdOrThrow(namespaceTable),
            StructWithScalarFieldsEx.JSON_ENCODING_ID.toNodeIdOrThrow(namespaceTable)
        );
    }
}
