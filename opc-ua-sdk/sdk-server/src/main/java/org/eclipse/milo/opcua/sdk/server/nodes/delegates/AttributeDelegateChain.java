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

package org.eclipse.milo.opcua.sdk.server.nodes.delegates;

import java.util.function.Function;
import java.util.stream.Stream;

import org.jooq.lambda.Seq;

public class AttributeDelegateChain {

    @SafeVarargs
    public static AttributeDelegate create(
        AttributeDelegate root,
        Function<AttributeDelegate, AttributeDelegate>... childFns) {

        return Seq.foldLeft(
            Stream.of(childFns), root,
            (parent, childFn) -> childFn.apply(parent)
        );
    }

}
