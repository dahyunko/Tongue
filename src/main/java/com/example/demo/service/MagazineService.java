package com.example.demo.service;

import com.example.demo.model.magazine.MagazineDetailDto;
import com.example.demo.model.magazine.MagazineDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MagazineService {

    // MagazineMapper
    List<MagazineDetailDto> createMagazineMemo(String user_id, String travel_id);
    List<MagazineDetailDto> viewMagazine(String magazine_id) throws Exception;
    List<String> getMagazineIdList(String user_id) throws Exception;

    // MagazineDetailMapper
    List<MagazineDetailDto> registMagazineDetail(List<MagazineDetailDto> magazineDetailDtos) throws Exception;
    List<MagazineDetailDto> listMagazineDetail(String magazine_id) throws Exception;
    List<MagazineDto> listMagazine(String userId) throws Exception;
}
