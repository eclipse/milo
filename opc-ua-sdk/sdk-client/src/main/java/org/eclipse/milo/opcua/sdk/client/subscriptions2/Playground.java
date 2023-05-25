package org.eclipse.milo.opcua.sdk.client.subscriptions2;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.stack.core.UaException;

public class Playground {

    public static void main(String[] args) {
        var client = new OpcUaClient(null, null);

        var subscription = new OpcUaSubscription(client);
        try {
            subscription.create();
        } catch (UaException e) {
            throw new RuntimeException(e);
        }

        subscription.setPublishingInterval(250.0);
        try {
            subscription.modify();
        } catch (UaException e) {
            throw new RuntimeException(e);
        }

        var monitoredItem = new OpcUaMonitoredItem(subscription);
        monitoredItem.addDataValueListener((item, value) -> System.out.println("Received value: " + value));

        try {
            monitoredItem.create();
        } catch (UaException e) {
            throw new RuntimeException(e);
        }
    }

}
