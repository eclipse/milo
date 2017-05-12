package org.eclipse.milo.opcua.sdk.server.namespaces;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;

import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.nodes.UaFolderNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaObjectNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaObjectTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaVariableNode;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class AMLParser {
	private File aml;
	private SAXBuilder builder;
	private OpcUaServer server;
	private int NAMESPACE_IDX = 2;

	public AMLParser(File amlFile, OpcUaServer server) {
		this.aml = amlFile;
		this.builder = new SAXBuilder();
		this.server = server;
		elementsLists();
	}

	private void elementsLists() {
		try {
			Document doc = (Document) builder.build(aml);
			server.getNodeMap().get(new NodeId(2, 338)).setBrowseName(new QualifiedName(NAMESPACE_IDX, doc.getRootElement().getAttributeValue("FileName")));
			server.getNodeMap().get(new NodeId(2, 338)).setDisplayName(new LocalizedText("en", doc.getRootElement().getAttributeValue("FileName")));
			getChildren(doc.getRootElement());
			//addReferences(doc.getRootElement());
		} catch (IOException ioex) {
			System.out.println(ioex.getLocalizedMessage());
		} catch (JDOMException jdomex) {
			System.out.println(jdomex.getLocalizedMessage());
		}
	}

	private void getChildren(Element element) {
		for (Element e : element.getChildren()) {
			if (e.getName().equals("InstanceHierarchy")) {
				addFolder(e, new NodeId(2, 342), new NodeId(1, 5005));
			} else if (e.getName().equals("InterfaceClassLib")) {
				addFolder(e, new NodeId(2, 345), new NodeId(1, 5008));
			} else if (e.getName().equals("RoleClassLib")) {
				addFolder(e, new NodeId(2, 344), new NodeId(1, 5003));
			} else if (e.getName().equals("SystemUnitClassLib")) {
				addFolder(e, new NodeId(2, 343), new NodeId(1, 5004));
			} else if (e.getName().equals("InternalElement")) {
				if (!server.getNodeMap().containsNodeId(new NodeId(NAMESPACE_IDX, e.getAttributeValue("Name")))) {
					addObject(e);
				}
			} else if (e.getName().equals("SystemUnitClass") && element.getName().equals("InternalElement")) {
				if (!server.getNodeMap().containsNodeId(new NodeId(NAMESPACE_IDX, e.getAttributeValue("Name")))) {
					addObjectType(e, new NodeId(NAMESPACE_IDX, getPrefix(element)), Identifiers.HasTypeDefinition);
				}
			} else if (e.getName().equals("RoleClass") && (element.getName().equals("RoleClassLib"))) {
				addObjectType(e, new NodeId(NAMESPACE_IDX, getPrefix(element)), Identifiers.HasComponent);
			} else if (e.getName().equals("RoleClass") && (element.getName().equals("RoleClass"))) {
				if (!server.getNodeMap().containsNodeId(new NodeId(NAMESPACE_IDX, e.getAttributeValue("Name")))) {
					addObjectType(e, new NodeId(NAMESPACE_IDX, getPrefix(element)), Identifiers.Organizes);
				}
			} else if (e.getName().equals("RoleClass") && (element.getName().equals("SystemUnitClass"))) {
				addObjectType(e, new NodeId(NAMESPACE_IDX, getPrefix(element)), new NodeId(1, 4001));
			} else if (e.getName().equals("SystemUnitClass") && (element.getName().equals("SystemUnitClassLib"))) {
				addObjectType(e, new NodeId(NAMESPACE_IDX, getPrefix(element)), Identifiers.HasComponent);
			} else if (e.getName().equals("SystemUnitClass") && (element.getName().equals("SystemUnitClass"))) {
				if (!server.getNodeMap().containsNodeId(new NodeId(NAMESPACE_IDX, e.getAttributeValue("Name")))) {
					addObjectType(e, new NodeId(NAMESPACE_IDX, getPrefix(element)), Identifiers.Organizes);
				}
			} else if (e.getName().equals("Attribute")) {
				if (!server.getNodeMap().containsNodeId(new NodeId(NAMESPACE_IDX, e.getAttributeValue("Name")))) {
					addVariable(e, new NodeId(NAMESPACE_IDX, getPrefix(element)), Identifiers.HasComponent);
				}
			} else if (e.getName().equals("ExternalInterface") && !element.getName().equals("ExternalInterface")) {
				if (!server.getNodeMap().containsNodeId(new NodeId(NAMESPACE_IDX, e.getAttributeValue("Name")))) {
					addObject(e);
				}
			} else if (e.getName().equals("ExternalInterface") && element.getName().equals("ExternalInterface")) {
				if (!server.getNodeMap().containsNodeId(new NodeId(NAMESPACE_IDX, e.getAttributeValue("Name")))) {
					addObject(e);
				}
			} else if (e.getName().equals("InterfaceClass") && element.getName().equals("InterfaceClass")) {
				if (!server.getNodeMap().containsNodeId(new NodeId(NAMESPACE_IDX, e.getAttributeValue("Name")))) {
					addObjectType(e, new NodeId(NAMESPACE_IDX, getPrefix(element)), Identifiers.Organizes);
				}
			}
			if (e.getChildren().size() > 0) {
				getChildren(e);

			}

		}

	}

	private void addReferences(Element element) {
		for (Element e : element.getChildren()) {
			if ((e.getName().equals("SupportedRoleClass") || e.getName().equals("RoleRequirements"))
					&& (element.getName().equals("InternalElement") || element.getName().equals("SystemUnitClass"))) {
				String name = e.getAttributeValue("RefRoleClassPath") == null
						? e.getAttributeValue("RefBaseRoleClassPath") : e.getAttributeValue("RefRoleClassPath");
				server.getNodeMap().addReference(new Reference(

						new NodeId(NAMESPACE_IDX, getPrefix(e.getParentElement())), new NodeId(1, 4001),
						server.getNodeMap().getNode(new NodeId(NAMESPACE_IDX, name)).get().getNodeId().expanded(), // new
																													// NodeId(1,4001)
																													// =
																													// HasAmlRoleReference
						server.getNodeMap().getNode(new NodeId(NAMESPACE_IDX, name)).get().getNodeClass(), true));

			}
			if ((e.getName().equals("SystemUnitClass") && (element.getName().equals("SystemUnitClass"))
					|| (e.getName().equals("RoleClass") && element.getName().equals("RoleClass"))
							&& e.getAttributeValue("RefBaseClassPath") != null)) {
				String name = e.getAttributeValue("RefBaseClassPath");
				try {
					server.getNodeMap().addReference(new Reference(

							server.getNodeMap().getNode(new NodeId(NAMESPACE_IDX, name)).get().getNodeId(),
							Identifiers.HasSubtype, new NodeId(NAMESPACE_IDX, getPrefix(e)).expanded(),
							server.getNodeMap().getNode(new NodeId(NAMESPACE_IDX, getPrefix(e))).get().getNodeClass(),
							true));
				} catch (NoSuchElementException exc) {

				}
			}
			if (e.getName().equals("InternalElement") && (e.getAttributeValue("RefBaseSystemUnitPath") != null)) {
				String name = e.getAttributeValue("RefBaseSystemUnitPath");
				try {
					server.getNodeMap().addReference(new Reference(

							new NodeId(NAMESPACE_IDX, getPrefix(e)), Identifiers.HasTypeDefinition,
							server.getNodeMap().getNode(new NodeId(NAMESPACE_IDX, name)).get().getNodeId().expanded(),
							server.getNodeMap().getNode(new NodeId(NAMESPACE_IDX, name)).get().getNodeClass(), true));
				} catch (NoSuchElementException exc) {

				}

			}
			if (e.getName().equals("ExternalInterface") && (e.getAttributeValue("RefBaseClassPath") != null)) {
				String name = e.getAttributeValue("RefBaseClassPath");
				try {
					server.getNodeMap().addReference(new Reference(

							new NodeId(NAMESPACE_IDX, getPrefix(e)), Identifiers.HasTypeDefinition,
							server.getNodeMap().getNode(new NodeId(NAMESPACE_IDX, name)).get().getNodeId().expanded(),
							server.getNodeMap().getNode(new NodeId(NAMESPACE_IDX, name)).get().getNodeClass(), true));
				} catch (NoSuchElementException exc) {

				}

			}
			if (e.getName().equals("InterfaceClass") && (e.getAttributeValue("RefBaseClassPath") != null)) {
				String name = e.getAttributeValue("RefBaseClassPath");
				try {
					server.getNodeMap().addReference(new Reference(

							new NodeId(NAMESPACE_IDX, getPrefix(e)), Identifiers.HasSubtype,
							server.getNodeMap().getNode(new NodeId(NAMESPACE_IDX, name)).get().getNodeId().expanded(),
							server.getNodeMap().getNode(new NodeId(NAMESPACE_IDX, name)).get().getNodeClass(), true));
				} catch (NoSuchElementException exc) {

				}

			}
			if (e.getName().equals("ExternalInterface") && (e.getAttributeValue("InternalLink") != null)) {
				String name = e.getAttributeValue("RefRoleClassPath") == null
						? e.getAttributeValue("RefBaseRoleClassPath") : e.getAttributeValue("RefRoleClassPath");
				server.getNodeMap().addReference(new Reference(
						new NodeId(NAMESPACE_IDX, getPrefix(e.getParentElement())), new NodeId(1, 4002),
						server.getNodeMap().getNode(new NodeId(NAMESPACE_IDX, name)).get().getNodeId().expanded(), // new
																													// NodeId(1,4002)
																													// =
																													// HasAmlInternalLink
						server.getNodeMap().getNode(new NodeId(NAMESPACE_IDX, name)).get().getNodeClass(), true));

			}
			if (e.getChildren().size() > 0) {
				addReferences(e);
			}
		}
	}

	private void addObject(Element e) {
		UaObjectNode internalElement = new UaObjectNode(server.getNodeMap(), new NodeId(NAMESPACE_IDX, getPrefix(e)),
				new QualifiedName(NAMESPACE_IDX, e.getAttributeValue("Name")),
				new LocalizedText("en", e.getAttributeValue("Name")));
		server.getNodeMap().addNode(internalElement);
		server.getNodeMap()
				.addReference(new Reference(new NodeId(NAMESPACE_IDX, getPrefix(e.getParentElement())),
						Identifiers.HasComponent, internalElement.getNodeId().expanded(),
						internalElement.getNodeClass(), true));
	}

	private void addFolder(Element e, NodeId parentFolder, NodeId nonHierarchicalRef) {
		UaFolderNode folder = new UaFolderNode(server.getNodeMap(), new NodeId(NAMESPACE_IDX, getPrefix(e)),
				new QualifiedName(NAMESPACE_IDX, e.getAttributeValue("Name")),
				new LocalizedText("en", e.getAttributeValue("Name")));
		server.getNodeMap().addNode(folder);
		server.getNodeMap().addReference(new Reference(nonHierarchicalRef, Identifiers.Organizes,
				folder.getNodeId().expanded(), folder.getNodeClass(), true));
		server.getNodeMap().addReference(new Reference(parentFolder, Identifiers.HasComponent,
				folder.getNodeId().expanded(), folder.getNodeClass(), true));
	}

	private void addObjectType(Element e, NodeId parentNodeId, NodeId referenceType) {
		UaObjectTypeNode systemUnitClass = new UaObjectTypeNode(server.getNodeMap(),
				new NodeId(NAMESPACE_IDX, getPrefix(e)), new QualifiedName(NAMESPACE_IDX, e.getAttributeValue("Name")),
				new LocalizedText("en", e.getAttributeValue("Name")),
				new LocalizedText("en", e.getAttributeValue("Name")), UInteger.valueOf(0), UInteger.valueOf(0), false);
		server.getNodeMap().addNode(systemUnitClass);
		server.getNodeMap().addReference(new Reference(parentNodeId, referenceType,
				systemUnitClass.getNodeId().expanded(), systemUnitClass.getNodeClass(), true));
	}

	private void addVariable(Element e, NodeId parentNodeId, NodeId referenceType) {
		UaVariableNode varNode = new UaVariableNode.UaVariableNodeBuilder(server.getNodeMap())
				.setNodeId(new NodeId(NAMESPACE_IDX, getPrefix(e)))
				.setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_WRITE)))
				.setBrowseName(new QualifiedName(NAMESPACE_IDX, e.getAttributeValue("Name")))
				.setDisplayName(LocalizedText.english(e.getAttributeValue("Name")))
				.setDataType(nodeIdFromAttribute(e.getAttributeValue("AttributeDataType")))
				.setTypeDefinition(Identifiers.BaseDataVariableType).setValue(e.getChild("Value") == null
						? new DataValue(new Variant(0)) : new DataValue(new Variant(e.getChild("Value").getValue())))
				.build();

		server.getNodeMap().addNode(varNode);
		server.getNodeMap().addReference(new Reference(parentNodeId, referenceType, varNode.getNodeId().expanded(),
				varNode.getNodeClass(), true));
	}

	private String getPrefix(Element e) {
		String id = "";
		try {
			while (!(e.getName().equals("SystemUnitClassLib") || e.getName().equals("RoleClassLib")
					|| e.getName().equals("InstanceHierarchy"))) {
				id = e.getAttributeValue("Name") + "/" + id;
				e = e.getParentElement();
			}
		} catch (NullPointerException exc) {

		}
		try {
			id = e.getAttributeValue("Name") + "/" + id;
		} catch (NullPointerException exc) {

		}

		return id.substring(0, id.lastIndexOf("/"));
	}

	private NodeId nodeIdFromAttribute(String attribute) {
		if (attribute != null) {
			switch (attribute) {
			case "xs:string":
				return Identifiers.String;
			case "xs:anyURI":
				return Identifiers.String;
			case "xs:integer":
				return Identifiers.Integer;
			case "xs:int":
				return Identifiers.Integer;
			case "xs:double":
				return Identifiers.Double;
			case "xs:unsignedInt":
				return Identifiers.UInteger;
			default:
				// System.out.println(attribute);
				return Identifiers.String;
			}
		}
		return Identifiers.String;
	}
}