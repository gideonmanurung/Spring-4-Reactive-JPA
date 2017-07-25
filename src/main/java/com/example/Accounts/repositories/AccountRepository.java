package com.example.Accounts.repositories;

import com.example.Accounts.domains.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by Gideon D Manurung on 7/11/2017.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
//    @Async
//    Mono<Account> findAccountsByIdAccount(Integer idAccount);

    //@Async
    Account findAccountsByUsername(String username);

    //@Async
    List<Account> findAccountsByAddress(String address);

    //@Async
    List<Account> findAccountsByName(String name);

    @Query("select account from Account account")
    Stream<Account> findAllAccount();
}
