/*
 * Copyright (c) 2018 Kevin Herron
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

package org.eclipse.milo.opcua.stack.server.handlers;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import com.google.common.collect.ConcurrentHashMultiset;
import com.google.common.collect.Maps;
import com.google.common.collect.Multiset;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.ipfilter.AbstractRemoteAddressFilter;
import org.eclipse.milo.opcua.stack.server.tcp.UaTcpStackServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A shared, stack-wide, one-per-application-regardless-of-how-many-server-instances-you-have handler that is added to
 * the beginning of every server pipeline to handle rate limiting and connection limits.
 * <p>
 * Any configuration changes must be made before {@link UaTcpStackServer#startup()} is called for the first time,
 * application-wide. Once the instance has been created further configuration changes will have no effect.
 */
@ChannelHandler.Sharable
public class RateLimitingHandler extends AbstractRemoteAddressFilter<InetSocketAddress> {

    public static final AtomicLong CUMULATIVE_REJECTION_COUNT = new AtomicLong(0L);

    /**
     * Allows rate limiting to be disabled stack-wide.
     */
    @SuppressWarnings("WeakerAccess")
    public static boolean ENABLED = true;

    /**
     * Maximum number of connect attempts per {@link #RATE_LIMIT_WINDOW_MS}.
     */
    @SuppressWarnings("WeakerAccess")
    public static int MAX_ATTEMPTS = 3;

    /**
     * The window of time over which connect attempts will be counted for rate limiting.
     */
    @SuppressWarnings("WeakerAccess")
    public static int RATE_LIMIT_WINDOW_MS = 1000;

    /**
     * The maximum number of connections allowed in total (any remote address, not including localhost).
     */
    @SuppressWarnings("WeakerAccess")
    public static int MAX_CONNECTIONS = 10000;

    /**
     * The maximum number of connections allowed from any 1 remote address.
     */
    @SuppressWarnings("WeakerAccess")
    public static int MAX_CONNECTIONS_PER_ADDRESS = 100;


    /**
     * Get the shared {@link RateLimitingHandler} instance.
     * <p>
     * The values of {@link #ENABLED}, {@link #MAX_ATTEMPTS}, {@link #RATE_LIMIT_WINDOW_MS}, {@link #MAX_CONNECTIONS},
     * and {@link #MAX_CONNECTIONS_PER_ADDRESS} will be locked in whenever the first invocation of this method occurs.
     *
     * @return the shared {@link RateLimitingHandler} instance.
     */
    public static RateLimitingHandler getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private static class InstanceHolder {

        private static final RateLimitingHandler INSTANCE = new RateLimitingHandler(
            ENABLED, MAX_ATTEMPTS, RATE_LIMIT_WINDOW_MS, MAX_CONNECTIONS, MAX_CONNECTIONS_PER_ADDRESS);

    }

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Multiset<InetAddress> connections = ConcurrentHashMultiset.create();
    private final ConcurrentMap<InetAddress, LinkedList<Date>> timestamps = Maps.newConcurrentMap();

    private final boolean enabled;
    private final int maxAttempts;
    private final int rateLimitWindowMs;
    private final int maxConnections;
    private final int maxConnectionsPerAddress;

    private RateLimitingHandler(
        boolean enabled,
        int maxAttempts,
        int rateLimitWindowMs,
        int maxConnections,
        int maxConnectionsPerAddress) {

        this.enabled = enabled;
        this.maxAttempts = maxAttempts;
        this.rateLimitWindowMs = rateLimitWindowMs;
        this.maxConnections = maxConnections;
        this.maxConnectionsPerAddress = maxConnectionsPerAddress;

        logger.debug(
            "enabled=%s, maxAttempts=%s, rateLimitWindowMs=%s, maxConnections=%s, maxConnectionsPerAddress=%s",
            enabled, maxAttempts, rateLimitWindowMs, maxConnections, maxConnectionsPerAddress);
    }

    @Override
    protected synchronized boolean accept(ChannelHandlerContext ctx, InetSocketAddress isa) {
        final InetAddress address = isa.getAddress();

        if (!enabled || address.isLoopbackAddress()) {
            return true;
        }

        LinkedList<Date> dates = timestamps.computeIfAbsent(address, ia -> new LinkedList<>());

        if (dates.size() >= maxAttempts) {
            long now = System.currentTimeMillis();

            // count the number of previous connections from this address
            // that have occurred within the rate limit window.
            int attemptsInWindow = 0;
            for (Date d : dates) {
                if (now - d.getTime() < rateLimitWindowMs) {
                    attemptsInWindow++;
                }
            }

            dates.addLast(new Date());
            while (dates.size() > maxAttempts) {
                dates.removeFirst();
            }

            int connectionsTotal = connections.size();
            int connectionsFromAddress = connections.count(address);

            boolean accept = attemptsInWindow < maxAttempts &&
                connectionsTotal < maxConnections &&
                connectionsFromAddress < maxConnectionsPerAddress;

            if (accept) {
                logger.debug(String.format(
                    "Accepting connection from %s. " +
                        "window=%sms, attemptsInWindow=%s, connectionsTotal=%s, connectionsFromAddress=%s",
                    isa, rateLimitWindowMs, attemptsInWindow, connectionsTotal, connectionsFromAddress));
            } else {
                logger.debug(String.format(
                    "Rejecting connection from %s. " +
                        "window=%sms, attemptsInWindow=%s, connectionsTotal=%s, connectionsFromAddress=%s",
                    isa, rateLimitWindowMs, attemptsInWindow, connectionsTotal, connectionsFromAddress));

                long cumulativeRejectionCount = CUMULATIVE_REJECTION_COUNT.incrementAndGet();
                logger.debug("cumulativeRejectionCount=" + cumulativeRejectionCount);
            }

            return accept;
        } else {
            dates.addLast(new Date());

            return true;
        }
    }

    @Override
    protected void channelAccepted(ChannelHandlerContext ctx, InetSocketAddress remoteAddress) {
        final InetAddress address = remoteAddress.getAddress();

        if (!enabled || address.isLoopbackAddress()) {
            return;
        }

        connections.add(address);

        ctx.channel().closeFuture().addListener((ChannelFutureListener) future -> {
            connections.remove(address);

            if (connections.count(address) == 0) {
                logger.debug("Scheduling timestamp removal for " + address);

                ctx.executor().schedule(
                    () -> {
                        // If there's still no connections from the remote address after the rate limit window remove
                        // the timestamps.
                        // Removing them before the window elapses would allow a remote address to connect/disconnect
                        // at an effectively unlimited rate.
                        if (connections.count(address) == 0) {
                            timestamps.remove(address);
                            logger.debug("Removed timestamps for " + address);
                        }
                    },
                    rateLimitWindowMs,
                    TimeUnit.MILLISECONDS
                );
            }
        });
    }

}
