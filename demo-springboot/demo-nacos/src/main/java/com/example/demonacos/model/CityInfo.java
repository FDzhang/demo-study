package com.example.demonacos.model;

/**
 * @author ：zxq
 * @date ：Created in 2020/4/27 14:50
 */

public class CityInfo {

    private String cityName;
    private String cityUrl;
    private String AppId;
    private String AppKey;

    public CityInfo() {
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityUrl() {
        return cityUrl;
    }

    public void setCityUrl(String cityUrl) {
        this.cityUrl = cityUrl;
    }

    public String getAppId() {
        return AppId;
    }

    public void setAppId(String appId) {
        AppId = appId;
    }

    public String getAppKey() {
        return AppKey;
    }

    public void setAppKey(String appKey) {
        AppKey = appKey;
    }

    @Override
    public String toString() {
        return "CityInfo{" +
                "cityName='" + cityName + '\'' +
                ", cityUrl='" + cityUrl + '\'' +
                ", AppId='" + AppId + '\'' +
                ", AppKey='" + AppKey + '\'' +
                '}';
    }
}
