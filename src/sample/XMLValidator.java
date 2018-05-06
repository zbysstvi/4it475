package sample;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.InputStream;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;

public class XMLValidator {

    /**
     * Method containing the logic for validation of an XML file against an XSD schema.
     * @param xmlPath path to the xml file
     * @param xsdPath path to the xsd schema file
     * @return validation result message
     */
    public static String Validate (String xmlPath, String xsdPath) {

        String result;
        Source xmlFile = new StreamSource(new File(xmlPath));
        File schemaFile = new File(xsdPath);

        SchemaFactory schemaFactory = SchemaFactory
            .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        try {
            Schema schema = schemaFactory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            validator.validate(xmlFile);
            result = xmlPath.substring(xmlPath.lastIndexOf("\\")+1) + " is valid against schema " + schemaFile.getName();

        }
        catch (SAXException e) {
            result = xmlPath.substring(xmlPath.lastIndexOf("\\")+1) + " is NOT valid against " + schemaFile.getName() + ", \nreason:" + e;
        }
        catch (IOException e) {
            result = "Error. Try again.";
        }
        return result;
}
}
