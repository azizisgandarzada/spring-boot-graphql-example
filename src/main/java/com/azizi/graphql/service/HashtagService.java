package com.azizi.graphql.service;

import com.azizi.graphql.dto.response.HashtagResponseDto;
import com.azizi.graphql.entity.Hashtag;
import com.azizi.graphql.entity.Post;
import com.azizi.graphql.mapper.HashtagMapper;
import com.azizi.graphql.repository.HashtagRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HashtagService {

    private final HashtagRepository hashtagRepository;
    private final HashtagMapper hashtagMapper;

    @Transactional(readOnly = true)
    public List<HashtagResponseDto> getHashtags(String postId) {
        return hashtagRepository.findAllByPostId(UUID.fromString(postId))
                .stream()
                .map(hashtagMapper::toDto)
                .collect(Collectors.toList());
    }

    public void createHashtags(List<String> hashtags, Post post) {
        if (hashtags == null) {
            return;
        }
        List<Hashtag> tags = hashtags.stream()
                .map(hashtag -> hashtagMapper.toEntity(hashtag, post))
                .collect(Collectors.toList());
        hashtagRepository.saveAll(tags);
    }

    public void updateHashtags(List<String> hashtags, Post post) {
        hashtagRepository.deleteAllByPost(post);
        createHashtags(hashtags, post);
    }

    public void deleteHashtags(Post post) {
        hashtagRepository.deleteAllByPost(post);
    }

}
