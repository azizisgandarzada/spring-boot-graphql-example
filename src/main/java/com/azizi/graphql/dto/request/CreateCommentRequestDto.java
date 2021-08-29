package com.azizi.graphql.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCommentRequestDto {

    @NotBlank
    private String description;

    @NotBlank
    private String author;

    @NotBlank
    private String postId;

}
