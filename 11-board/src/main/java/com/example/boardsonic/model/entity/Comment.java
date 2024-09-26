package com.example.boardsonic.model.entity;

import org.hibernate.annotations.SQLDelete;

import com.example.boardsonic.model.DeleteStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE comment SET DELETE_STATUS = 'DELETE' WHERE comment_no = ?")
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) //자동 채번!
  private Long commentNo;

  @Column(length = 1000)
  private String body;

  @Enumerated(EnumType.STRING)
  private DeleteStatus deleteStatus;

  //연관관계 매핑
  // OneToMany, ManyToOne -> 1:N, N:1
  // Board : Comment = 1 : N
  // 즉시로딩과 지연로딩
  //N+1 댓글만 조회를 하고 싶은데 댓글수+보드1개 만큼 쿼리가 날아가는 문제
  //지연로딩 적용: DB가 아닌 프록시에서 데이터를 가져옴. 엔티티 (프록시)객체에서 데이터를 가져옵니다.
  // -> 이렇게 하면 n+1에서 +1 만큼 (board조회) 해결
  // n개 쿼리 발생 문제 -> JOIN FETCH 라는것을 이용
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "BOARD_NO")
  private Board board;



}
