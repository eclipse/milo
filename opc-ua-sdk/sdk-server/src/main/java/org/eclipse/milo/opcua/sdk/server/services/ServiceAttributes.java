/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.services;

import io.netty.util.AttributeKey;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;

public interface ServiceAttributes {

    AttributeKey<OpcUaServer> SERVER_KEY = AttributeKey.valueOf("server");

    AttributeKey<Session> SESSION_KEY = AttributeKey.valueOf("session");

}
