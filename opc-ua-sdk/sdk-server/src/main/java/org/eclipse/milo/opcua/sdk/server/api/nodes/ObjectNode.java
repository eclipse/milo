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

import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;

public interface ObjectNode extends Node {

    /**
     * The EventNotifier attribute identifies whether the Object can be used to subscribe to Events or to read and
     * write the history of the Events.
     *
     * @return the EventNotifier attribute of this Object.
     */
    UByte getEventNotifier();

    /**
     * Set the EventNotifier attribute of this Object.
     *
     * @param eventNotifier the EventNotifier attribute to set.
     */
    void setEventNotifier(UByte eventNotifier);

}
