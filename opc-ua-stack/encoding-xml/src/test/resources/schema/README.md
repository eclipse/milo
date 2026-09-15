# XML namespace schema fixtures

The three `Opc.Ua.*.xsd` files are unmodified copies from OPC Foundation UA-Nodeset revision
`6338cced8e6cc2fa2c3816bc6b3bad5daee3f101`. They are test inputs, not runtime dependencies.

- [DI/Opc.Ua.Di.Types.xsd](https://github.com/OPCFoundation/UA-Nodeset/blob/6338cced8e6cc2fa2c3816bc6b3bad5daee3f101/DI/Opc.Ua.Di.Types.xsd)
  SHA-256 `6b5c0a195f95aa329970927f2b03fccadbb0424cca07e5f5f6f945ce0b4e1df1`
- [AMB/Opc.Ua.AMB.Types.xsd](https://github.com/OPCFoundation/UA-Nodeset/blob/6338cced8e6cc2fa2c3816bc6b3bad5daee3f101/AMB/Opc.Ua.AMB.Types.xsd)
  SHA-256 `ac9b16ac221b76face00a7f87bc7c3653773f6f3babfb401f0983fa6ccd7598c`
- [Schema/Opc.Ua.Types.xsd](https://github.com/OPCFoundation/UA-Nodeset/blob/6338cced8e6cc2fa2c3816bc6b3bad5daee3f101/Schema/Opc.Ua.Types.xsd)
  SHA-256 `45270d114c97e751346446d65ca1fd5fc62e5850f6ab3833587e23e06f5571ea`

## Model metadata

[Part 6 F.2](https://reference.opcfoundation.org/specs/OPC-10000-6/f-2) defines `XmlSchemaUri`
as the XML namespace for serialized model values, separately from `ModelUri` and the NodeId
namespace table.

The [DI model entry](https://github.com/OPCFoundation/UA-Nodeset/blob/6338cced8e6cc2fa2c3816bc6b3bad5daee3f101/DI/Opc.Ua.Di.NodeSet2.xml#L36-L38) declares
`ModelUri="http://opcfoundation.org/UA/DI/"` and
`XmlSchemaUri="http://opcfoundation.org/UA/DI/Types.xsd"`.
The DI schema independently declares that target namespace and includes the model URI in its
`ua:Model` annotation.

The [AMB model entry](https://github.com/OPCFoundation/UA-Nodeset/blob/6338cced8e6cc2fa2c3816bc6b3bad5daee3f101/AMB/Opc.Ua.AMB.NodeSet2.xml#L35-L39) has
`ModelUri="http://opcfoundation.org/UA/AMB/"` and no `XmlSchemaUri`.
The AMB schema's target namespace is `http://opcfoundation.org/UA/AMB/Types.xsd`.
The tests supply this authoritative override explicitly. Missing mappings preserve the model URI
for compatibility; callers cannot assume this fallback produces schema-valid XML.

## Validation coverage

`OpcUaXmlNamespaceTest` uses hand-written codecs for schema-shaped DI and AMB values, with
synthetic string NodeIds clearly separated from the models' published IDs. All five unmapped
values fail schema validation; mapped values must pass. This establishes the namespace error
independently of Milo's decoder.

`NamespaceFixtures.xsd` is a Milo-owned test schema. It imports the pinned schemas to check
cross-model structure and enumeration arrays, inherited AMB fields, nested structures, and XML
ExtensionObjects, including those constructed internally for scalar, array, and matrix Variants.
Its namespace is `urn:milo:test:xml`, which also guards against suffix guessing.

JAXP loads the complete schema set locally. External DTD and schema access are disabled during
schema compilation and instance validation. Tests never fetch model metadata or schemas.

The original copyright/license headers in the DI and UA schemas are preserved. The AMB schema
has no embedded license header; the accompanying AMB NodeSet carries the OPC Foundation MIT License 1.00.
That notice is reproduced in `LICENSE-UA-Nodeset`.
