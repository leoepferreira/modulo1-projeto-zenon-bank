package br.com.zenon.fraud.api.io;


import br.com.zenon.fraud.domain.enums.Type;
import br.com.zenon.fraud.domain.records.Transaction;
import br.com.zenon.fraud.api.io.TransactionUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.lang.IO.println;

public  class  TransactionIngestor {

    List<Transaction> transactions = new ArrayList<>();
    Path filePath;

    public TransactionIngestor(String fileName) {
        this.filePath = Path.of(fileName);
    }


    public List<Transaction> readLines(int linesRead) {
        try (Stream<String> lines = Files.lines(filePath, StandardCharsets.UTF_8)) {
            transactions =  lines.skip(1).limit(linesRead)
                    .map(linha -> linha.split(","))
                    .map(TransactionUtils::createTransactionFromArray)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList();
            return transactions;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printLines(int linesPrint) {
        transactions.stream().limit(linesPrint).forEach(System.out::println);
    }

    public int size() {
        return transactions.size();
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
