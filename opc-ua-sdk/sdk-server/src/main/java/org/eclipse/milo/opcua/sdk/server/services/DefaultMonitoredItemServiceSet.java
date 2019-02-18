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
import org.eclipse.milo.opcua.stack.server.services.MonitoredItemServiceSet;
import org.eclipse.milo.opcua.stack.server.services.ServiceRequest;

public class DefaultMonitoredItemServiceSet implements MonitoredItemServiceSet {

    private final ServiceCounter createMonitoredItemsMetric = new ServiceCounter();
    private final ServiceCounter modifyMonitoredItemsMetric = new ServiceCounter();
    private final ServiceCounter deleteMonitoredItemsMetric = new ServiceCounter();
    private final ServiceCounter setMonitoringModeMetric = new ServiceCounter();
    private final ServiceCounter setTriggeringMetric = new ServiceCounter();

    private final SubscriptionManager subscriptionManager;

    public DefaultMonitoredItemServiceSet(SubscriptionManager subscriptionManager) {
        this.subscriptionManager = subscriptionManager;
    }

    @Override
    public void onCreateMonitoredItems(ServiceRequest service) throws UaException {
        createMonitoredItemsMetric.record(service);

        subscriptionManager.createMonitoredItems(service);
    }

    @Override
    public void onModifyMonitoredItems(ServiceRequest service) throws UaException {
        modifyMonitoredItemsMetric.record(service);

        subscriptionManager.modifyMonitoredItems(service);
    }

    @Override
    public void onDeleteMonitoredItems(ServiceRequest service) throws UaException {
        deleteMonitoredItemsMetric.record(service);

        subscriptionManager.deleteMonitoredItems(service);
    }

    @Override
    public void onSetMonitoringMode(ServiceRequest service) {
        setMonitoringModeMetric.record(service);

        subscriptionManager.setMonitoringMode(service);
    }

    @Override
    public void onSetTriggering(ServiceRequest service) {
        setTriggeringMetric.record(service);

        subscriptionManager.setTriggering(service);
    }

}
