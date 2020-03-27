package com.example.amazonsync.Service;

import org.json.simple.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.*;

public interface IAmazonUtilService {

    public JSONObject getChannelConfig(String channelLoc);
    public String getTimeStamp(String channelLoc);
    public String getSiteId(String channelLoc);
    public String getApiUrl(String channelLoc);
    public String fetchAllOrders(String eachLoc);
    public String generateSignature(JSONObject channelConfig, Map<String, String> parameters);
    public String sign(String data, String secretKey) throws NoSuchAlgorithmException, InvalidKeyException, IllegalStateException, UnsupportedEncodingException;
    public String calculateStringToSignV2(Map<String, String> parameters, String serviceUrl)
            throws SignatureException, URISyntaxException;
}
