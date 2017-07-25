package com.example.Accounts.services;

import com.example.Accounts.domains.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Created by Gideon D Manurung on 7/11/2017.
 */
public interface AccountService {
    Account findAccountById(Integer idAccount);
    Mono<Account> findAccountsByIdMono(Integer idAccount);
    Account findAccountsByUsername(String username);
    List<Account> findAccountsByAddress(String address);
    List<Account> findAccountsByName(String name);
    Flux<List<Account>> findAllAccount();
    Flux<Account> findAllAccountAsStream();
    Mono<Account> saveOrUpdateAccount(Account account);
    Mono<Account> saveOrUpdateAccountMono(Mono<Account> account);
}
