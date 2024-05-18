package com.example.demo.model.travel;

import lombok.Getter;

@Getter
public class TmapTravelDto {
    private int day;
    private String loc;
    private String transport;
    private String des;
    private int cost;
    private String lat;
    private String lon;
    private String address;

    @Override
    public String toString() {
        return "TmapTravelDto{" +
                "day='" + day + '\'' +
                ", loc='" + loc + '\'' +
                ", transport='" + transport + '\'' +
                ", des='" + des + '\'' +
                ", cost=" + cost +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                '}';
    }
}
/*
        "day": "1일차",
        "loc": "동궁과 월지",
        "transport": "버스",
        "des": "신라 왕실의 별궁과 정원",
        "cost": 1000,
        "lat": 35.83480513670537,
        "lon": 129.22656376946412

* */