package com.azizi.graphql.service;

import com.azizi.graphql.dto.request.CreatePostRequestDto;
import com.azizi.graphql.dto.request.UpdatePostRequestDto;
import com.azizi.graphql.dto.response.PostResponseDto;
import com.azizi.graphql.entity.Post;
import com.azizi.graphql.exception.PostNotFoundException;
import com.azizi.graphql.mapper.PostMapper;
import com.azizi.graphql.repository.PostRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final HashtagService hashtagService;
    private final CommentService commentService;

    @Transactional(readOnly = true)
    public List<PostResponseDto> getPosts(Integer page, Integer size) {
        return postRepository.findAll(PageRequest.of(page, size))
                .stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PostResponseDto getPost(String id) {
        return postRepository.findById(UUID.fromString(id))
                .map(postMapper::toDto)
                .orElseThrow(() -> new PostNotFoundException(id));
    }

    @Transactional
    public PostResponseDto createPost(CreatePostRequestDto createPostRequestDto) {
        Post post = postMapper.toEntity(createPostRequestDto);
        postRepository.save(post);
        hashtagService.createHashtags(createPostRequestDto.getHashtags(), post);
        return postMapper.toDto(post);
    }

    @Transactional
    public PostResponseDto updatePost(UpdatePostRequestDto updatePostRequestDto) {
        Post post = postRepository.findById(UUID.fromString(updatePostRequestDto.getId()))
                .orElseThrow(() -> new PostNotFoundException(updatePostRequestDto.getId()));
        post = postMapper.toEntity(updatePostRequestDto, post);
        postRepository.save(post);
        hashtagService.updateHashtags(updatePostRequestDto.getHashtags(), post);
        return postMapper.toDto(post);
    }

    @Transactional
    public Boolean deletePost(String id) {
        postRepository.findById(UUID.fromString(id)).ifPresentOrElse(post -> {
            hashtagService.deleteHashtags(post);
            commentService.deleteComments(post);
            postRepository.delete(post);
        }, () -> {
            throw new PostNotFoundException(id);
        });
        return true;
    }

}
