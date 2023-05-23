package com.cdtn.computerstore.service;

import com.cdtn.computerstore.entity.Transactions;
import com.cdtn.computerstore.repository.transactions.ITransactionsRepository;
import org.springframework.stereotype.Service;


@Service
public class TransactionsService implements ITransactionsService{
    private final ITransactionsRepository iTransactionsRepository;

    public TransactionsService(ITransactionsRepository iTransactionsRepository) {
        this.iTransactionsRepository = iTransactionsRepository;
    }

    @Override
    public void saveDataResponse(Transactions payload) {
        iTransactionsRepository.save(payload);
    }
}
