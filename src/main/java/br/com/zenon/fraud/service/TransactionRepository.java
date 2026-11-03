package br.com.zenon.fraud.service;


import br.com.zenon.fraud.domain.records.Transaction;

import java.util.Collection;
import java.util.Optional;

public interface TransactionRepository {


    void ReadLines(String fileName, int limit);

    Optional<Transaction> FindTransaction(String costumerOriginName);
}