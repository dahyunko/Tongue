package com.example.demo.model.users;

import com.example.demo.model.magazine.MagazineDto;
import com.example.demo.model.travel.TravelDto;
import com.example.demo.model.travel.TravelInfoDto;
import lombok.Getter;

import java.util.List;

@Getter
public class MyPageDto {
    private List<TravelDto> travelDtoList;
    private List<MagazineDto> magazineDtoList;
    private UserDto userDto;

    public MyPageDto(){}

    public MyPageDto(List<TravelDto> travelDtoList, List<MagazineDto> magazineDtoList, UserDto userDto) {
        this.travelDtoList = travelDtoList;
        this.magazineDtoList = magazineDtoList;
        this.userDto = userDto;
    }
}
