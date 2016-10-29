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

package org.eclipse.milo.opcua.sdk.server.api;

import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public interface DataItem extends MonitoredItem {

    /**
     * Set the latest sampled value.
     *
     * @param value the latest sampled value.
     */
    void setValue(DataValue value);

    /**
     * Apply a new {@link StatusCode} to the last value that passed the filter and then set the derived value.
     *
     * @param quality the {@link StatusCode} to apply.
     */
    void setQuality(StatusCode quality);

    /**
     * @return the rate to sample this item at.
     */
    double getSamplingInterval();

}
