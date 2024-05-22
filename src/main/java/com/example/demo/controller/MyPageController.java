package com.example.demo.controller;

import com.example.demo.model.magazine.MagazineDto;
import com.example.demo.model.travel.TravelDto;
import com.example.demo.model.users.MyPageDto;
import com.example.demo.model.users.UserDto;
import com.example.demo.service.MagazineService;
import com.example.demo.service.TravelService;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/mypage")
public class MyPageController {
    private TravelService travelService;
    private MagazineService magazineService;
    private UserService userService;

    public MyPageController(TravelService travelService, MagazineService magazineService, UserService userService){
        this.travelService = travelService;
        this.magazineService = magazineService;
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<?> info() throws Exception{
        try {
            String userId = SecurityContextHolder.getContext().getAuthentication().getName();

            log.info("mypage:", userId);
            List<TravelDto> travelInfos = travelService.listMyTravel(userId);
            List<MagazineDto> magazineInfos = magazineService.listMagazine(userId);
            UserDto userInfo = userService.getUserInfo(userId);
            log.info("travelInfos:", travelInfos);
            log.info("magazineInfos:", magazineInfos);
            log.info("userInfo:", userInfo);


            MyPageDto myPageInfo = new MyPageDto(travelInfos, magazineInfos, userInfo);
            log.info("mypagedto:", myPageInfo);
            return new ResponseEntity<MyPageDto>(myPageInfo, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/magazine")
    public ResponseEntity<?> getMagazineList() throws Exception{
        try{
            String userId = SecurityContextHolder.getContext().getAuthentication().getName();
            List<MagazineDto> magazineDtoList = new ArrayList<>();
            List<String> magazineIdList = magazineService.getMagazineIdList(userId);
            for (String magazineId:magazineIdList){
                magazineDtoList.add(magazineService.viewDetailMagazine(magazineId, userId));
            }
            return new ResponseEntity<List<MagazineDto>>(magazineDtoList, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

}
