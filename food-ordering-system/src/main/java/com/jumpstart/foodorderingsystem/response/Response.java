package com.jumpstart.foodorderingsystem.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Generic response wrapper used by all API endpoints.
 * T represents the type of data being returned.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    private int statusCode;

    private String message;

    private T data;

    private LocalDateTime timestamp;

}