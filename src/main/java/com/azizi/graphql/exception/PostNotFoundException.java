package com.azizi.graphql.exception;

import com.azizi.graphql.exception.base.NotFoundException;

public class PostNotFoundException extends NotFoundException {

    private static final long serialVersionUID = -1L;

    public PostNotFoundException(String id) {
        super(String.format("Post with id %s not found!", id));
    }

}
