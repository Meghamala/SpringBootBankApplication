package com.springbank.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor // data doesnot create constructor
public class ResponseDto {

    private String statusCode;

    private String statusMsg;
}
