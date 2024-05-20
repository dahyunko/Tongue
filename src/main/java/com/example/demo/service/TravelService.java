package com.example.demo.service;

import com.example.demo.model.travel.TmapTravelDto;
import com.example.demo.model.travel.TravelDto;
import com.example.demo.model.travel.TravelInfoDto;

import java.util.List;

public interface TravelService {
    TravelDto viewTravel(String travelId, String userId) throws Exception;
    List<TravelDto> listTravel() throws Exception;
    void registTravel(List<TmapTravelDto> tmapTravelDtos, String travelName, String userId, int travelDay, Boolean travelOwner) throws Exception;

    void updateTravel(TravelDto travelDto);
    void deleteTravel(String travelId, String userId) throws Exception;
    List<TravelDto> listMyTravel(String userId) throws Exception;
    void duplicateTravel(String travelId, String userId) throws Exception;

    List<TravelInfoDto> listTravelInfo(String travelId) throws Exception;
}
