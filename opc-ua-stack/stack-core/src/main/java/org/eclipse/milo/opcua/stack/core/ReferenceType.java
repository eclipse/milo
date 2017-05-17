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

package org.eclipse.milo.opcua.stack.core;

import java.util.Optional;

import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;

public interface ReferenceType {

    /**
     * @return the {@link NodeId} that identifies this {@link ReferenceType}.
     */
    NodeId getNodeId();

    /**
     * @return the {@link QualifiedName} that identifies this {@link ReferenceType}.
     */
    QualifiedName getBrowseName();

    /**
     * @return the inverse name of the reference, i.e. the meaning of the reference as seen from the target node.
     */
    Optional<String> getInverseName();

    /**
     * @return {@code true} if the reference is the same as seen from the source node and the target node.
     */
    boolean isSymmetric();

    /**
     * @return {@code true} if the reference is an abstract reference type, i.e. no references of this type exist, only
     * references of its subtypes.
     */
    boolean isAbstract();

    /**
     * @return the {@link NodeId} of the super type of this reference type.
     * All references except the root reference type have a super type.
     */
    Optional<NodeId> getSuperTypeId();

}
