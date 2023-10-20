package com.softwarealchemist.accountsservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(
        name = "ErrorResponseDTO",
        description = "Schema to hold error response information."
)
@Data
@AllArgsConstructor
public class ErrorResponseDTO {

    @Schema(
            description = "API path invoked by the client"
    )
    private String apiPath;

    @Schema(
            description = "Error code to represent wtf is going on"
    )
    private HttpStatus errorCode;

    @Schema(
            description = "Error message representing the error response"
    )
    private String errorMessage;

    @Schema(
            description = "The time when error happened"
    )
    private LocalDateTime errorTime;

}
