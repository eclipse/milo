/*
 * Copyright (c) 2021 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.api.methods;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.AddressSpace;
import org.eclipse.milo.opcua.sdk.client.methods.UaMethodException;
import org.eclipse.milo.opcua.sdk.client.nodes.UaObjectNode;
import org.eclipse.milo.opcua.sdk.test.AbstractClientServerTest;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AbstractMethodInvocationHandlerTest extends AbstractClientServerTest {

    @Test
    public void inputArgumentResultsIsEmptyOnSuccess() throws ExecutionException, InterruptedException {
        CallMethodResult result = client.call(new CallMethodRequest(
            Identifiers.ObjectsFolder,
            NodeId.parse("ns=2;s=onlyAcceptsPositiveInputs()"),
            new Variant[]{new Variant(1)}
        )).get();

        assertEquals(StatusCode.GOOD, result.getStatusCode());
        assertEquals(0, result.getInputArgumentResults().length);
    }

    @Test
    public void implementationCanValidateArguments() throws UaException {
        AddressSpace addressSpace = client.getAddressSpace();

        UaObjectNode objectsNode = addressSpace.getObjectNode(Identifiers.ObjectsFolder);

        try {
            objectsNode.callMethod(
                new QualifiedName(2, "onlyAcceptsPositiveInputs()"),
                new Variant[]{new Variant(-1)}
            );
        } catch (UaMethodException e) {
            System.out.println("result: " + e.getStatusCode());
            System.out.println("inputArgumentResults: " + Arrays.toString(e.getInputArgumentResults()));

            assertEquals(StatusCodes.Bad_InvalidArgument, e.getStatusCode().getValue());
            assertEquals(StatusCodes.Bad_OutOfRange, e.getInputArgumentResults()[0].getValue());
        }
    }

}
