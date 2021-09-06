package com.example.projection;

import com.example.projection.model.Account;
import com.example.projection.model.Company;
import com.example.projection.model.Person;
import com.example.projection.repository.projections.AccountAdvancedProjection;
import com.example.projection.repository.AccountRepository;
import com.example.projection.repository.CompanyRepository;
import com.example.projection.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class AccountRepositoryTest {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private CompanyRepository companyRepository;

    private Account account;

    @BeforeEach
    void setUp() {
        account = Account.builder()
                .accountNumber("ABC123")
                .accountName("Tamara")
                .build();
        accountRepository.save(account);
    }

    @Test
    void findAccountByNameWithOwner() {
        Company company = new Company("Company XYZ");
        companyRepository.save(company);
        Person person = Person.builder()
                        .company(company)
                                .build();
        personRepository.save(person);
        account.setOwner(person);
        AccountAdvancedProjection foundAccount = accountRepository.findByAccountNumber("ABC123");
        assertEquals(foundAccount.getOwner().getCompany().getName(), company.getName());
        assertEquals(foundAccount.getOwner().getId(), person.getId());
    }
}