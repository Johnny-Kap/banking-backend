package com.fokou.banking.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Transaction extends AbstractEntity {

    private BigDecimal amount;

    @Column(updatable = false)
    private LocalDate transactionDate;

    @Enumerated(EnumType.STRING)
    private TransactionType type;
    private String destinationIban;


    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
}
