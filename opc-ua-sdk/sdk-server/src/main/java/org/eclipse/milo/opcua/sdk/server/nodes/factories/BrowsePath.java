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
