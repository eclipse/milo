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
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.NodeIds;
import org.eclipse.milo.opcua.stack.core.encoding.DataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.structured.XVType;
import org.eclipse.milo.sdk.core.types.json.JsonCodecFactory;
import org.mockito.Mockito;

public class DynamicEncodingContext extends AbstractEncodingContext {

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


    public DynamicEncodingContext() {
        super();

        DataTypeCodec xvDataTypeCodec =
            JsonCodecFactory.create(XV_DATA_TYPE, dataTypeTree);

        dataTypeManager.registerType(
            XV_DATA_TYPE.getNodeId(),
            xvDataTypeCodec,
            XV_DATA_TYPE.getBinaryEncodingId(),
            XV_DATA_TYPE.getXmlEncodingId(),
            XV_DATA_TYPE.getJsonEncodingId()
        );

        Mockito.when(dataTypeTree.getDataType(XV_DATA_TYPE.getNodeId()))
            .thenReturn(XV_DATA_TYPE);

        dataTypeManager.registerType(
            abstractTestType.getNodeId(),
            JsonCodecFactory.create(abstractTestType, dataTypeTree),
            abstractTestType.getBinaryEncodingId(),
            abstractTestType.getXmlEncodingId(),
            abstractTestType.getJsonEncodingId()
        );

        dataTypeManager.registerType(
            concreteTestType.getNodeId(),
            JsonCodecFactory.create(concreteTestType, dataTypeTree),
            concreteTestType.getBinaryEncodingId(),
            concreteTestType.getXmlEncodingId(),
            concreteTestType.getJsonEncodingId()
        );

        dataTypeManager.registerType(
            concreteTestTypeEx.getNodeId(),
            JsonCodecFactory.create(concreteTestTypeEx, dataTypeTree),
            concreteTestTypeEx.getBinaryEncodingId(),
            concreteTestTypeEx.getXmlEncodingId(),
            concreteTestTypeEx.getJsonEncodingId()
        );

        dataTypeManager.registerType(
            structWithScalarFields.getNodeId(),
            JsonCodecFactory.create(structWithScalarFields, dataTypeTree),
            structWithScalarFields.getBinaryEncodingId(),
            structWithScalarFields.getXmlEncodingId(),
            structWithScalarFields.getJsonEncodingId()
        );

        dataTypeManager.registerType(
            structWithScalarFields.getNodeId(),
            JsonCodecFactory.create(structWithScalarFields, dataTypeTree),
            structWithScalarFields.getBinaryEncodingId(),
            structWithScalarFields.getXmlEncodingId(),
            structWithScalarFields.getJsonEncodingId()
        );

        dataTypeManager.registerType(
            structWithScalarFieldsEx.getNodeId(),
            JsonCodecFactory.create(structWithScalarFieldsEx, dataTypeTree),
            structWithScalarFieldsEx.getBinaryEncodingId(),
            structWithScalarFieldsEx.getXmlEncodingId(),
            structWithScalarFieldsEx.getJsonEncodingId()
        );

        dataTypeManager.registerType(
            structWithArrayFields.getNodeId(),
            JsonCodecFactory.create(structWithArrayFields, dataTypeTree),
            structWithArrayFields.getBinaryEncodingId(),
            structWithArrayFields.getXmlEncodingId(),
            structWithArrayFields.getJsonEncodingId()
        );

        dataTypeManager.registerType(
            structWithArrayFieldsEx.getNodeId(),
            JsonCodecFactory.create(structWithArrayFieldsEx, dataTypeTree),
            structWithArrayFieldsEx.getBinaryEncodingId(),
            structWithArrayFieldsEx.getXmlEncodingId(),
            structWithArrayFieldsEx.getJsonEncodingId()
        );

        dataTypeManager.registerType(
            structWithAbstractScalarFields.getNodeId(),
            JsonCodecFactory.create(structWithAbstractScalarFields, dataTypeTree),
            structWithAbstractScalarFields.getBinaryEncodingId(),
            structWithAbstractScalarFields.getXmlEncodingId(),
            structWithAbstractScalarFields.getJsonEncodingId()
        );

        dataTypeManager.registerType(
            structWithAbstractArrayFields.getNodeId(),
            JsonCodecFactory.create(structWithAbstractArrayFields, dataTypeTree),
            structWithAbstractArrayFields.getBinaryEncodingId(),
            structWithAbstractArrayFields.getXmlEncodingId(),
            structWithAbstractArrayFields.getJsonEncodingId()
        );

        dataTypeManager.registerType(
            structWithAbstractMatrixFields.getNodeId(),
            JsonCodecFactory.create(structWithAbstractMatrixFields, dataTypeTree),
            structWithAbstractMatrixFields.getBinaryEncodingId(),
            structWithAbstractMatrixFields.getXmlEncodingId(),
            structWithAbstractMatrixFields.getJsonEncodingId()
        );

        dataTypeManager.registerType(
            structWithOptionalScalarFields.getNodeId(),
            JsonCodecFactory.create(structWithOptionalScalarFields, dataTypeTree),
            structWithOptionalScalarFields.getBinaryEncodingId(),
            structWithOptionalScalarFields.getXmlEncodingId(),
            structWithOptionalScalarFields.getJsonEncodingId()
        );

        dataTypeManager.registerType(
            structWithOptionalArrayFields.getNodeId(),
            JsonCodecFactory.create(structWithOptionalArrayFields, dataTypeTree),
            structWithOptionalArrayFields.getBinaryEncodingId(),
            structWithOptionalArrayFields.getXmlEncodingId(),
            structWithOptionalArrayFields.getJsonEncodingId()
        );

        dataTypeManager.registerType(
            structWithMatrixFields.getNodeId(),
            JsonCodecFactory.create(structWithMatrixFields, dataTypeTree),
            structWithMatrixFields.getBinaryEncodingId(),
            structWithMatrixFields.getXmlEncodingId(),
            structWithMatrixFields.getJsonEncodingId()
        );

        dataTypeManager.registerType(
            structWithMatrixFieldsEx.getNodeId(),
            JsonCodecFactory.create(structWithMatrixFieldsEx, dataTypeTree),
            structWithMatrixFieldsEx.getBinaryEncodingId(),
            structWithMatrixFieldsEx.getXmlEncodingId(),
            structWithMatrixFieldsEx.getJsonEncodingId()
        );

        dataTypeManager.registerType(
            unionOfScalar.getNodeId(),
            JsonCodecFactory.create(unionOfScalar, dataTypeTree),
            unionOfScalar.getBinaryEncodingId(),
            unionOfScalar.getXmlEncodingId(),
            unionOfScalar.getJsonEncodingId()
        );

        dataTypeManager.registerType(
            unionOfArray.getNodeId(),
            JsonCodecFactory.create(unionOfArray, dataTypeTree),
            unionOfArray.getBinaryEncodingId(),
            unionOfArray.getXmlEncodingId(),
            unionOfArray.getJsonEncodingId()
        );
    }


}
