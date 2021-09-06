package com.example.projection.repository.projections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AccountBasicProjection {
    String accountNumber;
    String accountName;
}
