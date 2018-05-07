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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;

import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
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

    /**
     * Replace the hostname in the endpoint URL field of {@code endpoint} with a new one.
     * <p>
     * All other fields of the original {@link EndpointDescription} remain equal.
     * <p>
     * If the endpoint URL does not match the expected format the original endpoint is returned.
     *
     * @param endpoint the {@link EndpointDescription} to modify.
     * @param hostname the new hostname to use.
     * @return an updated {@link EndpointDescription} in which the hostname of the endpoint URL has been replaced with
     * {@code hostname}.
     */
    public static EndpointDescription replaceUrlHostname(EndpointDescription endpoint, String hostname) {
        return new EndpointDescription(
            replaceUrlHostname(endpoint.getEndpointUrl(), hostname),
            endpoint.getServer(),
            endpoint.getServerCertificate(),
            endpoint.getSecurityMode(),
            endpoint.getSecurityPolicyUri(),
            endpoint.getUserIdentityTokens(),
            endpoint.getTransportProfileUri(),
            endpoint.getSecurityLevel()
        );

    }

    private static final Pattern ENDPOINT_URL_PATTERN =
        Pattern.compile("opc.tcp://([[a-zA-Z0-9_\\-.]&&[^:/]]+)(:\\d+)?(/?.+)?");

    static String replaceUrlHostname(String endpointUrl, String hostname) {
        Matcher matcher = ENDPOINT_URL_PATTERN.matcher(endpointUrl);

        if (matcher.matches()) {
            String port = matcher.group(2); // e.g. ":4840"
            if (port == null) port = "";

            String path = matcher.group(3); // e.g. "/" or "/foo" or "/foo/bar"
            if (path == null) path = "";

            return "opc.tcp://" + hostname + port + path;
        } else {
            return endpointUrl;
        }
    }

}
