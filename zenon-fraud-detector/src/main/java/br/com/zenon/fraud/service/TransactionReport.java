package br.com.zenon.fraud.service;

import br.com.zenon.fraud.domain.records.Transaction;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;

import static br.com.zenon.fraud.api.io.TransactionUtils.createTransactionFromArray;

public class TransactionReport  {

    private static class EstatisticasRelatorio {
        int totalLinhas = 0;
        int totalFraudes = 0;
        BigDecimal valorTotal = BigDecimal.ZERO;
    }

    public void createReport(String fileName) {

        final EstatisticasRelatorio stats = new EstatisticasRelatorio();

        try (Stream<String> linhas = Files.lines(Path.of(fileName), StandardCharsets.UTF_8)) {

            linhas.skip(1).forEach(linha -> {
                Optional<Transaction> optionalTransaction = createTransactionFromArray(linha.split(","));

                if (optionalTransaction.isPresent()) {

                    Transaction buffer = optionalTransaction.get();

                    stats.totalLinhas++;

                    if (buffer.isFraud()) {
                        stats.totalFraudes++;
                    }

                    stats.valorTotal = stats.valorTotal.add(buffer.amount());
                }
            });

            System.out.println("Total de linhas: " + stats.totalLinhas);
            System.out.println("Total de fraudes: " + stats.totalFraudes);
            System.out.println("Valor total transacionado: " + stats.valorTotal);

        } catch (IOException e) {
            System.err.println("Deu erro ao ler o arquivo: " + e.getMessage());
        }
    }

}
