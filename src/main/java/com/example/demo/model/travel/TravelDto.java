package com.example.demo.model.travel;

import lombok.Getter;

import java.util.List;

@Getter
public class TravelDto {
    private String travelId;
    private String userId;
    private String travelName;
    private int travelDay;
    private List<TravelInfoDto> travelInfoDtoList;

    public TravelDto(String travelId, String userId, String travelName, List<TravelInfoDto> travelInfoDtoList, int travelDay) {
        this.travelId = travelId;
        this.userId = userId;
        this.travelName = travelName;
        this.travelInfoDtoList = travelInfoDtoList;
        this.travelDay = travelDay;
    }
    public TravelDto(String travelId, String travelName, String userId, int travelDay) {
        this.travelId = travelId;
        this.travelName = travelName;
        this.userId = userId;
        this.travelDay = travelDay;
    }

    @Override
    public String toString() {
        return "TravelDto{" +
                "travelId='" + travelId + '\'' +
                ", userId='" + userId + '\'' +
                ", travelName='" + travelName + '\'' +
                ", travelInfoDtoList=" + travelInfoDtoList +
                '}';
    }
}
