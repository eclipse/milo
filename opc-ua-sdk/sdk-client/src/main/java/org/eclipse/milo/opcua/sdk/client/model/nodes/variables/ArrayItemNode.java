/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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

    public CompletableFuture<PropertyNode> getInstrumentRangeNode() {
        return getPropertyNode(ArrayItemType.INSTRUMENT_RANGE);
    }

    public CompletableFuture<Range> getInstrumentRange() {
        return getProperty(ArrayItemType.INSTRUMENT_RANGE);
    }

    public CompletableFuture<StatusCode> setInstrumentRange(Range value) {
        return setProperty(ArrayItemType.INSTRUMENT_RANGE, value);
    }

    public CompletableFuture<PropertyNode> getEURangeNode() {
        return getPropertyNode(ArrayItemType.E_U_RANGE);
    }

    public CompletableFuture<Range> getEURange() {
        return getProperty(ArrayItemType.E_U_RANGE);
    }

    public CompletableFuture<StatusCode> setEURange(Range value) {
        return setProperty(ArrayItemType.E_U_RANGE, value);
    }

    public CompletableFuture<PropertyNode> getEngineeringUnitsNode() {
        return getPropertyNode(ArrayItemType.ENGINEERING_UNITS);
    }

    public CompletableFuture<EUInformation> getEngineeringUnits() {
        return getProperty(ArrayItemType.ENGINEERING_UNITS);
    }

    public CompletableFuture<StatusCode> setEngineeringUnits(EUInformation value) {
        return setProperty(ArrayItemType.ENGINEERING_UNITS, value);
    }

    public CompletableFuture<PropertyNode> getTitleNode() {
        return getPropertyNode(ArrayItemType.TITLE);
    }

    public CompletableFuture<LocalizedText> getTitle() {
        return getProperty(ArrayItemType.TITLE);
    }

    public CompletableFuture<StatusCode> setTitle(LocalizedText value) {
        return setProperty(ArrayItemType.TITLE, value);
    }

    public CompletableFuture<PropertyNode> getAxisScaleTypeNode() {
        return getPropertyNode(ArrayItemType.AXIS_SCALE_TYPE);
    }

    public CompletableFuture<AxisScaleEnumeration> getAxisScaleType() {
        return getProperty(ArrayItemType.AXIS_SCALE_TYPE);
    }

    public CompletableFuture<StatusCode> setAxisScaleType(AxisScaleEnumeration value) {
        return setProperty(ArrayItemType.AXIS_SCALE_TYPE, value);
    }
}
