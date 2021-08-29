package com.azizi.graphql.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponseDto {

    private String id;
    private String title;
    private String content;
    private Integer viewCount;
    private Integer likeCount;
    private String author;
    private String createdAt;

}
