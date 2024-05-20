package com.example.demo.model.mapper;

import com.example.demo.model.magazine.MagazineDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MagazineMapper {
    void createMagazine(MagazineDto magazineDto) throws Exception;
    MagazineDto viewMagazine(String magazine_id) throws Exception;
    List<String> getMagazineIdList(String userId) throws Exception;
    List<MagazineDto> getMagazineList(String userId) throws Exception;
}
