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

package org.eclipse.milo.opcua.sdk.server.events;

import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.api.AccessContext;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.BaseEventNode;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.structured.ContentFilterElement;
import org.eclipse.milo.opcua.stack.core.types.structured.FilterOperand;

public interface OperatorContext extends AccessContext {

    OpcUaServer getServer();

    ContentFilterElement[] getElements();

    Object resolve(FilterOperand operand, BaseEventNode eventNode) throws UaException;

}
