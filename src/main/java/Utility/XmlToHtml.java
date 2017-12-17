package Utility;

import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XmlToHtml {
    private static final String XML_FILE_PATH = "src/main/resources/Diamonds.xml";
     private static final String XSL_FILE_PATH = "src/main/resources/Diamonds.xsl";

    public static void transformToHTML(String xslFilePath, String xmlFilePath) {
        try {
            TransformerFactory tFact = TransformerFactory.newInstance();
            javax.xml.transform.Transformer transformer = tFact
                    .newTransformer(new StreamSource(XSL_FILE_PATH));
            transformer.transform(new StreamSource(XML_FILE_PATH), new StreamResult("src/main/resources/Diamonds.html"));
            System.out.println("Transformation to HTML is successful.");
        } catch (TransformerException e) {
            System.out.println("Transformation to HTML is not successful.");
        }
    }
}
