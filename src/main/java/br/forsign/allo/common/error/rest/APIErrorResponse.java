package br.forsign.allo.common.error.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author kaiooliveira
 * created 18/06/2024
 */
@Getter
@AllArgsConstructor
public class APIErrorResponse {

    private String message;
    private int code;
    private String status;
    private LocalDateTime timestamp;
    private String description;
    private List<String> errors;

}
