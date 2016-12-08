/*
 * Copyright (c) 2016 Stefan Profanter and others
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

package org.eclipse.milo.opcua.sdk.server.services.helpers;


import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.ServerOnNetwork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MdnsHelper implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private JmDNS jmdns = null;

    private final String mdnsListening = "_opcua-tcp._tcp.local.";

    private Consumer<ServerOnNetwork> multicastServerConsumer = null;

    private HashMap<String, ServerOnNetworkMdns> serverOnNetworkMap;
    private LinkedList<ServerOnNetwork> serverOnNetworkList;

    private int lastServerOnNetworkId;

    private Date lastServerOnNetworkIdReset;
    private InetAddress ownAddress;

    private Optional<InetAddress> getMostPublicAddress(Set<String> bindAddresses) {
        Function<InetAddress, Integer> addressToInt = inetAddress -> {
            if (inetAddress.isLoopbackAddress()) {
                return 0;
            }
            if (inetAddress.isLinkLocalAddress()) {
                return 1;
            }
            if (inetAddress.isMulticastAddress()) {
                return 2;
            }
            if (inetAddress.isAnyLocalAddress()) {
                return 3;
            }
            if (inetAddress.isSiteLocalAddress()) {
                return 4;
            }
            return 5;
        };

        return bindAddresses.stream().map(s -> {
            try {
                return InetAddress.getByName(s);
            } catch (UnknownHostException e) {
                e.printStackTrace();
                return null;
            }
        }).sorted((a1, a2) -> addressToInt.apply(a2) - addressToInt.apply(a1)).findFirst();
    }

    public MdnsHelper(Set<String> bindAddresses) {

        Optional<InetAddress> highestPrioAddress = getMostPublicAddress(bindAddresses);

        if (highestPrioAddress.isPresent()) {
            ownAddress = highestPrioAddress.get();
        } else {
            try {
                logger.warn("mDNS: no bind address valid. Setting to 0.0.0.0");
                ownAddress = InetAddress.getByName("0.0.0.0"); // Create a JmDNS instance
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }

        try {
            jmdns = JmDNS.create(ownAddress);
        } catch (IOException e) {
            logger.error("Could not create instance of JmDNS: " + e.getMessage());
            e.printStackTrace();
        }
        serverOnNetworkMap = new HashMap<>();
        serverOnNetworkList = new LinkedList<>();
        lastServerOnNetworkId = 0;
        lastServerOnNetworkIdReset = new Date();
    }

    public void setMulticastServerConsumer(Consumer<ServerOnNetwork> multicastServerConsumer) {
        this.multicastServerConsumer = multicastServerConsumer;
    }


    public boolean addRecord(String mdnsName, int port, String endpointName, String[] capabilities) {
        if (endpointName.length() > 2 && endpointName.startsWith("/")) {
            endpointName = endpointName.substring(1);
        }

        String txt = "path=/" + endpointName;
        if (capabilities != null && capabilities.length > 0) {
            txt += " caps=" + String.join(",", (CharSequence[]) capabilities);
        }

        ServiceInfo serviceInfo = ServiceInfo.create(mdnsListening, mdnsName, port, txt);
        try {
            jmdns.registerService(serviceInfo);
        } catch (IOException e) {
            logger.error("Could not add mDNS record for: " + mdnsName + " on port " + port + " with endpoint path: " +
                endpointName + ". Error: " + e.getMessage());
            return false;
        }
        return true;
    }


    public boolean removeRecord(String mdnsName, int port, String endpointName) {
        if (endpointName.length() > 2 && endpointName.startsWith("/")) {
            endpointName = endpointName.substring(1);
        }

        ServiceInfo serviceInfo = ServiceInfo.create(mdnsListening, mdnsName, port, "path=/" + endpointName);
        jmdns.unregisterService(serviceInfo);
        return true;
    }

    private String serviceInfoToDiscoveryUrl(ServiceInfo serviceInfo) {
        String discoveryUrl = null;

        InetAddress[] addressList = serviceInfo.getInetAddresses();
        for (InetAddress mdnsIa : addressList) {

            discoveryUrl = "opc.tcp://" + mdnsIa.getHostAddress() + ":" + serviceInfo.getPort() +
                serviceInfo.getPropertyString("path");

            if (discoveryUrl.endsWith("/")) {
                discoveryUrl = discoveryUrl.substring(0, discoveryUrl.length() - 1);
            }

            break;

        }

        return discoveryUrl;
    }

    private void processRecord(ServiceInfo serviceInfo) {

        String discoveryUrl = serviceInfoToDiscoveryUrl(serviceInfo);


        if (!serverOnNetworkMap.containsKey(discoveryUrl)) {


            String[] caps;

            if (serviceInfo.getPropertyString("caps") != null) {
                caps = serviceInfo.getPropertyString("caps").split(",");
            } else {
                caps = new String[0];
            }


            if (lastServerOnNetworkId == Integer.MAX_VALUE) {
                lastServerOnNetworkIdReset = new Date();
                lastServerOnNetworkId = 0;
            }

            ServerOnNetwork son = addToServerOnNetwork(serviceInfo.getName(), discoveryUrl, caps);

            if (multicastServerConsumer != null) {
                multicastServerConsumer.accept(son);
            }
        } else {

            serverOnNetworkMap.get(discoveryUrl).lastSeen = new Date();
        }

    }

    public ServerOnNetwork addToServerOnNetwork(String serverName, String discoveryUrl, String[] caps) {
        if (serverOnNetworkMap.containsKey(discoveryUrl)) {
            serverOnNetworkMap.get(discoveryUrl).lastSeen = new Date();
            return serverOnNetworkMap.get(discoveryUrl).serverOnNetwork;
        }

        ServerOnNetworkMdns entry = new ServerOnNetworkMdns();
        entry.serverOnNetwork =
            new ServerOnNetwork(UInteger.valueOf(++lastServerOnNetworkId), serverName, discoveryUrl, caps);
        entry.lastSeen = new Date();
        serverOnNetworkMap.put(discoveryUrl, entry);
        serverOnNetworkList.add(entry.serverOnNetwork);
        return entry.serverOnNetwork;
    }

    public boolean removeFromServerOnNetwork(String discoveryUrl) {
        //noinspection SuspiciousMethodCalls
        boolean retVal = serverOnNetworkList.remove(serverOnNetworkMap.get(discoveryUrl).serverOnNetwork);
        serverOnNetworkMap.remove(discoveryUrl);
        return retVal;
    }

    public void run() {
        jmdns.addServiceListener(mdnsListening, new ServiceListener() {

            @Override
            public void serviceResolved(ServiceEvent event) {
                logger.info("mDNS: found server: " + event.getInfo());
                processRecord(event.getInfo());
            }

            @Override
            public void serviceRemoved(ServiceEvent event) {
                String discoveryUrl = serviceInfoToDiscoveryUrl(event.getInfo());
                if (serverOnNetworkMap.containsKey(discoveryUrl)) {
                    logger.info("mDNS: remove server (TTL=0): " + discoveryUrl);
                    removeFromServerOnNetwork(discoveryUrl);
                }

            }

            @Override
            public void serviceAdded(ServiceEvent event) {
                event.getDNS().getServiceInfo(event.getType(), event.getName());
            }
        });

        logger.info("mDNS registration done");
        // Wait until the largest possible integer value
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            // do nothing
        }
    }


    private class ServerOnNetworkMdns {
        ServerOnNetwork serverOnNetwork;
        final Date created;
        Date lastSeen;

        ServerOnNetworkMdns() {
            created = new Date();
        }
    }

    public int getCurrentServerOnNetworkId() {
        return lastServerOnNetworkId;
    }

    public Date getLastServerOnNetworkIdReset() {
        return lastServerOnNetworkIdReset;
    }


    public List<ServerOnNetwork> getServerOnNetwork() {
        return serverOnNetworkList;
    }

}
