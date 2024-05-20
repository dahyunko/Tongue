package com.example.demo.model.mapper;


import com.example.demo.model.travel.TravelDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface TravelMapper {
    void registTravel(TravelDto travelDto) throws SQLException;

    String getTravelId(String userId, String travelName) throws Exception;
    TravelDto viewTravel(String travelId) throws Exception;
    List<TravelDto> listTravel() throws Exception;
    List<TravelDto> listMyTravel(String userId) throws Exception;

    void updateTravel(TravelDto travelDto);
    void deleteTravel(String travelId, String userId);

    List<String> getTravelIdList() throws Exception;
}
