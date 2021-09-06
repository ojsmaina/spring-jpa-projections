package com.example.projection.repository;

import com.example.projection.model.Account;
import com.example.projection.repository.projections.AccountAdvancedProjection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    AccountAdvancedProjection findByAccountNumber(String accountNumber);
}
