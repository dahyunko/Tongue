package com.example.demo.model.magazine;

import com.example.demo.model.travel.TravelInfoDto;

import java.util.List;

public class MagazineDto {
    private String magazineId;
    private String userId;
    private String travelId;
    private String magazineTitle;
    private int reuseCnt;
    private int hit;
    private int like;
    private List<TravelInfoDto> travelInfoDtoList;
    private List<MagazineDetailDto> magazineDetailDtoList;

    public MagazineDto() {}

    public MagazineDto(String magazineId, String userId, String travelId){
        this.magazineId = magazineId;
        this.userId = userId;
        this.travelId = travelId;
    }


    @Override
    public String toString() {
        return "MagazineDto{" +
                "magazine_id='" + magazineId + '\'' +
                ", user_id='" + userId + '\'' +
                ", travel_id='" + travelId + '\'' +
                ", magazine_title='" + magazineTitle + '\'' +
                ", reuse_cnt=" + reuseCnt +
                ", hit=" + hit +
                ", like=" + like +
                ", travelInfoDtoList=" + travelInfoDtoList +
                ", magazineDetailDtoList=" + magazineDetailDtoList +
                '}';
    }
}
