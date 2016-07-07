/*
 * Copyright (c) 2016 Jens Reimann
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 * 	http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * 	http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.sdk.server.util;

import java.util.function.Consumer;

import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned;
import org.testng.Assert;
import org.testng.annotations.Test;

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
		});
	}

	public interface UaOperation {
		public void run() throws UaException;
	}

	public static void expectFailure(long code, UaOperation operation) {
		try {
			operation.run();
			Assert.fail("Operation is expected to fail with code: " + code);
		} catch (UaException e) {
			Assert.assertEquals(e.getStatusCode().getValue(), code, "Status code does not match");
		}
	}

	private void testWriteConversion(Variant value,
			NodeId dataType,
			Consumer<org.eclipse.milo.opcua.sdk.server.model.UaVariableNode> nodeCustomizer ) throws UaException {
		
		testWriteConversion(new DataValue(value), dataType, nodeCustomizer);
		
	}

	private void testWriteConversion(DataValue value,
			NodeId dataType,
			Consumer<org.eclipse.milo.opcua.sdk.server.model.UaVariableNode> nodeCustomizer) throws UaException {
		
		final org.eclipse.milo.opcua.sdk.server.model.UaVariableNode varNode = createMockNode("test", node -> {
			node.setAccessLevel(Unsigned.ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)));
			if ( nodeCustomizer != null ) {
				nodeCustomizer.accept(node);
			}
		});

		if (dataType != null) {
			varNode.setDataType(dataType);
		}

		AttributeWriter.writeAttribute(null, varNode, AttributeId.Value, value, null);
	}

	private org.eclipse.milo.opcua.sdk.server.model.UaVariableNode createMockNode(String id,
			Consumer<org.eclipse.milo.opcua.sdk.server.model.UaVariableNode> nodeCustomizer
			) {
		
		final NodeId nodeId = new NodeId(0, id);

		final QualifiedName browseName = new QualifiedName(0, id);
		final LocalizedText displayName = LocalizedText.english(id);

		final org.eclipse.milo.opcua.sdk.server.model.UaVariableNode node = new org.eclipse.milo.opcua.sdk.server.model.UaVariableNode(
				null, nodeId, browseName, displayName);

		if (nodeCustomizer != null) {
			nodeCustomizer.accept(node);
		}

		return node;
	}
}
