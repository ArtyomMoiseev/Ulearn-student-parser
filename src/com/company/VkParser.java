package com.company;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.users.Fields;
import org.json.JSONObject;

import java.util.HashMap;

public abstract class VkParser {

    public static String getStringIfExist(JSONObject json, String key) {
        if (json != null && json.has(key)) {
            return json.getString(key);
        }
        else
            return null;
    }

    public static JSONObject getJsonObjectIfExist(JSONObject json, String key) {
        if (json.has(key)) {
            return json.getJSONObject(key);
        }
        else
            return null;
    }



    public static HashMap<String, VkData> parse() throws Exception {
        TransportClient transportClient = HttpTransportClient.getInstance();
        VkApiClient vk = new VkApiClient(transportClient);
        UserActor actor = new UserActor(vkConst.userId, vkConst.token);
        var fields = new Fields[] {Fields.PERSONAL, Fields.PHOTO_MAX_ORIG, Fields.ABOUT,Fields.PHOTO_50, Fields.CITY, Fields.BDATE, Fields.NICKNAME, Fields.CONTACTS, Fields.CAREER, Fields.SEX};

        var jsonString = vk.groups()
                .getMembers(actor)
                .groupId(vkConst.groupId)
                .count(1000).offset(0)
                .fields(fields)
                .executeAsString();

        int count = new JSONObject(jsonString)
                .getJSONObject("response")
                .getInt("count") / 1000 + 1;

        var dict = new HashMap<String, VkData>();
        for (var c = 0; c <= count; c++) {
            if (c != 0) {

            }
            var json = new JSONObject(jsonString)
                    .getJSONObject("response")
                    .getJSONArray("items");

            jsonString = vk.groups()
                    .getMembers(actor)
                    .groupId(vkConst.groupId)
                    .count(1000).offset(c * 1000)
                    .fields(fields)
                    .executeAsString();

        for (var i = 0; i < json.length(); i++) {
            var jObject = json.getJSONObject(i);
            var name = jObject.getString("first_name");
            var lastname = jObject.getString("last_name");
            var sex = jObject.getInt("sex");
            var id = jObject.getInt("id");
            var bigPhotoUrl = jObject.getString("photo_max_orig");
            var smallPhotoUrl = jObject.getString("photo_50");
            var city = getStringIfExist(getJsonObjectIfExist(jObject, "city"), "title");
            var bDate = getStringIfExist(jObject, "bdate");
            var nickname = getStringIfExist(jObject, "nickname");
            var record = new VkData(id, name, lastname,sex, bigPhotoUrl, smallPhotoUrl, city, bDate);
            dict.put(name + " " + lastname, record);
        }
        }
        return dict;
    }
}
