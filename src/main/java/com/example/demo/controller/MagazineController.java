package com.example.demo.controller;

import com.example.demo.model.magazine.MagazineDetailDto;
import com.example.demo.model.magazine.MagazineDto;
import com.example.demo.model.travel.TravelInfoDto;
import com.example.demo.service.MagazineService;
import com.example.demo.service.TravelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/magazine")
public class MagazineController {
    private MagazineService magazineService;
    private TravelService travelService;

    public MagazineController(MagazineService magazineService, TravelService travelService) {
        this.magazineService = magazineService;
        this.travelService = travelService;
    }

    @PostMapping("/memo/{travelId}")
    public ResponseEntity<?> memo(@PathVariable("travelId") String travelId){
        try{
            String userId = SecurityContextHolder.getContext().getAuthentication().getName();
            String magazineId = magazineService.createMagazineMemo(userId, travelId);
            return new ResponseEntity<String>(magazineId, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/{magazineId}")
    public ResponseEntity<?> generateMagazine(@PathVariable("magazineId") String magazineId, @RequestBody List<MagazineDetailDto> magazineDetailDtos){
        try{
//            String userId = SecurityContextHolder.getContext().getAuthentication().getName();
            List<MagazineDetailDto> magazineDetailDtoList =magazineService.registMagazineDetail(magazineDetailDtos);
//            return new ResponseEntity<List<MagazineDetailDto>>(magazineDetailDtos, HttpStatus.OK);
            return new ResponseEntity<List<MagazineDetailDto>>(magazineDetailDtoList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/{magazineId}")
    public ResponseEntity<?> viewMagazine(@PathVariable("magazineId") String magazineId){
        try {
//            String userId = SecurityContextHolder.getContext().getAuthentication().getName();
            MagazineDto magazineInfo = magazineService.viewMagazine(magazineId);
            List<TravelInfoDto> travelInfoDtos = travelService.listTravelInfo(magazineInfo.getTravelId());
            List<MagazineDetailDto> magazineDetailDtos = magazineService.viewMagazineDetail(magazineId);
            String userId = SecurityContextHolder.getContext().getAuthentication().getName();
            MagazineDto magazineDto = new MagazineDto(magazineId, userId, magazineInfo.getTravelId(), magazineInfo.getMagazineTitle(), travelInfoDtos, magazineDetailDtos);
            return new ResponseEntity<MagazineDto>(magazineDto, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}


