package br.com.zenon.fraud.api.io;


import br.com.zenon.fraud.domain.enums.Type;
import br.com.zenon.fraud.domain.records.Transaction;
import br.com.zenon.fraud.domain.records.TransactionCustomer;

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
                    .map(this::createTransactionFromArray)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList();
            return transactions;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Optional<Transaction> createTransactionFromArray(String[] linha) {

        try {
            return Optional.of( new Transaction(Integer.parseInt(linha[0]),
                    Type.valueOf(linha[1]),
                    new BigDecimal(linha[2]),
                    new TransactionCustomer(linha[3],
                            new BigDecimal(linha[4]),
                            new BigDecimal(linha[5])),
                    new TransactionCustomer(linha[6],
                            new BigDecimal(linha[7]),
                            new BigDecimal(linha[8])),
                    "1".equals(linha[9]),
                    "1".equals(linha[10])));
        } catch (IllegalArgumentException e) {
            println("Erro: "+String.join(",", linha) +" | " + e);
        }
        return Optional.empty();
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
