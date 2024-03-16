/*
 * Copyright (c) 2024 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.methods;

import java.util.List;

import org.eclipse.milo.opcua.sdk.test.AbstractClientServerTest;
import org.eclipse.milo.opcua.stack.core.NodeIds;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CallMethodResult;
import org.eclipse.milo.opcua.stack.core.types.structured.CallResponse;
import org.junit.jupiter.api.Test;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ConditionRefreshMethodTest extends AbstractClientServerTest {

    @Test
    void callConditionRefresh() throws UaException {
        var request = new CallMethodRequest(
            NodeIds.ConditionType,
            NodeIds.ConditionType_ConditionRefresh,
            new Variant[]{new Variant(UInteger.valueOf(0))}
        );

        CallResponse response = client.call(List.of(request));
        System.out.println(response.getResponseHeader().getServiceResult());

        CallMethodResult result = requireNonNull(response.getResults())[0];
        System.out.println(result);

        assertNotEquals(new StatusCode(StatusCodes.Bad_InvalidArgument), result.getStatusCode());
    }

}
