/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.transport.client.tcp;

import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.transport.client.OpcClientTransportConfig;
import org.eclipse.milo.opcua.stack.transport.client.uasc.UascClientConfig;

public interface OpcTcpClientTransportConfig extends OpcClientTransportConfig, UascClientConfig {

    UInteger getConnectTimeout();

    static OpcTcpClientTransportConfigBuilder newBuilder() {
        return new OpcTcpClientTransportConfigBuilder();
    }

}
