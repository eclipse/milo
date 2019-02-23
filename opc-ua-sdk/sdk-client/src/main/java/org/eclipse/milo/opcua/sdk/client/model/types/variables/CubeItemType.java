/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.model.types.variables;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.AxisInformation;

public interface CubeItemType extends ArrayItemType {
    QualifiedProperty<AxisInformation> X_AXIS_DEFINITION = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "XAxisDefinition",
        NodeId.parse("ns=0;i=12079"),
        ValueRanks.Scalar,
        AxisInformation.class
    );

    QualifiedProperty<AxisInformation> Y_AXIS_DEFINITION = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "YAxisDefinition",
        NodeId.parse("ns=0;i=12079"),
        ValueRanks.Scalar,
        AxisInformation.class
    );

    QualifiedProperty<AxisInformation> Z_AXIS_DEFINITION = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ZAxisDefinition",
        NodeId.parse("ns=0;i=12079"),
        ValueRanks.Scalar,
        AxisInformation.class
    );

    CompletableFuture<? extends PropertyType> getXAxisDefinitionNode();

    CompletableFuture<AxisInformation> getXAxisDefinition();

    CompletableFuture<StatusCode> setXAxisDefinition(AxisInformation value);

    CompletableFuture<? extends PropertyType> getYAxisDefinitionNode();

    CompletableFuture<AxisInformation> getYAxisDefinition();

    CompletableFuture<StatusCode> setYAxisDefinition(AxisInformation value);

    CompletableFuture<? extends PropertyType> getZAxisDefinitionNode();

    CompletableFuture<AxisInformation> getZAxisDefinition();

    CompletableFuture<StatusCode> setZAxisDefinition(AxisInformation value);
}
