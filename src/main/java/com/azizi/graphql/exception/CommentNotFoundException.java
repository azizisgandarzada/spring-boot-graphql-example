package com.azizi.graphql.exception;

import com.azizi.graphql.exception.base.NotFoundException;

public class CommentNotFoundException extends NotFoundException {

    private static final long serialVersionUID = -1L;

    public CommentNotFoundException(String id) {
        super(String.format("Comment with id %s not found!", id));
    }

}
