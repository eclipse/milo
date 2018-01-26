/*
 * Copyright (c) 2017 Kevin Herron
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

package org.eclipse.milo.opcua.stack.core.util;

import java.net.URI;
import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EndpointUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(EndpointUtil.class);

    /**
     * Get the path component from an endpoint URL.
     * <p>
     * An empty path becomes "/" and trailing "/" are removed from non-empty paths.
     * <p>
     * If the URL is not valid the full URL will be returned.
     *
     * @param endpointUrl the endpoint URL.
     * @return the path component from the endpoint URL.
     */
    public static String getPath(@Nonnull String endpointUrl) {
        try {
            URI uri = new URI(endpointUrl).parseServerAuthority();

            String path = uri.getPath();

            if (path == null || path.isEmpty()) {
                path = "/";
            } else if (path.length() > 1 && path.endsWith("/")) {
                path = path.substring(0, path.length() - 1);
            }
            return path;
        } catch (Throwable e) {
            LOGGER.warn("Endpoint URL '{}' is not a valid URI: {}", e.getMessage(), e);
            return endpointUrl;
        }
    }

}
