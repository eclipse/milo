/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.examples.server.methods;

import org.eclipse.milo.opcua.sdk.server.annotations.UaInputArgument;
import org.eclipse.milo.opcua.sdk.server.annotations.UaMethod;
import org.eclipse.milo.opcua.sdk.server.annotations.UaOutputArgument;
import org.eclipse.milo.opcua.sdk.server.util.AnnotationBasedInvocationHandler.InvocationContext;
import org.eclipse.milo.opcua.sdk.server.util.AnnotationBasedInvocationHandler.Out;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqrtMethod {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @UaMethod
    public void invoke(
        InvocationContext context,

        @UaInputArgument(
            name = "x",
            description = "A value.")
            Double x,

        @UaOutputArgument(
            name = "x_sqrt",
            description = "The positive square root of x. If the argument is NaN or less than zero, the result is NaN.")
            Out<Double> xSqrt) {

        System.out.println("sqrt(" + x.toString() + ")");
        logger.debug("Invoking sqrt() method of Object '{}'", context.getOwnerNode().getBrowseName().getName());

        xSqrt.set(Math.sqrt(x));
    }

}