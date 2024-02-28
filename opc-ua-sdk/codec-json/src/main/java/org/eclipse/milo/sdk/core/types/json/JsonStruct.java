/*
 * Copyright (c) 2024 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.sdk.core.types.json;

import com.google.gson.JsonObject;
import org.eclipse.milo.opcua.sdk.core.typetree.DataType;
import org.eclipse.milo.opcua.stack.core.types.UaStructuredType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;

public class JsonStruct implements UaStructuredType {

    private final DataType dataType;
    private final JsonObject jsonObject;

    public JsonStruct(DataType dataType, JsonObject jsonObject) {
        this.dataType = dataType;
        this.jsonObject = jsonObject;
    }

    public DataType getDataType() {
        return dataType;
    }

    public JsonObject getJsonObject() {
        return jsonObject;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return dataType.getNodeId().expanded();
    }

}
