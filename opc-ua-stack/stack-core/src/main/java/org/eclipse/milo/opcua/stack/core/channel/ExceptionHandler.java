/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.channel;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.UaRuntimeException;
import org.eclipse.milo.opcua.stack.core.channel.messages.ErrorMessage;
import org.eclipse.milo.opcua.stack.core.channel.messages.TcpMessageEncoder;

public class ExceptionHandler {

    public static ErrorMessage sendErrorMessage(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        String message = cause.getMessage();
        long statusCode = StatusCodes.Bad_UnexpectedError;

        if (cause instanceof UaException) {
            UaException ex = (UaException) cause;
            message = ex.getMessage();
            statusCode = ex.getStatusCode().getValue();
        } else {
            Throwable innerCause = cause.getCause();

            if (innerCause instanceof UaException) {
                UaException ex = (UaException) innerCause;
                message = ex.getMessage();
                statusCode = ex.getStatusCode().getValue();
            } else if (innerCause instanceof UaRuntimeException) {
                UaRuntimeException ex = (UaRuntimeException) innerCause;
                message = ex.getMessage();
                statusCode = ex.getStatusCode().getValue();
            }
        }

        ErrorMessage error = new ErrorMessage(statusCode, message);
        ByteBuf messageBuffer = TcpMessageEncoder.encode(error);

        ctx.writeAndFlush(messageBuffer).addListener(future -> ctx.close());

        return error;
    }

}
