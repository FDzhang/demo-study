package com.example.demokafka.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Track {
    private String id;
    private String thingCode;
    private String source;
    private String cityCode;
    private String cityName;
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private Date time;
    private String place;
    private String category;
    private String machineIp;
    private String features;
    private String featuresCode;
    private String duration;
    private String unionCode;
    private Date createTime;
    private String startTime;
    private String endTime;
    private String version;
    private String mode;
    private String longitude;
    private String latitude;
    private String platform;
    private Date updateTime;
    private String phoneNumber;

    public Track() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCityCode() {
        return this.cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Date getTime() {
        return this.time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getThingCode() {
        return this.thingCode;
    }

    public void setThingCode(String thingCode) {
        this.thingCode = thingCode;
    }

    public String getUnionCode() {
        return this.unionCode;
    }

    public void setUnionCode(String unionCode) {
        this.unionCode = unionCode;
    }

    public String getPlace() {
        return this.place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMachineIp() {
        return this.machineIp;
    }

    public void setMachineIp(String machineIp) {
        this.machineIp = machineIp;
    }

    public String getFeatures() {
        return this.features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getFeaturesCode() {
        return this.featuresCode;
    }

    public void setFeaturesCode(String featuresCode) {
        this.featuresCode = featuresCode;
    }

    public String getDuration() {
        return this.duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMode() {
        return this.mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getPlatform() {
        return this.platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    @Override
    public String toString() {
        return "UserTrack{id='" + this.id + '\'' + ", thingCode='" + this.thingCode + '\'' + ", source='" + this.source + '\'' + ", cityCode='" + this.cityCode + '\'' + ", cityName='" + this.cityName + '\'' + ", time=" + this.time + ", place='" + this.place + '\'' + ", category='" + this.category + '\'' + ", machineIp='" + this.machineIp + '\'' + ", features='" + this.features + '\'' + ", featuresCode='" + this.featuresCode + '\'' + ", duration='" + this.duration + '\'' + ", unionCode='" + this.unionCode + '\'' + ", createTime=" + this.createTime + ", startTime='" + this.startTime + '\'' + ", endTime='" + this.endTime + '\'' + ", version='" + this.version + '\'' + ", mode='" + this.mode + '\'' + ", longitude='" + this.longitude + '\'' + ", latitude='" + this.latitude + '\'' + ", platform='" + this.platform + '\'' + ", updateTime=" + this.updateTime + ", phoneNumber='" + this.phoneNumber + '\'' + '}';
    }
}
