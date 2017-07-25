package com.example.Accounts.services.implementations;

import com.example.Accounts.domains.Account;
import com.example.Accounts.repositories.AccountRepository;
import com.example.Accounts.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

/**
 * Created by Gideon D Manurung on 7/11/2017.
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account findAccountById(Integer idAccount) {
        return accountRepository.findOne(idAccount);
//      return Mono.justOrEmpty(accountRepository.getOne(idAccount));
//      return Mono.justOrEmpty(accountRepository.getOne(idAccount)).subscribeOn(Schedulers.elastic());
//      return Mono.defer(()->Mono.justOrEmpty(accountRepository.getOne(idAccount)))
//                .subscribeOn(Schedulers.elastic())
//                .map(account -> new Account(account.getUsername(),account.getPassword(),account.getName(),account.getAddress()));
    }

    @Override
    public Mono<Account> findAccountsByIdMono(Integer idAccount) {
//      return Mono.justOrEmpty(accountRepository.getOne(idAccount));
      return Mono.justOrEmpty(accountRepository.getOne(idAccount)).subscribeOn(Schedulers.elastic());
//      return Mono.defer(()->Mono.justOrEmpty(accountRepository.getOne(idAccount)))
//                .subscribeOn(Schedulers.elastic())
//                .map(account -> new Account(account.getUsername(),account.getPassword(),account.getName(),account.getAddress()));
    }

    @Override
    public Account findAccountsByUsername(String username) {
        return accountRepository.findAccountsByUsername(username);
    }

    @Override
    public List<Account> findAccountsByAddress(String address) {
        return accountRepository.findAccountsByAddress(address);
    }

    @Override
    public List<Account> findAccountsByName(String name) {
        return accountRepository.findAccountsByName(name);
    }

    @Override
    public Flux<List<Account>> findAllAccount() {
        return Flux.just(accountRepository.findAll());
    }

    @Transactional
    @Override
    public Flux<Account> findAllAccountAsStream() {
        return Flux.fromStream(accountRepository.findAllAccount()).publishOn(Schedulers.elastic());
    }

    @Override
    public Mono<Account> saveOrUpdateAccount(Account account) {
        return Mono.just(accountRepository.save(account));

    }

    @Override
    public Mono<Account> saveOrUpdateAccountMono(Mono<Account> account) {
//        return account.then(message -> {
//            return Mono.just(accountRepository.save(message));
//        });

        return account.map(accountSave ->
                new Account(accountSave.getUsername(),accountSave.getPassword(),accountSave.getName(),accountSave.getAddress()))
                .publishOn(Schedulers.parallel());
    }
}
