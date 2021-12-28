package com.company;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.users.Fields;
import org.apache.commons.collections.functors.ExceptionPredicate;
import org.json.JSONObject;

import java.util.ArrayList;
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

    public static ArrayList<Student> joinVkStudent(ArrayList<Student> students) {
        try {
            var vkData = parse();
            for (var s: students) {
                if (vkData.get(s.getFirstName() + " " + s.getLastName()) != null) {
                    var d = vkData.get(s.getFirstName() + " " + s.getLastName());
                    s.setVkData(d.getId(), d.getBigPhotoUrl(), d.getSmallPhotoUrl(), d.getCity(), d.getbDate(), d.getSex());
                }
            }

        }
        catch (Exception e) {
            System.out.println("Vk parse failure");
            e.printStackTrace();
        }
        return students;

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

class VkData {
    private int id;
    private String name;
    private String lastName;
    private String bigPhotoUrl;
    private String smallPhotoUrl;
    private String city;
    private String bDate;
    private int sex = 0;


    public VkData(int id, String name, String lastName, int sex, String bigPhotoUrl, String smallPhotoUrl, String city, String bDate) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.sex = sex;
        this.bigPhotoUrl = bigPhotoUrl;
        this.smallPhotoUrl = smallPhotoUrl;
        this.city = city;
        this.bDate = bDate;
    }

    public int getId() {
        return id;
    }

    public String getBigPhotoUrl() {
        return bigPhotoUrl;
    }

    public int getSex() {
        return sex;
    }

    public String getCity() {
        return city;
    }

    public String getSmallPhotoUrl() {
        return smallPhotoUrl;
    }

    public String getbDate() {
        return bDate;
    }

    @Override
    public String toString() {
        return id + " " + sex + " " + bigPhotoUrl + " " + city + " " + bDate;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

}
