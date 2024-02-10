package com.example.mocu.Dto.address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SelectUserAddressResponse {
    private long addressId;
    private String addressName;
    private String status;
}