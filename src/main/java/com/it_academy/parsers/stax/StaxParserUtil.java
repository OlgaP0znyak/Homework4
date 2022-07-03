package com.it_academy.parsers.stax;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class StaxParserUtil {
    public static XMLStreamReader createXMLStreamReader(String path){
        XMLInputFactory factory = XMLInputFactory.newInstance();
        try {
            return factory.createXMLStreamReader(
                    ClassLoader.getSystemResourceAsStream(path));
        } catch (XMLStreamException e) {
            System.out.println("ParserConfigurationException log");
            return null;
        }
    }
}
