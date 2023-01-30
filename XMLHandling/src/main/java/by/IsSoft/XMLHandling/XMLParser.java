package by.IsSoft.XMLHandling;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class XMLParser {

    public static Map<String, Enum.SortingType> GetSortingorder()  {

        try{

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        File file = new File("./XMLHandling/src/main/resources/config.xml");
        if (file.exists()) {
            Map<String, Enum.SortingType> map = new LinkedHashMap<>();
            Document doc = db.parse(file);

            String name = doc.getElementsByTagName("name").item(0).getTextContent();
            String price = doc.getElementsByTagName("price").item(0).getTextContent();
            String rate = doc.getElementsByTagName("rate").item(0).getTextContent();

            map.put("name", Enum.SortingType.valueOf(name));
            map.put("price", Enum.SortingType.valueOf(price));
            map.put("rate", Enum.SortingType.valueOf(rate));

            return map;
        }}
        catch (ParserConfigurationException | IOException | SAXException  e){
            e.printStackTrace();
        }
        return null;
    }
}