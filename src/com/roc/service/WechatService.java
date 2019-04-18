package com.roc.service;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.sax.SAXResult;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WechatService {
    private static final String TOKEN = "roc";
    public static boolean check(String timestamp, String nonce, String signature) {
        String[] strs = new String[] {TOKEN, timestamp, nonce};
        Arrays.sort(strs);

        String str = strs[0] +strs[1] + strs[2];
        String mysig = sha1(str);
        System.out.println(mysig);
        System.out.println(signature);
        return mysig.equalsIgnoreCase(signature);
    }

    private static String sha1(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("sha1");
            byte[] digest = md.digest(str.getBytes());
            char[] chars={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
            StringBuilder sb = new StringBuilder();
            for(byte b : digest) {
                sb.append(chars[(b>>4)&15]);
                sb.append(chars[b&15]);
            }
            return sb.toString();
        }
        catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, String> parseRequest(InputStream is) {
        Map<String, String> map = new HashMap<>();
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(is);
            Element root = document.getRootElement();
            List<Element> elements = root.elements();
            for(Element e : elements) {
                map.put(e.getName(), e.getStringValue());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static String getResponse(Map<String, String> requestMap) {
        String msgType = requestMap.get("MsgType");
        switch (msgType) {
            case "text":
                break;
            case "image":
                break;
            case "voice":
                break;
            case "video":
                break;
            case "shortvideo":
                break;
            case "location":
                break;
            case "link":
                break;
            default:
                break;
        }
        return null;
    }
}
