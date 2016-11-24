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

public interface ObjectTypeNode extends Node {

    /**
     * The IsAbstract attribute indicates if this ObjectType is abstract or not.
     *
     * @return {@code true} if this ObjectType is abstract.
     */
    Boolean getIsAbstract();

    /**
     * Set the IsAbstract attribute of this ObjectType.
     *
     * @param isAbstract {@code true} if this
     */
    void setIsAbstract(Boolean isAbstract);

}
