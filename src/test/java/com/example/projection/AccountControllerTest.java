package com.example.projection;

import com.example.projection.model.Account;
import com.example.projection.model.Company;
import com.example.projection.model.Person;
import com.example.projection.repository.AccountRepository;
import com.example.projection.repository.CompanyRepository;
import com.example.projection.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

    @Mock
    AccountRepository accountRepository;

    @Mock
    PersonRepository personRepository;

    @Mock
    CompanyRepository companyRepository;

    @InjectMocks
    AccountController accountController;

    @Autowired
    MockMvc mockMvc;

    Account account;

    @BeforeEach
    void setUp() {
        Company company = new Company("Company XYZ");
        companyRepository.save(company);
        Person person = Person.builder()
                .company(company)
                .build();
        personRepository.save(person);

        account = Account.builder()
                .accountName("Test account")
                .accountNumber("ABC123")
                .owner(person).
                build();
        accountRepository.save(account);
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @Test
    void getAccount() throws Exception {
        mockMvc.perform(get("/accounts/").
                        param("number", "ABC123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();
    }
}