package com.softwarealchemist.accountsservice.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CustomerDTO {

    @NotEmpty(message = "Name can't be null or empty")
    @Size(min = 5, max = 30, message = "The length of customer name should be between 5 and 30 characters long")
    private String name;

    @NotEmpty(message = "Name cant' be null or empty")
    @Email(message = "Email address should have valid format")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must have 10 digits")
    private String mobileNumber;

    private AccountsDTO accountsDTO;

}
