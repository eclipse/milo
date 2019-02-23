/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
