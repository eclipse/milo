/*
 * Copyright (c) 2024 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.sdk.core.types.json.util;

import org.eclipse.milo.opcua.sdk.core.typetree.DataType;
import org.eclipse.milo.opcua.sdk.core.typetree.DataTypeTree;
import org.eclipse.milo.opcua.stack.core.BuiltinDataType;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.NodeIds;
import org.eclipse.milo.opcua.stack.core.ServerTable;
import org.eclipse.milo.opcua.stack.core.channel.EncodingLimits;
import org.eclipse.milo.opcua.stack.core.encoding.DefaultEncodingManager;
import org.eclipse.milo.opcua.stack.core.encoding.EncodingContext;
import org.eclipse.milo.opcua.stack.core.encoding.EncodingManager;
import org.eclipse.milo.opcua.stack.core.types.DataTypeManager;
import org.eclipse.milo.opcua.stack.core.types.DefaultDataTypeManager;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.structured.XVType;
import org.eclipse.milo.opcua.test.types.AbstractTestType;
import org.eclipse.milo.opcua.test.types.ConcreteTestType;
import org.eclipse.milo.opcua.test.types.ConcreteTestTypeEx;
import org.eclipse.milo.opcua.test.types.StructWithAbstractArrayFields;
import org.eclipse.milo.opcua.test.types.StructWithAbstractScalarFields;
import org.eclipse.milo.opcua.test.types.StructWithArrayFields;
import org.eclipse.milo.opcua.test.types.StructWithArrayFieldsEx;
import org.eclipse.milo.opcua.test.types.StructWithMatrixFields;
import org.eclipse.milo.opcua.test.types.StructWithMatrixFieldsEx;
import org.eclipse.milo.opcua.test.types.StructWithOptionalArrayFields;
import org.eclipse.milo.opcua.test.types.StructWithOptionalScalarFields;
import org.eclipse.milo.opcua.test.types.StructWithScalarFields;
import org.eclipse.milo.opcua.test.types.StructWithScalarFieldsEx;
import org.eclipse.milo.opcua.test.types.TestEnumType;
import org.eclipse.milo.opcua.test.types.UnionOfArray;
import org.eclipse.milo.opcua.test.types.UnionOfScalar;
import org.mockito.Mockito;

public abstract class AbstractEncodingContext implements EncodingContext {

    public static final DataType XV_DATA_TYPE = new AbstractDataType(
        NodeIds.XVType,
        new QualifiedName(0, "XVType"),
        XVType.definition(new NamespaceTable()),
        false
    ) {

        @Override
        public NodeId getBinaryEncodingId() {
            return NodeIds.XVType_Encoding_DefaultBinary;
        }

        @Override
        public NodeId getXmlEncodingId() {
            return NodeIds.XVType_Encoding_DefaultXml;
        }

        @Override
        public NodeId getJsonEncodingId() {
            return NodeIds.XVType_Encoding_DefaultJson;
        }
    };

    protected final DataType abstractTestType;
    protected final DataType concreteTestType;
    protected final DataType concreteTestTypeEx;
    protected final DataType structWithScalarFields;
    protected final DataType structWithScalarFieldsEx;
    protected final DataType structWithArrayFields;
    protected final DataType structWithArrayFieldsEx;
    protected final DataType structWithAbstractScalarFields;
    protected final DataType structWithAbstractArrayFields;
    protected final DataType structWithOptionalScalarFields;
    protected final DataType structWithOptionalArrayFields;
    protected final DataType structWithMatrixFields;
    protected final DataType structWithMatrixFieldsEx;
    protected final DataType unionOfScalar;
    protected final DataType unionOfArray;

    public DataTypeTree dataTypeTree = Mockito.mock(DataTypeTree.class);
    public DataTypeManager dataTypeManager = new DefaultDataTypeManager();

    public NamespaceTable namespaceTable = new NamespaceTable();

    public AbstractEncodingContext() {
        namespaceTable.add("https://github.com/eclipse/milo/DataTypeTest");

        Mockito.when(dataTypeTree.getDataType(XV_DATA_TYPE.getNodeId()))
            .thenReturn(XV_DATA_TYPE);

        dataTypeManager.registerType(
            XV_DATA_TYPE.getNodeId(),
            new XVType.Codec(),
            XV_DATA_TYPE.getBinaryEncodingId(),
            XV_DATA_TYPE.getXmlEncodingId(),
            XV_DATA_TYPE.getJsonEncodingId()
        );

        abstractTestType = setupAbstractTestType();
        concreteTestType = setupConcreteTestType();
        concreteTestTypeEx = setupConcreteTestTypeEx();
        structWithScalarFields = setupStructWithScalarFields();
        structWithScalarFieldsEx = setupStructWithScalarFieldsEx();
        structWithArrayFields = setupStructWithArrayFields();
        structWithArrayFieldsEx = setupStructWithArrayFieldsEx();
        structWithAbstractScalarFields = setupStructWithAbstractScalarFields();
        structWithAbstractArrayFields = setupStructWithAbstractArrayFields();
        structWithOptionalScalarFields = setupStructWithOptionalScalarFields();
        structWithOptionalArrayFields = setupStructWithOptionalArrayFields();
        structWithMatrixFields = setupStructWithMatrixFields();
        structWithMatrixFieldsEx = setupStructWithMatrixFieldsEx();
        unionOfScalar = setupUnionOfScalar();
        unionOfArray = setupUnionOfArray();
    }

    @Override
    public DataTypeManager getDataTypeManager() {
        return dataTypeManager;
    }

    @Override
    public EncodingManager getEncodingManager() {
        return DefaultEncodingManager.createAndInitialize();
    }

    @Override
    public EncodingLimits getEncodingLimits() {
        return EncodingLimits.DEFAULT;
    }

    @Override
    public NamespaceTable getNamespaceTable() {
        return namespaceTable;
    }

    @Override
    public ServerTable getServerTable() {
        return new ServerTable();
    }

    protected DataType setupAbstractTestType() {
        NodeId typeId = AbstractTestType.TYPE_ID.toNodeId(getNamespaceTable()).orElseThrow();

        var dataType = new AbstractDataType(
            typeId,
            new QualifiedName(1, "AbstractTestType"),
            AbstractTestType.definition(getNamespaceTable()),
            true
        ) {

            @Override
            public NodeId getBinaryEncodingId() {
                return NodeId.NULL_VALUE;
            }

            @Override
            public NodeId getXmlEncodingId() {
                return NodeId.NULL_VALUE;
            }

            @Override
            public NodeId getJsonEncodingId() {
                return NodeId.NULL_VALUE;
            }
        };

        Mockito.when(dataTypeTree.getDataType(typeId)).thenReturn(dataType);
        Mockito.when(dataTypeTree.isStructType(typeId)).thenReturn(true);

        return dataType;
    }

    protected DataType setupConcreteTestType() {
        NodeId typeId = ConcreteTestType.TYPE_ID.toNodeId(getNamespaceTable()).orElseThrow();

        var dataType = new AbstractDataType(
            typeId,
            new QualifiedName(1, "ConcreteTestType"),
            ConcreteTestType.definition(getNamespaceTable()),
            false
        ) {

            @Override
            public NodeId getBinaryEncodingId() {
                return ConcreteTestType.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow();
            }

            @Override
            public NodeId getXmlEncodingId() {
                return ConcreteTestType.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow();
            }

            @Override
            public NodeId getJsonEncodingId() {
                return ConcreteTestType.JSON_ENCODING_ID.toNodeId(namespaceTable).orElseThrow();
            }
        };

        Mockito.when(dataTypeTree.getDataType(typeId)).thenReturn(dataType);
        Mockito.when(dataTypeTree.isStructType(typeId)).thenReturn(true);

        return dataType;
    }

    protected DataType setupConcreteTestTypeEx() {
        NodeId typeId = ConcreteTestTypeEx.TYPE_ID.toNodeId(getNamespaceTable()).orElseThrow();

        var dataType = new AbstractDataType(
            typeId,
            new QualifiedName(1, "ConcreteTestTypeEx"),
            ConcreteTestTypeEx.definition(getNamespaceTable()),
            false
        ) {

            @Override
            public NodeId getBinaryEncodingId() {
                return ConcreteTestTypeEx.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow();
            }

            @Override
            public NodeId getXmlEncodingId() {
                return ConcreteTestTypeEx.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow();
            }

            @Override
            public NodeId getJsonEncodingId() {
                return ConcreteTestTypeEx.JSON_ENCODING_ID.toNodeId(namespaceTable).orElseThrow();
            }
        };

        Mockito.when(dataTypeTree.getDataType(typeId)).thenReturn(dataType);
        Mockito.when(dataTypeTree.isStructType(typeId)).thenReturn(true);

        return dataType;
    }

    protected DataType setupStructWithScalarFields() {
        NodeId typeId = StructWithScalarFields.TYPE_ID.toNodeId(getNamespaceTable()).orElseThrow();

        var dataType = new AbstractDataType(
            typeId,
            new QualifiedName(1, "StructWithScalarFields"),
            StructWithScalarFields.definition(getNamespaceTable()),
            false
        ) {

            @Override
            public NodeId getBinaryEncodingId() {
                return StructWithScalarFields.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow();
            }

            @Override
            public NodeId getXmlEncodingId() {
                return StructWithScalarFields.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow();
            }

            @Override
            public NodeId getJsonEncodingId() {
                return StructWithScalarFields.JSON_ENCODING_ID.toNodeId(namespaceTable).orElseThrow();
            }
        };

        Mockito.when(dataTypeTree.getDataType(typeId)).thenReturn(dataType);

        return dataType;
    }

    protected DataType setupStructWithScalarFieldsEx() {
        NodeId typeId = StructWithScalarFieldsEx.TYPE_ID.toNodeId(getNamespaceTable()).orElseThrow();

        var dataType = new AbstractDataType(
            typeId,
            new QualifiedName(1, "StructWithScalarFieldsEx"),
            StructWithScalarFieldsEx.definition(getNamespaceTable()),
            false
        ) {

            @Override
            public NodeId getBinaryEncodingId() {
                return StructWithScalarFieldsEx.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow();
            }

            @Override
            public NodeId getXmlEncodingId() {
                return StructWithScalarFieldsEx.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow();
            }

            @Override
            public NodeId getJsonEncodingId() {
                return StructWithScalarFieldsEx.JSON_ENCODING_ID.toNodeId(namespaceTable).orElseThrow();
            }
        };

        Mockito.when(dataTypeTree.getDataType(typeId)).thenReturn(dataType);
        Mockito.when(dataTypeTree.getBuiltinType(NodeIds.Duration)).thenReturn(BuiltinDataType.Double);
        Mockito.when(dataTypeTree.isEnumType(NodeIds.ApplicationType)).thenReturn(true);
        Mockito.when(dataTypeTree.isEnumType(
            TestEnumType.TypeInfo.TYPE_ID.toNodeId(getNamespaceTable()).orElseThrow())).thenReturn(true);
        Mockito.when(dataTypeTree.isStructType(NodeIds.XVType)).thenReturn(true);

        return dataType;
    }

    protected DataType setupStructWithArrayFields() {
        NodeId typeId = StructWithArrayFields.TYPE_ID.toNodeId(getNamespaceTable()).orElseThrow();

        var dataType = new AbstractDataType(
            typeId,
            new QualifiedName(1, "StructWithArrayFields"),
            StructWithArrayFields.definition(getNamespaceTable()),
            false
        ) {

            @Override
            public NodeId getBinaryEncodingId() {
                return StructWithArrayFields.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow();
            }

            @Override
            public NodeId getXmlEncodingId() {
                return StructWithArrayFields.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow();
            }

            @Override
            public NodeId getJsonEncodingId() {
                return StructWithArrayFields.JSON_ENCODING_ID.toNodeId(namespaceTable).orElseThrow();
            }
        };

        Mockito.when(dataTypeTree.getDataType(typeId)).thenReturn(dataType);

        return dataType;
    }

    protected DataType setupStructWithArrayFieldsEx() {
        NodeId typeId = StructWithArrayFieldsEx.TYPE_ID.toNodeId(getNamespaceTable()).orElseThrow();

        var dataType = new AbstractDataType(
            typeId,
            new QualifiedName(1, "StructWithArrayFieldsEx"),
            StructWithArrayFieldsEx.definition(getNamespaceTable()),
            false
        ) {

            @Override
            public NodeId getBinaryEncodingId() {
                return StructWithArrayFieldsEx.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow();
            }

            @Override
            public NodeId getXmlEncodingId() {
                return StructWithArrayFieldsEx.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow();
            }

            @Override
            public NodeId getJsonEncodingId() {
                return StructWithArrayFieldsEx.JSON_ENCODING_ID.toNodeId(namespaceTable).orElseThrow();
            }
        };

        Mockito.when(dataTypeTree.getDataType(typeId)).thenReturn(dataType);

        return dataType;
    }

    protected DataType setupStructWithAbstractScalarFields() {
        NodeId typeId = StructWithAbstractScalarFields.TYPE_ID.toNodeId(getNamespaceTable()).orElseThrow();

        var dataType = new AbstractDataType(
            typeId,
            new QualifiedName(1, "StructWithAbstractScalarFields"),
            StructWithAbstractScalarFields.definition(getNamespaceTable()),
            false
        ) {

            @Override
            public NodeId getBinaryEncodingId() {
                return StructWithAbstractScalarFields.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow();
            }

            @Override
            public NodeId getXmlEncodingId() {
                return StructWithAbstractScalarFields.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow();
            }

            @Override
            public NodeId getJsonEncodingId() {
                return StructWithAbstractScalarFields.JSON_ENCODING_ID.toNodeId(namespaceTable).orElseThrow();
            }
        };

        Mockito.when(dataTypeTree.getDataType(typeId)).thenReturn(dataType);
        Mockito.when(dataTypeTree.getBuiltinType(NodeIds.Number)).thenReturn(BuiltinDataType.Variant);
        Mockito.when(dataTypeTree.isStructType(typeId)).thenReturn(true);

        return dataType;
    }

    protected DataType setupStructWithAbstractArrayFields() {
        NodeId typeId = StructWithAbstractArrayFields.TYPE_ID.toNodeId(getNamespaceTable()).orElseThrow();

        var dataType = new AbstractDataType(
            typeId,
            new QualifiedName(1, "StructWithAbstractArrayFields"),
            StructWithAbstractArrayFields.definition(getNamespaceTable()),
            false
        ) {

            @Override
            public NodeId getBinaryEncodingId() {
                return StructWithAbstractArrayFields.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow();
            }

            @Override
            public NodeId getXmlEncodingId() {
                return StructWithAbstractArrayFields.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow();
            }

            @Override
            public NodeId getJsonEncodingId() {
                return StructWithAbstractArrayFields.JSON_ENCODING_ID.toNodeId(namespaceTable).orElseThrow();
            }
        };

        Mockito.when(dataTypeTree.getDataType(typeId)).thenReturn(dataType);
        Mockito.when(dataTypeTree.getBuiltinType(NodeIds.Number)).thenReturn(BuiltinDataType.Variant);
        Mockito.when(dataTypeTree.isStructType(typeId)).thenReturn(true);

        return dataType;
    }

    protected DataType setupStructWithOptionalScalarFields() {
        NodeId typeId = StructWithOptionalScalarFields.TYPE_ID.toNodeId(getNamespaceTable()).orElseThrow();

        var dataType = new AbstractDataType(
            typeId,
            new QualifiedName(1, "StructWithOptionalScalarFields"),
            StructWithOptionalScalarFields.definition(getNamespaceTable()),
            false
        ) {

            @Override
            public NodeId getBinaryEncodingId() {
                return StructWithOptionalScalarFields.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow();
            }

            @Override
            public NodeId getXmlEncodingId() {
                return StructWithOptionalScalarFields.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow();
            }

            @Override
            public NodeId getJsonEncodingId() {
                return StructWithOptionalScalarFields.JSON_ENCODING_ID.toNodeId(namespaceTable).orElseThrow();
            }
        };

        Mockito.when(dataTypeTree.getDataType(typeId)).thenReturn(dataType);

        return dataType;
    }

    protected DataType setupStructWithOptionalArrayFields() {
        NodeId typeId = StructWithOptionalArrayFields.TYPE_ID.toNodeId(getNamespaceTable()).orElseThrow();

        var dataType = new AbstractDataType(
            typeId,
            new QualifiedName(1, "StructWithOptionalArrayFields"),
            StructWithOptionalArrayFields.definition(getNamespaceTable()),
            false
        ) {

            @Override
            public NodeId getBinaryEncodingId() {
                return StructWithOptionalArrayFields.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow();
            }

            @Override
            public NodeId getXmlEncodingId() {
                return StructWithOptionalArrayFields.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow();
            }

            @Override
            public NodeId getJsonEncodingId() {
                return StructWithOptionalArrayFields.JSON_ENCODING_ID.toNodeId(namespaceTable).orElseThrow();
            }
        };

        Mockito.when(dataTypeTree.getDataType(typeId)).thenReturn(dataType);

        return dataType;
    }

    protected DataType setupStructWithMatrixFields() {
        NodeId typeId = StructWithMatrixFields.TYPE_ID.toNodeId(getNamespaceTable()).orElseThrow();

        var dataType = new AbstractDataType(
            typeId,
            new QualifiedName(1, "StructWithMatrixFields"),
            StructWithMatrixFields.definition(getNamespaceTable()),
            false
        ) {

            @Override
            public NodeId getBinaryEncodingId() {
                return StructWithMatrixFields.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow();
            }

            @Override
            public NodeId getXmlEncodingId() {
                return StructWithMatrixFields.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow();
            }

            @Override
            public NodeId getJsonEncodingId() {
                return StructWithMatrixFields.JSON_ENCODING_ID.toNodeId(namespaceTable).orElseThrow();
            }
        };

        Mockito.when(dataTypeTree.getDataType(typeId)).thenReturn(dataType);

        return dataType;
    }

    protected DataType setupStructWithMatrixFieldsEx() {
        NodeId typeId = StructWithMatrixFieldsEx.TYPE_ID.toNodeId(getNamespaceTable()).orElseThrow();

        var dataType = new AbstractDataType(
            typeId,
            new QualifiedName(1, "StructWithMatrixFieldsEx"),
            StructWithMatrixFieldsEx.definition(getNamespaceTable()),
            false
        ) {

            @Override
            public NodeId getBinaryEncodingId() {
                return StructWithMatrixFieldsEx.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow();
            }

            @Override
            public NodeId getXmlEncodingId() {
                return StructWithMatrixFieldsEx.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow();
            }

            @Override
            public NodeId getJsonEncodingId() {
                return StructWithMatrixFieldsEx.JSON_ENCODING_ID.toNodeId(namespaceTable).orElseThrow();
            }
        };

        Mockito.when(dataTypeTree.getDataType(typeId)).thenReturn(dataType);

        return dataType;
    }

    protected DataType setupUnionOfScalar() {
        NodeId typeId = UnionOfScalar.TYPE_ID.toNodeId(getNamespaceTable()).orElseThrow();

        var dataType = new AbstractDataType(
            typeId,
            new QualifiedName(1, "UnionOfScalar"),
            UnionOfScalar.definition(getNamespaceTable()),
            false
        ) {

            @Override
            public NodeId getBinaryEncodingId() {
                return UnionOfScalar.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow();
            }

            @Override
            public NodeId getXmlEncodingId() {
                return UnionOfScalar.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow();
            }

            @Override
            public NodeId getJsonEncodingId() {
                return UnionOfScalar.JSON_ENCODING_ID.toNodeId(namespaceTable).orElseThrow();
            }
        };

        Mockito.when(dataTypeTree.getDataType(typeId)).thenReturn(dataType);
        Mockito.when(dataTypeTree.isStructType(typeId)).thenReturn(true);

        return dataType;
    }

    protected DataType setupUnionOfArray() {
        NodeId typeId = UnionOfArray.TYPE_ID.toNodeId(getNamespaceTable()).orElseThrow();

        var dataType = new AbstractDataType(
            typeId,
            new QualifiedName(1, "UnionOfArray"),
            UnionOfArray.definition(getNamespaceTable()),
            false
        ) {

            @Override
            public NodeId getBinaryEncodingId() {
                return UnionOfArray.BINARY_ENCODING_ID.toNodeId(namespaceTable).orElseThrow();
            }

            @Override
            public NodeId getXmlEncodingId() {
                return UnionOfArray.XML_ENCODING_ID.toNodeId(namespaceTable).orElseThrow();
            }

            @Override
            public NodeId getJsonEncodingId() {
                return UnionOfArray.JSON_ENCODING_ID.toNodeId(namespaceTable).orElseThrow();
            }
        };

        Mockito.when(dataTypeTree.getDataType(typeId)).thenReturn(dataType);
        Mockito.when(dataTypeTree.isStructType(typeId)).thenReturn(true);

        return dataType;
    }

}
