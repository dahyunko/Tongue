package com.example.demo.model.travel;

import lombok.Getter;

import java.util.List;

@Getter
public class TravelDto {
    private String travelId;
    private String userId;
    private String travelName;
    private int travelDay;
    private Boolean travelOwner;
    private List<TravelInfoDto> travelInfoDtoList;

    public TravelDto(String travelId, String userId, String travelName, List<TravelInfoDto> travelInfoDtoList, int travelDay, Boolean travelOwner) {
        this.travelId = travelId;
        this.userId = userId;
        this.travelName = travelName;
        this.travelInfoDtoList = travelInfoDtoList;
        this.travelDay = travelDay;
        this.travelOwner = travelOwner;
    }
    public TravelDto(String travelId, String travelName, String userId, int travelDay, Boolean travelOwner) {
        this.travelId = travelId;
        this.travelName = travelName;
        this.userId = userId;
        this.travelDay = travelDay;
        this.travelOwner = travelOwner;
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
