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

import java.util.EnumSet;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public enum WriteMask {

    AccessLevel(1),
    ArrayDimensions(2),
    BrowseName(4),
    ContainsNoLoops(8),
    DataType(16),
    Description(32),
    DisplayName(64),
    EventNotifier(128),
    Executable(256),
    Historizing(512),
    InverseName(1024),
    IsAbstract(2048),
    MinimumSamplingInterval(4096),
    NodeClass(8192),
    NodeId(16384),
    Symmetric(32768),
    UserAccessLevel(65536),
    UserExecutable(131072),
    UserWriteMask(262144),
    ValueRank(524288),
    WriteMask(1048576),
    ValueForVariableType(2097152);

    public static final Set<WriteMask> NONE = ImmutableSet.of();

    private final int value;

    private WriteMask(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static EnumSet<WriteMask> fromMask(int accessLevel) {
        EnumSet<WriteMask> e = EnumSet.noneOf(WriteMask.class);
        for (WriteMask wm : values()) {
            if ((wm.value & accessLevel) != 0) {
                e.add(wm);
            }
        }
        return e;
    }

    public static EnumSet<WriteMask> fromMask(UInteger accessLevel) {
        return fromMask(accessLevel.intValue());
    }

}
