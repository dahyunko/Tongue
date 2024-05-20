package com.example.demo.model.mapper;

import com.example.demo.model.travel.PlaceDto;

import java.sql.SQLException;

public interface PlaceMapper {
    void registPlace(PlaceDto placeDto) throws SQLException;
    PlaceDto viewPlace(String placeId) throws SQLException;

    void deletePlace(String placeId) throws SQLException;
}
