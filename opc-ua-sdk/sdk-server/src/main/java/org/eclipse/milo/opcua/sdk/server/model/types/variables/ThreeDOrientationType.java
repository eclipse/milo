/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.types.variables;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/7.26">https://reference.opcfoundation.org/v105/Core/docs/Part5/7.26</a>
 */
public interface ThreeDOrientationType extends OrientationType {
    BaseDataVariableType getANode();

    Double getA();

    void setA(Double value);

    BaseDataVariableType getBNode();

    Double getB();

    void setB(Double value);

    BaseDataVariableType getCNode();

    Double getC();

    void setC(Double value);
}