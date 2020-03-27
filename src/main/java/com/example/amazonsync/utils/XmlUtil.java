package com.example.amazonsync.utils;

import com.github.underscore.lodash.U;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;

public class XmlUtil {

    public static JSONObject xmlToJson(String xmlString) {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try {
            String json = U.xmlToJson(xmlString);
            System.out.println(json);
            jsonObject = (JSONObject) parser.parse(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
