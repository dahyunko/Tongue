package com.example.demo.model.magazine;

import com.example.demo.model.travel.TravelDto;
import com.example.demo.model.travel.TravelInfoDto;
import lombok.Getter;

import java.util.List;

@Getter
public class MagazineDto {
    private String magazineId;
    private String userId;
    private String travelId;
    private String magazineTitle;
    private int reuseCnt;
    private int magazineHit;
    private int magazineLike;
    private TravelDto travelDto;
    private List<TravelInfoDto> travelInfoDtoList;
    private List<MagazineDetailDto> magazineDetailDtoList;

    public MagazineDto() {}

    public MagazineDto(String magazineId, String userId, String travelId){
        this.magazineId = magazineId;
        this.userId = userId;
        this.travelId = travelId;
    }

    public MagazineDto(String magazineId, String userId, String travelId, String magazineTitle, List<MagazineDetailDto> magazineDetailDtoList) {
        this.magazineId = magazineId;
        this.userId = userId;
        this.travelId = travelId;
        this.magazineTitle = magazineTitle;
        this.magazineDetailDtoList = magazineDetailDtoList;
    }

    public MagazineDto(String magazineId, String userId, String travelId, String magazineTitle, TravelDto travelDto, List<MagazineDetailDto> magazineDetailDtoList) {
        this.magazineId = magazineId;
        this.userId = userId;
        this.travelId = travelId;
        this.magazineTitle = magazineTitle;
        this.travelDto = travelDto;
//        this.travelInfoDtoList = travelInfoDtoList;
        this.magazineDetailDtoList = magazineDetailDtoList;
    }
}
