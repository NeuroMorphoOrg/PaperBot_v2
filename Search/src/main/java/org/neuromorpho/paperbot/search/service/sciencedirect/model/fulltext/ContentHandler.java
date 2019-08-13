package org.neuromorpho.paperbot.search.service.sciencedirect.model.fulltext;


import org.w3c.dom.Element;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;

@XmlAccessorType(XmlAccessType.FIELD)
public class ContentHandler extends XmlAdapter<java.lang.Object, String> {

    /**
     * Factory for building DOM documents.
     */
    private final DocumentBuilderFactory docBuilderFactory;
    /**
     * Factory for building transformers.
     */
    private final TransformerFactory transformerFactory;

    public ContentHandler() {
        docBuilderFactory = DocumentBuilderFactory.newInstance();
        transformerFactory = TransformerFactory.newInstance();
    }

    @Override
    public String unmarshal(java.lang.Object o) throws Exception {
        // The provided Objects is a DOM Element
        Element titleElement = (Element) o;
        return titleElement.getTextContent();
       /* // Getting the "a" child elements
        String a = titleElement.getFirstChild().getNodeValue();
        NodeList anchorElements = titleElement.getElementsByTagName("ce:cross-refs");
        // If there's none or multiple, return empty string
        for (Integer i = 0; i < anchorElements.getLength(); i++) {
            Element anchor = (Element) anchorElements.item(i);
            // Creating a DOMSource as input for the transformer
            DOMSource source = new DOMSource(anchor);
            // Default transformer: identity tranformer (doesn't alter input)
            Transformer transformer = transformerFactory.newTransformer();
            // This is necessary to avoid the <?xml ...?> prolog
            transformer.setOutputProperty("omit-xml-declaration", "yes");
            // Transform to a StringWriter
            StringWriter stringWriter = new StringWriter();
            StreamResult result = new StreamResult(stringWriter);
            transformer.transform(source, result);
            // Returning result as string
            tagResult = tagResult + stringWriter.toString();
        }
       return tagResult;*/
    }

    @Override
    public java.lang.Object marshal(String s) throws Exception {
        return null;
    }
}


