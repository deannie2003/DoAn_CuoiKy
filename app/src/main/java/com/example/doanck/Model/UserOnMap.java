package com.example.doanck.Model;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import org.osmdroid.util.GeoPoint;

public class UserOnMap {
    public static UserOnMap nearbyUsers = null;

    public static UserOnMap getNearbyUsers() {
        return nearbyUsers;
    }

    public static void setNearbyUsers(UserOnMap nearbyUsersRef) {
        nearbyUsers = nearbyUsersRef;
    }

    @SerializedName("id")
    public String idUserNearby;
    @SerializedName("version")
    public String versionUserNearby;
    @SerializedName("createdOn")
    public String createdOnUserNearby;
    @SerializedName("name")
    public String nameUserNearby;
    @SerializedName("accessPublicRead")
    public String accessPublicReadUserNearby;
    @SerializedName("parentID")
    public String parentIDUserNearby;
    @SerializedName("realm")
    public String realmUserNearby;
    @SerializedName("type")
    public String typeUserNearby;
    @SerializedName("path")
    public String pathUserNearby[];
    @SerializedName("attributes")
    public JsonObject attributesUserNearby;


    public String getIdUserNearby() {
        return idUserNearby;
    }

    public GeoPoint getLocation() {
        if (attributesUserNearby.has("location")) {
            JsonObject location = attributesUserNearby.getAsJsonObject("location");
            if (location.has("value")) {
                JsonObject value = location.getAsJsonObject("value");
                if (value.has("coordinates")) {
                    JsonArray coordinates = value.getAsJsonArray("coordinates");
                    if (coordinates.size() >= 2) {
                        double longitude = coordinates.get(0).getAsDouble();
                        double latitude = coordinates.get(1).getAsDouble();
                        return new GeoPoint(latitude, longitude);
                    }
                }
            }
        }
        return null; // Hoặc trả về điểm mặc định khác tùy theo logic của bạn
    }

    public String getEmail(){
        return String.valueOf(attributesUserNearby
                .getAsJsonObject("email")
                .get("value").getAsString());
    }


    public String getColourTemperature(){
        return String.valueOf(attributesUserNearby
                .getAsJsonObject("colourTemperature")
                .get("value").getAsInt());
    }

    public String getBrightness(){
        return String.valueOf(attributesUserNearby
                .getAsJsonObject("brightness")
                .get("value").getAsInt());
    }

    public String getOnOff(){
        return String.valueOf(attributesUserNearby
                .getAsJsonObject("onOff")
                .get("value").getAsBoolean());
    }


    public String getTemperature(){
        return String.valueOf(attributesUserNearby
                .getAsJsonObject("temperature")
                .get("value").getAsFloat());
    }

    public String getHumidity(){
        return String.valueOf(attributesUserNearby
                .getAsJsonObject("humidity")
                .get("value").getAsInt());
    }

    public String getPlace(){
        return String.valueOf(attributesUserNearby
                .getAsJsonObject("place")
                .get("value").getAsString());
    }

    public String getManufacturer(){
        return String.valueOf(attributesUserNearby
                .getAsJsonObject("manufacturer")
                .get("value").getAsString());
    }

    public String getWindDirection(){
        return String.valueOf(attributesUserNearby
                .getAsJsonObject("windDirection")
                .get("value").getAsInt());
    }

    public String getWindSpeed(){
        return String.valueOf(attributesUserNearby
                .getAsJsonObject("windSpeed")
                .get("value").getAsFloat());
    }

    public String getRainFall(){
        return String.valueOf(attributesUserNearby
                .getAsJsonObject("rainfall")
                .get("value").getAsFloat());
    }
}
