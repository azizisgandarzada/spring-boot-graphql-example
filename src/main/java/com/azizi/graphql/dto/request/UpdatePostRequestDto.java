package com.azizi.graphql.dto.request;

import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdatePostRequestDto {

    @NotBlank
    private String id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private List<String> hashtags;

}
