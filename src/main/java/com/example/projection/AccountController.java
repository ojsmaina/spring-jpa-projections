package com.example.projection;

import com.example.projection.repository.AccountRepository;
import com.example.projection.repository.projections.AccountAdvancedProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "accounts")
public class AccountController {

    /**
     * Direct access to repository for simplicity
     */
    private final AccountRepository accountRepository;

    @GetMapping("")
    @ResponseBody
    public AccountAdvancedProjection getAccount(@RequestParam("number") String number) {
        return accountRepository.findByAccountNumber(number);
    }
}
