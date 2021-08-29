package com.azizi.graphql.resolver.mutation;

import com.azizi.graphql.dto.request.CreateCommentRequestDto;
import com.azizi.graphql.dto.request.UpdateCommentRequestDto;
import com.azizi.graphql.dto.response.CommentResponseDto;
import com.azizi.graphql.service.CommentService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@RequiredArgsConstructor
@Validated
public class CommentMutationResolver implements GraphQLMutationResolver {

    private final CommentService commentService;

    public CommentResponseDto createComment(@Valid CreateCommentRequestDto createCommentRequestDto) {
        return commentService.createComment(createCommentRequestDto);
    }

    public CommentResponseDto updateComment(@Valid UpdateCommentRequestDto updateCommentRequestDto) {
        return commentService.updateComment(updateCommentRequestDto);
    }

    public Boolean deleteComment(@NotBlank String id) {
        return commentService.deleteComment(id);
    }

}
