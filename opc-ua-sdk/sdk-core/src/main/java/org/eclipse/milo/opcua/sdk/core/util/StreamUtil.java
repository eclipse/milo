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

package org.eclipse.milo.opcua.sdk.core.util;

import java.util.Optional;
import java.util.stream.Stream;

public class StreamUtil {

    /**
     * Turns an Optional into a Stream of length zero or one depending upon whether a value is present.
     */
    public static <T> Stream<T> opt2stream(Optional<T> opt) {
        return opt.map(Stream::of).orElseGet(Stream::empty);
    }

}
