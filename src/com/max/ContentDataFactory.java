package com.max;

import com.google.gson.Gson;

public class ContentDataFactory {
    public static ContentData getContentData(String json) {
        Gson g = new Gson();
        ContentData data = g.fromJson(json, ContentData.class);
        return data;
    }
}
