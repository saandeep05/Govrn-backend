package com.saandeep.govrn.service;

import com.saandeep.govrn.dto.EntityDTO;

import java.util.List;

public class BaseService<T> {
    public EntityDTO<T> getResponseDTO(List<String> errors, String message, T data) {
        return new EntityDTO<>(errors, message, data);
    }

    public String getEntityNotFound(Class<?> className) {
        return "Entity of type " + className + " not found";
    }
}
