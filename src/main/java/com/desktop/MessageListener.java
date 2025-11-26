package com.desktop;

import org.json.JSONObject;

public interface MessageListener {
    void receiveMessage(JSONObject msgObj);
}