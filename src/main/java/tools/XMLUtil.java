package tools;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.*;

/**
 * XML文件读取工具类
 * 
 * @author Evsward
 *
 */
public class XMLUtil {
    private static final Logger logger = LogManager.getLogger();

    /**
     * 通过xml文件里的tagName搜索，获得其内部的值
     * 
     * @param tagName
     * @return
     */
    public static Object getBean(String tagName) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File("config/config.xml"));
            
            NodeList nl = doc.getElementsByTagName(tagName);
            Node n = nl.item(0).getFirstChild();
            String className = n.getNodeValue();
            logger.info("class: " + className);
            Object c = Class.forName(className).newInstance();
            return c;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
