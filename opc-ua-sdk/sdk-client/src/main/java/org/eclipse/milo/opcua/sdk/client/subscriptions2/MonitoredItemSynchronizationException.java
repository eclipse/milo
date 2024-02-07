/*
 * Copyright (c) 2024 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.subscriptions2;

import java.util.List;

import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class MonitoredItemSynchronizationException extends UaException {

    private final List<MonitoredItemOperationResult> createResults;
    private final List<MonitoredItemOperationResult> modifyResults;
    private final List<MonitoredItemOperationResult> deleteResults;

    public MonitoredItemSynchronizationException(
        String message,
        List<MonitoredItemOperationResult> createResults,
        List<MonitoredItemOperationResult> modifyResults,
        List<MonitoredItemOperationResult> deleteResults
    ) {

        super(StatusCodes.Bad_UnexpectedError, message);

        this.createResults = createResults;
        this.modifyResults = modifyResults;
        this.deleteResults = deleteResults;
    }

    public List<MonitoredItemOperationResult> getCreateResults() {
        return createResults;
    }

    public List<MonitoredItemOperationResult> getModifyResults() {
        return modifyResults;
    }

    public List<MonitoredItemOperationResult> getDeleteResults() {
        return deleteResults;
    }

}
