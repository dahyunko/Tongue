package com.example.demo.model.magazine;

import lombok.Getter;

@Getter
public class MagazineCommentDto {
    String commentId;
    String magazineId;
    String userId;
    String comment;
    String registTime;

    public MagazineCommentDto(){}

    public MagazineCommentDto(String commentId, String magazineId, String userId, String comment) {
        this.commentId = commentId;
        this.magazineId = magazineId;
        this.userId = userId;
        this.comment = comment;
    }
}
