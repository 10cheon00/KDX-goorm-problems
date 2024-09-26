package com.example.boardsonic.model.response;

import java.util.List;
import java.util.stream.Collectors;

import com.example.boardsonic.model.DeleteStatus;
import com.example.boardsonic.model.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Data;

//게시물 단건
@Data
@AllArgsConstructor
public class BoardResponse {
  private Long boardNo;
  private String title;
  private String body;
  private DeleteStatus deleteStatus;
  private List<CommentResponse> comments;

  //정적 팩토리 메서드
  // board entity-> board response
  public static BoardResponse from(Board board) {
    return new BoardResponse(
      board.getBoardNo(),
      board.getTitle(),
      board.getBody(),
      board.getDeleteStatus(),
      board.getComments().stream().map(CommentResponse::from).collect(Collectors.toList())
    );
  }

}
