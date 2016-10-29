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

package org.eclipse.milo.opcua.sdk.core;

public enum ValueRank {

    OneDimension(1),
    OneOrMoreDimensions(0),
    Scalar(-1),
    Any(-2),
    ScalarOrOneDimension(-3);

    private final int value;

    ValueRank(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
