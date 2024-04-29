package com.example.demo.model.travel;

import lombok.Getter;

@Getter
public class PlaceDto {
    private String placeId;
    private String loc;
    private String des;
    private int cost;
    private int transportTime;
    private String transport;
    private String lat;
    private String lon;
    private TravelInfoDto travelInfoDto;

    public PlaceDto(String placeId, String loc, String des, int cost, String transport, String lat, String lon) {
        this.placeId = placeId;
        this.loc = loc;
        this.des = des;
        this.cost = cost;
        this.transport = transport;
        this.lat = lat;
        this.lon = lon;
    }
}
