package com.safi.dairy.base;

import com.safi.dairy.base.domain.MessageType;
import com.safi.dairy.base.domain.News;
import com.safi.dairy.base.domain.NewsMessage;
import com.safi.dairy.base.domain.TextMessage;
import com.safi.dairy.base.utils.MessageUtil;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.*;

import static com.safi.dairy.base.domain.MessageType.NEWS;
import static com.safi.dairy.base.domain.MessageType.TEXT;

/**
 * Created by safi on 17/4/8.
 */
@Controller
@RequestMapping("/system")
public class SysController {
    private static final String token = "safi";

    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("user","safi");
        model.addAttribute("greeting","welcome");
        return "index";
    }

    @RequestMapping(value = "/weixin-check", method = RequestMethod.GET)
    public void checkWX(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        String[] arr = new String[]{token, timestamp, nonce};
        Arrays.sort(arr);

        StringBuffer result = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            result.append(arr[i]);
        }

        String temp = encode(result.toString());

        PrintWriter out = response.getWriter();
        if (temp.equals(signature))
            out.print(echostr);
    }

    @RequestMapping(value = "/weixin-check", method = RequestMethod.POST)
    public void dealWithWXMessage(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        System.out.println(session.getServletContext().getRealPath("/"));

        try {
            Map<String, String> map = MessageUtil.xmlToMap(request);
            String msgType = map.get("MsgType");
            String replyContent = "";
            MessageType messageType = TEXT;

            if (MessageUtil.TEXT.equals(msgType)) {
                String content = map.get("Content");

                if ("1".equals(content)) {
                    replyContent = "维护dota环境人人有责";
                } else if ("2".equals(content)) {
                    replyContent = "菜逼倪康";
                } else if ("3".equals(content)) {
                    replyContent = "彩笔倪康是如何被队友喷退的?";
                    messageType = NEWS;
                } else {
                    replyContent = "来，带你飞。。";
                }
            } else if (MessageUtil.EVENT.equals(msgType)) {
                String eventType = map.get("Event");
                if (MessageUtil.SUBSCRIBE.equals(eventType)) {
                    replyContent = "感谢关注本公众号\n" + "回复：\n" + "1.了解公约\n" + "2.学习怎么喷倪神\n" + "3.看具体教程\n";
                }
            }

            String message = getAimMessage(map, replyContent, messageType);
            out.print(message);
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    private String getAimMessage(Map<String, String> map, String replyContent, MessageType messageType) {
        String message = "";
        switch (messageType) {
            case TEXT:
                message = getTextXMLMessage(map, replyContent);
                break;
            case NEWS:
                message = getPicXMLMessage(map, replyContent);
                break;
        }
        return message;
    }

    /**
     * 获取文本微信消息
     *
     * @param map
     * @param replyContent
     * @return
     */
    private String getTextXMLMessage(Map<String, String> map, String replyContent) {
        String fromUserName = map.get("FromUserName");
        String toUserName = map.get("ToUserName");

        TextMessage textMessage = new TextMessage();
        textMessage.setFromUserName(toUserName);
        textMessage.setToUserName(fromUserName);
        textMessage.setCreateTime(new Date().getTime());
        textMessage.setContent(replyContent);
        textMessage.setMsgType(MessageUtil.TEXT);
        return MessageUtil.textMessageToXml(textMessage);
    }

    /**
     * 获取图文微信消息
     *
     * @param map
     * @param replyContent
     * @return
     */
    private String getPicXMLMessage(Map<String, String> map, String replyContent) {
        String fromUserName = map.get("FromUserName");
        String toUserName = map.get("ToUserName");

        List<News> newses = new ArrayList<>();
        News news = new News();
        news.setTitle("开启怒喷倪康之旅");
        news.setDescription(replyContent);
        news.setPicUrl("http://safi.static.ngrok.cc/dairy-static/image/ff.jpg");
        news.setUrl("http://safi.static.ngrok.cc/dairy/system/index");
        newses.add(news);

        NewsMessage newsMessage = new NewsMessage();
        newsMessage.setFromUserName(toUserName);
        newsMessage.setToUserName(fromUserName);
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setMsgType(MessageUtil.NEWS);
        newsMessage.setArticles(newses);
        newsMessage.setArticleCount(newses.size());

        return MessageUtil.newsMessageToXml(newsMessage);
    }

    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * Takes the raw bytes from the digest and formats them correct.
     *
     * @param bytes the raw bytes from the digest.
     * @return the formatted bytes.
     */
    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密文转换成十六进制的字符串形式
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }

    public static String encode(String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(str.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
