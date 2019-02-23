/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.server.transport.http;

import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.util.Objects;
import java.util.Optional;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.channel.ServerSecureChannel;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaBinaryStreamDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaBinaryStreamEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.transport.TransportProfile;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.ResponseHeader;
import org.eclipse.milo.opcua.stack.core.types.structured.ServiceFault;
import org.eclipse.milo.opcua.stack.core.util.BufferUtil;
import org.eclipse.milo.opcua.stack.core.util.DigestUtil;
import org.eclipse.milo.opcua.stack.core.util.EndpointUtil;
import org.eclipse.milo.opcua.stack.server.UaStackServer;
import org.eclipse.milo.opcua.stack.server.services.ServiceRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OpcServerHttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private static final String UABINARY_CONTENT_TYPE =
        HttpHeaderValues.APPLICATION_OCTET_STREAM.toString();

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final UaStackServer stackServer;

    OpcServerHttpRequestHandler(UaStackServer stackServer) {
        this.stackServer = stackServer;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest httpRequest) throws Exception {
        String host = httpRequest.headers().get(HttpHeaderNames.HOST);
        String uri = httpRequest.uri();
        String contentType = httpRequest.headers().get(HttpHeaderNames.CONTENT_TYPE);
        String securityPolicyUri = httpRequest.headers().get("OPCUA-SecurityPolicy");

        logger.debug("host={} uri={} contentType={} securityPolicy={}", host, uri, contentType, securityPolicyUri);

        SecurityPolicy securityPolicy = securityPolicyUri != null ?
            SecurityPolicy.fromUri(securityPolicyUri) :
            SecurityPolicy.None;

        MessageSecurityMode securityMode = securityPolicy == SecurityPolicy.None ?
            MessageSecurityMode.None :
            MessageSecurityMode.Sign;

        EndpointDescription endpoint = stackServer.getEndpointDescriptions()
            .stream()
            .filter(e -> {
                // TODO use contentType to determine which TransportProfile to match
                boolean transportMatch = Objects.equals(
                    e.getTransportProfileUri(),
                    TransportProfile.HTTPS_UABINARY.getUri()
                );

                boolean pathMatch = Objects.equals(
                    EndpointUtil.getPath(e.getEndpointUrl()),
                    uri
                );

                boolean securityPolicyMatch = Objects.equals(
                    e.getSecurityPolicyUri(),
                    securityPolicy.getUri()
                );

                boolean securityModeMatch = Objects.equals(
                    e.getSecurityMode(),
                    securityMode
                );

                return transportMatch && pathMatch && securityPolicyMatch && securityModeMatch;
            })
            .findFirst()
            .orElseThrow(() ->
                new UaException(
                    StatusCodes.Bad_TcpEndpointUrlInvalid,
                    "unrecognized endpoint uri: " + uri));


        ServerSecureChannel secureChannel = new ServerSecureChannel();
        secureChannel.setChannelId(0L); // TODO shared id per endpoint URL / path?
        secureChannel.setSecurityPolicy(securityPolicy);
        secureChannel.setMessageSecurityMode(securityMode);

        ByteString thumbprint = ByteString.of(DigestUtil.sha1(endpoint.getServerCertificate().bytesOrEmpty()));

        Optional<X509Certificate[]> certificateChain = stackServer.getConfig()
            .getCertificateManager()
            .getCertificateChain(thumbprint);

        Optional<KeyPair> keyPair = stackServer.getConfig()
            .getCertificateManager()
            .getKeyPair(thumbprint);

        certificateChain.ifPresent(chain -> {
            secureChannel.setLocalCertificateChain(chain);
            secureChannel.setLocalCertificate(chain[0]);
        });

        keyPair.ifPresent(secureChannel::setKeyPair);

        ByteBuf content = httpRequest.content();
        OpcUaBinaryStreamDecoder decoder = new OpcUaBinaryStreamDecoder(content);

        try {
            UaRequestMessage request = (UaRequestMessage) decoder.readMessage(null);
            UInteger requestHandle = request.getRequestHeader().getRequestHandle();

            ServiceRequest serviceRequest = new ServiceRequest(
                stackServer,
                request,
                endpoint,
                secureChannel.getChannelId(),
                null
            );

            serviceRequest.getFuture().whenComplete((response, fault) -> {
                if (response != null) {
                    sendServiceResponse(ctx, request, response);
                } else {
                    sendServiceFault(ctx, requestHandle, fault);
                }
            });

            stackServer.onServiceRequest(uri, serviceRequest);
        } catch (Throwable t) {
            logger.error("Error decoding UaRequestMessage", t);

            sendServiceFault(ctx, null, t);
        }
    }

    private void sendServiceResponse(
        ChannelHandlerContext ctx,
        UaRequestMessage request,
        UaResponseMessage response) {

        ByteBuf contentBuffer = BufferUtil.pooledBuffer();

        // TODO switch on transport profile for binary vs xml encoding
        OpcUaBinaryStreamEncoder binaryEncoder = new OpcUaBinaryStreamEncoder();
        binaryEncoder.setBuffer(contentBuffer);
        binaryEncoder.writeMessage(null, response);

        FullHttpResponse httpResponse = new DefaultFullHttpResponse(
            HttpVersion.HTTP_1_1,
            HttpResponseStatus.OK,
            contentBuffer
        );

        httpResponse.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        httpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, UABINARY_CONTENT_TYPE);
        httpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, contentBuffer.readableBytes());

        ctx.writeAndFlush(httpResponse);
    }

    private void sendServiceFault(
        ChannelHandlerContext ctx,
        UInteger requestHandle,
        Throwable fault) {

        StatusCode statusCode = UaException.extract(fault)
            .map(UaException::getStatusCode)
            .orElse(StatusCode.BAD);

        ServiceFault serviceFault = new ServiceFault(
            new ResponseHeader(
                DateTime.now(),
                requestHandle,
                statusCode,
                null,
                null,
                null
            )
        );

        ByteBuf contentBuffer = BufferUtil.pooledBuffer();

        // TODO switch on transport profile for binary vs xml encoding
        OpcUaBinaryStreamEncoder binaryEncoder = new OpcUaBinaryStreamEncoder();
        binaryEncoder.setBuffer(contentBuffer);
        binaryEncoder.writeMessage(null, serviceFault);

        FullHttpResponse httpResponse = new DefaultFullHttpResponse(
            HttpVersion.HTTP_1_1,
            HttpResponseStatus.OK,
            contentBuffer
        );

        httpResponse.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        httpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, UABINARY_CONTENT_TYPE);
        httpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, contentBuffer.readableBytes());

        ctx.writeAndFlush(httpResponse);
    }

}
