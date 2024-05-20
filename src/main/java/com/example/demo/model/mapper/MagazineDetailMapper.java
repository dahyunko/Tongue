package com.example.demo.model.mapper;

import com.example.demo.model.magazine.MagazineDetailDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MagazineDetailMapper {
    void registMagazineDetail(MagazineDetailDto magazineDetailDto) throws Exception;
    void updateMagazineDetail(MagazineDetailDto magazineDetailDto) throws Exception;
    List<MagazineDetailDto> listMagazineDetail(String magazineId) throws Exception;

}
