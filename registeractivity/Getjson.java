package com.rait.registeractivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Shree on 10/25/2016.
 */
public class Getjson {
    public static String[] fid_1;
    public static String[] name_1;
    public static final String JSON_ARRAY="result";
    public static final String fid = "fid";
    public static final String name = "name";
    private String json;
    private JSONArray urls;
    public Getjson(String json){
        this.json = json;
        try {
            JSONObject jsonObject = new JSONObject(json);
            urls = jsonObject.getJSONArray(JSON_ARRAY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getAllImages() throws JSONException {
        name_1 = new String[urls.length()];
        fid_1 = new String[urls.length()];
        for(int i=0;i<urls.length();i++)
        { name_1[i]= urls.getJSONObject(i).getString(name);
            fid_1[i] = urls.getJSONObject(i).getString(fid);
            JSONObject jsonObject = urls.getJSONObject(i);
        }
    }
}