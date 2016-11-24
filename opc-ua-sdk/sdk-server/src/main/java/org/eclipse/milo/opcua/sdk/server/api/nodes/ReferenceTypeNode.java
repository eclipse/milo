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

import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;

public interface ReferenceTypeNode extends Node {

    Boolean getIsAbstract();

    Boolean getSymmetric();

    LocalizedText getInverseName();

    void setIsAbstract(Boolean isAbstract);

    void setSymmetric(Boolean isSymmetric);

    void setInverseName(LocalizedText inverseName);

}
