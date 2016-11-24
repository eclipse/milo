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

public interface DataTypeNode extends Node {

    /**
     * The IsAbstract attribute specifies if the DataType is abstract or not.
     * <p>
     * Abstract DataTypes can be used in the AddressSpace, i.e. Variables and VariableTypes can point with their
     * DataType Attribute to an abstract DataType. However, concrete values can never be of an abstract DataType and
     * shall always be of a concrete subtype of the abstract DataType.
     *
     * @return {@code true} if the DataType is abstract.
     */
    Boolean getIsAbstract();

    /**
     * Set the IsAbstract attribute of this DataType.
     *
     * @param isAbstract {@code true} if this
     */
    void setIsAbstract(Boolean isAbstract);

}
