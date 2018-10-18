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

package org.eclipse.milo.opcua.sdk.server.subscriptions;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import org.eclipse.milo.opcua.stack.core.application.services.ServiceRequest;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.PublishRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.PublishResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.RequestHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PublishQueue {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final LinkedList<ServiceRequest<PublishRequest, PublishResponse>> serviceQueue = new LinkedList<>();

    private final LinkedHashMap<UInteger, WaitingSubscription> waitList = new LinkedHashMap<>();

    /**
     * Add a Publish {@link ServiceRequest} to the queue.
     * <p>
     * If there are wait-listed Subscriptions this request will be used immediately, otherwise it will be queued for
     * later use by a Subscription whose publish timer has expired and has notifications to send.
     *
     * @param service the Publish {@link ServiceRequest}.
     */
    public synchronized void addRequest(ServiceRequest<PublishRequest, PublishResponse> service) {
        List<WaitingSubscription> waitingSubscriptions = Lists.newArrayList(waitList.values());

        if (waitingSubscriptions.isEmpty()) {
            serviceQueue.add(service);

            logger.debug("Queued PublishRequest requestHandle={}, size={}",
                service.getRequest().getRequestHeader().getRequestHandle(),
                serviceQueue.size()
            );
        } else {
            WaitingSubscription subscription = null;

            int maxPriority = 0;
            long minWaitingSince = Long.MAX_VALUE;

            for (WaitingSubscription waiting : waitingSubscriptions) {
                int priority = waiting.getSubscription().getPriority();
                long waitingSince = waiting.getWaitingSince().getTime();

                if (priority > maxPriority) {
                    maxPriority = priority;
                    minWaitingSince = Long.MAX_VALUE;
                }
                if (priority >= maxPriority && waitingSince < minWaitingSince) {
                    minWaitingSince = waitingSince;
                    subscription = waiting;
                }
            }

            if (subscription != null) {
                waitList.remove(subscription.subscription.getId());

                logger.debug("Delivering PublishRequest to Subscription [id={}]",
                    subscription.getSubscription().getId());

                final WaitingSubscription ws = subscription;

                service.getServer().getExecutorService().execute(
                    () -> ws.subscription.onPublish(service)
                );
            } else {
                serviceQueue.add(service);
            }
        }
    }

    /**
     * Add a subscription to the wait list.
     * <p>
     * A subscription should be added to the wait list when either:
     * <p>
     * a) The previous Publish response indicated that there were still more Notifications ready to be transferred and
     * there were no more Publish requests queued to transfer them.
     * <p>
     * b) The publishing timer of a Subscription expired and there were either Notifications to be sent or a keep-alive
     * Message to be sent.
     *
     * @param subscription the subscription to wait-list.
     */
    public synchronized void addSubscription(Subscription subscription) {
        if (waitList.isEmpty() && !serviceQueue.isEmpty()) {
            ServiceRequest<PublishRequest, PublishResponse> request = poll();

            if (request != null) {
                request.getServer().getExecutorService().execute(
                    () ->
                        subscription.onPublish(request)
                );
            } else {
                waitList.putIfAbsent(subscription.getId(), new WaitingSubscription(subscription));
            }
        } else {
            waitList.putIfAbsent(subscription.getId(), new WaitingSubscription(subscription));
        }
    }

    public synchronized boolean isEmpty() {
        return serviceQueue.isEmpty();
    }

    public synchronized boolean isNotEmpty() {
        return !isEmpty();
    }

    @Nullable
    public synchronized ServiceRequest<PublishRequest, PublishResponse> poll() {
        long now = System.currentTimeMillis();

        while (true) {
            ServiceRequest<PublishRequest, PublishResponse> serviceRequest = serviceQueue.poll();

            if (serviceRequest == null) {
                return null;
            } else {
                RequestHeader requestHeader = serviceRequest
                    .getRequest()
                    .getRequestHeader();

                long timeoutHint = requestHeader.getTimeoutHint().longValue();
                long timestamp = requestHeader.getTimestamp().getJavaTime();

                if (timeoutHint == 0 || timestamp + timeoutHint >= now) {
                    return serviceRequest;
                } else {
                    logger.debug(
                        "Discarding expired PublishRequest requestHandle={} timestamp={} timeoutHint={}",
                        serviceRequest.getRequest().getRequestHeader().getRequestHandle(),
                        requestHeader.getTimestamp().getJavaDate(),
                        timeoutHint
                    );
                }
            }
        }
    }

    public static class WaitingSubscription {

        private final Date waitingSince = new Date();

        private final Subscription subscription;

        public WaitingSubscription(Subscription subscription) {
            this.subscription = subscription;
        }

        public Subscription getSubscription() {
            return subscription;
        }

        public Date getWaitingSince() {
            return waitingSince;
        }

    }

}
