/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.util;

import java.util.function.Consumer;

import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class AttributeWriterTest {

    @Test
    public void testVariantToVariant() throws UaException {
        testWriteConversion(new Variant("String"), null, null);
    }

    @Test
    public void testStringToString() throws UaException {
        testWriteConversion(new Variant("String"), Identifiers.String, null);
    }

    @Test
    public void testStringToDouble() throws UaException {
        expectFailure(StatusCodes.Bad_TypeMismatch, () -> testWriteConversion(new Variant("String"), Identifiers.Double, null));
    }

    @Test
    public void testByteStringToUByteArray() throws UaException {
        testWriteConversion(new Variant(ByteString.of("foo".getBytes())), Identifiers.Byte, node -> {
            node.setValueRank(ValueRanks.OneDimension);
            node.setArrayDimensions(new UInteger[]{uint(0)});
        });
    }

    public interface UaOperation {
        void run() throws UaException;
    }

    private static void expectFailure(long code, UaOperation operation) {
        try {
            operation.run();
            Assert.fail("Operation is expected to fail with code: " + code);
        } catch (UaException e) {
            Assert.assertEquals(e.getStatusCode().getValue(), code, "Status code does not match");
        }
    }

    private void testWriteConversion(
        Variant value,
        NodeId dataType,
        Consumer<UaVariableNode> nodeCustomizer) throws UaException {

        testWriteConversion(new DataValue(value), dataType, nodeCustomizer);

    }

    private void testWriteConversion(
        DataValue value,
        NodeId dataType,
        Consumer<UaVariableNode> nodeCustomizer) throws UaException {

        final UaVariableNode varNode = createMockNode("test", node -> {
            UByte accessLevel = ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE));
            node.setAccessLevel(accessLevel);
            node.setUserAccessLevel(accessLevel);
            if (nodeCustomizer != null) {
                nodeCustomizer.accept(node);
            }
        });

        if (dataType != null) {
            varNode.setDataType(dataType);
        }

        OpcUaServer server = Mockito.mock(OpcUaServer.class);

        AttributeWriter.writeAttribute(
            new AttributeContext(server, null),
            varNode,
            AttributeId.Value,
            value,
            null
        );
    }

    private UaVariableNode createMockNode(
        String id,
        Consumer<UaVariableNode> nodeCustomizer) {

        final NodeId nodeId = new NodeId(0, id);

        final QualifiedName browseName = new QualifiedName(0, id);
        final LocalizedText displayName = LocalizedText.english(id);

        final UaVariableNode node = new UaVariableNode(
            null, nodeId, browseName, displayName);

        if (nodeCustomizer != null) {
            nodeCustomizer.accept(node);
        }

        return node;
    }

}
