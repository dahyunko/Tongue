package com.example.demo.controller;

import com.example.demo.model.magazine.MagazineDetailDto;
import com.example.demo.service.MagazineService;
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

    public MagazineController(MagazineService magazineService) {
        this.magazineService = magazineService;
    }

    @PostMapping("/memo/{travelId}")
    public ResponseEntity<?> memo(@PathVariable("travelId") String travelId){
        try{
            String userId = SecurityContextHolder.getContext().getAuthentication().getName();
            List<MagazineDetailDto> magazineDetailDtos = magazineService.createMagazineMemo(userId, travelId);
            return new ResponseEntity<List<MagazineDetailDto>>(magazineDetailDtos, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/{magazineId}")
    public ResponseEntity<?> generateMagazine(@PathVariable("magazineId") String magazineId, @RequestBody List<MagazineDetailDto> magazineDetailDtos){
        try{
//            String userId = SecurityContextHolder.getContext().getAuthentication().getName();
            magazineService.registMagazineDetail(magazineDetailDtos);
            return new ResponseEntity<List<MagazineDetailDto>>(magazineDetailDtos, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/{magazineId}")
    public ResponseEntity<?> viewMagazine(@PathVariable("magazineId") String magazineId){
        try {
//            String userId = SecurityContextHolder.getContext().getAuthentication().getName();
            List<MagazineDetailDto> magazineDetailDtos = magazineService.viewMagazine(magazineId);
            return new ResponseEntity<List<MagazineDetailDto>>(magazineDetailDtos, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}


