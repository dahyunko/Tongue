package com.example.demo.model.travel;

import lombok.Getter;

@Getter
public class TravelInfoDto {
    private String travelInfoId;
    private String day;
    private int order;
    private TravelDto travelDto;
    private PlaceDto placeDto;
    private String placeId;

    public TravelInfoDto(String travelInfoId, String day, int order, PlaceDto placeDto) {
        this.travelInfoId = travelInfoId;
        this.day =day;
        this.order = order;
        this.placeDto = placeDto;
    }

    @Override
    public String toString() {
        return "TravelInfoDto{" +
                "travelInfoId='" + travelInfoId + '\'' +
                ", day='" + day + '\'' +
                ", order=" + order +
                ", travelDto=" + travelDto +
                ", placeDto=" + placeDto +
                '}';
    }

    public TravelInfoDto(String travelInfoId, String day, int order, TravelDto travelDto , PlaceDto placeDto) {
        this.travelInfoId = travelInfoId;
        this.day = day;
        this.order = order;
        this.travelDto = travelDto;
        this.placeDto = placeDto;
    }

    public TravelInfoDto(String travelInfoId, String day, int order, String placeId) {
        this.travelInfoId = travelInfoId;
        this.day = day;
        this.order = order;
        this.placeId = placeId;
    }
}
