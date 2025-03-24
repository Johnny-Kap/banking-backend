package com.fokou.banking.services.imple;

import com.fokou.banking.dto.TransactionDto;
import com.fokou.banking.models.Transaction;
import com.fokou.banking.models.TransactionType;
import com.fokou.banking.models.User;
import com.fokou.banking.repositories.TransactionRepository;
import com.fokou.banking.repositories.UserRepository;
import com.fokou.banking.services.TransactionService;
import com.fokou.banking.services.UserService;
import com.fokou.banking.validators.ObjectValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TransactionServiceImple implements TransactionService {

    private final TransactionRepository repository;

    private final UserRepository userRepository;
    private final ObjectValidator<TransactionDto> validator;

    @Override
    public Integer save(TransactionDto dto) {
        validator.validate(dto);
        Transaction transaction = TransactionDto.toEntity(dto);

        if (dto.getUserId() != null){

            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));
            transaction.setUser(user);
        }

        BigDecimal transactionMultiplier = BigDecimal.valueOf(getTransactionMultiplier(transaction.getType()));
        BigDecimal amount = transaction.getAmount().multiply(transactionMultiplier);
        transaction.setAmount(amount);
        return repository.save(transaction).getId();
    }

    @Override
    public List<TransactionDto> findAll() {
        return repository.findAll()
                .stream()
                .map(TransactionDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public TransactionDto findById(Integer id) {
        return repository.findById(id)
                .map(TransactionDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No transaction found by id : " + id));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    private int getTransactionMultiplier(TransactionType type){

        return TransactionType.TRANSFERT == type ? 1 : -1;
    }

    @Override
    public List<TransactionDto> findallByUserId(Integer userId) {

        return repository.findAllByUserId(userId)
                .stream()
                .map(TransactionDto::fromEntity).collect(Collectors.toList());
    }
}
