/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.xml.bind.JAXBException;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import org.eclipse.milo.opcua.binaryschema.parser.BsdParser;
import org.eclipse.milo.opcua.binaryschema.parser.CodecDescription;
import org.eclipse.milo.opcua.binaryschema.parser.DictionaryDescription;
import org.eclipse.milo.opcua.stack.client.UaStackClient;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.types.DataTypeDictionary;
import org.eclipse.milo.opcua.stack.core.types.OpcUaBinaryDataTypeDictionary;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.BrowseDirection;
import org.eclipse.milo.opcua.stack.core.types.enumerated.BrowseResultMask;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseNextRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseNextResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseResult;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.ReferenceDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.RequestHeader;
import org.eclipse.milo.opcua.stack.core.types.structured.ViewDescription;
import org.eclipse.milo.opcua.stack.core.util.FutureUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.concurrent.CompletableFuture.completedFuture;
import static org.eclipse.milo.opcua.sdk.core.util.StreamUtil.opt2stream;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;
import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.failedFuture;

public class DataTypeDictionaryReader {

    private static final int DEFAULT_FRAGMENT_SIZE = 8192;
    private static final int PARTITION_SIZE = 64;
    private static final QualifiedName QN_DEFAULT_BINARY =
        new QualifiedName(0, "Default Binary");

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final UaStackClient stackClient;
    private final OpcUaSession session;
    private final BsdParser bsdParser;

    public DataTypeDictionaryReader(
        UaStackClient stackClient,
        OpcUaSession session,
        BsdParser bsdParser) {

        this.stackClient = stackClient;
        this.session = session;
        this.bsdParser = bsdParser;
    }

    public CompletableFuture<List<DataTypeDictionary<?>>> readDataTypeDictionaries() {
        CompletableFuture<List<ReferenceDescription>> browseFuture = browseNode(new BrowseDescription(
            Identifiers.OPCBinarySchema_TypeSystem,
            BrowseDirection.Forward,
            Identifiers.HasComponent,
            false,
            uint(NodeClass.Variable.getValue()),
            uint(BrowseResultMask.All.getValue())
        ));

        CompletableFuture<Stream<NodeId>> dictionaryNodeIds = browseFuture.thenApply(
            references ->
                references.stream()
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
        logger.debug("Reading DataTypeDictionary nodeId={}", nodeId);

        return readDataTypeDictionaryBytes(nodeId, DEFAULT_FRAGMENT_SIZE)
            .thenCompose(bs -> createDataTypeDictionary(nodeId, bs))
            .exceptionally(ex -> {
                logger.debug("Failed to create DataTypeDictionary: {}", ex.getMessage(), ex);
                return null;
            });
    }

    CompletableFuture<ByteString> readDataTypeDictionaryBytes(NodeId nodeId, int fragmentSize) {
        CompositeByteBuf fragmentBuffer = Unpooled.compositeBuffer();

        CompletableFuture<ByteBuf> future = readFragments(
            nodeId,
            fragmentBuffer,
            fragmentSize,
            0
        );

        return future.thenApply(buffer -> {
            // trim any junk at the end. some servers have a bug
            // that cause a null byte to be appended to the end,
            // which makes it invalid XML.
            int length = buffer.readableBytes();

            for (int i = buffer.writerIndex() - 1; i >= 0; i--) {
                byte lastByte = buffer.getByte(i);

                boolean empty =
                    (lastByte == 0 ||
                        Character.isWhitespace(lastByte) ||
                        Character.isSpaceChar(lastByte));

                if (!empty) break;
                else length -= 1;
            }

            byte[] bs = new byte[length];
            buffer.readBytes(bs, 0, length);

            if (logger.isDebugEnabled()) {
                String xmlString = new String(bs);
                logger.debug("Dictionary XML: {}", xmlString);
            }

            return ByteString.of(bs);
        });
    }

    private CompletableFuture<ByteBuf> readFragments(
        NodeId nodeId, CompositeByteBuf fragmentBuffer, int fragmentSize, int index) {

        Preconditions.checkArgument(fragmentSize > 0, "fragmentSize=" + fragmentSize);

        String indexRange = fragmentSize <= 1 ?
            String.valueOf(index) :
            String.format("%d:%d", index, index + fragmentSize - 1);

        CompletableFuture<DataValue> valueFuture = readNode(
            new ReadValueId(
                nodeId,
                AttributeId.Value.uid(),
                indexRange,
                QualifiedName.NULL_VALUE
            )
        );

        return valueFuture.thenComposeAsync(value -> {
            StatusCode statusCode = value.getStatusCode();

            if (statusCode == null || statusCode.isGood()) {
                ByteString fragmentBytes = (ByteString) value.getValue().getValue();

                if (fragmentBytes != null) {
                    int bytesRead = fragmentBytes.length();

                    if (bytesRead > 0) {
                        fragmentBuffer.addComponent(Unpooled.wrappedBuffer(fragmentBytes.bytesOrEmpty()));
                        fragmentBuffer.writerIndex(fragmentBuffer.writerIndex() + bytesRead);
                    }

                    if (bytesRead < fragmentSize) {
                        // A partial fragment means this is the last read that will
                        // succeed; don't bother trying to read the next fragment.
                        return completedFuture(fragmentBuffer);
                    } else if (bytesRead > fragmentSize) {
                        // Some servers don't support index range properly and just
                        // return the entire contents. when this happens, we can assume
                        // we've read everything there is to read.
                        // An edge case where the dictionary size is exactly equal to the
                        // fragment size still exists. In this case we must hope the server
                        // properly terminates the subsequent request with something like
                        // Bad_IndexRangeNoData or else the infinite loop could still happen.
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

    private CompletableFuture<DataTypeDictionary<?>> createDataTypeDictionary(NodeId dictionaryNodeId, ByteString bs) {
        ByteArrayInputStream is = new ByteArrayInputStream(bs.bytesOrEmpty());

        try {
            DictionaryDescription dictionaryDescription = bsdParser.parse(is);

            String namespaceUri = dictionaryDescription.getNamespaceUri();
            OpcUaBinaryDataTypeDictionary dictionary = new OpcUaBinaryDataTypeDictionary(namespaceUri);

            dictionaryDescription.getEnumCodecs()
                .forEach(cd -> dictionary.registerEnumCodec(cd.getCodec(), cd.getDescription()));

            List<CodecDescription> structCodecs = dictionaryDescription.getStructCodecs();

            CompletableFuture<List<NodeId>> descriptionNodeIds =
                browseDataTypeDescriptionNodeIds(dictionaryNodeId);

            CompletableFuture<List<String>> descriptionValues =
                descriptionNodeIds.thenCompose(this::readDataTypeDescriptionValues);

            if (logger.isTraceEnabled()) {
                try {
                    List<NodeId> ids = descriptionNodeIds.get();
                    List<String> values = descriptionValues.get();

                    if (ids.size() != values.size()) {
                        throw new IllegalStateException("size mismatch");
                    }

                    for (int i = 0; i < ids.size(); i++) {
                        NodeId id = ids.get(i);
                        String value = values.get(i);

                        logger.trace("description NodeId={} value={}", id, value);
                    }
                } catch (Exception e) {
                    logger.error("Error reading description NodeIds", e);
                }
            }

            CompletableFuture<List<NodeId>> encodingIdsFuture =
                descriptionNodeIds.thenCompose(this::browseDataTypeEncodingNodeIds);

            return encodingIdsFuture.thenCompose(encodingIds ->
                browseDataTypeIds(encodingIds).thenCompose(dataTypeIds ->
                    descriptionValues.thenApply(descriptions -> {
                            Map<String, NodeId> encodingIdMap = new HashMap<>();
                            Map<String, NodeId> dataTypeIdMap = new HashMap<>();

                            Iterator<String> descriptionIter = descriptions.iterator();
                            Iterator<NodeId> encodingIdIter = encodingIds.iterator();
                            Iterator<NodeId> dataTypeIdIter = dataTypeIds.iterator();

                            while (descriptionIter.hasNext() && encodingIdIter.hasNext() && dataTypeIdIter.hasNext()) {
                                String description = descriptionIter.next();
                                encodingIdMap.put(description, encodingIdIter.next());
                                dataTypeIdMap.put(description, dataTypeIdIter.next());
                            }

                            structCodecs.forEach(cd -> {
                                String description = cd.getDescription();
                                NodeId encodingId = encodingIdMap.get(description);
                                NodeId dataTypeId = dataTypeIdMap.get(description);

                                if (encodingId == null || encodingId.isNull()) {
                                    logger.warn("encodingId is null for description={}", description);
                                } else if (dataTypeId == null || dataTypeId.isNull()) {
                                    logger.warn("dataTypeId is null for description={}", description);
                                } else {
                                    dictionary.registerStructCodec(cd.getCodec(), description, dataTypeId, encodingId);

                                    logger.debug(
                                        "Registered codec description={} dataTypeId={} encodingId={}",
                                        description, dataTypeId, encodingId
                                    );
                                }
                            });

                            return dictionary;
                        }
                    )
                )
            );
        } catch (JAXBException e) {
            return failedFuture(e);
        }
    }

    private CompletableFuture<List<NodeId>> browseDataTypeDescriptionNodeIds(NodeId dictionaryNodeId) {
        CompletableFuture<List<ReferenceDescription>> browseResult = browseNode(
            new BrowseDescription(
                dictionaryNodeId,
                BrowseDirection.Forward,
                Identifiers.HasComponent,
                false,
                uint(NodeClass.Variable.getValue()),
                uint(BrowseResultMask.All.getValue())
            )
        );

        return browseResult.thenApply(references ->
            references.stream()
                .filter(r -> Identifiers.DataTypeDescriptionType.expanded().equals(r.getTypeDefinition()))
                .flatMap(r -> opt2stream(r.getNodeId().local()))
                .collect(Collectors.toList())
        );
    }

    private CompletableFuture<List<String>> readDataTypeDescriptionValues(List<NodeId> nodeIds) {
        CompletableFuture<UInteger> maxNodesPerRead = readNode(
            new ReadValueId(
                Identifiers.Server_ServerCapabilities_OperationLimits_MaxNodesPerRead,
                AttributeId.Value.uid(),
                null,
                QualifiedName.NULL_VALUE
            )
        ).thenApply(dv -> (UInteger) dv.getValue().getValue());

        CompletableFuture<Integer> getPartitionSize = maxNodesPerRead
            .thenApply(m -> Math.max(1, Ints.saturatedCast(m.longValue())))
            .exceptionally(ex -> PARTITION_SIZE);

        return getPartitionSize.thenCompose(partitionSize -> {
            List<List<NodeId>> partitions = Lists.partition(nodeIds, partitionSize);

            CompletableFuture<List<List<DataValue>>> sequence = FutureUtils.sequence(
                partitions.stream().map(list -> {
                    List<ReadValueId> readValueIds = list.stream()
                        .map(nodeId ->
                            new ReadValueId(
                                nodeId,
                                AttributeId.Value.uid(),
                                null,
                                QualifiedName.NULL_VALUE))
                        .collect(Collectors.toList());

                    return readNodes(readValueIds);
                })
            );

            return sequence.thenApply(values ->
                values.stream()
                    .flatMap(List::stream)
                    .map(v -> (String) v.getValue().getValue())
                    .collect(Collectors.toList())
            );
        });
    }

    private CompletableFuture<List<NodeId>> browseDataTypeEncodingNodeIds(List<NodeId> descriptionNodeIds) {
        Stream<CompletableFuture<NodeId>> futures = descriptionNodeIds.stream().map(nodeId -> {
            CompletableFuture<List<ReferenceDescription>> browse = browseNode(new BrowseDescription(
                nodeId,
                BrowseDirection.Inverse,
                Identifiers.HasDescription,
                false,
                uint(NodeClass.Object.getValue()),
                uint(BrowseResultMask.All.getValue())
            ));

            return browse.thenApply(references -> {
                Optional<ReferenceDescription> ref = references.stream()
                    .filter(r -> QN_DEFAULT_BINARY.equals(r.getBrowseName()) &&
                        Identifiers.DataTypeEncodingType.expanded().equals(r.getTypeDefinition()))
                    .findFirst();

                return ref.map(r -> r.getNodeId().local().orElse(NodeId.NULL_VALUE)).orElse(NodeId.NULL_VALUE);
            });
        });

        return FutureUtils.sequence(futures);
    }

    private CompletableFuture<List<NodeId>> browseDataTypeIds(List<NodeId> dataTypeEncodingIds) {
        Stream<CompletableFuture<NodeId>> futures = dataTypeEncodingIds.stream().map(nodeId -> {
            CompletableFuture<List<ReferenceDescription>> browse = browseNode(new BrowseDescription(
                nodeId,
                BrowseDirection.Inverse,
                Identifiers.HasEncoding,
                false,
                uint(NodeClass.DataType.getValue()),
                uint(BrowseResultMask.All.getValue())
            ));

            return browse.thenApply(references -> {
                Optional<ReferenceDescription> ref = references.stream().findFirst();

                return ref.map(r -> r.getNodeId().local().orElse(NodeId.NULL_VALUE)).orElse(NodeId.NULL_VALUE);
            });
        });

        return FutureUtils.sequence(futures);
    }

    private CompletableFuture<List<ReferenceDescription>> browseNode(BrowseDescription browseDescription) {
        RequestHeader requestHeader = stackClient.newRequestHeader(
            session.getAuthenticationToken(),
            uint(60000)
        );

        BrowseRequest browseRequest = new BrowseRequest(
            requestHeader,
            new ViewDescription(NodeId.NULL_VALUE, DateTime.MIN_VALUE, uint(0)),
            uint(0),
            new BrowseDescription[]{browseDescription}
        );

        return stackClient.sendRequest(browseRequest)
            .thenApply(BrowseResponse.class::cast)
            .thenApply(r -> Objects.requireNonNull(r.getResults())[0])
            .thenCompose(result -> {
                List<ReferenceDescription> references =
                    Collections.synchronizedList(new ArrayList<>());

                return maybeBrowseNext(result, references);
            });
    }

    private CompletionStage<List<ReferenceDescription>> maybeBrowseNext(
        BrowseResult result,
        List<ReferenceDescription> references) {

        if (result.getStatusCode().isGood()) {
            ReferenceDescription[] rds = result.getReferences();
            if (rds != null) Collections.addAll(references, rds);

            ByteString continuationPoint = result.getContinuationPoint();

            if (continuationPoint.isNotNull()) {
                logger.debug("Continuation point was non-null; calling BrowseNext");

                return browseNextAsync(continuationPoint, references);
            } else {
                logger.debug("Browse finished with {} references", references.size());

                return CompletableFuture.completedFuture(references);
            }
        } else {
            return CompletableFuture.completedFuture(references);
        }
    }

    private CompletableFuture<List<ReferenceDescription>> browseNextAsync(
        ByteString continuationPoint,
        List<ReferenceDescription> references) {

        RequestHeader requestHeader = stackClient.newRequestHeader(
            session.getAuthenticationToken(),
            uint(60000)
        );

        BrowseNextRequest request = new BrowseNextRequest(
            requestHeader,
            false,
            new ByteString[]{continuationPoint}
        );

        return stackClient.sendRequest(request)
            .thenApply(BrowseNextResponse.class::cast)
            .thenCompose(response -> {
                BrowseResult result = l(response.getResults()).get(0);

                return maybeBrowseNext(result, references);
            });
    }

    private CompletableFuture<DataValue> readNode(ReadValueId readValueId) {
        return readNodes(newArrayList(readValueId)).thenApply(values -> values.get(0));
    }

    private CompletableFuture<List<DataValue>> readNodes(List<ReadValueId> readValueIds) {
        RequestHeader requestHeader = stackClient.newRequestHeader(
            session.getAuthenticationToken(),
            uint(60000)
        );

        ReadRequest readRequest = new ReadRequest(
            requestHeader,
            0.0,
            TimestampsToReturn.Neither,
            readValueIds.toArray(new ReadValueId[0])
        );

        return stackClient.sendRequest(readRequest)
            .thenApply(ReadResponse.class::cast)
            .thenApply(r -> l(r.getResults()));
    }

}
