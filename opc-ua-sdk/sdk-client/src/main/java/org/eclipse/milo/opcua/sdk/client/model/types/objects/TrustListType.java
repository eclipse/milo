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
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public interface TrustListType extends FileType {
    QualifiedProperty<DateTime> LAST_UPDATE_TIME = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "LastUpdateTime",
        NodeId.parse("ns=0;i=294"),
        ValueRanks.Scalar,
        DateTime.class
    );

    CompletableFuture<? extends PropertyType> getLastUpdateTimeNode();

    CompletableFuture<DateTime> getLastUpdateTime();

    CompletableFuture<StatusCode> setLastUpdateTime(DateTime value);
}
