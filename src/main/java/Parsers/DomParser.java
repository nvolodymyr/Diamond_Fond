package Parsers;

import Entity.Diamond;
import Entity.Gem;
import Entity.VisualParameters;
import Enums.Preciousness;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DomParser {
    static Logger log = Logger.getLogger(DomParser.class.getName());

    private DomParser() {
    }

    private static Element getBaby(Element parent, String childName) {
        NodeList nlist = parent.getElementsByTagName(childName);
        Element child = (Element) nlist.item(0);
        return child;
    }

    private static String getBabyValue(Element parent, String childName) {
        Element child = getBaby(parent, childName);
        Node node = child.getFirstChild();
        String value = node.getNodeValue();
        return value;
    }

    public static Diamond parse(File xmlFile) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        }
        Document document = null;
        try {
            document = builder.parse(xmlFile);
        } catch (SAXException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        List<Gem> gemList = new ArrayList<Gem>();
        NodeList gemsNodes = document.getDocumentElement().getElementsByTagName("gem");
        VisualParameters visualParameters;
        for (int i = 0; i < gemsNodes.getLength(); i++) {
            Gem gem = new Gem();
            Element gemElement = (Element) gemsNodes.item(i);
            gem.setId(gemElement.getAttribute("id"));
            gem.setName(getBabyValue(gemElement, "name"));
            gem.setOrigin(getBabyValue(gemElement, "origin"));
            if (getBabyValue(gemElement, "preciousness").equals("PRECIOUS")) {
                gem.setPreciousness(Preciousness.PRECIOUS);
            }
            if (getBabyValue(gemElement, "preciousness").equals("SEMIPRECIOUS")) {
                gem.setPreciousness(Preciousness.SEMIPRECIOUS);
            }
            visualParameters = new VisualParameters();
            Element visualParametersElement = getBaby(gemElement, "visualParameters");
            visualParameters.setVisualId(getBabyValue(visualParametersElement, "visualId"));
            visualParameters.setColor(getBabyValue(visualParametersElement, "color"));
            visualParameters.setTransparency(new Integer(getBabyValue(visualParametersElement, "transparency")));
            visualParameters.setGemCutting(new Integer(getBabyValue(visualParametersElement, "gemCutting")));
            gem.setValue(new Double(getBabyValue(gemElement, "value")));
            gem.setVisualParameters(visualParameters);
            System.out.println();
            gemList.add(gem);


        }

        return new Diamond(gemList);
    }

}
