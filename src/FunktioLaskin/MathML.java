package FunktioLaskin;

//import net.sourceforge.jeuclid.parser.Parser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * FunktioLaskin
 *
 * @author diftraku
 * @package FunktioLaskin
 * @copyright Copyright (c) 2016, Diftraku
 * @license https://opensource.org/licenses/MIT The MIT License (MIT)
 */

public class MathML {
    public static StringBuilder sb = new StringBuilder();
    public static void test() throws IOException, SAXException, TransformerException {
        /*Parser mlParser = Parser.getInstance();
        String test = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "  <!DOCTYPE math PUBLIC \"-//W3C//DTD MathML 2.0//EN\"\n" +
                "           \"http://www.w3.org/Math/DTD/mathml2/mathml2.dtd\">\n" +
                "  <math xmlns=\"http://www.w3.org/1998/Math/MathML\">\n" +
                "    <apply asd=\"tissit\">\n" +
                "        <plus/>\n" +
                "        <cn>1</cn>\n" +
                "        <apply>\n" +
                "            <minus/>" +
                "            <cn>3</cn>" +
                "            <cn>1</cn>" +
                "        </apply>\n" +
                "    </apply>\n" +
                "</math>";
        StreamSource xml = new StreamSource(new StringReader(test));
        Document mlDoc = mlParser.parseStreamSource(xml);
        Element mlElement = mlDoc.getDocumentElement();
        if (mlElement.hasChildNodes()) {
            Node node = mlElement.getFirstChild();
            // Iterate over the root
            do {
                node = node.getNextSibling();
                // Ignore everything but actual elements
                // This is a huge caveat, since Java considers the whitespace as a node
                if (node instanceof Element) {
                    // Usually this would be apply
                    processNode(node.getFirstChild());
                }
            } while (node != null);
        }

        /*processNode(mlElement);
        System.out.println(new String(sb));*/
    }

    private static String processNode(Node node) {
        Node sibling = node;
        Element tag;
        ArrayList<String> params = new ArrayList<>();
        String output = "", operator = "";
        // Inside an apply node
        do {
            if (sibling instanceof Element) {
                tag = (Element) sibling;
                String tagName = tag.getTagName();
                // Check if the sibling is a nested node
                if (tagName.equals("apply")) {
                    params.add("("+processNode(sibling.getFirstChild())+")");
                }
                else {
                    if (tagName.equals("cn")) {
                        // We're at a param
                        params.add(tag.getTextContent());
                    }
                    System.out.println(tagName);
                    if (tagName.equals("plus") || tagName.equals("minus")) {
                        System.out.println(tagName);
                        // We're an operator
                        operator = tag.getTextContent();
                    }
                }
            }
            // Get the next available sibling
            sibling = sibling.getNextSibling();
        } while (sibling != null);
        if (params.size() == 2) {
            System.out.println(params.get(0)+operator+params.get(1));
            return params.get(0)+operator+params.get(1);
        }
        else {
            return "paskaa";
        }
    }



    /*private static void processNode(Node node) {
        Node nextSibling = node.getNextSibling();
        String tagName, operator, output = "";
        Element tag;
        // Check if the node is an Element
        if (node instanceof Element) {
            // Found a node, check how to handle it
            tag = (Element) node;
            tagName = tag.getTagName();
            if ((tagName.equals("math") || tagName.equals("apply")) && node.hasChildNodes()) {
                processNode(node.getFirstChild());
            }
        }
        // Do we have a sibling to go to?
        if (nextSibling != null) {
            // Step through to next sibling
            processNode(nextSibling);
        }
    }*/

    public static void processSiblings(Node node) {
        String output = "";
        Node sibling = node.getNextSibling();
        int parameterCount = 0;
        do {
            if (sibling != null) {
                if (sibling instanceof Element) {
                    Element tag = (Element) sibling;
                    String tagName = tag.getTagName();
                    if (tagName.equals("cn")) {
                        parameterCount++;
                        if (parameterCount == 1) {
                            output += tag.getTextContent();
                        }
                        else if (parameterCount == 2) {
                            sb.append(tag.getTextContent());
                        }
                    }
                    if (tagName.equals("apply")) {
                        //output += "("+processNode(tag)+")";
                    }
                }
                // Get ready for another round
                sibling = sibling.getNextSibling();
            }
        } while (sibling != null);
    }
}
