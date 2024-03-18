/*
 * Copyright (c) 2024 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.sdk.core.types.json;

import org.eclipse.milo.opcua.sdk.core.typetree.DataType;
import org.eclipse.milo.opcua.sdk.core.typetree.DataTypeTree;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.NodeIds;
import org.eclipse.milo.opcua.stack.core.ServerTable;
import org.eclipse.milo.opcua.stack.core.channel.EncodingLimits;
import org.eclipse.milo.opcua.stack.core.encoding.DataTypeCodec;
import org.eclipse.milo.opcua.stack.core.encoding.DefaultEncodingManager;
import org.eclipse.milo.opcua.stack.core.encoding.EncodingContext;
import org.eclipse.milo.opcua.stack.core.encoding.EncodingManager;
import org.eclipse.milo.opcua.stack.core.types.DataTypeManager;
import org.eclipse.milo.opcua.stack.core.types.DefaultDataTypeManager;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.structured.XVType;
import org.mockito.Mockito;

class TestEncodingContext implements EncodingContext {

    static final DataType XV_DATA_TYPE = new TestDataType(
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

    DataTypeTree dataTypeTree = Mockito.mock(DataTypeTree.class);
    DataTypeManager dataTypeManager = new DefaultDataTypeManager();

    public TestEncodingContext() {
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
        return new NamespaceTable();
    }

    @Override
    public ServerTable getServerTable() {
        return new ServerTable();
    }

}
