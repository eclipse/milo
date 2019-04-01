/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack;

import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.EncodingLimits;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.DataTypeManager;
import org.eclipse.milo.opcua.stack.core.types.OpcUaDataTypeManager;

public class TestSerializationContext implements SerializationContext {

    private final NamespaceTable namespaceTable = new NamespaceTable();

    @Override
    public EncodingLimits getEncodingLimits() {
        return EncodingLimits.DEFAULT;
    }

    @Override
    public NamespaceTable getNamespaceTable() {
        return namespaceTable;
    }

    @Override
    public DataTypeManager getDataTypeManager() {
        return OpcUaDataTypeManager.getInstance();
    }

}
