package com.user.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public interface ToJson {

    @SneakyThrows
    default String toJson(){
        return new ObjectMapper().writeValueAsString(this);
    }
}
