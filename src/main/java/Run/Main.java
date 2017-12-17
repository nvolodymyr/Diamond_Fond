package Run;

import Entity.Diamond;
import JDBSConection.ConnectionToCustomDataBase;
import JDBSConection.JdbsInputData;
import Parsers.DomParser;
import Parsers.SaxParser;
import Utility.Validol;
import Utility.XmlToHtml;
import org.apache.log4j.Logger;

import java.io.File;

public class Main {
    static Logger log = Logger.getLogger(Main.class);

    private static final String XML_FILE_PATH = "src/main/resources/Diamonds.xml";
    private static final String XSD_FILE_PATH = "src/main/resources/Schema.xsd";
    private static final String XSL_FILE_PATH = "src/main/resources/Diamonds.xsl";

    public static void main(String[] args) {
        File XmlFile = new File(XML_FILE_PATH);
        boolean isValid = Validol.validate(XML_FILE_PATH, XSD_FILE_PATH);
        log.info("XML is validly? " + isValid);
        Diamond domparser = DomParser.parse(XmlFile);
        log.info("dom parser: " + domparser);
             Diamond diamondSaxParser = SaxParser.parser(XmlFile);
        log.info("SaxParser for Diamonds: " + diamondSaxParser);
               Diamond staxParser = SaxParser.parser(XmlFile);
        log.info("StaxParser for Diamonds: " + staxParser);
        ConnectionToCustomDataBase.getConnection();
        JdbsInputData.executeTwoPart(XML_FILE_PATH);
        XmlToHtml.transformToHTML(XSL_FILE_PATH, XML_FILE_PATH);


    }
}
