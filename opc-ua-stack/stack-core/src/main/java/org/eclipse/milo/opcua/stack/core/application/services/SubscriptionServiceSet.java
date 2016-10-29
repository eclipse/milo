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

package org.eclipse.milo.opcua.stack.core.application.services;

import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSubscriptionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSubscriptionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteSubscriptionsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteSubscriptionsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ModifySubscriptionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ModifySubscriptionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.PublishRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.PublishResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.RepublishRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.RepublishResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.SetPublishingModeRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.SetPublishingModeResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.TransferSubscriptionsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.TransferSubscriptionsResponse;

public interface SubscriptionServiceSet {

    default void onCreateSubscription(
        ServiceRequest<CreateSubscriptionRequest, CreateSubscriptionResponse> serviceRequest) throws UaException {

        serviceRequest.setServiceFault(StatusCodes.Bad_ServiceUnsupported);
    }

    default void onModifySubscription(
        ServiceRequest<ModifySubscriptionRequest, ModifySubscriptionResponse> serviceRequest) throws UaException {

        serviceRequest.setServiceFault(StatusCodes.Bad_ServiceUnsupported);
    }

    default void onDeleteSubscriptions(
        ServiceRequest<DeleteSubscriptionsRequest, DeleteSubscriptionsResponse> serviceRequest) throws UaException {

        serviceRequest.setServiceFault(StatusCodes.Bad_ServiceUnsupported);
    }

    default void onTransferSubscriptions(
        ServiceRequest<TransferSubscriptionsRequest, TransferSubscriptionsResponse> serviceRequest) throws UaException {

        serviceRequest.setServiceFault(StatusCodes.Bad_ServiceUnsupported);
    }

    default void onSetPublishingMode(
        ServiceRequest<SetPublishingModeRequest, SetPublishingModeResponse> serviceRequest) throws UaException {

        serviceRequest.setServiceFault(StatusCodes.Bad_ServiceUnsupported);
    }

    default void onPublish(ServiceRequest<PublishRequest, PublishResponse> serviceRequest) throws UaException {
        serviceRequest.setServiceFault(StatusCodes.Bad_ServiceUnsupported);
    }

    default void onRepublish(ServiceRequest<RepublishRequest, RepublishResponse> serviceRequest) throws UaException {
        serviceRequest.setServiceFault(StatusCodes.Bad_ServiceUnsupported);
    }

}
