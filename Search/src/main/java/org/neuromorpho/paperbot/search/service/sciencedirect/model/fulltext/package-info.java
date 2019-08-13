@XmlSchema(
        namespace="http://www.elsevier.com/xml/svapi/article/dtd",
        elementFormDefault = XmlNsForm.QUALIFIED,
        xmlns={
                @XmlNs(prefix="bk", namespaceURI="http://www.elsevier.com/xml/bk/dtd"),
                @XmlNs(prefix="cals", namespaceURI="http://www.elsevier.com/xml/common/cals/dtd"),
                @XmlNs(prefix="ce", namespaceURI="http://www.elsevier.com/xml/common/dtd"),
                @XmlNs(prefix="ja", namespaceURI="http://www.elsevier.com/xml/ja/dtd"),
                @XmlNs(prefix="mml", namespaceURI="http://www.w3.org/1998/Math/MathML"),
                @XmlNs(prefix="sa", namespaceURI="http://www.elsevier.com/xml/common/struct-aff/dtd"),
                @XmlNs(prefix="sb", namespaceURI="http://www.elsevier.com/xml/common/struct-bib/dtd"),
                @XmlNs(prefix="tb", namespaceURI="http://www.elsevier.com/xml/common/table/dtd"),
                @XmlNs(prefix="xlink", namespaceURI="http://www.w3.org/1999/xlink"),
                @XmlNs(prefix="xocs", namespaceURI="http://www.elsevier.com/xml/xocs/dtd"),
                @XmlNs(prefix="dc", namespaceURI="http://purl.org/dc/elements/1.1/"),
                @XmlNs(prefix="dcterms", namespaceURI="http://purl.org/dc/terms/"),
                @XmlNs(prefix="prism", namespaceURI="http://prismstandard.org/namespaces/basic/2.0/"),
                @XmlNs(prefix="xsi", namespaceURI="http://www.w3.org/2001/XMLSchema-instance"),

        }
)
        
package org.neuromorpho.paperbot.search.service.sciencedirect.model.fulltext;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;

