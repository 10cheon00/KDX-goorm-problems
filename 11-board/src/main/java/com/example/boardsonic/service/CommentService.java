package com.example.boardsonic.service;

import java.util.Optional;

import com.example.boardsonic.model.DeleteStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.boardsonic.model.entity.Board;
import com.example.boardsonic.model.entity.Comment;
import com.example.boardsonic.model.request.CommentPostRequest;
import com.example.boardsonic.model.response.BoardResponse;
import com.example.boardsonic.repository.BoardRepository;
import com.example.boardsonic.repository.CommentRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public BoardResponse writeComment(CommentPostRequest request) {
        Optional<Board> boardOptional = boardRepository.findBoardWithCommentsByBoardNo(request.getBoardNo());
        Board board = boardOptional.orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다!"));

        //board에 댓글 추가
        board.addComment(request.getCommentBody());
        boardRepository.save(board);

        return BoardResponse.from(board);
    }

    @Transactional
    public BoardResponse updateComment(Long commentNo, CommentPostRequest commentPostRequest) {
        Comment comment = commentRepository.findByCommentNoAndDeleteStatus(commentNo, DeleteStatus.ACTIVE)
                .orElseThrow(() -> new RuntimeException("존재하지않는 댓글입니다!"));

        // 댓글이 수정될 때, 게시글도 바로 수정될까? context에 바로 반영이 될까?
        comment.setBody(commentPostRequest.getCommentBody());

        // Transactional을 쓴 순간 영속성컨텍스트에 들어간 엔티티의 값을 바꾸면 곧바로 db에 반영이 된다.
        // save를 안해도 반영이 된다.
        // commentRepository.save(comment);

        Board board = boardRepository.findBoardWithCommentsByBoardNo(comment.getBoard().getBoardNo())
                .orElseThrow(() -> new RuntimeException("존재하지않는 게시글입니다!"));

        return BoardResponse.from(board);
    }

    @Transactional
    public BoardResponse deleteComment(Long commentNo) {
        Comment comment = commentRepository.findById(commentNo)
                .orElseThrow(() -> new RuntimeException("존재하지않는 댓글입니다!"));
        commentRepository.delete(comment);

        Board board = boardRepository.findBoardWithCommentsByBoardNo(comment.getBoard().getBoardNo())
                .orElseThrow(() -> new RuntimeException("존재하지않는 게시글입니다!"));

//        Optional<Comment> commentOptional = commentRepository.findById(commentNo);
//        Comment comment = commentOptional.orElseThrow(() -> new RuntimeException("존재하지않는 댓글입니다!"));
//        Board board = comment.getBoard();

//        commentRepository.delete(comment);

        return BoardResponse.from(board);
    }
}
