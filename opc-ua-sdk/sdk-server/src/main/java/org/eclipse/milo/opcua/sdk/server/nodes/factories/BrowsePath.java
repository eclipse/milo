/*
 * Copyright (c) 2018 Kevin Herron
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

package org.eclipse.milo.opcua.sdk.server.nodes.factories;

import java.util.Objects;

import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;

class BrowsePath {
    BrowsePath parent;
    QualifiedName browseName;

    public BrowsePath(BrowsePath parent, QualifiedName browseName) {
        this.parent = parent;
        this.browseName = browseName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BrowsePath that = (BrowsePath) o;
        return Objects.equals(parent, that.parent) &&
            Objects.equals(browseName, that.browseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parent, browseName);
    }

    @Override
    public String toString() {
        if (parent == null) {
            return "/";
        } else {
            String s = parent.toString();
            if (!s.endsWith("/")) s += "/";
            return s + browseName.toParseableString();
        }
    }
}
