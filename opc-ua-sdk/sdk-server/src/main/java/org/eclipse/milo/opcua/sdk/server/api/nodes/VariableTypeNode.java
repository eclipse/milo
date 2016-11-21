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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public interface VariableTypeNode extends Node {

    DataValue getValue();

    NodeId getDataType();

    Integer getValueRank();

    UInteger[] getArrayDimensions();

    Boolean getIsAbstract();

    void setValue(DataValue value);

    void setDataType(NodeId dataType);

    void setValueRank(Integer valueRank);

    void setArrayDimensions(UInteger[] arrayDimensions);

    void setIsAbstract(Boolean isAbstract);

}
