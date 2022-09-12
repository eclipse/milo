package org.eclipse.milo.opcua.sdk.client;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.session.SessionFsm;
import org.eclipse.milo.opcua.sdk.core.types.DataType;
import org.eclipse.milo.opcua.sdk.core.types.DataTypeTree;
import org.eclipse.milo.opcua.sdk.core.types.DynamicStructCodec;
import org.eclipse.milo.opcua.stack.client.UaStackClient;
import org.eclipse.milo.opcua.stack.core.NodeIds;
import org.eclipse.milo.opcua.stack.core.types.structured.DataTypeDefinition;
import org.eclipse.milo.opcua.stack.core.types.structured.StructureDefinition;
import org.eclipse.milo.opcua.stack.core.util.Tree;
import org.eclipse.milo.opcua.stack.core.util.Unit;
import org.slf4j.LoggerFactory;

public class DataTypeCodecSessionInitializer implements SessionFsm.SessionInitializer {

    @Override
    public CompletableFuture<Unit> initialize(UaStackClient stackClient, OpcUaSession session) {
        String treeKey = DataTypeTreeSessionInitializer.SESSION_ATTRIBUTE_KEY;

        Object dataTypeTree = session.getAttribute(treeKey);

        if (dataTypeTree instanceof DataTypeTree) {
            registerCodecs(stackClient, (DataTypeTree) dataTypeTree);

            return CompletableFuture.completedFuture(Unit.VALUE);
        } else {
            return DataTypeTreeBuilder.buildAsync(stackClient, session)
                .thenAccept(tree -> {
                    session.setAttribute(treeKey, tree);

                    registerCodecs(stackClient, tree);
                })
                .thenApply(v -> Unit.VALUE);
        }
    }

    private static void registerCodecs(UaStackClient stackClient, DataTypeTree dataTypeTree) {
        Tree<DataType> structureNode = dataTypeTree.getTreeNode(NodeIds.Structure);

        if (structureNode != null) {
            structureNode.traverse(dataType -> {
                DataTypeDefinition definition = dataType.getDataTypeDefinition();

                if (definition instanceof StructureDefinition) {
                    stackClient.getDynamicDataTypeManager().registerType(
                        dataType.getNodeId(),
                        new DynamicStructCodec(dataTypeTree, dataType),
                        dataType.getBinaryEncodingId(),
                        dataType.getXmlEncodingId(),
                        dataType.getJsonEncodingId()
                    );
                }
            });
        } else {
            LoggerFactory.getLogger(DataTypeTreeSessionInitializer.class)
                .warn("Tree for NodeIds.Structure not found; is the server DataType hierarchy sane?");
        }
    }

}
