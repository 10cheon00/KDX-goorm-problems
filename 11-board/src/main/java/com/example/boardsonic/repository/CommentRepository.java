package com.example.boardsonic.repository;

import com.example.boardsonic.model.DeleteStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.boardsonic.model.entity.Comment;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c from Comment c LEFT JOIN FETCH c.board b Where c.commentNo = :commentNo and c.deleteStatus = :deleteStatus")
    Optional<Comment> findByCommentNoAndDeleteStatus(@Param("commentNo") Long commentNo, @Param("deleteStatus") DeleteStatus deleteStatus);
}
