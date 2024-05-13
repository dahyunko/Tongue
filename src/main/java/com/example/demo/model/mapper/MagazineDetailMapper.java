package com.example.demo.model.mapper;

import com.example.demo.model.magazine.MagazineDetailDto;

import java.util.List;

public interface MagazineDetailMapper {
    void registMagazineDetail(MagazineDetailDto magazineDetailDto) throws Exception;
    void updateMagazineDetail(MagazineDetailDto magazineDetailDto) throws Exception;
    List<MagazineDetailDto> listMagazineDetail(String magazine_id) throws Exception;

}
