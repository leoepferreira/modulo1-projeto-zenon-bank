package br.com.zenon.fraud.records;
import java.math.BigDecimal;

public record TransactionCostumer(String name,
                                  BigDecimal oldBalance,
                                  BigDecimal newBalance) {
}
