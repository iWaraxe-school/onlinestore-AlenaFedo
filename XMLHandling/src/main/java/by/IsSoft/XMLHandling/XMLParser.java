package by.IsSoft.XMLHandling;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class XMLParser {

    public static Map<String, Enum.SortingType> GetSortingOrder()  {

        try{

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        File file = new File("./XMLHandling/src/main/resources/config.xml");
        if (file.exists()) {
            Map<String, Enum.SortingType> map = new LinkedHashMap<>();
            Document doc = db.parse(file);

            NodeList nodeList = doc.getElementsByTagName("sort");

            Node nodeSort = nodeList.item(0);

            if (nodeSort.getNodeType() != Node.TEXT_NODE) {

            NodeList props = nodeSort.getChildNodes();

            for(int j = 0; j < props.getLength(); j++) {
                Node prop = props.item(j);
                if (prop.getNodeType() != Node.TEXT_NODE){
                    map.put(prop.getNodeName(), Enum.SortingType.valueOf(prop.getTextContent()));
            }}}
            return map;
        }}
        catch (ParserConfigurationException | IOException | SAXException  e){
            e.printStackTrace();
        }
        return null;
    }


}