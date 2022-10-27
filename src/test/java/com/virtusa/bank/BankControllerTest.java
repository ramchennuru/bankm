package com.virtusa.bank;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtusa.bank.controller.BankController;
import com.virtusa.bank.entity.Bank;
import com.virtusa.bank.entity.User;
import com.virtusa.bank.repository.BankRepo;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.http.RequestEntity.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class BankControllerTest {
    @MockBean
    BankRepo bankRepo;
    @MockBean
    private BankController bankController;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void shouldReturnHundredRupeesWhenHundredRupeesAreAddedToAccountWhenInitialBalanceIsZero()
    {
        Bank bank=new Bank((long)12345678,0.0,"ram",(long)1234567891,1234,1324,(long)987654321,0.0);
        int balance=100;

        bank.setBalance(bank.getBalance()+balance);
        bankRepo.save(bank);

        assertEquals(balance,bank.getBalance());
    }

    @Test
    void shouldReturnHundredRupeesWhenHundredRupeesAreWithdrawalFromAccountWhenIAvailableBalanceIsTwoHundred()
    {
        Bank bank=new Bank((long)12345678,200.0,"ram",(long)1234567891,1234,1324,(long)987654321,0.0);
        int balance=100;

        bank.setBalance(bank.getBalance()-balance);
        bankRepo.save(bank);

        assertEquals(100,bank.getBalance());
    }

//    @Test
//    void givenAccountNumber_whenDeleteAccount_thenReturn200()throws Exception {
//        String uri = "/bankcontroller/delete/2";
//        ConsoleIOContext.AllSuggestionsCompletionTask mockMvc;
//        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//        String content = mvcResult.getResponse().getContentAsString();
//        assertEquals(content, "Account is deleted successsfully");
//    }

}
