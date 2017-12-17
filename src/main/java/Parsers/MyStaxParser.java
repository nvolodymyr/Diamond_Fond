package Parsers;

import Entity.Diamond;
import Entity.Gem;
import Entity.VisualParameters;
import Enums.Preciousness;
import org.apache.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class MyStaxParser {
    final static String XSD_FILE_PATH = "src/main/resources/Schema.xsd";
    static Logger log = Logger.getLogger(MyStaxParser.class.getName());
    private MyStaxParser() {
    }
    public static Diamond parse(String xmlFile) {
        List<Gem> gemList = null;
        Gem gem = null;
        VisualParameters visualParameters = null;
        String tagContent = null;
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = null;
        try {
            StringReader stringReader = new StringReader(xmlFile);
            reader = factory.createXMLStreamReader(stringReader);
        } catch (XMLStreamException e) {
            log.error(e);
        }
        try {
            while (reader.hasNext()) {
                int event = reader.next();
                switch (event) {

                    case XMLStreamConstants.START_DOCUMENT:
                        gemList = new ArrayList<>();
                        break;

                    case XMLStreamConstants.START_ELEMENT:
                        if ("gem".equals(reader.getLocalName())) {
                            gem = new Gem();
                            visualParameters = new VisualParameters();
                            gem.setId(reader.getAttributeValue(0));
                        }
                        if ("gems".equals(reader.getLocalName())) {
                            gemList = new ArrayList<>();
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        tagContent = reader.getText().trim();
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        switch (reader.getLocalName()) {
                            case "gem":
                                gemList.add(gem);
                                break;
                            case "name":
                                gem.setName(tagContent);
                                break;

                            case "preciousness":
                                if (tagContent.equals("PRECIOUS")) {
                                    gem.setPreciousness(Preciousness.PRECIOUS);
                                } else if (tagContent.equals("SEMIPRECIOUS")) {
                                    gem.setPreciousness(Preciousness.SEMIPRECIOUS);
                                }
                                break;
                            case "origin":
                                gem.setOrigin(tagContent);
                                break;
                            case "visualId":
                                visualParameters.setVisualId(tagContent);
                            case "color":
                                visualParameters.setColor(tagContent);
                                break;
                            case "transparency":
                                visualParameters.setTransparency(new Integer(tagContent));
                                break;
                            case "gemCutting":
                                visualParameters.setGemCutting(new Integer(tagContent));
                                break;
                            case "value":
                                gem.setValue(new Double(tagContent));
                                gem.setVisualParameters(visualParameters);
                                break;
                        }
                        break;
                }
            }
        } catch (XMLStreamException e) {
            log.error(e);
        }
        return new Diamond(gemList);
    }
}
