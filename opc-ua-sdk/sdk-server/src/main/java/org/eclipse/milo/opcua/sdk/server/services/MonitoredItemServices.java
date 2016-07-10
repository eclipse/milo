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

package org.eclipse.milo.opcua.sdk.server.services;

import org.eclipse.milo.opcua.sdk.server.subscriptions.SubscriptionManager;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.application.services.MonitoredItemServiceSet;
import org.eclipse.milo.opcua.stack.core.application.services.ServiceRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateMonitoredItemsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateMonitoredItemsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteMonitoredItemsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteMonitoredItemsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ModifyMonitoredItemsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ModifyMonitoredItemsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.SetMonitoringModeRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.SetMonitoringModeResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.SetTriggeringRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.SetTriggeringResponse;

public class MonitoredItemServices implements MonitoredItemServiceSet {

    private final ServiceMetric createMonitoredItemsMetric = new ServiceMetric();
    private final ServiceMetric modifyMonitoredItemsMetric = new ServiceMetric();
    private final ServiceMetric deleteMonitoredItemsMetric = new ServiceMetric();
    private final ServiceMetric setMonitoringModeMetric = new ServiceMetric();
    private final ServiceMetric setTriggeringMetric = new ServiceMetric();

    private final SubscriptionManager subscriptionManager;

    public MonitoredItemServices(SubscriptionManager subscriptionManager) {
        this.subscriptionManager = subscriptionManager;
    }

    @Override
    public void onCreateMonitoredItems(
        ServiceRequest<CreateMonitoredItemsRequest, CreateMonitoredItemsResponse> service) {

        createMonitoredItemsMetric.record(service);

        subscriptionManager.createMonitoredItems(service);
    }

    @Override
    public void onModifyMonitoredItems(
        ServiceRequest<ModifyMonitoredItemsRequest, ModifyMonitoredItemsResponse> service) {

        modifyMonitoredItemsMetric.record(service);

        subscriptionManager.modifyMonitoredItems(service);
    }

    @Override
    public void onDeleteMonitoredItems(
        ServiceRequest<DeleteMonitoredItemsRequest, DeleteMonitoredItemsResponse> service) {

        deleteMonitoredItemsMetric.record(service);

        subscriptionManager.deleteMonitoredItems(service);
    }

    @Override
    public void onSetMonitoringMode(
        ServiceRequest<SetMonitoringModeRequest, SetMonitoringModeResponse> service) {

        setMonitoringModeMetric.record(service);

        subscriptionManager.setMonitoringMode(service);
    }

    @Override
    public void onSetTriggering(
        ServiceRequest<SetTriggeringRequest, SetTriggeringResponse> service) throws UaException {

        setTriggeringMetric.record(service);

        subscriptionManager.setTriggering(service);
    }
}
