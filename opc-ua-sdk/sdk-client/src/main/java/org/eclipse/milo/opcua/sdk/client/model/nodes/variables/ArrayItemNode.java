/*
 * Copyright (c) 2016 Kevin Herron
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
import org.eclipse.milo.opcua.sdk.client.model.types.variables.ArrayItemType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.AxisScaleEnumeration;
import org.eclipse.milo.opcua.stack.core.types.structured.EUInformation;
import org.eclipse.milo.opcua.stack.core.types.structured.Range;


public class ArrayItemNode extends DataItemNode implements ArrayItemType {

    public ArrayItemNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<PropertyNode> instrumentRange() {
        return getPropertyNode(ArrayItemType.INSTRUMENT_RANGE.getBrowseName());
    }

    @Override
    public CompletableFuture<Range> getInstrumentRange() {
        return getProperty(ArrayItemType.INSTRUMENT_RANGE);
    }

    @Override
    public CompletableFuture<StatusCode> setInstrumentRange(Range value) {
        return setProperty(ArrayItemType.INSTRUMENT_RANGE, value);
    }

    @Override
    public CompletableFuture<PropertyNode> eURange() {
        return getPropertyNode(ArrayItemType.E_U_RANGE.getBrowseName());
    }

    @Override
    public CompletableFuture<Range> getEURange() {
        return getProperty(ArrayItemType.E_U_RANGE);
    }

    @Override
    public CompletableFuture<StatusCode> setEURange(Range value) {
        return setProperty(ArrayItemType.E_U_RANGE, value);
    }

    @Override
    public CompletableFuture<PropertyNode> engineeringUnits() {
        return getPropertyNode(ArrayItemType.ENGINEERING_UNITS.getBrowseName());
    }

    @Override
    public CompletableFuture<EUInformation> getEngineeringUnits() {
        return getProperty(ArrayItemType.ENGINEERING_UNITS);
    }

    @Override
    public CompletableFuture<StatusCode> setEngineeringUnits(EUInformation value) {
        return setProperty(ArrayItemType.ENGINEERING_UNITS, value);
    }

    @Override
    public CompletableFuture<PropertyNode> title() {
        return getPropertyNode(ArrayItemType.TITLE.getBrowseName());
    }

    @Override
    public CompletableFuture<LocalizedText> getTitle() {
        return getProperty(ArrayItemType.TITLE);
    }

    @Override
    public CompletableFuture<StatusCode> setTitle(LocalizedText value) {
        return setProperty(ArrayItemType.TITLE, value);
    }

    @Override
    public CompletableFuture<PropertyNode> axisScaleType() {
        return getPropertyNode(ArrayItemType.AXIS_SCALE_TYPE.getBrowseName());
    }

    @Override
    public CompletableFuture<AxisScaleEnumeration> getAxisScaleType() {
        return getProperty(ArrayItemType.AXIS_SCALE_TYPE);
    }

    @Override
    public CompletableFuture<StatusCode> setAxisScaleType(AxisScaleEnumeration value) {
        return setProperty(ArrayItemType.AXIS_SCALE_TYPE, value);
    }


}