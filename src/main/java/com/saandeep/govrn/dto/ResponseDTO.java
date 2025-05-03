package com.saandeep.govrn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO<T> {
    private List<String> errors;
    private String message;
    private T data;
}
