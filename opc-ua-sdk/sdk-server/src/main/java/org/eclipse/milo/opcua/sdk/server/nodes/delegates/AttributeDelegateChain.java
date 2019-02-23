/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.nodes.delegates;

import java.util.function.Function;

public class AttributeDelegateChain {

    @SafeVarargs
    public static AttributeDelegate create(
        AttributeDelegate root,
        Function<AttributeDelegate, AttributeDelegate>... childFns) {

        AttributeDelegate delegate = root;

        for (Function<AttributeDelegate, AttributeDelegate> childFn : childFns) {
            delegate = childFn.apply(delegate);
        }

        return delegate;
    }

}
