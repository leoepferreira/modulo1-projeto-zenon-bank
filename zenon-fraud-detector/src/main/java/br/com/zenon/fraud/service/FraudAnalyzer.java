package br.com.zenon.fraud.service;

import br.com.zenon.fraud.domain.enums.Type;
import br.com.zenon.fraud.domain.records.Transaction;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.IO.println;

public  class FraudAnalyzer {

    public static void printFraudsCount(List<Transaction> transactions) {
        Objects.requireNonNull(transactions);
        long fraudsCount = transactions.stream()
                .filter(Transaction::isFraud)
                .count();
        println(fraudsCount);
    }

    public static void printBiggestAmountsFrauds(List<Transaction> transactions, int n){
        Objects.requireNonNull(transactions);

        String biggestAmountsFrauds = transactions.stream()
                .filter(Objects::nonNull)
                .filter(Transaction::isFraud)
                .sorted(Comparator.comparing(Transaction::amount).reversed())
                .map(t -> String.format("%.2f", t.amount()))
                .limit(n).collect(Collectors.joining("\n"));

        println(biggestAmountsFrauds);
    }

    public static void majorSuspects (List<Transaction> transactions, int n) {
        Objects.requireNonNull(transactions);

        Map<String, BigDecimal> suspectAmounts = transactions.stream()
                .filter(Transaction::isFraud)
                .collect(Collectors.groupingBy(
                        t -> t.origin().name(),
                        Collectors.reducing(BigDecimal.ZERO, Transaction::amount, BigDecimal::add)
                ));

        String majorSuspects = suspectAmounts.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(n)
                .map(Map.Entry::getKey)
                .collect(Collectors.joining("\n"));

        println(majorSuspects);
    }

    public static void printFraudsSum(List<Transaction> transactions) {
        Objects.requireNonNull(transactions);
        BigDecimal fraudsCount = transactions.stream()
                .filter(Objects::nonNull)
                .filter(Transaction::isFraud)
                .map(Transaction::amount)
                .reduce(BigDecimal.ZERO,BigDecimal::add);
        println(fraudsCount);
    }

    public static void printFraudsPerTypeCount(List<Transaction> transactions, Type type) {
        Objects.requireNonNull(transactions);
        long fraudsCount = transactions.stream()
                .filter(Objects::nonNull)
                .filter(Transaction::isFraud)
                .filter(t -> t.type() == type)
                .count();
        println(" -" + type + ": " + fraudsCount);
    }
}
