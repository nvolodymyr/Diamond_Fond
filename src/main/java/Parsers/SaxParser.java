package Parsers;

import Entity.Diamond;
import Entity.Gem;
import Entity.VisualParameters;
import Enums.Preciousness;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class SaxParser extends DefaultHandler {
    static Logger log = Logger.getLogger(SaxParser.class);

    public SaxParser() {
    }

    private Diamond diamond;
    private List<Gem> gemList = new ArrayList<>();
    private Gem gem;
    private VisualParameters visualParameters;
    private String thisElement;

    public static Diamond parser(File xmlPath) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser sp = null;
        try {
            sp = saxParserFactory.newSAXParser();
        } catch (ParserConfigurationException e) {
            log.error(e);
        } catch (SAXException e) {
            log.error(e);
        }
        SaxParser handler = new SaxParser();
        try {
            sp.parse(xmlPath, handler);
        } catch (SAXException e) {
            log.error(e);
        } catch (IOException e) {
            log.error(e);
        }
        return handler.diamond;
    }

    @Override
    public void startDocument() throws SAXException {
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes attributes) throws SAXException {
        thisElement = qName;
        if (thisElement.equals("gem")) {
            gem = new Gem();
            visualParameters = new VisualParameters();
            gem.setId(new String(attributes.getValue("id")));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (thisElement.equals("name")) {
            gem.setName(new String(ch, start, length));
        }
        if (thisElement.equals("preciousness")) {
            if (new String(ch, start, length).equals("PRECIOUS"))
                gem.setPreciousness(Preciousness.PRECIOUS);
            else if (new String(ch, start, length).equals("SEMIPRECIOUS"))
                gem.setPreciousness(Preciousness.SEMIPRECIOUS);
        }
        if (thisElement.equals("origin")) {
            gem.setOrigin(new String(ch, start, length));
        }
//        if (thisElement.equals("visualParameters")) {
//
//        }
        if (thisElement.equals("visualId")) {
            visualParameters.setColor(new String(ch, start, length));
        }
        if (thisElement.equals("color")) {
            visualParameters.setColor(new String(ch, start, length));
        }

        if (thisElement.equals("transparency")) {
            visualParameters.setTransparency(new Integer(new String(ch, start, length)));
        }
        if (thisElement.equals("gemCutting")) {
            visualParameters.setGemCutting(new Integer(new String(ch, start, length)));
        }
        if (thisElement.equals("value")) {
            gem.setValue(new Double(new String(ch, start, length)));
            gem.setVisualParameters(visualParameters);

        }
        gemList.add(gem);
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        thisElement = "";
    }

    @Override
    public void endDocument() {
        diamond = new Diamond(gemList);
    }

}
