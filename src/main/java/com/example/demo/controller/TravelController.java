package com.example.demo.controller;

import com.example.demo.model.travel.TmapTravelDto;
import com.example.demo.model.travel.TravelDto;
import com.example.demo.service.TravelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/travel")
public class TravelController {
    private TravelService travelService;

    public TravelController(TravelService travelService) {
        this.travelService = travelService;
    }

    @PostMapping("/regist")
    public ResponseEntity<?> regist(@RequestBody List<TmapTravelDto> tmapTravelDtos, @RequestParam("travelName") String travelName) throws Exception{
        try{
            String userId = SecurityContextHolder.getContext().getAuthentication().getName();
            travelService.registTravel(tmapTravelDtos, travelName, userId);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/view/{travelId}")
    public ResponseEntity<?> view(@PathVariable("travelId") String travelId) throws Exception{
        try{
            String userId =  SecurityContextHolder.getContext().getAuthentication().getName();
            TravelDto travelDto = travelService.viewTravel(travelId, userId);
            return new ResponseEntity<TravelDto>(travelDto, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> list() throws Exception{
        try{
            List<TravelDto> travelDtoList = travelService.listTravel();
            return new ResponseEntity<List<TravelDto>>(travelDtoList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
