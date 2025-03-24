package com.fokou.banking.services.imple;

import com.fokou.banking.dto.AccountDto;
import com.fokou.banking.exceptions.OperationNonPermittedException;
import com.fokou.banking.models.Account;
import com.fokou.banking.repositories.AccountRepository;
import com.fokou.banking.services.AccountService;
import com.fokou.banking.validators.ObjectValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AccountServiceImple implements AccountService {

    private final AccountRepository repository;
    private final ObjectValidator<AccountDto> validator;


    @Override
    public Integer save(AccountDto dto) {
        if(dto.getId() != null){
            throw new OperationNonPermittedException("Account cannot be updated", "Save Account", "Account", "Update not permitted");
        }
        validator.validate(dto);
        Account account = AccountDto.toEntity(dto);

        boolean userHasAlreadyAnAccount = repository.findByUserId(account.getUser().getId()).isPresent();
        if(userHasAlreadyAnAccount && account.getUser().isActive()){
            throw new OperationNonPermittedException(
                    "The selected user are already an active account",
                    "Create account",
                    "Account service",
                    "Account creation"
            );
        }
        account.setIban(generateRandomIban());
        return repository.save(account).getId();
    }

    @Override
    public List<AccountDto> findAll() {
        return repository.findAll()
                .stream()
                .map(AccountDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public AccountDto findById(Integer id) {
        return repository.findById(id)
                .map(AccountDto::fromEntity).orElseThrow(() -> new EntityNotFoundException("Not account found by id :" + id));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    private String generateRandomIban(){

        String iban = Iban.random(CountryCode.DE).toFormattedString();

        boolean ibanExists = repository.findByIban(iban).isPresent();

        if (ibanExists){
            generateRandomIban();
        }

        return iban;
    }
}
