package com.example.bbs.service;

import com.example.bbs.domain.Board;
import com.example.bbs.dto.BoardRequest;
import com.example.bbs.dto.BoardResponse;
import com.example.bbs.exception.CustomException;
import com.example.bbs.exception.ErrorCode;
import com.example.bbs.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;


    public List<BoardResponse> getList() throws Exception {
        List<Board> Board = boardRepository.findAllByOrderByCreatedAtDesc();
        List<BoardResponse> getListDTO = new ArrayList<>();
        Board.forEach(s -> getListDTO.add(BoardResponse.GetBoardDTO(s)));
        return getListDTO;
    }

    public Boolean create(BoardRequest.CreateDTO request) throws Exception {

        // 400 - 데이터 미입력(userId, pwd, title, contents 모두 입력해야 함)
        if(request.getUserId() == null || request.getPwd() == null ||request.getTitle() == null || request.getContents() == null)
            throw new CustomException(ErrorCode.INSUFFICIENT_DATA);

        try {
            Board board = Board.builder()
                    .title(request.getTitle())
                    .contents(request.getContents())
                    .userId(request.getUserId())
                    .pwd(request.getPwd())
                    .build();
            boardRepository.save(board);
        }
        catch (Exception e) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
        return true;
    }

}
