package io.bookquest.entrypoint.v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OTPEntrypoint(@JsonProperty("email") String email,
                            @JsonProperty("otp_type") String type,
                            @JsonProperty("phone") String phone,
                            @JsonProperty("otp") String otpNumber) {
}
