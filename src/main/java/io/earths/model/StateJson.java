package io.earths.model;

import java.util.List;

public class StateJson {
    String name;
    String code;
    String latitude;
    String longitude;

    List<CityJson> cities;

    public List<CityJson> getCities() {
        return cities;
    }

    public void setCities(List<CityJson> cities) {
        this.cities = cities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
