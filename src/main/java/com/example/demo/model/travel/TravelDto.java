package com.example.demo.model.travel;

import lombok.Getter;

import java.util.List;

@Getter
public class TravelDto {
    private String travelId;
    private String userId;
    private String travelName;
    private List<TravelInfoDto> travelInfoDtoList;

    public TravelDto(String travelId, String userId, String travelName, List<TravelInfoDto> travelInfoDtoList) {
        this.travelId = travelId;
        this.userId = userId;
        this.travelName = travelName;
        this.travelInfoDtoList = travelInfoDtoList;
    }
    public TravelDto(String travelId, String travelName, String userId) {
        this.travelId = travelId;
        this.travelName = travelName;
        this.userId = userId;
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
