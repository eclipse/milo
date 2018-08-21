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

package org.eclipse.milo.opcua.sdk.client.session;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Predicate;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.OpcUaSession;
import org.eclipse.milo.opcua.sdk.client.SessionActivityListener;
import org.eclipse.milo.opcua.sdk.client.api.ServiceFaultListener;
import org.eclipse.milo.opcua.sdk.client.session.events.CloseSessionEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.CreateSessionEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.Event;
import org.eclipse.milo.opcua.sdk.client.session.events.ServiceFaultEvent;
import org.eclipse.milo.opcua.sdk.client.session.states.Active;
import org.eclipse.milo.opcua.sdk.client.session.states.Inactive;
import org.eclipse.milo.opcua.sdk.client.session.states.SessionState;
import org.eclipse.milo.opcua.stack.client.UaTcpStackClient;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.ServiceFault;
import org.eclipse.milo.opcua.stack.core.util.ExecutionQueue;
import org.eclipse.milo.opcua.stack.core.util.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.collect.Lists.newCopyOnWriteArrayList;
import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.completeAsync;

public class SessionFsm {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Fsm fsm = new Fsm() {
        @Override
        public OpcUaClient getClient() {
            return SessionFsm.this.client;
        }

        @Override
        public void fireEvent(Event event) {
            SessionFsm.this.fireEvent(event);
        }

        @Override
        public List<SessionInitializer> getInitializers() {
            return initializers;
        }
    };

    private final List<SessionInitializer> initializers = newCopyOnWriteArrayList();
    private final List<SessionActivityListener> listeners = newCopyOnWriteArrayList();
    private final AtomicReference<SessionState> state = new AtomicReference<>(new Inactive());
    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

    private final ExecutionQueue notificationQueue;

    private final OpcUaClient client;

    public SessionFsm(OpcUaClient client) {
        this.client = client;

        notificationQueue = new ExecutionQueue(client.getConfig().getExecutor());

        client.addFaultListener(new FaultListener(fsm));
    }

    public CompletableFuture<OpcUaSession> openSession() {
        CompletableFuture<OpcUaSession> sessionFuture = new CompletableFuture<>();

        fireEvent(new CreateSessionEvent(sessionFuture));

        return completeAsync(
            new CompletableFuture<OpcUaSession>(),
            client.getConfig().getExecutor()
        ).with(sessionFuture);
    }

    public CompletableFuture<Unit> closeSession() {
        CompletableFuture<Unit> closeFuture = new CompletableFuture<>();

        fireEvent(new CloseSessionEvent(closeFuture));

        return completeAsync(
            new CompletableFuture<Unit>(),
            client.getConfig().getExecutor()
        ).with(closeFuture);
    }

    public CompletableFuture<OpcUaSession> getSession() {
        readWriteLock.readLock().lock();
        try {
            CompletableFuture<OpcUaSession> sessionFuture = state.get().getSessionFuture();

            if (sessionFuture.isDone()) {
                return sessionFuture;
            } else {
                return completeAsync(
                    new CompletableFuture<OpcUaSession>(),
                    client.getConfig().getExecutor()
                ).with(sessionFuture);
            }
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public void addInitializer(SessionInitializer initializer) {
        initializers.add(initializer);
    }

    public void removeInitializer(SessionInitializer initializer) {
        initializers.remove(initializer);
    }

    public void addListener(SessionActivityListener listener) {
        listeners.add(listener);
    }

    public void removeListener(SessionActivityListener listener) {
        listeners.remove(listener);
    }

    private void fireEvent(Event event) {
        if (readWriteLock.writeLock().isHeldByCurrentThread()) {
            client.getConfig().getExecutor()
                .execute(() -> fireEvent(event));
        } else {
            readWriteLock.writeLock().lock();

            try {
                SessionState prevState = state.get();

                SessionState nextState = state.updateAndGet(
                    state -> state.execute(fsm, event)
                );

                logger.debug(
                    "S({}) x E({}) = S'({})",
                    prevState.getClass().getSimpleName(),
                    event.getClass().getSimpleName(),
                    nextState.getClass().getSimpleName()
                );

                if (prevState.getClass() == nextState.getClass()) {
                    nextState.onInternalTransition(fsm, event);
                } else {
                    nextState.onExternalTransition(fsm, prevState, event);

                    if (nextState instanceof Active) {
                        // transition from non-Active to Active
                        notifySessionActive(((Active) nextState).getSession());
                    } else if (prevState instanceof Active) {
                        // transition from Active to non-Active
                        notifySessionInactive(((Active) prevState).getSession());
                    }
                }
            } finally {
                readWriteLock.writeLock().unlock();
            }
        }
    }

    private void notifySessionActive(OpcUaSession session) {
        logger.debug("notifySessionActive()");

        notificationQueue.submit(() -> {
            logger.debug("notifying {} listeners...", listeners.size());

            listeners.forEach(listener -> {
                try {
                    listener.onSessionActive(session);
                } catch (Throwable t) {
                    logger.warn("Uncaught Throwable notifying listener: {}", listener, t);
                }
            });
        });
    }

    private void notifySessionInactive(OpcUaSession session) {
        logger.debug("notifySessionInactive()");

        notificationQueue.submit(() -> {
            logger.debug("notifying {} listeners...", listeners.size());

            listeners.forEach(listener -> {
                try {
                    listener.onSessionInactive(session);
                } catch (Throwable t) {
                    logger.warn("Uncaught Throwable notifying listener: {}", listener, t);
                }
            });
        });
    }

    private static class FaultListener implements ServiceFaultListener {

        private static final Predicate<StatusCode> SESSION_ERROR = statusCode -> {
            long status = statusCode.getValue();

            return status == StatusCodes.Bad_SessionClosed ||
                status == StatusCodes.Bad_SessionIdInvalid ||
                status == StatusCodes.Bad_SessionNotActivated;
        };

        private static final Predicate<StatusCode> SECURE_CHANNEL_ERROR = statusCode -> {
            long status = statusCode.getValue();

            return status == StatusCodes.Bad_SecureChannelIdInvalid ||
                status == StatusCodes.Bad_SecurityChecksFailed ||
                status == StatusCodes.Bad_TcpSecureChannelUnknown ||
                status == StatusCodes.Bad_RequestTypeInvalid;
        };

        private final Logger logger = LoggerFactory.getLogger(getClass());

        private final Fsm fsm;

        FaultListener(Fsm fsm) {
            this.fsm = fsm;
        }

        @Override
        public void onServiceFault(ServiceFault serviceFault) {
            StatusCode serviceResult = serviceFault.getResponseHeader().getServiceResult();

            if (SESSION_ERROR.or(SECURE_CHANNEL_ERROR).test(serviceResult)) {
                logger.debug("ServiceFault: {}", serviceResult);

                fsm.fireEvent(new ServiceFaultEvent(serviceResult));
            }
        }

    }

    public interface SessionInitializer {

        CompletableFuture<Unit> initialize(UaTcpStackClient stackClient, OpcUaSession session);

    }

}
