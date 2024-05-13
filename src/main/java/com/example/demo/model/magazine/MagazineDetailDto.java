package com.example.demo.model.magazine;

import com.example.demo.model.travel.PlaceDto;
import com.example.demo.model.travel.TravelInfoDto;
import lombok.Getter;

@Getter
public class MagazineDetailDto {
    private String magazine_detail_id;
    private String magazine_id;
    private String travel_info_id;

    private String content;
    private String img;

    private MagazineDto magazineDto;
    private TravelInfoDto travelInfoDto;
    private PlaceDto placeDto;

    public MagazineDetailDto(String magazine_detail_id, String magazine_id, String travel_info_id){
        this.magazine_detail_id = magazine_detail_id;
        this.magazine_id = magazine_id;
        this.travel_info_id = travel_info_id;
    }

    public MagazineDetailDto(String content, String img){
        this.content = content;
        this.img = img;
    }

    @Override
    public String toString() {
        return "MagazineDetailDto{" +
                "magazine_detail_id='" + magazine_detail_id + '\'' +
                ", magazine_id='" + magazine_id + '\'' +
                ", travel_info_id='" + travel_info_id + '\'' +
                ", content='" + content + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
