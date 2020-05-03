/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.api.subscriptions;

import java.util.Optional;

import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

/**
 * Describes the operation- and service-level results of a batch operation.
 *
 * @param <T> the type of the operation result for the batch operation.
 */
public interface BatchItemResult<T> {

    /**
     * The service-level result of service call the batch item was part of.
     *
     * @return the service-level result of service call the batch item was part of.
     */
    StatusCode serviceResult();

    /**
     * The operation-level result of the batch operation.
     * <p>
     * May not be present if the service result is not good.
     *
     * @return the operation-level result of the batch operation.
     * @see #serviceResult()
     * @see StatusCode#isGood()
     */
    Optional<T> operationResult();

}
