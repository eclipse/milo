/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack;

import java.security.Security;
import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.util.ReferenceCountUtil;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.eclipse.milo.opcua.stack.client.transport.uasc.ClientSecureChannel;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.channel.MessageLimits;
import org.eclipse.milo.opcua.stack.core.channel.ChannelParameters;
import org.eclipse.milo.opcua.stack.core.channel.ChunkDecoder;
import org.eclipse.milo.opcua.stack.core.channel.ChunkEncoder;
import org.eclipse.milo.opcua.stack.core.channel.MessageAbortedException;
import org.eclipse.milo.opcua.stack.core.channel.SecureChannel;
import org.eclipse.milo.opcua.stack.core.channel.ServerSecureChannel;
import org.eclipse.milo.opcua.stack.core.channel.messages.MessageType;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.serialization.EncodingLimits;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.util.BufferUtil;
import org.eclipse.milo.opcua.stack.core.util.LongSequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.eclipse.milo.opcua.stack.core.channel.MessageLimits.DEFAULT_MAX_CHUNK_SIZE;
import static org.eclipse.milo.opcua.stack.core.channel.MessageLimits.DEFAULT_MAX_MESSAGE_SIZE;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class ChunkSerializationTest extends SecureChannelFixture {

    static {
        // Required for SecurityPolicy.Aes256_Sha256_RsaPss
        Security.addProvider(new BouncyCastleProvider());
    }

    private Logger logger = LoggerFactory.getLogger(getClass());

    private ChannelParameters smallParameters = new ChannelParameters(
        32 * 8196,
        8196,
        8196,
        64,
        32 * 8196,
        8196,
        8196,
        64
    );

    private ChannelParameters defaultParameters = new ChannelParameters(
        DEFAULT_MAX_MESSAGE_SIZE,
        DEFAULT_MAX_CHUNK_SIZE,
        DEFAULT_MAX_CHUNK_SIZE,
        0,
        DEFAULT_MAX_MESSAGE_SIZE,
        DEFAULT_MAX_CHUNK_SIZE,
        DEFAULT_MAX_CHUNK_SIZE,
        0
    );

    private ChannelParameters unlimitedChunkCountParameters = new ChannelParameters(
        MessageLimits.DEFAULT_MAX_MESSAGE_SIZE,
        MessageLimits.DEFAULT_MAX_CHUNK_SIZE,
        MessageLimits.DEFAULT_MAX_CHUNK_SIZE,
        0,
        MessageLimits.DEFAULT_MAX_MESSAGE_SIZE,
        MessageLimits.DEFAULT_MAX_CHUNK_SIZE,
        MessageLimits.DEFAULT_MAX_CHUNK_SIZE,
        0
    );

    private ChannelParameters unlimitedMessageSizeParameters = new ChannelParameters(
        0,
        MessageLimits.DEFAULT_MAX_CHUNK_SIZE,
        MessageLimits.DEFAULT_MAX_CHUNK_SIZE,
        0,
        0,
        MessageLimits.DEFAULT_MAX_CHUNK_SIZE,
        MessageLimits.DEFAULT_MAX_CHUNK_SIZE,
        0
    );


    @DataProvider
    public Object[][] getAsymmetricSecurityParameters() {
        return new Object[][]{
            {SecurityPolicy.None, MessageSecurityMode.None, 128},
            {SecurityPolicy.Basic128Rsa15, MessageSecurityMode.Sign, 128},
            {SecurityPolicy.Basic128Rsa15, MessageSecurityMode.SignAndEncrypt, 128},
            {SecurityPolicy.Basic256, MessageSecurityMode.Sign, 128},
            {SecurityPolicy.Basic256, MessageSecurityMode.SignAndEncrypt, 128},
            {SecurityPolicy.Basic256Sha256, MessageSecurityMode.Sign, 128},
            {SecurityPolicy.Basic256Sha256, MessageSecurityMode.SignAndEncrypt, 128},
            {SecurityPolicy.Aes128_Sha256_RsaOaep, MessageSecurityMode.Sign, 128},
            {SecurityPolicy.Aes128_Sha256_RsaOaep, MessageSecurityMode.SignAndEncrypt, 128},
            {SecurityPolicy.Aes256_Sha256_RsaPss, MessageSecurityMode.Sign, 128},
            {SecurityPolicy.Aes256_Sha256_RsaPss, MessageSecurityMode.SignAndEncrypt, 128},

            {SecurityPolicy.None, MessageSecurityMode.None, DEFAULT_MAX_CHUNK_SIZE},
            {SecurityPolicy.Basic128Rsa15, MessageSecurityMode.Sign, DEFAULT_MAX_CHUNK_SIZE},
            {SecurityPolicy.Basic128Rsa15, MessageSecurityMode.SignAndEncrypt, DEFAULT_MAX_CHUNK_SIZE},
            {SecurityPolicy.Basic256, MessageSecurityMode.Sign, DEFAULT_MAX_CHUNK_SIZE},
            {SecurityPolicy.Basic256, MessageSecurityMode.SignAndEncrypt, DEFAULT_MAX_CHUNK_SIZE},
            {SecurityPolicy.Basic256Sha256, MessageSecurityMode.Sign, DEFAULT_MAX_CHUNK_SIZE},
            {SecurityPolicy.Basic256Sha256, MessageSecurityMode.SignAndEncrypt, DEFAULT_MAX_CHUNK_SIZE},
            {SecurityPolicy.Aes128_Sha256_RsaOaep, MessageSecurityMode.Sign, DEFAULT_MAX_CHUNK_SIZE},
            {SecurityPolicy.Aes128_Sha256_RsaOaep, MessageSecurityMode.SignAndEncrypt, DEFAULT_MAX_CHUNK_SIZE},
            {SecurityPolicy.Aes256_Sha256_RsaPss, MessageSecurityMode.Sign, DEFAULT_MAX_CHUNK_SIZE},
            {SecurityPolicy.Aes256_Sha256_RsaPss, MessageSecurityMode.SignAndEncrypt, DEFAULT_MAX_CHUNK_SIZE},
        };
    }

    @Test(dataProvider = "getAsymmetricSecurityParameters")
    public void testAsymmetricMessage(SecurityPolicy securityPolicy,
                                      MessageSecurityMode messageSecurity,
                                      int messageSize) throws Exception {

        logger.info("Asymmetric chunk serialization, securityPolicy={}, messageSecurityMode={}, messageSize={}",
            securityPolicy, messageSecurity, messageSize);

        ChannelParameters[] channelParameters = {
            smallParameters,
            defaultParameters,
            unlimitedChunkCountParameters,
            unlimitedMessageSizeParameters
        };

        for (ChannelParameters parameters : channelParameters) {
            ChunkEncoder encoder = new ChunkEncoder(parameters);

            ChunkDecoder decoder = new ChunkDecoder(
                parameters,
                EncodingLimits.DEFAULT_MAX_ARRAY_LENGTH,
                EncodingLimits.DEFAULT_MAX_STRING_LENGTH
            );

            SecureChannel[] channels = generateChannels(securityPolicy, messageSecurity);
            ClientSecureChannel clientChannel = (ClientSecureChannel) channels[0];
            ServerSecureChannel serverChannel = (ServerSecureChannel) channels[1];

            clientChannel
                .attr(ClientSecureChannel.KEY_REQUEST_ID_SEQUENCE)
                .setIfAbsent(new LongSequence(1L, UInteger.MAX_VALUE));

            LongSequence requestId = clientChannel
                .attr(ClientSecureChannel.KEY_REQUEST_ID_SEQUENCE).get();

            byte[] messageBytes = new byte[messageSize];
            for (int i = 0; i < messageBytes.length; i++) {
                messageBytes[i] = (byte) i;
            }

            ByteBuf messageBuffer = BufferUtil.pooledBuffer().writeBytes(messageBytes);

            List<ByteBuf> chunkBuffers = new ArrayList<>();

            encoder.encodeAsymmetric(
                clientChannel,
                requestId.getAndIncrement(),
                messageBuffer,
                MessageType.OpenSecureChannel,
                new ChunkEncoder.Callback() {
                    @Override
                    public void onEncodingError(UaException ex) {
                        fail("onEncodingError", ex);
                    }

                    @Override
                    public void onMessageEncoded(List<ByteBuf> messageChunks, long requestId) {
                        chunkBuffers.addAll(messageChunks);
                    }
                }
            );

            decoder.decodeAsymmetric(serverChannel, chunkBuffers, new ChunkDecoder.Callback() {
                @Override
                public void onDecodingError(UaException ex) {
                    fail("onDecodingError", ex);
                }

                @Override
                public void onMessageAborted(MessageAbortedException ex) {
                    fail("onMessageAborted", ex);
                }

                @Override
                public void onMessageDecoded(ByteBuf message, long requestId) {
                    messageBuffer.readerIndex(0);
                    assertEquals(message, messageBuffer);

                    ReferenceCountUtil.release(message);
                    ReferenceCountUtil.release(messageBuffer);
                }
            });
        }
    }

    @DataProvider
    public Object[][] getSymmetricSecurityParameters() {
        return new Object[][]{
            {SecurityPolicy.None, MessageSecurityMode.None},
            {SecurityPolicy.Basic128Rsa15, MessageSecurityMode.Sign},
            {SecurityPolicy.Basic128Rsa15, MessageSecurityMode.SignAndEncrypt},
            {SecurityPolicy.Basic256, MessageSecurityMode.Sign},
            {SecurityPolicy.Basic256, MessageSecurityMode.SignAndEncrypt},
            {SecurityPolicy.Basic256Sha256, MessageSecurityMode.Sign},
            {SecurityPolicy.Basic256Sha256, MessageSecurityMode.SignAndEncrypt},
            {SecurityPolicy.Aes128_Sha256_RsaOaep, MessageSecurityMode.Sign},
            {SecurityPolicy.Aes128_Sha256_RsaOaep, MessageSecurityMode.SignAndEncrypt},
            {SecurityPolicy.Aes256_Sha256_RsaPss, MessageSecurityMode.Sign},
            {SecurityPolicy.Aes256_Sha256_RsaPss, MessageSecurityMode.SignAndEncrypt}
        };
    }

    @Test(dataProvider = "getSymmetricSecurityParameters")
    public void testSymmetricMessage(SecurityPolicy securityPolicy,
                                     MessageSecurityMode messageSecurity) throws Exception {

        logger.info(
            "Symmetric chunk serialization, " +
                "securityPolicy={}, messageSecurityMode={}",
            securityPolicy, messageSecurity);


        ChannelParameters[] channelParameters = {
            smallParameters,
            defaultParameters,
            unlimitedChunkCountParameters,
            unlimitedMessageSizeParameters
        };

        for (ChannelParameters parameters : channelParameters) {
            int[] messageSizes = new int[]{
                128,
                parameters.getRemoteMaxMessageSize()
            };

            for (int messageSize : messageSizes) {
                ChunkEncoder encoder = new ChunkEncoder(parameters);

                ChunkDecoder decoder = new ChunkDecoder(
                    parameters,
                    EncodingLimits.DEFAULT_MAX_ARRAY_LENGTH,
                    EncodingLimits.DEFAULT_MAX_STRING_LENGTH
                );

                SecureChannel[] channels = generateChannels(securityPolicy, messageSecurity);
                ClientSecureChannel clientChannel = (ClientSecureChannel) channels[0];
                ServerSecureChannel serverChannel = (ServerSecureChannel) channels[1];

                clientChannel
                    .attr(ClientSecureChannel.KEY_REQUEST_ID_SEQUENCE)
                    .setIfAbsent(new LongSequence(1L, UInteger.MAX_VALUE));

                LongSequence requestId = clientChannel
                    .attr(ClientSecureChannel.KEY_REQUEST_ID_SEQUENCE).get();

                byte[] messageBytes = new byte[messageSize];
                for (int i = 0; i < messageBytes.length; i++) {
                    messageBytes[i] = (byte) i;
                }

                ByteBuf messageBuffer = BufferUtil.pooledBuffer().writeBytes(messageBytes);

                List<ByteBuf> chunkBuffers = new ArrayList<>();

                encoder.encodeSymmetric(
                    clientChannel,
                    requestId.getAndIncrement(),
                    messageBuffer,
                    MessageType.SecureMessage,
                    new ChunkEncoder.Callback() {
                        @Override
                        public void onEncodingError(UaException ex) {
                            fail("onEncodingError", ex);
                        }

                        @Override
                        public void onMessageEncoded(List<ByteBuf> messageChunks, long requestId) {
                            chunkBuffers.addAll(messageChunks);
                        }
                    }
                );

                decoder.decodeSymmetric(serverChannel, chunkBuffers, new ChunkDecoder.Callback() {
                        @Override
                        public void onDecodingError(UaException ex) {
                            fail("onDecodingError", ex);
                        }

                        @Override
                        public void onMessageAborted(MessageAbortedException ex) {
                            fail("onMessageAborted", ex);
                        }

                        @Override
                        public void onMessageDecoded(ByteBuf message, long requestId) {
                            messageBuffer.readerIndex(0);
                            assertEquals(message, messageBuffer);

                            ReferenceCountUtil.release(messageBuffer);
                            ReferenceCountUtil.release(message);
                        }
                    }
                );
            }
        }
    }

}
