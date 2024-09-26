package com.example.boardsonic.controller;

import org.springframework.web.bind.annotation.*;

import com.example.boardsonic.model.request.CommentPostRequest;
import com.example.boardsonic.model.response.BoardResponse;
import com.example.boardsonic.service.CommentService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    //댓글 등록
    @PostMapping("comment")
    public BoardResponse writeComment(
            @RequestBody CommentPostRequest commentPostRequest
    ) {
        return commentService.writeComment(commentPostRequest);
    }

    //댓글 수정
    @PatchMapping("comment")
    public BoardResponse updateComment(@RequestParam("commentNo") Long commentNo, @RequestBody CommentPostRequest commentPostRequest) {
        return commentService.updateComment(commentNo, commentPostRequest);
    }

    @DeleteMapping("comment")
    public BoardResponse deleteComment(@RequestParam("commentNo") Long commentNo) {
        return commentService.deleteComment(commentNo);
    }
}
