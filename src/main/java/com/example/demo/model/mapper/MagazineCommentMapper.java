package com.example.demo.model.mapper;

import com.example.demo.model.magazine.MagazineCommentDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MagazineCommentMapper {
    void insertComment(MagazineCommentDto magazineCommentDto) throws Exception;
    List<MagazineCommentDto> getComment(String magazineId) throws Exception;
    List<String> listCommentId(String magazineId) throws Exception;
    void deleteComment(String commentId) throws Exception;
}
