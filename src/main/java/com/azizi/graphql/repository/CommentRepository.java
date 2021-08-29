package com.azizi.graphql.repository;

import com.azizi.graphql.entity.Comment;
import com.azizi.graphql.entity.Post;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, UUID> {

    List<Comment> findAllByPostId(UUID id, Pageable pageable);

    void deleteAllByPost(Post post);

}
