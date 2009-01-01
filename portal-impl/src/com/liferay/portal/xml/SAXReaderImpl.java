/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portal.xml;

import com.liferay.portal.kernel.xml.Attribute;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.Entity;
import com.liferay.portal.kernel.xml.Namespace;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.ProcessingInstruction;
import com.liferay.portal.kernel.xml.QName;
import com.liferay.portal.kernel.xml.SAXReader;
import com.liferay.portal.kernel.xml.Text;
import com.liferay.portal.kernel.xml.XPath;
import com.liferay.portal.util.EntityResolver;
import com.liferay.util.xml.XMLSafeReader;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.dom4j.DocumentFactory;
import org.dom4j.DocumentHelper;

/**
 * <a href="SAXReaderImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class SAXReaderImpl implements SAXReader {

	public static List<Attribute> toNewAttributes(
		List<org.dom4j.Attribute> oldAttributes) {

		List<Attribute> newAttributes = new ArrayList<Attribute>(
			oldAttributes.size());

		for (org.dom4j.Attribute oldAttribute : oldAttributes) {
			newAttributes.add(new AttributeImpl(oldAttribute));
		}

		return new NodeList<Attribute, org.dom4j.Attribute>(
			newAttributes, oldAttributes);
	}

	public static List<Element> toNewElements(
		List<org.dom4j.Element> oldElements) {

		List<Element> newElements = new ArrayList<Element>(oldElements.size());

		for (org.dom4j.Element oldElement : oldElements) {
			newElements.add(new ElementImpl(oldElement));
		}

		return new NodeList<Element, org.dom4j.Element>(
			newElements, oldElements);
	}

	public static List<Namespace> toNewNamespaces(
		List<org.dom4j.Namespace> oldNamespaces) {

		List<Namespace> newNamespaces = new ArrayList<Namespace>(
			oldNamespaces.size());

		for (org.dom4j.Namespace oldNamespace : oldNamespaces) {
			newNamespaces.add(new NamespaceImpl(oldNamespace));
		}

		return new NodeList<Namespace, org.dom4j.Namespace>(
			newNamespaces, oldNamespaces);
	}

	public static List<Node> toNewNodes(List<org.dom4j.Node> oldNodes) {
		List<Node> newNodes = new ArrayList<Node>(oldNodes.size());

		for (org.dom4j.Node oldNode : oldNodes) {
			if (oldNode instanceof org.dom4j.Element) {
				newNodes.add(new ElementImpl((org.dom4j.Element)oldNode));
			}
			else {
				newNodes.add(new NodeImpl(oldNode));
			}
		}

		return new NodeList<Node, org.dom4j.Node>(newNodes, oldNodes);
	}

	public static List<ProcessingInstruction> toNewProcessingInstructions(
		List<org.dom4j.ProcessingInstruction> oldProcessingInstructions) {

		List<ProcessingInstruction> newProcessingInstructions =
			new ArrayList<ProcessingInstruction>(
				oldProcessingInstructions.size());

		for (org.dom4j.ProcessingInstruction oldProcessingInstruction :
				oldProcessingInstructions) {

			newProcessingInstructions.add(
				new ProcessingInstructionImpl(oldProcessingInstruction));
		}

		return new NodeList
			<ProcessingInstruction, org.dom4j.ProcessingInstruction>(
				newProcessingInstructions, oldProcessingInstructions);
	}

	public static List<org.dom4j.Attribute> toOldAttributes(
		List<Attribute> newAttributes) {

		List<org.dom4j.Attribute> oldAttributes =
			new ArrayList<org.dom4j.Attribute>(newAttributes.size());

		for (Attribute newAttribute : newAttributes) {
			AttributeImpl newAttributeImpl = (AttributeImpl)newAttribute;

			oldAttributes.add(newAttributeImpl.getWrappedAttribute());
		}

		return oldAttributes;
	}

	public static List<org.dom4j.Node> toOldNodes(List<Node> newNodes) {
		List<org.dom4j.Node> oldNodes = new ArrayList<org.dom4j.Node>(
			newNodes.size());

		for (Node newNode : newNodes) {
			NodeImpl newNodeImpl = (NodeImpl)newNode;

			oldNodes.add(newNodeImpl.getWrappedNode());
		}

		return oldNodes;
	}

	public static List<org.dom4j.ProcessingInstruction>
		toOldProcessingInstructions(
			List<ProcessingInstruction> newProcessingInstructions) {

		List<org.dom4j.ProcessingInstruction> oldProcessingInstructions =
			new ArrayList<org.dom4j.ProcessingInstruction>(
				newProcessingInstructions.size());

		for (ProcessingInstruction newProcessingInstruction :
				newProcessingInstructions) {

			ProcessingInstructionImpl newProcessingInstructionImpl =
				(ProcessingInstructionImpl)newProcessingInstruction;

			oldProcessingInstructions.add(
				newProcessingInstructionImpl.getWrappedProcessingInstruction());
		}

		return oldProcessingInstructions;
	}

	public Attribute createAttribute(
		Element element, QName qName, String value) {

		ElementImpl elementImpl = (ElementImpl)element;
		QNameImpl qNameImpl = (QNameImpl)qName;

		DocumentFactory documentFactory = DocumentFactory.getInstance();

		return new AttributeImpl(
			documentFactory.createAttribute(
				elementImpl.getWrappedElement(), qNameImpl.getWrappedQName(),
				value));
	}

	public Attribute createAttribute(
		Element element, String name, String value) {

		ElementImpl elementImpl = (ElementImpl)element;

		DocumentFactory documentFactory = DocumentFactory.getInstance();

		return new AttributeImpl(
			documentFactory.createAttribute(
				elementImpl.getWrappedElement(), name, value));
	}

	public Document createDocument() {
		return new DocumentImpl(DocumentHelper.createDocument());
	}

	public Document createDocument(Element rootElement) {
		ElementImpl rootElementImpl = (ElementImpl)rootElement;

		return new DocumentImpl(
			DocumentHelper.createDocument(rootElementImpl.getWrappedElement()));
	}

	public Document createDocument(String encoding) {
		DocumentFactory documentFactory = DocumentFactory.getInstance();

		return new DocumentImpl(documentFactory.createDocument(encoding));
	}

	public Element createElement(QName qName) {
		QNameImpl qNameImpl = (QNameImpl)qName;

		return new ElementImpl(
			DocumentHelper.createElement(qNameImpl.getWrappedQName()));
	}

	public Element createElement(String name) {
		return new ElementImpl(DocumentHelper.createElement(name));
	}

	public Entity createEntity(String name, String text) {
		return new EntityImpl(DocumentHelper.createEntity(name, text));
	}

	public Namespace createNamespace(String uri) {
		return new NamespaceImpl(org.dom4j.Namespace.get(uri));
	}

	public Namespace createNamespace(String prefix, String uri) {
		return new NamespaceImpl(DocumentHelper.createNamespace(prefix, uri));
	}

	public ProcessingInstruction createProcessingInstruction(
		String target, Map<String, String> data) {

		org.dom4j.ProcessingInstruction processingInstruction =
			DocumentHelper.createProcessingInstruction(target, data);

		if (processingInstruction == null) {
			return null;
		}
		else {
			return new ProcessingInstructionImpl(processingInstruction);
		}
	}

	public ProcessingInstruction createProcessingInstruction(
		String target, String data) {

		org.dom4j.ProcessingInstruction processingInstruction =
			DocumentHelper.createProcessingInstruction(target, data);

		if (processingInstruction == null) {
			return null;
		}
		else {
			return new ProcessingInstructionImpl(processingInstruction);
		}
	}

	public QName createQName(String localName) {
		return new QNameImpl(DocumentHelper.createQName(localName));
	}

	public QName createQName(String localName, Namespace namespace) {
		NamespaceImpl namespaceImpl = (NamespaceImpl)namespace;

		return new QNameImpl(
			DocumentHelper.createQName(
				localName, namespaceImpl.getWrappedNamespace()));
	}

	public Text createText(String text) {
		return new TextImpl(DocumentHelper.createText(text));
	}

	public XPath createXPath(String xpathExpression) {
		return new XPathImpl(DocumentHelper.createXPath(xpathExpression));
	}

	public List<Node> selectNodes(
		String xpathFilterExpression, List<Node> nodes) {

		return toNewNodes(
			DocumentHelper.selectNodes(
				xpathFilterExpression, toOldNodes(nodes)));
	}

	public List<Node> selectNodes(
		String xpathFilterExpression, Node node) {

		NodeImpl nodeImpl = (NodeImpl)node;

		return toNewNodes(
			DocumentHelper.selectNodes(
				xpathFilterExpression, nodeImpl.getWrappedNode()));
	}

	public void sort(List<Node> nodes, String xpathExpression) {
		DocumentHelper.sort(toOldNodes(nodes), xpathExpression);
	}

	public void sort(
		List<Node> nodes, String xpathExpression, boolean distinct) {

		DocumentHelper.sort(toOldNodes(nodes), xpathExpression, distinct);
	}

	public Document read(File file) throws DocumentException {
		return read(file, false);
	}

	public Document read(File file, boolean validate)
		throws DocumentException {

		try {
			org.dom4j.io.SAXReader saxReader = getSAXReader(validate);

			return new DocumentImpl(saxReader.read(file));
		}
		catch (org.dom4j.DocumentException de) {
			throw new DocumentException(de.getMessage());
		}
	}

	public Document read(InputStream is) throws DocumentException {
		return read(is, false);
	}

	public Document read(InputStream is, boolean validate)
		throws DocumentException {

		try {
			org.dom4j.io.SAXReader saxReader = getSAXReader(validate);

			return new DocumentImpl(saxReader.read(is));
		}
		catch (org.dom4j.DocumentException de) {
			throw new DocumentException(de.getMessage());
		}
	}

	public Document read(Reader reader) throws DocumentException {
		return read(reader, false);
	}

	public Document read(Reader reader, boolean validate)
		throws DocumentException {

		try {
			org.dom4j.io.SAXReader saxReader = getSAXReader(validate);

			return new DocumentImpl(saxReader.read(reader));
		}
		catch (org.dom4j.DocumentException de) {
			throw new DocumentException(de.getMessage());
		}
	}

	public Document read(String xml) throws DocumentException {
		return read(new XMLSafeReader(xml));
	}

	public Document read(String xml, boolean validate)
		throws DocumentException {

		return read(new XMLSafeReader(xml), validate);
	}

	public Document read(URL url) throws DocumentException {
		return read(url, false);
	}

	public Document read(URL url, boolean validate) throws DocumentException {
		try {
			org.dom4j.io.SAXReader saxReader = getSAXReader(validate);

			return new DocumentImpl(saxReader.read(url));
		}
		catch (org.dom4j.DocumentException de) {
			throw new DocumentException(de.getMessage());
		}
	}

	public Document readURL(String url)
		throws DocumentException, MalformedURLException {

		return read(new URL(url), false);
	}

	public Document readURL(String url, boolean validate)
		throws DocumentException, MalformedURLException {

		return read(new URL(url), validate);
	}

	protected org.dom4j.io.SAXReader getSAXReader(boolean validate) {

		// Crimson cannot do XSD validation. See the following links:
		//
		// http://www.geocities.com/herong_yang/jdk/xsd_validation.html
		// http://www.burnthacker.com/archives/000086.html
		// http://www.theserverside.com/news/thread.tss?thread_id=22525

		org.dom4j.io.SAXReader reader = null;

		try {
			reader = new org.dom4j.io.SAXReader(_SAX_PARSER_IMPL, validate);

			reader.setEntityResolver(new EntityResolver());

			reader.setFeature(_FEATURES_VALIDATION, validate);
			reader.setFeature(_FEATURES_VALIDATION_SCHEMA, validate);
			reader.setFeature(
				_FEATURES_VALIDATION_SCHEMA_FULL_CHECKING, validate);
			reader.setFeature(_FEATURES_DYNAMIC, validate);
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"XSD validation is diasabled because " + e.getMessage());
			}

			reader = new org.dom4j.io.SAXReader(validate);

			reader.setEntityResolver(new EntityResolver());
		}

		return reader;
	}

	private static final String _SAX_PARSER_IMPL =
		"org.apache.xerces.parsers.SAXParser";

	private static final String _FEATURES_VALIDATION =
		"http://xml.org/sax/features/validation";

	private static final String _FEATURES_VALIDATION_SCHEMA =
		"http://apache.org/xml/features/validation/schema";

	private static final String _FEATURES_VALIDATION_SCHEMA_FULL_CHECKING =
		"http://apache.org/xml/features/validation/schema-full-checking";

	private static final String _FEATURES_DYNAMIC =
		"http://apache.org/xml/features/validation/dynamic";

	private static Log _log = LogFactory.getLog(SAXReaderImpl.class);

}