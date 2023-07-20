package com.jfantou.demo.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ErrorResponse {
    private final int status;
    private final List<String> messageError;
    private final LocalDateTime timestamp;
    private final int countError;
}