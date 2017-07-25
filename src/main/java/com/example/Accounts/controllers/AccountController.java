package com.example.Accounts.controllers;

import com.example.Accounts.services.AccountService;
import com.example.Accounts.domains.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

/**
 * Created by Gideon D Manurung on 7/11/2017.
 */
@RestController
@RequestMapping(value = "/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/all" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Flux<List<Account>> getAllAccount(){
        return accountService.findAllAccount();
    }

    @GetMapping(value = "/allAsStream" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Flux<Account> getAllAccountAsStream(){
        return accountService.findAllAccountAsStream();
    }

    @GetMapping(value = "/findMono/{idAccount}" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Mono<Account> getAccountByIdMono(@PathVariable("idAccount") Integer idAccount){
        return accountService.findAccountsByIdMono(idAccount);
    }

    @GetMapping(value = "/find/{idAccount}" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Mono<Account> getAccountById(@PathVariable("idAccount") Integer idAccount){
        return Mono.just(accountService.findAccountById(idAccount));
    }

    @GetMapping(value = "/findByUsername/{username}" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Mono<Account> getAccountByUsername(@PathVariable("username") String username){
        return Mono.just(accountService.findAccountsByUsername(username));
    }

    @GetMapping(value = "/findByAddress/{address}" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Flux<List<Account>> getAccountByAddress(@PathVariable("address") String address){
        return Flux.just(accountService.findAccountsByAddress(address));
    }

    @GetMapping(value = "/findByName/{name}" , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Flux<List<Account>> getAccountByName(@PathVariable("name") String name){
        return Flux.just(accountService.findAccountsByName(name));
    }

    @PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Mono<Account> saveAccount(@RequestBody Account account){
        return accountService.saveOrUpdateAccount(account);
    }

    @PostMapping(value = "/newMono", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Mono<Account> saveAccountMono(@RequestBody Mono<Account> account){
        return accountService.saveOrUpdateAccountMono(account);
    }

    @PostMapping(value = "/update/{idAccount}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Mono<Account> updateAccount(@RequestBody Account account, @PathVariable("idAccount") Integer idAccount){
        Account accountUpdate = accountService.findAccountById(idAccount);
        accountUpdate.setUsername(account.getUsername());
        accountUpdate.setAddress(account.getAddress());
        accountUpdate.setName(account.getName());
        accountUpdate.setPassword(account.getPassword());
        return accountService.saveOrUpdateAccount(accountUpdate);
    }
}
