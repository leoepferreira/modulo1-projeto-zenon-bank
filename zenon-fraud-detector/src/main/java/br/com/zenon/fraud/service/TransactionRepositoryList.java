package br.com.zenon.fraud.service;

import br.com.zenon.fraud.api.io.TransactionIngestor;
import br.com.zenon.fraud.domain.records.Transaction;

import java.util.Objects;
import java.util.Optional;

public class TransactionRepositoryList implements TransactionRepository {

    TransactionIngestor  transactionIngestor;

    public TransactionRepositoryList() {

    }

    @Override
    public void ReadLines(String fileName, int limit) {
        this.transactionIngestor = new TransactionIngestor(fileName);
        transactionIngestor.readLines(limit);
    }

    @Override
    public Optional<Transaction> FindTransaction( String costumerOriginName) {
        return transactionIngestor.getTransactions().stream().filter(transaction ->
            Objects.equals(transaction.origin().name(),costumerOriginName)).findFirst();
    }

    public void ReadAllLines(String fileName) {
        this.transactionIngestor = new TransactionIngestor(fileName);
        transactionIngestor.readAllLines();
    }
}
