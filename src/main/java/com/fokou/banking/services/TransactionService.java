package com.fokou.banking.services;

import com.fokou.banking.dto.TransactionDto;

import java.util.List;

public interface TransactionService extends AbstractService<TransactionDto>{

    List<TransactionDto> findallByUserId(Integer userId);
}
