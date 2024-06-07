package com.example.bbs.dto;

import lombok.Getter;
import lombok.Setter;

public class BoardRequest {
    @Getter
    @Setter
    public static class CreateDTO {
        private String userId;
        private String pwd;
        private String title;
        private String contents;
    }

    @Getter
    @Setter
    public static class UpdateDTO {
        private Long boardId;
        private String title;
        private String contents;
        private String userId;
        private String pwd;
    }
}
