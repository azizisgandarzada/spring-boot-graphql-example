package com.azizi.graphql.mapper;

import com.azizi.graphql.dto.request.CreatePostRequestDto;
import com.azizi.graphql.dto.request.UpdatePostRequestDto;
import com.azizi.graphql.dto.response.PostResponseDto;
import com.azizi.graphql.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface PostMapper {


    @Mapping(target = "id", expression = "java(post.getId().toString())")
    @Mapping(target = "createdAt", dateFormat = "dd-MM-yyyy HH:mm:ss")
    PostResponseDto toDto(Post post);

    @Mapping(target = "likeCount", expression = "java(0)")
    @Mapping(target = "viewCount", expression = "java(0)")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    Post toEntity(CreatePostRequestDto createPostRequestDto);

    @Mapping(target = "id", ignore = true)
    Post toEntity(UpdatePostRequestDto updatePostRequestDto, @MappingTarget Post post);

}
