/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server;

import org.eclipse.milo.opcua.sdk.server.services.AttributeHistoryServices;
import org.eclipse.milo.opcua.sdk.server.services.AttributeServices;
import org.eclipse.milo.opcua.sdk.server.services.MethodServices;
import org.eclipse.milo.opcua.sdk.server.services.MonitoredItemServices;
import org.eclipse.milo.opcua.sdk.server.services.NodeManagementServices;
import org.eclipse.milo.opcua.sdk.server.services.ViewServices;

/**
 * A composite interface composed of service sub-interfaces an {@link AddressSpace} must implement:
 * <ul>
 * <li>{@link AttributeServices}</li>
 * <li>{@link AttributeHistoryServices}</li>
 * <li>{@link MethodServices}</li>
 * <li>{@link MonitoredItemServices}</li>
 * <li>{@link NodeManagementServices}</li>
 * <li>{@link ViewServices}</li>
 * </ul>
 */
public interface AddressSpace extends
    AttributeServices,
    AttributeHistoryServices,
    MethodServices,
    MonitoredItemServices,
    NodeManagementServices,
    ViewServices {

}
