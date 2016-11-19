package org.eclipse.milo.opcua.sdk.server.nodes.delegates;

import java.util.function.Function;

public class AttributeDelegateChain {

    @SafeVarargs
    public static AttributeDelegate create(
        AttributeDelegate root,
        Function<AttributeDelegate, AttributeDelegate>... childFns) {

        AttributeDelegate delegate = root;

        for (Function<AttributeDelegate, AttributeDelegate> childFn : childFns) {
            delegate = childFn.apply(delegate);
        }

        return delegate;
    }

}
