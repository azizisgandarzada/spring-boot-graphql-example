package com.azizi.graphql.resolver.field;

import com.azizi.graphql.dto.response.HashtagResponseDto;
import com.azizi.graphql.dto.response.PostResponseDto;
import com.azizi.graphql.service.HashtagService;
import graphql.kickstart.tools.GraphQLResolver;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PostFieldResolver implements GraphQLResolver<PostResponseDto> {

    private final HashtagService hashtagService;

    @Transactional(readOnly = true)
    public List<HashtagResponseDto> getHashtags(PostResponseDto postResponseDto) {
        return hashtagService.getHashtags(postResponseDto.getId());
    }

}
