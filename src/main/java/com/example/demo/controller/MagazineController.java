package com.example.demo.controller;

import com.example.demo.model.magazine.MagazineCommentDto;
import com.example.demo.model.magazine.MagazineDetailDto;
import com.example.demo.model.magazine.MagazineDto;
import com.example.demo.model.travel.TravelDto;
import com.example.demo.model.travel.TravelInfoDto;
import com.example.demo.service.MagazineService;
import com.example.demo.service.TravelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.security.interfaces.RSAKey;
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
    public ResponseEntity<?> generateMagazine(@PathVariable("magazgineId") String magazineId, @RequestBody MagazineDto magazineDto){
        try{
            magazineService.registMagazineDetail(magazineDto);
            return new ResponseEntity<String>(magazineId, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/{magazineId}")
    public ResponseEntity<?> viewDetailMagazine(@PathVariable("magazineId") String magazineId){
        try {
            MagazineDto magazineDto = magazineService.viewDetailMagazine(magazineId);
            return new ResponseEntity<MagazineDto>(magazineDto, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/save/{magazineId}")
    public ResponseEntity<?> saveMagazine(@PathVariable("magazineId") String magazineId, @RequestBody MagazineDto magazineDto){
        try{
            MagazineDto magazineDtoNew =  magazineService.saveMagazine(magazineDto);
            return new ResponseEntity<MagazineDto>(magazineDtoNew, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/delete/{magazineId}")
    public ResponseEntity<?> deleteMagazine(@PathVariable("magazineId") String magazineId){
        try{
            magazineService.deleteCommentAll(magazineId);
            magazineService.deleteMagazineDetail(magazineId);
            magazineService.deleteMagazine(magazineId);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/view/all")
    public ResponseEntity<?> viewAllMagazine(){
        try {
            String userId = SecurityContextHolder.getContext().getAuthentication().getName();
            List<MagazineDto> magazineDtos = magazineService.getAllMagazine();
            return new ResponseEntity<List<MagazineDto>>(magazineDtos, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/view/hot")
    public ResponseEntity<?> viewHotMagazine(){
        try {
            String userId = SecurityContextHolder.getContext().getAuthentication().getName();
            List<MagazineDto> magazineDtos = magazineService.getHotMagazine();
            return new ResponseEntity<List<MagazineDto>>(magazineDtos, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/view/new")
    public ResponseEntity<?> viewNewMagazine(){
        try {
            String userId = SecurityContextHolder.getContext().getAuthentication().getName();
            List<MagazineDto> magazineDtos = magazineService.getNewMagazine();
            return new ResponseEntity<List<MagazineDto>>(magazineDtos, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
        }
    }


    @GetMapping("/check/{travelId}")
    public ResponseEntity<?> checkMagazine(@PathVariable("travelId") String travelId){
        try{
            List<String> existMagazine = magazineService.checkMagazine(travelId);
            return new ResponseEntity<List<String>>(existMagazine, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
        }
    }


    @PostMapping("/comment/{magazineId}")
    public ResponseEntity<?> newComment(@PathVariable("magazineId") String magazineId, @RequestParam("comment") String comment){
        try{
            String userId = SecurityContextHolder.getContext().getAuthentication().getName();
            log.info(comment);
            magazineService.insertComment(magazineId, userId, comment);

            return new ResponseEntity<Void>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/comment/{magazineId}")
    public ResponseEntity<?> getComment(@PathVariable("magazineId") String magazineId){
        try{
            List<MagazineCommentDto> magazineCommentDtos = magazineService.listComment(magazineId);
            return new ResponseEntity<List<MagazineCommentDto>>(magazineCommentDtos, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}


