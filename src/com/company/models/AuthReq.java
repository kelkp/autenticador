package com.company.models;

import com.company.constants.ClientConstants;
import org.json.simple.JSONObject;

import java.io.Serializable;

public class AuthReq implements Serializable {


    public JSONObject generateJsonBody() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", ClientConstants.AUTH_REQ_TYPE);
        jsonObject.put("auth", ClientConstants.AUTH_REQ);
        return jsonObject;
    }

}

