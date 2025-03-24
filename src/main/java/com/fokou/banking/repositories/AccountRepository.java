package com.fokou.banking.repositories;

import com.fokou.banking.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    List<Account>findAllByIban(String iban);


    Optional<Account> findByIban(String iban);

    Optional<Account> findByUserId(Integer id);
}
