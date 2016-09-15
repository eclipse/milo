package org.eclipse.milo.examples.client;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.BrowseDirection;
import org.eclipse.milo.opcua.stack.core.types.enumerated.BrowseResultMask;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseResult;
import org.eclipse.milo.opcua.stack.core.types.structured.ReferenceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BrowseExample implements ClientExample {

	public static void main(String[] args) throws Exception
	{
		BrowseExample example = new BrowseExample();

		new ClientExampleRunner(example).run();
	}

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void run(OpcUaClient client, CompletableFuture<OpcUaClient> future) throws Exception
	{
		// synchronous connect
		client.connect().get();

		// start browsing at root folder
		browseNode("", client, Identifiers.RootFolder);

		future.complete(client);
	}

	private void browseNode(String indent, OpcUaClient client, NodeId container) throws Exception
	{
		BrowseDescription browse = new BrowseDescription(container, BrowseDirection.Forward, Identifiers.References,
				true, uint(NodeClass.Object.getValue() | NodeClass.Variable.getValue()),
				uint(BrowseResultMask.All.getValue()));

		BrowseResult res = client.browse(browse).get();

		for (ReferenceDescription rd : res.getReferences()) {

			logger.info("{} Node={}", indent, rd.getBrowseName().getName());

			// recursively browse to children
			browseNode(indent + "  ", client, rd.getNodeId().local().get());
		}
	}
}
