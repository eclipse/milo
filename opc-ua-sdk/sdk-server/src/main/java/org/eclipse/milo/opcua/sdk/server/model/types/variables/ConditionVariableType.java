/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.types.variables;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public interface ConditionVariableType extends BaseDataVariableType {
    QualifiedProperty<DateTime> SOURCE_TIMESTAMP = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "SourceTimestamp",
        NodeId.parse("ns=0;i=294"),
        ValueRanks.Scalar,
        DateTime.class
    );

    PropertyType getSourceTimestampNode();

    DateTime getSourceTimestamp();

    void setSourceTimestamp(DateTime value);
}
