package br.com.zenon.fraud.api.io;

import br.com.zenon.fraud.domain.enums.Type;
import br.com.zenon.fraud.domain.records.Transaction;
import br.com.zenon.fraud.domain.records.TransactionCostumer;

import java.math.BigDecimal;
import java.util.Optional;

import static java.lang.IO.println;

public class TransactionUtils {

    public static  Optional<Transaction> createTransactionFromArray(String[] linha) {

        try {
            return Optional.of( new Transaction(Integer.parseInt(linha[0]),
                    Type.valueOf(linha[1]),
                    new BigDecimal(linha[2]),
                    new TransactionCostumer(linha[3],
                            new BigDecimal(linha[4]),
                            new BigDecimal(linha[5])),
                    new TransactionCostumer(linha[6],
                            new BigDecimal(linha[7]),
                            new BigDecimal(linha[8])),
                    "1".equals(linha[9]),
                    "1".equals(linha[10])));
        } catch (IllegalArgumentException e) {
            println("Erro: "+String.join(",", linha) +" | " + e);
        }
        return Optional.empty();
    }
}
