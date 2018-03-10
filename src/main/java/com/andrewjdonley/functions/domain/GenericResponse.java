package com.andrewjdonley.functions.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse<T> {

    public GenericResponse(T data) {
        this.setData(data);
        this.setError(false);
        this.setMessage("Ok");
    }

    private String message;
    private boolean error;
    private T Data;
}
