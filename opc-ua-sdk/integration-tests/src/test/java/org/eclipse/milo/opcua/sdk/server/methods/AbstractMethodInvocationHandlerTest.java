/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.methods;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.eclipse.milo.opcua.sdk.client.AddressSpace;
import org.eclipse.milo.opcua.sdk.client.methods.UaMethodException;
import org.eclipse.milo.opcua.sdk.client.nodes.UaObjectNode;
import org.eclipse.milo.opcua.sdk.test.AbstractClientServerTest;
import org.eclipse.milo.opcua.stack.core.NodeIds;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodResult;
import org.eclipse.milo.opcua.stack.core.types.structured.CallResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AbstractMethodInvocationHandlerTest extends AbstractClientServerTest {

    @Test
    public void inputArgumentResultsIsEmptyOnSuccess() throws UaException {
        CallResponse response = client.call(List.of(
            new CallMethodRequest(
                NodeIds.ObjectsFolder,
                NodeId.parse("ns=2;s=onlyAcceptsPositiveInputs()"),
                new Variant[]{new Variant(1)}
            )
        ));

        CallMethodResult result = Objects.requireNonNull(response.getResults())[0];
        StatusCode[] inputArgumentResults = Objects.requireNonNull(result.getInputArgumentResults());

        assertEquals(StatusCode.GOOD, result.getStatusCode());
        assertEquals(0, inputArgumentResults.length);
    }

    @Test
    public void implementationCanValidateArguments() throws UaException {
        AddressSpace addressSpace = client.getAddressSpace();

        UaObjectNode objectsNode = addressSpace.getObjectNode(NodeIds.ObjectsFolder);

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

    @Test
    void wrongNumberOfArguments() throws UaException {
        // too few arguments
        {
            CallResponse response = client.call(List.of(new CallMethodRequest(
                NodeIds.ObjectsFolder,
                NodeId.parse("ns=2;s=onlyAcceptsPositiveInputs()"),
                new Variant[]{}
            )));

            CallMethodResult result = Objects.requireNonNull(response.getResults())[0];

            assertEquals(StatusCodes.Bad_ArgumentsMissing, result.getStatusCode().getValue());
        }

        // too many arguments
        {
            CallResponse response = client.call(List.of(new CallMethodRequest(
                NodeIds.ObjectsFolder,
                NodeId.parse("ns=2;s=onlyAcceptsPositiveInputs()"),
                new Variant[]{new Variant(1), new Variant(2)}
            )));

            CallMethodResult result = Objects.requireNonNull(response.getResults())[0];

            assertEquals(StatusCodes.Bad_TooManyArguments, result.getStatusCode().getValue());
        }
    }

}
