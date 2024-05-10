package com.example.demo.service;

import com.example.demo.model.magazine.MagazineDetailDto;
import com.example.demo.model.magazine.MagazineDto;
import com.example.demo.model.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class MagazineServiceImpl implements MagazineService {

    private final MagazineMapper magazineMapper;
    private final MagazineDetailMapper magazineDetailMapper;
    private final TravelMapper travelMapper;
    private final TravelInfoMapper travelInfoMapper;
    private final PlaceMapper placeMapper;

    private static String PLACE = "place";
    private static String TRAVEL = "travel";
    private static String TRAVELINFO = "travelinfo";
    private static String MAGAZINE = "magazine";
    private static String MAGAZINEINFO = "magazineinfo";

    public MagazineServiceImpl(MagazineMapper magazineMapper, MagazineDetailMapper magazineDetailMapper, TravelMapper travelMapper, TravelInfoMapper travelInfoMapper, PlaceMapper placeMapper) {
        this.magazineMapper = magazineMapper;
        this.magazineDetailMapper = magazineDetailMapper;
        this.travelMapper = travelMapper;
        this.travelInfoMapper = travelInfoMapper;
        this.placeMapper = placeMapper;
    }

    private static String generateRandomId(){
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit,rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

//        System.out.println(generatedString);
        return generatedString;
    }

    @Override
    public void createMagazine(String user_id, String travel_id) throws Exception {
        String MagazineId = MAGAZINE + generateRandomId();
        MagazineDto magazineDto = new MagazineDto(MagazineId, user_id, travel_id);
        magazineMapper.createMagazine(magazineDto);
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
