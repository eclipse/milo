package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/12.31">https://reference.opcfoundation.org/v105/Core/docs/Part5/12.31</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public abstract class DataTypeSchemaHeader extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=15534");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=15676");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=15950");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=16151");

    private final String[] namespaces;

    private final StructureDescription[] structureDataTypes;

    private final EnumDescription[] enumDataTypes;

    private final SimpleTypeDescription[] simpleDataTypes;

    public DataTypeSchemaHeader(String[] namespaces, StructureDescription[] structureDataTypes,
                                EnumDescription[] enumDataTypes, SimpleTypeDescription[] simpleDataTypes) {
        this.namespaces = namespaces;
        this.structureDataTypes = structureDataTypes;
        this.enumDataTypes = enumDataTypes;
        this.simpleDataTypes = simpleDataTypes;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getJsonEncodingId() {
        return JSON_ENCODING_ID;
    }

    public String[] getNamespaces() {
        return namespaces;
    }

    public StructureDescription[] getStructureDataTypes() {
        return structureDataTypes;
    }

    public EnumDescription[] getEnumDataTypes() {
        return enumDataTypes;
    }

    public SimpleTypeDescription[] getSimpleDataTypes() {
        return simpleDataTypes;
    }
}
