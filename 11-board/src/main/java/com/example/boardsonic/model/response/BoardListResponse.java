package com.example.boardsonic.model.response;

import com.example.boardsonic.model.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardListResponse {
  private Long boardNo;
  private String title;

  //static factory method
  public static BoardListResponse from(Board board) {
    return new BoardListResponse(
      board.getBoardNo(),
      board.getTitle()
    );
  }
}
