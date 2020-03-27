package com.example.amazonsync.utils;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CommonUtil {

    public static boolean isEmpty(String value) {
        if (value != null && !value.isEmpty() && !value.equals("null")) {
            return false;
        }
        return true;
    }

    public static boolean isEmpty(Object value) {
        if (value == null) {
            return true;
        }

        if (value instanceof String) {
            return isEmpty((String) value);
        } // Add instance check here and handle separately
        // sslogger.debug("Empty check for Object " + value);

        if (value instanceof JSONArray) {
            return value == null || ((JSONArray) value).isEmpty();
        }

        if (value instanceof List) {
            return value == null || ((List) value).isEmpty();
        }

        if (value instanceof Map) {
            return value == null || ((Map) value).size() == 0;
        }

        if (value != null)
            return false;
        return true;
    }

    public static boolean isEmptyKey(Map sourcemap, String searchkey) {
        if (!CommonUtil.isEmpty(sourcemap)) {
            if (sourcemap.containsKey(searchkey)) {
                Object searchvalue = sourcemap.get(searchkey);
                return isEmpty(searchvalue);
            }
        }
        return true;
    }
}
