package com.example.demo.service;

import com.example.demo.model.mapper.PlaceMapper;
import com.example.demo.model.mapper.TravelInfoMapper;
import com.example.demo.model.mapper.TravelMapper;
import com.example.demo.model.travel.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class TravelServiceImpl implements TravelService {
    private final TravelMapper travelMapper;
    private final PlaceMapper placeMapper;
    private final TravelInfoMapper travelInfoMapper;
    private static int ORDER = 1;
    private static String PLACE = "place";
    private static String TRAVEL = "travel";
    private static String TRAVELINFO = "travelinfo";


    public TravelServiceImpl(TravelMapper travelMapper, PlaceMapper placeMapper, TravelInfoMapper travelInfoMapper) {
        super();
        this.travelMapper = travelMapper;
        this.placeMapper = placeMapper;
        this.travelInfoMapper = travelInfoMapper;
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
    public void registTravel(List<TmapTravelDto> tmapTravelDtos, String travelName, String userId , int travelDay, Boolean travelOwner) {
        List<TravelInfoDto> travelInfoDtoList = new ArrayList<>();

        try{
            String travelId = TRAVEL + generateRandomId();
            TravelDto travelDto = new TravelDto(travelId, travelName, userId, travelDay, travelOwner);
            log.info("travelDto: {}", travelDto);
            travelMapper.registTravel(travelDto);

            for (TmapTravelDto tmapTravelDto : tmapTravelDtos) {
                String placeId = PLACE + generateRandomId();
                PlaceDto placeDto = new PlaceDto(placeId, tmapTravelDto.getLoc(), tmapTravelDto.getDes(), tmapTravelDto.getCost(), tmapTravelDto.getTransport(), tmapTravelDto.getLat(), tmapTravelDto.getLoc(), tmapTravelDto.getAddress());
                placeMapper.registPlace(placeDto);

                String travelInfoId = TRAVELINFO + generateRandomId();
                TravelInfoDto travelInfoDto = new TravelInfoDto(travelInfoId ,tmapTravelDto.getDay(), ORDER++,travelDto, placeDto);
                log.info("travelInfo: {} ", travelInfoDto.toString());
                travelInfoMapper.registTravelInfo(travelInfoDto);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateTravel(TravelDto travelDto) {

    }

    @Override
    public void deleteTravel(String travelId, String userId) {

    }

    @Override
    public List<TravelDto> listMyTravel(String userId) throws Exception {
        List<TravelDto> travelDtoList = travelMapper.listMyTravel(userId);

        return travelDtoList;
    }

    @Override
    public TravelDto viewTravel(String travelId, String userId) throws Exception {
        try{
            log.info("in");
            List<TravelInfoDto> travelInfoDtoList = new ArrayList<>();
            List<TravelInfoDto> travelInfoDtos = travelInfoMapper.listTravelInfo(travelId);
            log.info("travelInfoDtos : ", travelInfoDtos.toString());
            for(TravelInfoDto travelInfoDto : travelInfoDtos){
                PlaceDto placeDto = placeMapper.viewPlace(travelInfoDto.getPlaceId());

                travelInfoDtoList.add(new TravelInfoDto(
                        travelInfoDto.getTravelInfoId(),
                        travelInfoDto.getDay(),
                        travelInfoDto.getOrder(),
                        placeDto
                ));
            }

            log.info("TIDlst: {}", travelInfoDtoList);
            TravelDto travelDto = travelMapper.viewTravel(travelId);
            return new TravelDto(
                    travelDto.getTravelId(),
                    travelDto.getUserId(),
                    travelDto.getTravelName(),
                    travelInfoDtoList,
                    travelDto.getTravelDay(),
                    travelDto.getTravelOwner()
            );
        }catch (Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    @Override
    public List<TravelDto> listTravel() throws Exception {
        List<String> travelIdList = travelMapper.getTravelIdList();
        List<TravelDto> travelDtoList = new ArrayList<>();

        for(String travelId : travelIdList){
            travelDtoList.add(viewTravel(travelId, ""));
        }
        return travelDtoList;
    }



}
