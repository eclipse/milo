/*
 * Copyright (c) 2016 Jens Reimann
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *  http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.newtypes;

public interface UnsignedArithmetic<T extends UnsignedArithmetic<T>> {

    T plus(T value) throws OutOfRangeException;

    T minus(T value) throws OutOfRangeException;

}
