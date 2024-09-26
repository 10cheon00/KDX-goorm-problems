package com.example.boardsonic.model.response;

import com.example.boardsonic.model.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentResponse {
  private Long commentNo;
  private String body;

  //정적팩토리메소드
  public static CommentResponse from(Comment comment) {
    return new CommentResponse(comment.getCommentNo(), comment.getBody());
  }
}
