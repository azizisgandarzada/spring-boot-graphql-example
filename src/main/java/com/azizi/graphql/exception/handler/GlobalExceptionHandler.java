package com.azizi.graphql.exception.handler;

import com.azizi.graphql.exception.base.NotFoundException;
import graphql.GraphQLException;
import graphql.kickstart.spring.error.ThrowableGraphQLError;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ThrowableGraphQLError handleException(NotFoundException ex) {
        return new ThrowableGraphQLError(ex);
    }

    @ExceptionHandler(GraphQLException.class)
    public ThrowableGraphQLError handleException(GraphQLException ex) {
        return new ThrowableGraphQLError(ex);
    }

    @ExceptionHandler(Throwable.class)
    public ThrowableGraphQLError handleException(Throwable ex) {
        return new ThrowableGraphQLError(ex);
    }

}
