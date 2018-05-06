package sample;

import jdk.nashorn.internal.runtime.regexp.JoniRegExp;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class XMLTransformator {


    /**
     * Method containing XSL transformation logic.
     * @param xmlPath path to the xml file
     * @param xsltPath path to  the xls stylesheet
     * @param resultFile the file in which the finished transformation will be saved
     * @return transformation result message
     */

        public static String Transform(String xmlPath, String xsltPath, File resultFile) {

            String result;

            try {
                TransformerFactory factory = TransformerFactory.newInstance();
                Source xslt = new StreamSource(new File(xsltPath));
                Transformer transformer = factory.newTransformer(xslt);

                Source text = new StreamSource(new File(xmlPath));
                transformer.transform(text, new StreamResult(resultFile));
                Desktop.getDesktop().open(resultFile);
                result = "Transformation successful.";
            }
            catch (TransformerException e)  {
                result = "Transformation failed.";
            }
            catch (Exception e)  {
                result = "Error. Try again.";
            }
         return result;
        }
    }
