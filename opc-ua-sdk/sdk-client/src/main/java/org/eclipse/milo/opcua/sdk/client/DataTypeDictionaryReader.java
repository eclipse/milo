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

package org.eclipse.milo.opcua.sdk.client;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.xml.bind.JAXBException;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import org.eclipse.milo.opcua.binaryschema.parser.BsdParser;
import org.eclipse.milo.opcua.binaryschema.parser.CodecDescription;
import org.eclipse.milo.opcua.binaryschema.parser.DictionaryDescription;
import org.eclipse.milo.opcua.sdk.client.model.types.variables.DataTypeDictionaryType;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.types.DataTypeDictionary;
import org.eclipse.milo.opcua.stack.core.types.OpcUaBinaryDataTypeDictionary;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.BrowseDirection;
import org.eclipse.milo.opcua.stack.core.types.enumerated.BrowseResultMask;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseResult;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.ReferenceDescription;
import org.eclipse.milo.opcua.stack.core.util.FutureUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.concurrent.CompletableFuture.completedFuture;
import static org.eclipse.milo.opcua.sdk.core.util.StreamUtil.opt2stream;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.s;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.toList;
import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.failedFuture;

public class DataTypeDictionaryReader {

    private static final int DEFAULT_FRAGMENT_SIZE = 8192;
    private static final int PARTITION_SIZE = 64;
    private static final QualifiedName QN_DEFAULT_BINARY =
        new QualifiedName(0, "Default Binary");

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final OpcUaClient client;
    private final BsdParser bsdParser;

    public DataTypeDictionaryReader(OpcUaClient client, BsdParser bsdParser) {
        this.client = client;
        this.bsdParser = bsdParser;
    }

    public CompletableFuture<List<DataTypeDictionary<?>>> readDataTypeDictionaries() {
        CompletableFuture<BrowseResult> browse = client.browse(new BrowseDescription(
            Identifiers.OPCBinarySchema_TypeSystem,
            BrowseDirection.Forward,
            Identifiers.HasComponent,
            false,
            uint(NodeClass.Variable.getValue()),
            uint(BrowseResultMask.All.getValue())
        ));

        CompletableFuture<Stream<NodeId>> dictionaryNodeIds = browse.thenApply(
            browseResult ->
                toList(browseResult.getReferences()).stream()
                    .filter(r -> r.getNodeId().getNamespaceIndex().intValue() != 0)
                    .filter(r -> r.getTypeDefinition().equals(Identifiers.DataTypeDictionaryType.expanded()))
                    .flatMap(r -> opt2stream(r.getNodeId().local()))
        );

        return dictionaryNodeIds
            .thenApply(nodeIds ->
                nodeIds
                    .map(this::readDataTypeDictionary)
                    .collect(Collectors.toList()))
            .thenCompose(FutureUtils::sequence)
            .thenApply(list ->
                list.stream()
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList()));
    }

    private CompletableFuture<DataTypeDictionary<?>> readDataTypeDictionary(NodeId nodeId) {
        return readDataTypeDictionaryBytes(nodeId)
            .thenCompose(this::createDataTypeDictionary)
            .exceptionally(ex -> null);
    }

    private CompletableFuture<ByteString> readDataTypeDictionaryBytes(NodeId nodeId) {
        CompositeByteBuf fragmentBuffer = Unpooled.compositeBuffer();

        CompletableFuture<ByteBuf> future = readFragments(
            nodeId,
            fragmentBuffer,
            DEFAULT_FRAGMENT_SIZE,
            0
        );

        return future.thenApply(buffer -> {
            byte[] bs = new byte[buffer.readableBytes()];
            buffer.readBytes(bs);
            return ByteString.of(bs);
        });
    }

    private CompletableFuture<ByteBuf> readFragments(
        NodeId nodeId, CompositeByteBuf fragmentBuffer, int fragmentSize, int index) {

        Preconditions.checkArgument(fragmentSize > 0, "fragmentSize > 0");

        String indexRange = fragmentSize <= 1 ?
            String.valueOf(index) :
            String.format("%d:%d", index, index + fragmentSize - 1);

        CompletableFuture<DataValue> valueFuture = client.read(
            0.0,
            TimestampsToReturn.Neither,
            newArrayList(new ReadValueId(
                nodeId,
                AttributeId.Value.uid(),
                indexRange,
                QualifiedName.NULL_VALUE
            ))
        ).thenApply(r -> l(r.getResults()).get(0));

        return valueFuture.thenCompose(value -> {
            StatusCode statusCode = value.getStatusCode();

            if (statusCode.isGood()) {
                ByteString fragmentBytes = (ByteString) value.getValue().getValue();

                if (fragmentBytes != null) {
                    int bytesRead = fragmentBytes.length();

                    if (bytesRead > 0) {
                        fragmentBuffer.addComponent(Unpooled.wrappedBuffer(fragmentBytes.bytesOrEmpty()));
                        fragmentBuffer.writerIndex(fragmentBuffer.writerIndex() + bytesRead);
                    }

                    if (bytesRead < fragmentSize) {
                        // a partial fragment means this is the last read that will
                        // succeed; don't bother trying to read the next fragment.
                        return completedFuture(fragmentBuffer);
                    } else {
                        return readFragments(nodeId, fragmentBuffer, fragmentSize, index + bytesRead);
                    }
                } else {
                    logger.warn("Read a null type dictionary " +
                        "fragment at indexRange=\"%s\"", indexRange);

                    return completedFuture(fragmentBuffer);
                }
            } else {
                if (statusCode.getValue() != StatusCodes.Bad_IndexRangeNoData) {
                    logger.warn(
                        "Reading type dictionary fragments expected to " +
                            "terminate with Bad_IndexRangeNoData but got {}", statusCode);
                }

                return completedFuture(fragmentBuffer);
            }
        });
    }

    private CompletableFuture<DataTypeDictionary<?>> createDataTypeDictionary(ByteString bs) {
        ByteArrayInputStream is = new ByteArrayInputStream(bs.bytesOrEmpty());

        try {
            DictionaryDescription dictionaryDescription = bsdParser.parse(is);

            String namespaceUri = dictionaryDescription.getNamespaceUri();
            OpcUaBinaryDataTypeDictionary dictionary = new OpcUaBinaryDataTypeDictionary(namespaceUri);

            dictionaryDescription.getEnumCodecs()
                .forEach(cd -> dictionary.registerEnumCodec(cd.getCodec(), cd.getDescription()));

            List<CodecDescription> structCodecs = dictionaryDescription.getStructCodecs();

            CompletableFuture<DataTypeDictionaryType> dictionaryNode = browseDictionaryNode(namespaceUri);

            CompletableFuture<List<NodeId>> descriptionNodeIds =
                dictionaryNode.thenCompose(this::browseDataTypeDescriptionNodeIds);

            CompletableFuture<List<String>> descriptionValues =
                descriptionNodeIds.thenCompose(this::readDataTypeDescriptionValues);

            CompletableFuture<List<NodeId>> encodingIds =
                descriptionNodeIds.thenCompose(this::browseDataTypeEncodingNodeIds);

            return descriptionValues.thenCombine(encodingIds, (descriptions, nodeIds) -> {
                Map<String, NodeId> descriptionMap = new HashMap<>();

                Iterator<String> di = descriptions.iterator();
                Iterator<NodeId> ni = nodeIds.iterator();

                while (di.hasNext() && ni.hasNext()) {
                    descriptionMap.put(di.next(), ni.next());
                }

                structCodecs.forEach(cd -> {
                    String description = cd.getDescription();
                    NodeId encodingId = descriptionMap.get(description);

                    if (encodingId != null && encodingId.isNotNull()) {
                        dictionary.registerStructCodec(cd.getCodec(), description, encodingId);
                    }
                });

                return dictionary;
            });
        } catch (JAXBException e) {
            return failedFuture(e);
        }
    }

    private CompletableFuture<DataTypeDictionaryType> browseDictionaryNode(final String namespaceUri) {
        CompletableFuture<BrowseResult> browseResult = client.browse(new BrowseDescription(
            Identifiers.OPCBinarySchema_TypeSystem,
            BrowseDirection.Forward,
            Identifiers.HasComponent,
            false,
            uint(NodeClass.Variable.getValue()),
            uint(BrowseResultMask.All.getValue())
        ));

        CompletableFuture<List<DataTypeDictionaryType>> dictionaryNodes = browseResult
            .thenCompose(result -> {
                List<ReferenceDescription> references = l(result.getReferences());

                Stream<CompletableFuture<DataTypeDictionaryType>> futures = references.stream()
                    .filter(r -> r.getTypeDefinition().equals(
                        Identifiers.DataTypeDictionaryType.expanded()))
                    .flatMap(r -> {
                        NodeId nodeId = r.getNodeId().local().orElse(null);
                        if (nodeId != null) {
                            return Stream.of(
                                client.getAddressSpace()
                                    .getVariableNode(nodeId, DataTypeDictionaryType.class)
                                    .exceptionally(ex -> null));
                        } else {
                            return Stream.empty();
                        }
                    });

                return FutureUtils.sequence(futures);
            })
            .thenApply(list -> list.stream().filter(Objects::nonNull).collect(Collectors.toList()));

        CompletableFuture<List<String>> namespaceUris = dictionaryNodes.thenCompose(nodes -> {
            Stream<CompletableFuture<String>> futures = nodes.stream()
                .map(DataTypeDictionaryType::getNamespaceUri);

            return FutureUtils.sequence(futures);
        });

        return dictionaryNodes.thenCombine(namespaceUris, (nodes, uris) -> {
            for (int i = 0; i < nodes.size(); i++) {
                String uri = uris.get(i);

                if (namespaceUri.equals(uri)) {
                    return nodes.get(i);
                }
            }

            return null;
        }).thenCompose(node -> {
            if (node != null) {
                return CompletableFuture.completedFuture(node);
            } else {
                return failedFuture(new Exception(String.format(
                    "DataTypeDictionaryNode with namespace URI \"%s\" not found", namespaceUri)));
            }
        });
    }

    private CompletableFuture<List<NodeId>> browseDataTypeDescriptionNodeIds(DataTypeDictionaryType dictionaryNode) {
        CompletableFuture<BrowseResult> browseResult =
            dictionaryNode.getNodeId().thenCompose(nodeId ->
                client.browse(new BrowseDescription(
                    nodeId,
                    BrowseDirection.Forward,
                    Identifiers.HasComponent,
                    false,
                    uint(NodeClass.Variable.getValue()),
                    uint(BrowseResultMask.All.getValue())
                ))
            );

        return browseResult.thenApply(result ->
            s(result.getReferences())
                .filter(r -> Identifiers.DataTypeDescriptionType.expanded().equals(r.getTypeDefinition()))
                .flatMap(r -> opt2stream(r.getNodeId().local()))
                .collect(Collectors.toList())
        );
    }

    private CompletableFuture<List<String>> readDataTypeDescriptionValues(List<NodeId> nodeIds) {
        List<List<NodeId>> partitions = Lists.partition(nodeIds, PARTITION_SIZE);

        CompletableFuture<List<List<DataValue>>> sequence = FutureUtils.sequence(
            partitions.stream()
                .map(nodeId -> client.readValues(
                    0.0, TimestampsToReturn.Neither, nodeId))
        );

        return sequence.thenApply(values ->
            values.stream()
                .flatMap(List::stream)
                .map(v -> (String) v.getValue().getValue())
                .collect(Collectors.toList()));
    }

    private CompletableFuture<List<NodeId>> browseDataTypeEncodingNodeIds(List<NodeId> nodeIds) {
        Stream<CompletableFuture<NodeId>> futures = nodeIds.stream().map(nodeId -> {
            CompletableFuture<BrowseResult> browse = client.browse(new BrowseDescription(
                nodeId,
                BrowseDirection.Inverse,
                Identifiers.HasDescription,
                false,
                uint(NodeClass.Object.getValue()),
                uint(BrowseResultMask.All.getValue())
            ));

            return browse.thenApply(result -> {
                Optional<ReferenceDescription> ref = s(result.getReferences())
                    .filter(r -> QN_DEFAULT_BINARY.equals(r.getBrowseName()) &&
                        Identifiers.DataTypeEncodingType.expanded().equals(r.getTypeDefinition()))
                    .findFirst();

                return ref.map(r -> r.getNodeId().local().orElse(NodeId.NULL_VALUE)).orElse(NodeId.NULL_VALUE);
            });
        });

        return FutureUtils.sequence(futures);
    }

}
