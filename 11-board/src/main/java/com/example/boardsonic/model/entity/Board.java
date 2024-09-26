package com.example.boardsonic.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Where;

import com.example.boardsonic.model.DeleteStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE board SET DELETE_STATUS = 'DELETE' WHERE board_no = ?")
public class Board {

  @Id // javax, jakarta 동일함!
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long boardNo; //pk

  private String title;

  @Column(length = 1000)
  private String body;

  //soft delete 구현 ! => 삭제여부 플래그값
  @Enumerated(EnumType.STRING)
  private DeleteStatus deleteStatus;

  //관계매핑
  //1개의 게시물에 N개의 댓글을 들고 있는형태
  @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
  @SQLRestriction("DELETE_STATUS= 'ACTIVE'") //@Where(clause = "DELETE_STATUS= 'ACTIVE'")
  private List<Comment> comments = new ArrayList<>();

  public Board addComment(String commentBody) {
    Comment comment = new Comment();
    comment.setBody(commentBody);
    comment.setBoard(this);
    comment.setDeleteStatus(DeleteStatus.ACTIVE);

    this.getComments().add(comment);
    return this;
  }
}
