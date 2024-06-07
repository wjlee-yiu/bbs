package com.example.bbs.controller;

import com.example.bbs.dto.BoardRequest;
import com.example.bbs.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    // 전체 게시글 조회 [all]
    @GetMapping
    public ResponseEntity<List> getList() throws Exception {
        return new ResponseEntity<List>(boardService.getList(), HttpStatus.OK);
    }

    // 게시글 작성 [writer]
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Boolean> create(BoardRequest.CreateDTO request) throws Exception {
        return new ResponseEntity<Boolean>(boardService.create(request), HttpStatus.OK);
    }
}
