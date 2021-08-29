package com.azizi.graphql.service;

import com.azizi.graphql.dto.request.CreateCommentRequestDto;
import com.azizi.graphql.dto.request.UpdateCommentRequestDto;
import com.azizi.graphql.dto.response.CommentResponseDto;
import com.azizi.graphql.entity.Comment;
import com.azizi.graphql.entity.Post;
import com.azizi.graphql.exception.CommentNotFoundException;
import com.azizi.graphql.exception.PostNotFoundException;
import com.azizi.graphql.mapper.CommentMapper;
import com.azizi.graphql.repository.CommentRepository;
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
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Transactional(readOnly = true)
    public List<CommentResponseDto> getComments(String postId, Integer page, Integer size) {
        Post post = postRepository.findById(UUID.fromString(postId))
                .orElseThrow(() -> new PostNotFoundException(postId));
        return commentRepository.findAllByPostId(post.getId(), PageRequest.of(page, size))
                .stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentResponseDto createComment(CreateCommentRequestDto createCommentRequestDto) {
        Post post = postRepository.findById(UUID.fromString(createCommentRequestDto.getPostId()))
                .orElseThrow(() -> new PostNotFoundException(createCommentRequestDto.getPostId()));
        Comment comment = commentMapper.toEntity(createCommentRequestDto, post);
        commentRepository.save(comment);
        return commentMapper.toDto(comment);
    }

    @Transactional
    public CommentResponseDto updateComment(UpdateCommentRequestDto updateCommentRequestDto) {
        Comment comment = commentRepository.findById(UUID.fromString(updateCommentRequestDto.getId()))
                .orElseThrow(() -> new CommentNotFoundException(updateCommentRequestDto.getId()));
        comment = commentMapper.toEntity(updateCommentRequestDto, comment);
        commentRepository.save(comment);
        return commentMapper.toDto(comment);
    }

    @Transactional
    public Boolean deleteComment(String id) {
        commentRepository.findById(UUID.fromString(id)).ifPresentOrElse(commentRepository::delete, () -> {
            throw new CommentNotFoundException(id);
        });
        return true;
    }

    public void deleteComments(Post post) {
        commentRepository.deleteAllByPost(post);
    }

}
