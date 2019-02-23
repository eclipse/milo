/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public interface ProgramTransitionEventType extends TransitionEventType {
    QualifiedProperty<Object> INTERMEDIATE_RESULT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "IntermediateResult",
        NodeId.parse("ns=0;i=24"),
        ValueRanks.Scalar,
        Object.class
    );

    CompletableFuture<? extends PropertyType> getIntermediateResultNode();

    CompletableFuture<?> getIntermediateResult();

    CompletableFuture<StatusCode> setIntermediateResult(Object value);
}
