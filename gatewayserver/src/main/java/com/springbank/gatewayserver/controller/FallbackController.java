package com.springbank.gatewayserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    // fall back REST API
    @RequestMapping("/contactSupport")
    public Mono<String> contactSupport() { // since built on str reactive, wrap return str in mono
        return Mono.just("An error occurred. Please try after some time or contact support team!!!");
    }

}
