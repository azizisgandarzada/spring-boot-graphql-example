package com.azizi.graphql.mapper;

import com.azizi.graphql.dto.response.HashtagResponseDto;
import com.azizi.graphql.entity.Hashtag;
import com.azizi.graphql.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface HashtagMapper {

    @Mapping(target = "id", expression = "java(hashtag.getId().toString())")
    HashtagResponseDto toDto(Hashtag hashtag);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "hashtag")
    Hashtag toEntity(String hashtag, Post post);



}
