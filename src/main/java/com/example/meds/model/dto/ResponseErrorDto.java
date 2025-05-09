package com.example.meds.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseErrorDto {
    private String message;
    private LocalDateTime dateTime;
    private int code;

    public ResponseErrorDto(String message, int code){
        this.message = message;
        this.code = code;
        this.dateTime = LocalDateTime.now();
    }
}
