package com.example.demo.model.magazine;

import com.example.demo.model.travel.PlaceDto;
import com.example.demo.model.travel.TravelInfoDto;
import lombok.Getter;

@Getter
public class MagazineDetailDto {
    private String magazineDetailId;
    private String magazineId;
    private String travelInfoId;

    private String content;
    private String img;

    private MagazineDto magazineDto;
    private TravelInfoDto travelInfoDto;
    private PlaceDto placeDto;
    private String placeId;

    public MagazineDetailDto(){}

    public MagazineDetailDto(String magazineDetailId, String magazineId, String travelInfoId){
        this.magazineDetailId = magazineDetailId;
        this.magazineId = magazineId;
        this.travelInfoId = travelInfoId;
    }

    public MagazineDetailDto(String content, String img){
        this.content = content;
        this.img = img;
    }

    public MagazineDetailDto(String magazineDetailId, String magazineId, String travelInfoId, String content, String img){
        this.magazineDetailId = magazineDetailId;
        this.magazineId = magazineId;
        this.travelInfoId = travelInfoId;
        this.content = content;
        this.img = img;
    }

    public MagazineDetailDto(String magazineDetailId, String magazineId, String img, String content, String travelInfoId, TravelInfoDto travelInfoDto, PlaceDto placeDto) {
        this.magazineDetailId = magazineDetailId;
        this.magazineId = magazineId;
        this.img = img;
        this.content = content;
        this.travelInfoId = travelInfoId;
        this.travelInfoDto = travelInfoDto;
        this.placeDto = placeDto;
    }

    @Override
    public String toString() {
        return "MagazineDetailDto{" +
                "magazine_detail_id='" + magazineDetailId + '\'' +
                ", magazine_id='" + magazineId + '\'' +
                ", travel_info_id='" + travelInfoId + '\'' +
                ", content='" + content + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
