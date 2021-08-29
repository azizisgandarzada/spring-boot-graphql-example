package com.azizi.graphql.resolver.query;

import com.azizi.graphql.dto.response.PostResponseDto;
import com.azizi.graphql.service.PostService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostQueryResolver implements GraphQLQueryResolver {

    private final PostService postService;

    public List<PostResponseDto> getPosts(Integer page, Integer size, DataFetchingEnvironment environment) {
        return postService.getPosts(page, size);
    }

    public PostResponseDto getPost(String id) {
        return postService.getPost(id);
    }

}
