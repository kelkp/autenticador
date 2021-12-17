package com.company.models;

import com.company.constants.ClientConstants;
import org.json.simple.JSONObject;

import java.io.Serializable;

public class StartsReq implements Serializable {

    public JSONObject generateJsonBody() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(ClientConstants.TYPE, ClientConstants.GET_CANNONS);
        jsonObject.put(ClientConstants.AUTH, ClientConstants.AUTH_REQ);
        return jsonObject;
    }
}
