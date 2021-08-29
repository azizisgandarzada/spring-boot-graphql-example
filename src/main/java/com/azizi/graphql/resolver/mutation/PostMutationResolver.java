package com.azizi.graphql.resolver.mutation;

import com.azizi.graphql.dto.request.CreatePostRequestDto;
import com.azizi.graphql.dto.request.UpdatePostRequestDto;
import com.azizi.graphql.dto.response.PostResponseDto;
import com.azizi.graphql.service.PostService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@RequiredArgsConstructor
@Validated
public class PostMutationResolver implements GraphQLMutationResolver {

    private final PostService postService;

    public PostResponseDto createPost(@Valid CreatePostRequestDto createPostRequestDto) {
        return postService.createPost(createPostRequestDto);
    }

    public PostResponseDto updatePost(@Valid UpdatePostRequestDto updatePostRequestDto) {
        return postService.updatePost(updatePostRequestDto);
    }

    public Boolean deletePost(@NotBlank String id) {
        return postService.deletePost(id);
    }

}
