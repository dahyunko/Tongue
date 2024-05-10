package com.example.demo.service;

import com.example.demo.model.magazine.MagazineDetailDto;
import com.example.demo.model.magazine.MagazineDto;
import com.example.demo.model.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MagazineServiceImpl implements MagazineService {

    private final MagazineMapper magazineMapper;
    private final MagazineDetailMapper magazineDetailMapper;
    private final TravelMapper travelMapper;
    private final TravelInfoMapper travelInfoMapper;
    private final PlaceMapper placeMapper;

    public MagazineServiceImpl(MagazineMapper magazineMapper, MagazineDetailMapper magazineDetailMapper, TravelMapper travelMapper, TravelInfoMapper travelInfoMapper, PlaceMapper placeMapper) {
        this.magazineMapper = magazineMapper;
        this.magazineDetailMapper = magazineDetailMapper;
        this.travelMapper = travelMapper;
        this.travelInfoMapper = travelInfoMapper;
        this.placeMapper = placeMapper;
    }


    @Override
    public MagazineDto viewMagazine(String magazine_id) throws Exception {

        return null;
    }

    @Override
    public List<String> getMagazineIdList(String user_id) throws Exception {
        return null;
    }

    @Override
    public void registMagazineDetail(MagazineDetailDto magazineDetailDto) throws Exception {

    }

    @Override
    public List<MagazineDetailDto> listMagazineDetail(String magazine_id) throws Exception {
        return null;
    }
}
