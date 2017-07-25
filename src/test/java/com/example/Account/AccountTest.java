package com.example.Account;

import com.example.Accounts.controllers.AccountController;
import com.example.Accounts.domains.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AccountTest {
    @Autowired
    private AccountController accountController;

    @Test
    public void testFindAccountById1() {
        Mono<Account> account = accountController.getAccountById(1);
        assert account.block().getName().matches("Gideon");
    }

    @Test
    public void testFindAccountById2() {
        Mono<Account> account = accountController.getAccountById(7);
        assert account.block().getName().matches("Gideon");
    }

    @Test
    public void testFindAccountById3() {
        Mono<Account> account = accountController.getAccountById(4);
        assert account.block().getName().matches("Patrick");
    }

//    @Test
//    public void testFindAllAccount() {
//        Flux<Account> accountFlux = accountController.getAllAccountAsStream();
//        assert accountFlux.buffer()
//    }

    @Test
    public void testCreateNewAccount() {
        Account account = new Account("gideon","1234","Gideon","Del");
        assert accountController.saveAccount(account).block().getName().matches("Gideon");
    }
}
