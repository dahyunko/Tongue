package com.example.demo.service;

import com.example.demo.model.magazine.MagazineCommentDto;
import com.example.demo.model.magazine.MagazineDetailDto;
import com.example.demo.model.magazine.MagazineDto;
import com.example.demo.model.mapper.*;
import com.example.demo.model.travel.PlaceDto;
import com.example.demo.model.travel.TravelDto;
import com.example.demo.model.travel.TravelInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private final MagazineCommentMapper magazineCommentMapper;

    private static String PLACE = "place";
    private static String TRAVEL = "travel";
    private static String TRAVELINFO = "travelinfo";
    private static String MAGAZINE = "magazine";
    private static String MAGAZINEDETAIL = "magazinedetail";
    private static String MAGAZINECOMMENT = "magazinecomment";

    public MagazineServiceImpl(MagazineMapper magazineMapper, MagazineDetailMapper magazineDetailMapper, TravelMapper travelMapper, TravelInfoMapper travelInfoMapper, PlaceMapper placeMapper, MagazineCommentMapper magazineCommentMapper) {
        this.magazineMapper = magazineMapper;
        this.magazineDetailMapper = magazineDetailMapper;
        this.travelMapper = travelMapper;
        this.travelInfoMapper = travelInfoMapper;
        this.placeMapper = placeMapper;
        this.magazineCommentMapper = magazineCommentMapper;
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

        return generatedString;
    }

    // 일정 기록하기 눌렀을 때 실행 -> 매거진 디테일 생성하기
    @Override
    public String createMagazineMemo(String userId, String travelId){
        try{
            String magazineId = MAGAZINE + generateRandomId();
            MagazineDto magazineDto = new MagazineDto(magazineId, userId, travelId);
            magazineMapper.createMagazine(magazineDto);

            List<TravelInfoDto> travelInfoDtos = travelInfoMapper.listTravelInfo(travelId);
            for (TravelInfoDto travelInfoDto : travelInfoDtos){
                String magazineDetailId = MAGAZINEDETAIL + generateRandomId();
                MagazineDetailDto magazineDetailDto = new MagazineDetailDto(magazineDetailId, magazineId, travelInfoDto.getTravelInfoId());
                magazineDetailMapper.registMagazineDetail(magazineDetailDto);
            }
//            log.info(magazineId);
            return magazineId;
        }catch (Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    // 매거진 만들기 -> 매거진 업데이트 하고 gemini로 값 넘기기
    @Override
    public String registMagazineDetail(MagazineDto magazineDto) throws Exception {
        try {
            String magazineId = magazineDto.getMagazineId();
            List<MagazineDetailDto> magazineDetailDtos = magazineDto.getMagazineDetailDtoList();
            for(MagazineDetailDto magazineDetailDto : magazineDetailDtos){
                magazineDetailMapper.updateMagazineDetail(magazineDetailDto);
            }
            return magazineId;
        }catch (Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    @Override
    public MagazineDto saveMagazine(MagazineDto magazineDto) throws Exception {
        try{
            String magazineId = magazineDto.getMagazineId();
            log.info(magazineId);
            // 매거진 삭제
            for(MagazineDetailDto magazineDetailDto : magazineDto.getMagazineDetailDtoList()){
                magazineDetailMapper.deleteMagazineDetail(magazineId);
            }
            magazineMapper.deleteMagazine(magazineId);

            //매거진 새로 생성
            magazineMapper.createMagazine(magazineDto);
            for(MagazineDetailDto magazineDetailDto: magazineDto.getMagazineDetailDtoList()){
                log.info(magazineDetailDto.getMagazineId());
                magazineDetailMapper.createMagazineDetail(magazineDetailDto);
            }

            return viewDetailMagazine(magazineId, magazineDto.getUserId());

        }catch (Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }


    @Override
    public void deleteMagazine(String magazineId) throws Exception {
        try{
            magazineDetailMapper.deleteMagazineDetail(magazineId);
            magazineMapper.deleteMagazine(magazineId);
        }catch (Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void deleteMagazineDetail(String magazineId) throws Exception {
        try {
            magazineDetailMapper.deleteMagazineDetail(magazineId);
        }catch (Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void updateMagazineHit(String magazineId) throws Exception {
        try{
            magazineMapper.updateHit(magazineId);
        }catch (Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    @Override
    public List<MagazineDto> getAllMagazine() throws Exception {
        try{
            return magazineMapper.getAllMagazine();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    @Override
    public List<MagazineDto> getHotMagazine() throws Exception {
        try{
            return magazineMapper.getHotMagazine();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    @Override
    public List<MagazineDto> getNewMagazine() throws Exception {
        try{
            return magazineMapper.getNewMagazine();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }


    @Override
    public List<MagazineDetailDto> viewMagazineDetail(String magazine_id) throws Exception {
        try{
            return magazineDetailMapper.listMagazineDetail(magazine_id);
        }catch (Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    @Override
    public MagazineDto viewMagazine(String magazineId) throws Exception {
        try{
            return magazineMapper.viewMagazine(magazineId);
        } catch (Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }


    @Override
    public List<String> getMagazineIdList(String user_id) throws Exception {
        try{
            return magazineMapper.getMagazineIdList(user_id);
        }catch (Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    @Override
    public MagazineDto viewDetailMagazine(String magazineId, String userId) throws Exception {
        try{
            List<MagazineDetailDto> magazineDetailDtoList = new ArrayList<>();
            List<MagazineDetailDto> magazineDetailDtos = magazineDetailMapper.listMagazineDetail(magazineId);

            for (MagazineDetailDto magazineDetailDto : magazineDetailDtos){
                TravelInfoDto travelInfoDto = travelInfoMapper.viewTravelInfo(magazineDetailDto.getTravelInfoId());

                PlaceDto placeDto = placeMapper.viewPlace(travelInfoDto.getPlaceId());
                magazineDetailDtoList.add(new MagazineDetailDto(
                        magazineDetailDto.getMagazineDetailId(),
                        magazineId,
                        magazineDetailDto.getImg(),
                        magazineDetailDto.getContent(),
                        magazineDetailDto.getTravelInfoId(),
                        travelInfoDto,
                        placeDto
                ));
            }
                MagazineDto magazineDto = magazineMapper.viewMagazine(magazineId);
                return new MagazineDto(
                        magazineId,
                        userId,
                        magazineDto.getTravelId(),
                        magazineDto.getMagazineTitle(),
                        magazineDetailDtoList
                );
        }catch (Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    @Override
    public  List<String> checkMagazine(String travelId) throws Exception {
        try{
            List<String> magazineIds = magazineMapper.checkMagazine(travelId);
            return magazineIds;
        }catch (Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }


    @Override
    public List<MagazineDto> listMagazine(String userId) throws Exception {
        try{
            return magazineMapper.getMagazineList(userId);
        }catch (Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void insertComment(String magazineId, String userId, String comment) throws Exception {
        try{
            String commentId = MAGAZINECOMMENT+generateRandomId();
            log.info(commentId);
            log.info("comment: "+comment);
            MagazineCommentDto magazineCommentDto = new MagazineCommentDto(commentId, magazineId, userId, comment);
            magazineCommentMapper.insertComment(magazineCommentDto);
        } catch (Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    @Override
    public List<MagazineCommentDto> listComment(String magazineId) throws Exception {
        try{
            return magazineCommentMapper.getComment(magazineId);
        } catch (Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    @Override
    public List<String> listCommentId(String magazineId) throws Exception {
        try{
            return magazineCommentMapper.listCommentId(magazineId);
        }catch (Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void deleteCommentAll(String magazineId) throws Exception {
        try{
            List<String> commentIds = magazineCommentMapper.listCommentId(magazineId);
            for(String commentId:commentIds){
                magazineCommentMapper.deleteComment(commentId);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }


}
