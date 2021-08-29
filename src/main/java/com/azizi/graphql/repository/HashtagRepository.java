package com.azizi.graphql.repository;

import com.azizi.graphql.entity.Hashtag;
import com.azizi.graphql.entity.Post;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashtagRepository extends JpaRepository<Hashtag, UUID> {

    List<Hashtag> findAllByPostId(UUID id);

    void deleteAllByPost(Post post);

}
