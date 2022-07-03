package com.it_academy.parsers.stax;

import com.it_academy.entity.Article;
import com.it_academy.entity.Contact;
import com.it_academy.entity.Journal;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StaxParserDemo {
    private static final String XML_PATH = "Journal.xml";
    private static List<Article> articles;
    private static List<String> hotkeys;

    public static void parseContacts(XMLStreamReader reader, Journal journal) throws XMLStreamException {
        Contact contact = new Contact();
        int event;
        do {
            event = reader.next();
            if (event == XMLStreamConstants.START_ELEMENT) {
                if ("address".equals(reader.getLocalName())) {
                    contact.setAddress(reader.getElementText());
                } else if ("tel".equals(reader.getLocalName())) {
                    contact.setTel(reader.getElementText());
                } else if ("email".equals(reader.getLocalName())) {
                    contact.setEmail(reader.getElementText());
                } else if ("url".equals(reader.getLocalName())) {
                    contact.setUrl(reader.getElementText());
                }
            }
        } while (reader.hasNext() && event != 2);
        journal.setContacts(contact);
    }

    public static void parseArticles(XMLStreamReader reader) throws XMLStreamException {
        Article article = new Article();
        int event;
        article.setId(Integer.parseInt(reader.getAttributeValue(0)));
        do {
            event = reader.next();
            if (event == XMLStreamConstants.START_ELEMENT) {
                if ("title".equals(reader.getLocalName())) {
                    article.setTitle(reader.getElementText());
                } else if ("author".equals(reader.getLocalName())) {
                    article.setAuthor(reader.getElementText());
                } else if ("url".equals(reader.getLocalName())) {
                    article.setUrl(reader.getElementText());
                } else if ("hotkeys".equals(reader.getLocalName())) {
                    hotkeys = new ArrayList<>();
                } else if ("hotkey".equals(reader.getLocalName())) {
                    hotkeys.add(reader.getElementText());
                    article.setHotkeys(hotkeys);
                }
            }
        }
        while (reader.hasNext() && event != 2);
        articles.add(article);
        Journal.setArticles(articles);
    }


    public static void main(String[] args) {
        Journal journal = null;
        XMLStreamReader reader = StaxParserUtil.createXMLStreamReader(XML_PATH);

        if (reader != null) {
            try {
                while (reader.hasNext()) {
                    int event = reader.next();
                    if (event == XMLStreamConstants.START_ELEMENT) {
                        if ("journal".equals(reader.getLocalName())) {
                            journal = new Journal();
                        } else if ("title".equals(reader.getLocalName())) {
                            if (journal != null) {
                                journal.setTitle(reader.getElementText());
                            }
                        } else if ("contacts".equals(reader.getLocalName())) {
                            parseContacts(reader, journal);
                        } else if ("articles".equals(reader.getLocalName())) {
                            articles = new ArrayList<>();
                        } else if ("article".equals(reader.getLocalName())) {
                            parseArticles(reader);
                        }
                    }
                }
            } catch (XMLStreamException e) {
                System.out.println("Error in reading file");
            }
        }
        System.out.println(journal);
    }
}
