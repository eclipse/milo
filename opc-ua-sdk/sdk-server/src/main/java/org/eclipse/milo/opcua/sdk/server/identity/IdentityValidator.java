package org.eclipse.milo.opcua.sdk.server.identity;

import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.channel.SecureChannel;
import org.eclipse.milo.opcua.stack.core.types.structured.ActivateSessionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.SignatureData;
import org.eclipse.milo.opcua.stack.core.types.structured.UserIdentityToken;
import org.eclipse.milo.opcua.stack.core.types.structured.UserTokenPolicy;

public interface IdentityValidator {

    /**
     * Validate the provided {@link UserIdentityToken} and return an identity Object that represents the user.
     * <p>
     * This Object should implement equality in such a way that a subsequent identity validation for the same user
     * yields a comparable Object.
     *
     * @param channel        the {@link SecureChannel} the request is arriving on.
     * @param session        the {@link Session} the request is arriving on.
     * @param token          the {@link UserIdentityToken}.
     * @param tokenPolicy    the {@link UserTokenPolicy} specified by the policyId in {@code token}.
     * @param tokenSignature the {@link SignatureData} sent in the {@link ActivateSessionRequest}
     * @return an identity Object that represents the user.
     * @throws UaException if the token is invalid, rejected, or user access is denied.
     */
    Object validateIdentityToken(
        SecureChannel channel,
        Session session,
        UserIdentityToken token,
        UserTokenPolicy tokenPolicy,
        SignatureData tokenSignature) throws UaException;

}
