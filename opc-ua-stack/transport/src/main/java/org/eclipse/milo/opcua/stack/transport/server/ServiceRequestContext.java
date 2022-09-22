/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.transport.server;


import io.netty.channel.Channel;
import org.eclipse.milo.opcua.stack.core.channel.SecureChannel;

public interface ServiceRequestContext {

    String getEndpointUrl();

    Channel getChannel();

    SecureChannel getSecureChannel();

    Long receivedAtNanos();

}
