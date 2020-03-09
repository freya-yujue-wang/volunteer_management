package com.example.volunteer_management.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Response {
    private int isSuccessful;
    private String message;
    private Object obj;
}
