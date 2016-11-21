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

package org.eclipse.milo.opcua.sdk.server.api.nodes;

import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public interface VariableNode extends Node {

    DataValue getValue();

    NodeId getDataType();

    Integer getValueRank();

    UInteger[] getArrayDimensions();

    UByte getAccessLevel();

    UByte getUserAccessLevel();

    Double getMinimumSamplingInterval();

    Boolean getHistorizing();

    /**
     * Set the Value attribute of this Node.
     *
     * @param value the Value to set.
     */
    void setValue(DataValue value);

    /**
     * Set the DataType attribute of this Node.
     *
     * @param dataType the DataType to set.
     */
    void setDataType(NodeId dataType);

    void setValueRank(Integer valueRank);

    void setArrayDimensions(UInteger[] arrayDimensions);

    void setAccessLevel(UByte accessLevel);

    void setUserAccessLevel(UByte userAccessLevel);

    void setMinimumSamplingInterval(Double minimumSamplingInterval);

    void setHistorizing(Boolean historizing);

}
