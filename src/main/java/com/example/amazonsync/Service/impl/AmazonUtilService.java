package com.example.amazonsync.Service.impl;

import com.example.amazonsync.AmazonSync.ImportAmazonDataService;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AmazonUtilService extends ImportAmazonDataService {

    public static JSONObject getChannelConfig(String channelLoc) {
        JSONObject channelConfig = new JSONObject();

        channelConfig.put("awsaccesskeyid", "");
        channelConfig.put("sellerauthtoken", "");
        channelConfig.put("sellerid", "");
        channelConfig.put("signaturemethod", "HmacSHA256");
        channelConfig.put("signatureversion", "2");
        channelConfig.put("secretkey", "");
        channelConfig.put("marketplaceID", getSiteId(channelLoc));
        channelConfig.put("apiurl", getApiUrl(channelLoc));
        channelConfig.put("timeStamp", getTimeStamp(channelLoc));

        return channelConfig;
    }

    public static String getTimeStamp(String channelLoc) {
        try {
            LinkedHashMap<String, String> timeZoneMap = new LinkedHashMap<>();
            timeZoneMap.put("MX", "UTU-08:00");
            timeZoneMap.put("CA", "UTU-08:00");
            timeZoneMap.put("US", "UTU-11:00");

            return timeZoneMap.get(channelLoc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getSiteId(String channelLoc) {
        String marketplaceID = null;
        try {
            Map<String, String> locationname = new HashMap<String, String>();
            locationname.put("US", "ATVPDKIKX0DER");
            locationname.put("MX", "A1AM78C64UM0Y8");
            locationname.put("CA", "A2EUQ1WTGCTBG2");

            marketplaceID = locationname.get(channelLoc);
            System.out.println("Marketplace ID : " + marketplaceID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return marketplaceID;
    }

    public static String getApiUrl(String channelLoc) {
        String channelApiUrl = null;
        try {
            Map<String, String> apiURL = new HashMap<String, String>();
            apiURL.put("US", "mws.amazonservices.com");
            apiURL.put("MX", "mws.amazonservices.com.mx");
            apiURL.put("CA", "mws.amazonservices.ca");

            channelApiUrl = apiURL.get(channelLoc);
            System.out.println("Marketplace API URL : " + channelApiUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return channelApiUrl;
    }

    public static String getDateFormat(String format, Long millis, String zone) {
        TimeZone timeZone = TimeZone.getTimeZone(zone);
        DateFormat ISO_8601_DATE_TIME = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        ISO_8601_DATE_TIME.setTimeZone(timeZone);
        return ISO_8601_DATE_TIME.format(new Date(millis));
    }

    public static String urlEncode(String rawValue) {
        String value = rawValue == null ? "" : rawValue;
        String encoded = null;
        try {
            encoded = URLEncoder.encode(value, "UTF-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
        } catch (UnsupportedEncodingException e) {
            System.err.println("Unknown encoding: UTF-8");
            e.printStackTrace();
        }
        return encoded;
    }

    public static String generateSignature(JSONObject channelConfig, Map<String, String> parameters) {
        String signature = null;
        try {
            String secretkey = (String) channelConfig.get("secretkey");
            String amazonhost = "https://" + (String) channelConfig.get("apiurl") + "/Orders/2013-09-01";
            String apiurl = amazonhost + "?";
            String data = calculateStringToSignV2(parameters, amazonhost);
            System.out.println("String to sign " + data);
            signature = sign(data, secretkey);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return signature;
    }

    public static String sign(String data, String secretKey) throws NoSuchAlgorithmException, InvalidKeyException, IllegalStateException, UnsupportedEncodingException {
        byte[] signature = null;
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secretKey.getBytes(), "HmacSHA256"));
            signature = Base64.encodeBase64(mac.doFinal(data.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(signature);
    }

    public static String calculateStringToSignV2(Map<String, String> parameters, String serviceUrl)
            throws SignatureException, URISyntaxException {
        Map<String, String> sorted = new TreeMap();
        sorted.putAll(parameters);

        StringBuilder data = new StringBuilder();
        data.append("POST");
        data.append("\n");
        URI endpoint = null;
        try {
            endpoint = new URI(serviceUrl);
        } catch (URISyntaxException ex) {
            // sslogger.error("ERRYLI:URI Syntax Exception", ex);
            throw new SignatureException("URI Syntax Exception thrown while constructing string to sign", ex);
        }
        data.append(endpoint.getHost());
        data.append("\n");
        String uri = endpoint.getPath();
        if ((uri == null) || (uri.length() == 0)) {
            uri = "/";
        }
        data.append(uri);
        data.append("\n");

        Iterator<Map.Entry<String, String>> pairs = sorted.entrySet().iterator();
        while (pairs.hasNext()) {
            Map.Entry<String, String> pair = (Map.Entry) pairs.next();
            String key = (String) pair.getKey();
            data.append(urlEncode(key));
            data.append("=");
            String value = (String) pair.getValue();
            data.append(value);
            if (pairs.hasNext()) {
                data.append("&");
            }
        }
        return data.toString();
    }
}
