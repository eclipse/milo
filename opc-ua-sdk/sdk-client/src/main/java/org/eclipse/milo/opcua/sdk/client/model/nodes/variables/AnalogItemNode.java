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

package org.eclipse.milo.opcua.sdk.client.model.nodes.variables;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.AnalogItemType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.EUInformation;
import org.eclipse.milo.opcua.stack.core.types.structured.Range;

public class AnalogItemNode extends DataItemNode implements AnalogItemType {
    public AnalogItemNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getInstrumentRangeNode() {
        return getPropertyNode(AnalogItemType.INSTRUMENT_RANGE);
    }

    public CompletableFuture<Range> getInstrumentRange() {
        return getProperty(AnalogItemType.INSTRUMENT_RANGE);
    }

    public CompletableFuture<StatusCode> setInstrumentRange(Range value) {
        return setProperty(AnalogItemType.INSTRUMENT_RANGE, value);
    }

    public CompletableFuture<PropertyNode> getEURangeNode() {
        return getPropertyNode(AnalogItemType.E_U_RANGE);
    }

    public CompletableFuture<Range> getEURange() {
        return getProperty(AnalogItemType.E_U_RANGE);
    }

    public CompletableFuture<StatusCode> setEURange(Range value) {
        return setProperty(AnalogItemType.E_U_RANGE, value);
    }

    public CompletableFuture<PropertyNode> getEngineeringUnitsNode() {
        return getPropertyNode(AnalogItemType.ENGINEERING_UNITS);
    }

    public CompletableFuture<EUInformation> getEngineeringUnits() {
        return getProperty(AnalogItemType.ENGINEERING_UNITS);
    }

    public CompletableFuture<StatusCode> setEngineeringUnits(EUInformation value) {
        return setProperty(AnalogItemType.ENGINEERING_UNITS, value);
    }
}
