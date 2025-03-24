package com.fokou.banking.dto;

import com.fokou.banking.models.Transaction;
import com.fokou.banking.models.TransactionType;
import com.fokou.banking.models.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;


@Setter
@Getter
@Builder
@AllArgsConstructor
public class TransactionDto {

    @Positive
    private BigDecimal amount;
    private TransactionType type;
    private String destinationIban;
    private Integer id;
    private LocalDate transactionDate;
    private Integer userId;

    public static TransactionDto fromEntity(Transaction transaction){
        return TransactionDto.builder()
                .id(transaction.getId())
                .type(transaction.getType())
                .transactionDate(transaction.getTransactionDate())
                .destinationIban(transaction.getDestinationIban())
                .amount(transaction.getAmount())
                .userId(transaction.getUser().getId())
                .build();
    }

    public static Transaction toEntity(TransactionDto transaction){
        return Transaction.builder()
                .id(transaction.getId())
                .type(transaction.getType())
                .transactionDate(LocalDate.now())
                .destinationIban(transaction.getDestinationIban())
                .amount(transaction.getAmount())
                .user(
                        User.builder().id(transaction.getId()).build()
                )
                .build();
    }

}
