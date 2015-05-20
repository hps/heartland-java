package com.hps.integrator.infrastructure;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Element {
    private Document doc;
    private org.w3c.dom.Element element;

    public Element(Document doc, org.w3c.dom.Element element) {
        this.doc = doc;
        this.element = element;
    }

    public Element set(String name, String value){
        this.element.setAttribute(name, value);
        return this;
    }

    public Element text(String text){
        this.element.appendChild(doc.createTextNode(text));
        return this;
    }

    public Element append(Element child) {
        this.element.appendChild(child.getElement());
        return this;
    }

    public org.w3c.dom.Element getElement() { return this.element; }

    public static Element fromNode(Document doc, Node node) {
        return new Element(doc, (org.w3c.dom.Element)node);
    }

    public boolean has(String tagName) {
        return this.element.getElementsByTagName(tagName).getLength() > 0;
    }

    public Element get(String tagName) {
        return Element.fromNode(doc, this.element.getElementsByTagName(tagName).item(0));
    }

    public Element[] getAll(String tagName) {
        NodeList nodes = this.element.getElementsByTagName(tagName);

        Element[] elements = new Element[nodes.getLength()];
        for(int i = 0; i < nodes.getLength(); i++)
            elements[i] = Element.fromNode(this.doc, nodes.item(i));

        return elements;
    }

    public String getString(String tagName) {
        org.w3c.dom.Element element = (org.w3c.dom.Element)this.element.getElementsByTagName(tagName).item(0);
        if(element != null) {
            return element.getTextContent();
        } return null;
    }

    public int getInt(String tagName) {
        org.w3c.dom.Element element = (org.w3c.dom.Element)this.element.getElementsByTagName(tagName).item(0);
        if(element != null) {
            return Integer.parseInt(element.getTextContent());
        } return -1;
    }

    public long getLong(String tagName) {
        org.w3c.dom.Element element = (org.w3c.dom.Element)this.element.getElementsByTagName(tagName).item(0);
        if(element != null) {
            return Long.parseLong(element.getTextContent());
        } return -1;
    }

    public Date getDate(String tagName) {
        org.w3c.dom.Element element = (org.w3c.dom.Element)this.element.getElementsByTagName(tagName).item(0);
        if(element != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS");
                return sdf.parse(element.getTextContent());
            } catch (ParseException e) {
                return null;
            }
        } return null;
    }
}
