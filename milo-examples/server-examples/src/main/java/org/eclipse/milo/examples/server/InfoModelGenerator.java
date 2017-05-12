package org.eclipse.milo.examples.server;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.nodes.UaObjectNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaObjectTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaReferenceTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaVariableNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaVariableTypeNode;
import org.eclipse.milo.opcua.stack.core.BuiltinReferenceType;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.ReferenceType;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class InfoModelGenerator {
	private File xml;
	private SAXBuilder builder;
	private final OpcUaServer server;

	private NodeId nodeId;
	private NodeId dataType;
	private int namespaceIdx;
	private String browseName;
	private String displayName;
	private Boolean isSymmetric;
	private Map<Reference, NodeId> refCol = new HashMap<Reference, NodeId>();
	private Map<String, Integer> references = new HashMap<String, Integer>();
	private Element prev;

	public InfoModelGenerator(String xmlFilePath, OpcUaServer server) {
		this.xml = new File(xmlFilePath);
		this.builder = new SAXBuilder();
		this.server = server;

		elementsLists();

		for (Map.Entry<Reference, NodeId> entry : refCol.entrySet()) {
			try {
				if (!entry.getKey().isForward()) {
					server.getNodeMap().addReference(new Reference(
							new NodeId(entry.getKey().getTargetNodeId().getNamespaceIndex(),
									(UInteger) entry.getKey().getTargetNodeId().getIdentifier()),
							entry.getKey().getReferenceTypeId(), entry.getKey().getSourceNodeId().expanded(),
							server.getNodeMap().getNode(entry.getKey().getSourceNodeId()).get().getNodeClass(), true));
				}
				server.getNodeMap()
						.addReference(new Reference(entry.getKey().getSourceNodeId(),
								entry.getKey().getReferenceTypeId(), entry.getKey().getTargetNodeId(),
								server.getNodeMap().getNode(entry.getValue()).get().getNodeClass(),
								entry.getKey().isForward()));

			} catch (Exception e) {
				System.out.println("failed: " + entry.getValue());

			}

		}
		refCol.clear();
	}

	private void elementsLists() {
		try {
			Document doc = (Document) builder.build(xml);
			getChildren(doc.getRootElement());
			addNode(Integer.parseInt(
					prev.getAttributeValue("NodeId").substring(prev.getAttributeValue("NodeId").indexOf("ns=") + 3,
							prev.getAttributeValue("NodeId").indexOf(";"))));
		} catch (IOException ioex) {
			System.out.println(ioex.getLocalizedMessage());
		} catch (JDOMException jdomex) {
			System.out.println(jdomex.getLocalizedMessage());
		}
	}

	private void getChildren(Element e) {
		if (e.getName().equals("UAObject") || e.getName().equals("UAVariable") || e.getName().equals("UAObjectType")
				|| e.getName().equals("UAVariableType") || e.getName().equals("UAReferenceType")) {
			if (browseName != null) {
				addNode(Integer.parseInt(
						prev.getAttributeValue("NodeId").substring(prev.getAttributeValue("NodeId").indexOf("ns=") + 3,
								prev.getAttributeValue("NodeId").indexOf(";"))));
			}
		}

		if (e.getName().equals("Alias")) {
			int identifier = Integer.parseInt(e.getValue().substring(e.getValue().indexOf("i=") + 2));
			nodeId = new NodeId(0, identifier);
			references.put(e.getAttributeValue("Alias"), identifier);
		}

		if (e.getName().equals("UAObject") || e.getName().equals("UAObjectType") || e.getName().equals("UAVariableType")
				|| e.getName().equals("UAReferenceType")) {
			if (e.getAttributeValue("NodeId") != null) {
				namespaceIdx = Integer.parseInt(e.getAttributeValue("NodeId").substring(
						e.getAttributeValue("NodeId").indexOf("ns=") + 3, e.getAttributeValue("NodeId").indexOf(";")));
				int identifier = Integer.parseInt(
						e.getAttributeValue("NodeId").substring(e.getAttributeValue("NodeId").indexOf("i=") + 2));
				nodeId = new NodeId(namespaceIdx, identifier);
			}
			if (e.getAttributeValue("Symmetric") != null && e.getName().equals("UAReferenceType")) {
				isSymmetric = true;
			} else {
				isSymmetric = false;
			}
			if (!e.getName().equals("UAReferenceType")) {
				isSymmetric = null;
			}

			browseName = e.getAttributeValue("BrowseName");
		} else if (e.getName().equals("Description")) {
			// description = e.getValue();
		} else if (e.getName().equals("DisplayName")) {
			displayName = e.getValue();
		} else if (e.getName().equals("UAVariable")) {
			if (e.getAttributeValue("NodeId") != null) {
				namespaceIdx = Integer.parseInt(e.getAttributeValue("NodeId").substring(
						e.getAttributeValue("NodeId").indexOf("ns=") + 3, e.getAttributeValue("NodeId").indexOf(";")));
				int identifier = Integer.parseInt(
						e.getAttributeValue("NodeId").substring(e.getAttributeValue("NodeId").indexOf("i=") + 2));
				nodeId = new NodeId(namespaceIdx, identifier);
			}
			browseName = e.getAttributeValue("BrowseName");
			if (e.getAttributeValue("ParentNodeId") != null) {

			}
			if (e.getAttributeValue("DataType") != null) {
				dataType = new NodeId(0, references.get(e.getAttributeValue("DataType")));
			}

		} else if (e.getName().equals("Reference")) {
			if (e.getValue().contains("ns=")) {
				namespaceIdx = Integer
						.parseInt(e.getValue().substring(e.getValue().indexOf("ns=") + 3, e.getValue().indexOf(";")));
			} else {
				namespaceIdx = 0;
			}
			int identifier = Integer.parseInt(e.getValue().substring(e.getValue().indexOf("i=") + 2));
			NodeId targetNodeId = new NodeId(namespaceIdx, identifier);
			if (e.getAttributeValue("ReferenceType").contains("ns=")) {
				namespaceIdx = Integer.parseInt(e.getAttributeValue("ReferenceType").substring(
						e.getAttributeValue("ReferenceType").indexOf("ns=") + 3,
						e.getAttributeValue("ReferenceType").indexOf(";")));
				identifier = Integer.parseInt(e.getAttributeValue("ReferenceType")
						.substring(e.getAttributeValue("ReferenceType").indexOf("i=") + 2));
			} else {
				namespaceIdx = 0;
			}
			if (references.get(e.getAttributeValue("ReferenceType")) != null) {
				NodeId referenceTypeId = new NodeId(namespaceIdx, references.get(e.getAttributeValue("ReferenceType")));
				try {
					Reference ref = new Reference(nodeId, referenceTypeId, targetNodeId.expanded(),
							// server.getNodeManager().getNode(targetNodeId).get().getNodeClass(),
							NodeClass.Object, e.getAttributeValue("IsForward") == null ? true : false);
					refCol.put(ref, targetNodeId);
				} catch (Exception exc) {
				}
			} else {

			}
		}
		if (e.getName().equals("UAObject") || e.getName().equals("UAVariable") || e.getName().equals("UAObjectType")
				|| e.getName().equals("UAVariableType") || e.getName().equals("UAReferenceType")) {
			prev = e;

		}
		if (e.getChildren().size() > 0) {
			for (Element el : e.getChildren()) {
				getChildren(el);
			}
		}
		// addNode();

	}

	private void addNode(int namespaceIndex) {
		if (prev != null) {
			switch (prev.getName()) {
			case "UAObject":
				addNode(NodeClass.Object, namespaceIndex);
				break;
			case "UAVariable":
				addNode(NodeClass.Variable, namespaceIndex);
				break;
			case "UAObjectType":
				addNode(NodeClass.ObjectType, namespaceIndex);
				break;
			case "UAVariableType":
				addNode(NodeClass.VariableType, namespaceIndex);
				break;
			case "UAReferenceType":
				addNode(NodeClass.ReferenceType, namespaceIndex);

				break;

			}
		}
	}

	private NodeId addNode(NodeClass nodeClass, int namespaceIndex) {

		QualifiedName qualifiedName = new QualifiedName(namespaceIndex, browseName);
		LocalizedText localizedText = new LocalizedText("en", displayName);
		LocalizedText description = new LocalizedText("en", displayName);
		LocalizedText inverseName = new LocalizedText("en", displayName);
		UInteger writeMask = UInteger.valueOf(0);
		UInteger userWriteMask = UInteger.valueOf(0);
		Boolean isAbstract = false;
		switch (nodeClass) {
		case ObjectType:
			UaObjectTypeNode objectTypeNode = new UaObjectTypeNode(server.getNodeMap(), nodeId, qualifiedName,
					localizedText, new LocalizedText("en", displayName), UInteger.valueOf(0), UInteger.valueOf(0),
					isAbstract);
			server.getNodeMap().put(nodeId, objectTypeNode);
			break;
		case Object:
			UaObjectNode objectNode = new UaObjectNode(server.getNodeMap(), nodeId, qualifiedName, localizedText);
			server.getNodeMap().put(nodeId, objectNode);
			break;
		case Unspecified:
			break;
		case Variable:
			UaVariableNode varNode = new UaVariableNode(server.getNodeMap(), nodeId, qualifiedName, localizedText,
					description, writeMask, userWriteMask, new DataValue(new Variant(0), StatusCode.GOOD),
					dataType, null, null, UByte.valueOf(0), UByte.valueOf(0), null, false);
			
			varNode = new UaVariableNode.UaVariableNodeBuilder(server.getNodeMap())
					.setNodeId(nodeId)
					.setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.NONE)))
					.setUserAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.NONE)))
					.setBrowseName(qualifiedName)
					.setDisplayName(localizedText)
					.setDataType(new NodeId(0,0))
					.setTypeDefinition(Identifiers.BaseDataVariableType).setValue(new DataValue(new Variant(0)))
					.build();

			server.getNodeMap().put(nodeId, varNode);
			break;
		case Method:
			break;
		case VariableType:
			UaVariableTypeNode varTypeNode = new UaVariableTypeNode(server.getNodeMap(), nodeId, qualifiedName,
					localizedText, description, writeMask, userWriteMask, null, dataType, 0, null, false);
			server.getNodeMap().put(nodeId, varTypeNode);
			break;
		case ReferenceType:
			UaReferenceTypeNode refTypeNode = new UaReferenceTypeNode(server.getNodeMap(), nodeId, qualifiedName,
					localizedText, description, writeMask, userWriteMask, isAbstract, isSymmetric, inverseName);
			server.getNodeMap().put(nodeId, refTypeNode);
			Optional<String> in = Optional.of(displayName);
			server.getReferenceTypes().put(nodeId, new CustomReferenceType(nodeId, qualifiedName, in, isSymmetric,
					isAbstract, BuiltinReferenceType.NonHierarchicalReferences));
			break;
		case DataType:
			break;
		case View:
			break;
		}
		return nodeId;
	}

	private class CustomReferenceType implements ReferenceType {
		private NodeId nodeid;
		private QualifiedName qualifiedName;
		private Optional<String> inverseName;
		private boolean isSymmetric;
		private boolean isAbstract;
		private BuiltinReferenceType superType;

		public CustomReferenceType(NodeId nodeid, QualifiedName qualifiedName, Optional<String> inverseName,
				Boolean isSymmetric, Boolean isAbstract, BuiltinReferenceType superType) {
			this.nodeid = nodeid;
			this.qualifiedName = qualifiedName;
			this.inverseName = inverseName;
			this.isSymmetric = isSymmetric;
			this.isAbstract = isAbstract;
			this.superType = superType;
		}

		@Override
		public NodeId getNodeId() {
			return nodeid;
		}

		@Override
		public QualifiedName getBrowseName() {
			return qualifiedName;
		}

		@Override
		public Optional<String> getInverseName() {
			return inverseName;
		}

		@Override
		public boolean isSymmetric() {
			return isSymmetric;
		}

		@Override
		public boolean isAbstract() {
			return isAbstract;
		}

		@Override
		public Optional<ReferenceType> getSuperType() {
			return Optional.ofNullable(superType);
		}

	}
}
