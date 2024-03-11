package com.springbank.accounts.service.client;

import com.springbank.accounts.dto.LoansDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="loans", fallback = LoansFallback.class)
public interface LoansFeignClient {

    // abstract method should match exactly with method of cards MS
    @GetMapping(value = "/api/fetch", consumes = "application/json")
    public ResponseEntity<LoansDto> fetchLoanDetails(@RequestHeader("springBank-correlation-id")
                                                         String correlationId, @RequestParam String mobileNumber);

}
