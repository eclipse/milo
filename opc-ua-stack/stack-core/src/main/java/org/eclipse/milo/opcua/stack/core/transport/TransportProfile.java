/*
 * Copyright (c) 2018 Kevin Herron
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

package org.eclipse.milo.opcua.stack.core.transport;

import org.eclipse.milo.opcua.stack.core.Stack;

public enum TransportProfile {

    OPC_TCP_UASC_UABINARY,
    OPC_HTTPS_UABINARY,
    OPC_HTTPS_UAXML,
    OPC_HTTPS_UAJSON,
    OPC_WSS_UASC_UABINARY,
    OPC_WSS_UAJSON;

    public static TransportProfile fromUri(String uri) {
        //@formatter:off
        switch (uri) {
            case Stack.UATCP_UASC_UABINARY_TRANSPORT_URI:   return OPC_TCP_UASC_UABINARY;
            case Stack.HTTPS_UABINARY_TRANSPORT_URI:        return OPC_HTTPS_UABINARY;
            case Stack.HTTPS_UAXML_TRANSPORT_URI:           return OPC_HTTPS_UAXML;
            case Stack.HTTPS_UAJSON_TRANSPORT_URI:          return OPC_HTTPS_UAJSON;
            case Stack.WSS_UASC_UABINARY_TRANSPORT_URI:     return OPC_WSS_UASC_UABINARY;
            case Stack.WSS_UAJSON_TRANSPORT_URI:            return OPC_WSS_UAJSON;
            default:
                throw new IllegalArgumentException("unknown transport: " + uri);
        }
        //@formatter:on
    }

    public String getUri() {
        //@formatter:off
        switch (this) {
            case OPC_TCP_UASC_UABINARY: return Stack.UATCP_UASC_UABINARY_TRANSPORT_URI;
            case OPC_HTTPS_UABINARY:    return Stack.HTTPS_UABINARY_TRANSPORT_URI;
            case OPC_HTTPS_UAXML:       return Stack.HTTPS_UAXML_TRANSPORT_URI;
            case OPC_HTTPS_UAJSON:      return Stack.HTTPS_UAJSON_TRANSPORT_URI;
            case OPC_WSS_UASC_UABINARY: return Stack.WSS_UASC_UABINARY_TRANSPORT_URI;
            case OPC_WSS_UAJSON:        return Stack.WSS_UAJSON_TRANSPORT_URI;
            default:
                throw new IllegalArgumentException("unhandled profile: " + this);
        }
        //@formatter:on
    }

}
