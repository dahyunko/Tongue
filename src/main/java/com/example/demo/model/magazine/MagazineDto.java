package com.example.demo.model.magazine;

import com.example.demo.model.travel.TravelInfoDto;

import java.util.List;

public class MagazineDto {
    private String magazine_id;
    private String user_id;
    private String travel_id;
    private String magazine_title;
    private int reuse_cnt;
    private int hit;
    private int like;
    private List<TravelInfoDto> travelInfoDtoList;
    private List<MagazineDetailDto> magazineDetailDtoList;

    public MagazineDto(String magazine_id, String user_id, String travel_id){
        this.magazine_id = magazine_id;
        this.user_id = user_id;
        this.travel_id = travel_id;
    }

    @Override
    public String toString() {
        return "MagazineDto{" +
                "magazine_id='" + magazine_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", travel_id='" + travel_id + '\'' +
                ", magazine_title='" + magazine_title + '\'' +
                ", reuse_cnt=" + reuse_cnt +
                ", hit=" + hit +
                ", like=" + like +
                ", travelInfoDtoList=" + travelInfoDtoList +
                ", magazineDetailDtoList=" + magazineDetailDtoList +
                '}';
    }
}
