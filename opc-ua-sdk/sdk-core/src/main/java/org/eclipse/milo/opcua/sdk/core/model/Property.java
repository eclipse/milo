/*
 * Copyright (c) 2016 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.sdk.core.model;

import javax.annotation.Nullable;

import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public interface Property<T> {

    QualifiedName getBrowseName();

    NodeId getDataType();

    Integer getValueRank();

    Class<T> getJavaType();

    @Nullable
    default UInteger[] getArrayDimensions() {
        int valueRank = getValueRank();

        if (valueRank <= 0) {
            return null;
        } else {
            UInteger[] arrayDimensions = new UInteger[valueRank];
            for (int i = 0; i < valueRank; i++) {
                arrayDimensions[i] = uint(0);
            }
            return arrayDimensions;
        }
    }

}
