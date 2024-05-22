package com.example.demo.service;

import com.example.demo.model.magazine.MagazineCommentDto;
import com.example.demo.model.magazine.MagazineDetailDto;
import com.example.demo.model.magazine.MagazineDto;
import com.example.demo.model.mapper.MagazineCommentMapper;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MagazineService {

    // MagazineMapper
    String createMagazineMemo(String user_id, String travel_id);
    List<MagazineDetailDto> viewMagazineDetail(String magazine_id) throws Exception;
    MagazineDto viewMagazine(String magazineId) throws Exception;
    List<String> getMagazineIdList(String user_id) throws Exception;
    MagazineDto viewDetailMagazine(String magazineId, String userId) throws Exception;

    // MagazineDetailMapper
    String registMagazineDetail(MagazineDto magazineDto) throws Exception;
    MagazineDto saveMagazine(MagazineDto magazineDto) throws Exception;
    List<MagazineDto> listMagazine(String userId) throws Exception;

    // MagazineCommentMapper
    void insertComment(String magazineId, String userId, String comment) throws Exception;
    List<MagazineCommentDto> listComment(String magazineId) throws Exception;
}
