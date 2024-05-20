package com.example.demo.model.mapper;

import com.example.demo.model.travel.TravelDto;
import com.example.demo.model.travel.TravelInfoDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface TravelInfoMapper {
    void registTravelInfo(TravelInfoDto travelInfoDto) throws SQLException;

    TravelInfoDto viewTravelInfo(String travelId) throws SQLException;

    List<TravelInfoDto> listTravelInfo(String travelId) throws Exception;
    void deleteTravelInfoList(String travelId) throws Exception;
}
