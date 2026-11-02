package br.com.zenon.fraud.service;

import br.com.zenon.fraud.api.io.TransactionUtils;
import br.com.zenon.fraud.domain.enums.Type;
import br.com.zenon.fraud.domain.records.Transaction;
import br.com.zenon.fraud.domain.records.TransactionCustomer;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.IO.println;

public class TransactionRepositoryMap implements TransactionRepository{

    Map<String, Transaction> map = new HashMap<>();

    public TransactionRepositoryMap() {

    }

    @Override
    public void ReadLines(String fileName, int limit) {

        try (Stream<String> lines = Files.lines(Path.of(fileName), StandardCharsets.UTF_8)) {
            map =  lines.skip(1).limit(limit)
                    .map(linha -> linha.split(","))
                    .map(TransactionUtils::createTransactionFromArray)
                    .flatMap(Optional::stream)
                    .collect(Collectors.toMap(
                            t -> t.origin().name(),
                            t -> t
                    ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Transaction> FindTransaction(String costumerOriginName) {
        return Optional.ofNullable(map.get(costumerOriginName));
    }
}
