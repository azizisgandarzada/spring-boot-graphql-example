package com.azizi.graphql.resolver.query;

import com.azizi.graphql.dto.response.CommentResponseDto;
import com.azizi.graphql.service.CommentService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentQueryResolver implements GraphQLQueryResolver {

    private final CommentService commentService;

    public List<CommentResponseDto> getComments(String postId, Integer page, Integer size) {
        return commentService.getComments(postId, page, size);
    }

}
