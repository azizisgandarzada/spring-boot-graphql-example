package com.azizi.graphql.mapper;

import com.azizi.graphql.dto.request.CreateCommentRequestDto;
import com.azizi.graphql.dto.request.UpdateCommentRequestDto;
import com.azizi.graphql.dto.response.CommentResponseDto;
import com.azizi.graphql.entity.Comment;
import com.azizi.graphql.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface CommentMapper {


    @Mapping(target = "id", expression = "java(comment.getId().toString())")
    @Mapping(target = "createdAt", dateFormat = "dd-MM-yyyy HH:mm:ss")
    CommentResponseDto toDto(Comment comment);

    @Mapping(target = "author", source = "post.author")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "post", source = "post")
    Comment toEntity(CreateCommentRequestDto createCommentRequestDto, Post post);

    @Mapping(target = "id", ignore = true)
    Comment toEntity(UpdateCommentRequestDto updateCommentRequestDto, @MappingTarget Comment comment);

}
