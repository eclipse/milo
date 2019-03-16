/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.api;

import org.eclipse.milo.opcua.sdk.server.api.services.AttributeHistoryServices;
import org.eclipse.milo.opcua.sdk.server.api.services.AttributeServices;
import org.eclipse.milo.opcua.sdk.server.api.services.MethodServices;
import org.eclipse.milo.opcua.sdk.server.api.services.MonitoredItemServices;
import org.eclipse.milo.opcua.sdk.server.api.services.NodeManagementServices;
import org.eclipse.milo.opcua.sdk.server.api.services.ViewServices;

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
public interface AddressSpaceServices extends
    AttributeServices,
    AttributeHistoryServices,
    MethodServices,
    MonitoredItemServices,
    NodeManagementServices,
    ViewServices {

}
