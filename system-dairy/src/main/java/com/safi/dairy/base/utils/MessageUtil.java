package com.safi.dairy.base.utils;

import com.safi.dairy.base.domain.News;
import com.safi.dairy.base.domain.NewsMessage;
import com.safi.dairy.base.domain.TextMessage;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by safi on 17/4/8.
 */
public class MessageUtil {
    public static final String TEXT = "text";
    public static final String NEWS = "news";
    public static final String IMAGE = "image";
    public static final String VOICE = "voice";
    public static final String VIDEO = "video";
    public static final String LINK = "link";
    public static final String LOCATION = "location";
    public static final String EVENT = "event";
    public static final String SUBSCRIBE = "subscribe";
    public static final String UNSUBSCRIBE = "unsubscribe";
    public static final String CLICK = "CLICK";
    public static final String VIEW = "VIEW";

    public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {
        Map<String, String> map = new HashMap<String, String>();
        SAXReader reader = new SAXReader();

        InputStream ins = request.getInputStream();
        Document doc = reader.read(ins);
        Element root = doc.getRootElement();
        List<Element> elements = root.elements();
        elements.forEach(e -> map.put(e.getName(), e.getText()));

        ins.close();

        return map;
    }

    public static String textMessageToXml(TextMessage textMessage) {
        XStream xStream = new XStream();
        xStream.alias("xml", textMessage.getClass());
        return xStream.toXML(textMessage);
    }

    public static String newsMessageToXml(NewsMessage newsMessage) {
        XStream xStream = new XStream();
        xStream.alias("xml", newsMessage.getClass());
        xStream.alias("item", new News().getClass());
        return xStream.toXML(newsMessage);
    }
}
