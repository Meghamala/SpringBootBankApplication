package com.springbank.accounts.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

// this class makes sures there are only getter methods and no setter methods to make changes to fields
// all params are final and java creates a constructor to initialize. Can only initialize once

@ConfigurationProperties(prefix = "accounts")
public record AccountsContactInfoDto(String message, Map<String, String> contactDetails, List<String> onCallSupport) {
}
