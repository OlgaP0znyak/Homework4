package com.it_academy.parsers.dom;

import com.it_academy.entity.Article;
import com.it_academy.entity.Contact;
import com.it_academy.entity.Journal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class DomParserDemo {
    private static final String XML_PATH = "Journal.xml";
    private static final List<Article> articles = new ArrayList<>();

    private static void setTitleWithXMLChildNodeValues(Journal journal, Node node) {
        String content = node
                .getLastChild()
                .getTextContent()
                .trim();
        if (node.getNodeName().equals("title"))
            journal.setTitle(content);
    }

    private static void setContactWithXMLChildNodeValues(Contact contact, Node node) {
        String content = node
                .getLastChild()
                .getTextContent()
                .trim();
        switch (node.getNodeName()) {
            case "address":
                contact.setAddress(content);
                break;
            case "tel":
                contact.setTel(content);
                break;
            case "email":
                contact.setEmail(content);
                break;
            case "url":
                contact.setUrl(content);
                break;
        }
    }

    private static void setArticleWithXMLChildNodeValues(Article article, Node node) {
        if (node.getNodeName().equals("article")) {
            String valueAttributeOfNode = node
                    .getAttributes()
                    .getNamedItem("ID")
                    .getNodeValue();
            article.setId(Integer.parseInt(valueAttributeOfNode));

            NodeList childNodes = node.getChildNodes();
            DomParserUtil.getNodeListStream(childNodes).forEach(childNode -> {
                if (childNode instanceof Element) {
                    String contentChildNode = childNode
                            .getLastChild()
                            .getTextContent()
                            .trim();

                    switch (childNode.getNodeName()) {
                        case "title":
                            article.setTitle(contentChildNode);
                            break;
                        case "author":
                            article.setAuthor(contentChildNode);
                            break;
                        case "url":
                            article.setUrl(contentChildNode);
                            break;
                        case "hotkeys":
                            List<String> hotkeys = new ArrayList<>();
                            NodeList childChildNodes =childNode.getChildNodes();
                            DomParserUtil.getNodeListStream(childChildNodes).forEach(childChildNode ->{
                                if (childChildNode instanceof Element) {
                                    String contentChildChildNode = childChildNode
                                            .getLastChild()
                                            .getTextContent()
                                            .trim();
                                    hotkeys.add(contentChildChildNode);
                                }
                                article.setHotkeys(hotkeys);
                            });
                            break;
                    }
                }
            });
            articles.add(article);
        }
    }

    public static void main(String[] args) {
        Document document = DomParserUtil.parseXMLDocument(XML_PATH);
        NodeList nodeList = DomParserUtil.getNodeList(document);

        Journal journal = new Journal();
        Contact contact = new Contact();

        DomParserUtil.getNodeListStream(nodeList).forEach(node -> {
            if (node instanceof Element) {
                setTitleWithXMLChildNodeValues(journal, node);

                NodeList childNodes = node.getChildNodes();
                DomParserUtil.getNodeListStream(childNodes).forEach(childNode -> {
                    if (childNode instanceof Element) {
                        setContactWithXMLChildNodeValues(contact, childNode);
                        Article article = new Article();
                        setArticleWithXMLChildNodeValues(article, childNode);
                    }
                });
            }
        });

        journal.setContacts(contact);
        Journal.setArticles(articles);
        System.out.println(journal);
    }
}
