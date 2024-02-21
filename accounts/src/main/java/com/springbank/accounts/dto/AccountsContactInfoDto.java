package com.springbank.accounts.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

// this class makes sures there are only getter methods and no setter methods to make changes to fields
// all params are final and java creates a constructor to initialize. Can only initialize once

// converting record to class for enabling run time configuration changes
@ConfigurationProperties(prefix = "accounts")
@Getter
@Setter
public class AccountsContactInfoDto {

    private String message;
    private Map<String, String> contactDetails;
    private List<String> onCallSupport;
}
