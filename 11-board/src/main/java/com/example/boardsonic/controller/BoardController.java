package com.example.boardsonic.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.boardsonic.model.request.BoardDeleteRequest;
import com.example.boardsonic.model.request.BoardPostRequest;
import com.example.boardsonic.model.response.BoardListResponse;
import com.example.boardsonic.model.response.BoardResponse;
import com.example.boardsonic.service.BoardService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    //게시물 등록
    @PostMapping("boards")
    public BoardResponse writeBoard(@RequestBody BoardPostRequest boardPostRequest) {
        return boardService.writeBoard(boardPostRequest);
    }

    //조회
    //페이징조회. 다건. 댓글 가져오지 않음. 게시물 목록
    @GetMapping("boards")
    public List<BoardListResponse> searchBoardList(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
        return boardService.searchBoardList(page, pageSize);
    }

    //단건조회
    @GetMapping("board")
    public BoardResponse searchBoard(@RequestParam("boardNo") Long boardNo) {
        return boardService.searchBoard(boardNo);
    }

    //게시물 업데이트
    @PatchMapping("board")
    public BoardResponse updateBoard(@RequestParam("boardNo") Long boardNo, @RequestBody BoardPostRequest boardPostRequest) {
        return boardService.updateBoard(boardNo, boardPostRequest);
    }

    //게시물삭제
    @DeleteMapping("boards")
    public String deleteBoard(@RequestBody BoardDeleteRequest boardDeleteRequest) {
        return boardService.deleteBoard(boardDeleteRequest);
    }
}
