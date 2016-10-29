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

package org.eclipse.milo.opcua.sdk.server.util;

import java.util.EnumSet;
import java.util.List;

import com.google.common.collect.Lists;
import org.eclipse.milo.opcua.stack.core.types.enumerated.BrowseResultMask;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

public class UaEnumUtil {

    public static EnumSet<NodeClass> nodeClasses(long mask) {
        List<NodeClass> list = Lists.newArrayList();

        for (NodeClass nc : NodeClass.values()) {
            if ((mask & nc.getValue()) == nc.getValue()) {
                list.add(nc);
            }
        }

        return EnumSet.copyOf(list);
    }

    public static EnumSet<BrowseResultMask> browseResultMasks(long mask) {
        List<BrowseResultMask> list = Lists.newArrayList();

        for (BrowseResultMask brm : BrowseResultMask.values()) {
            if ((mask & brm.getValue()) == brm.getValue()) {
                list.add(brm);
            }
        }

        return EnumSet.copyOf(list);
    }

}
