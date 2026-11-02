package br.com.zenon.fraud.domain.records;
import java.math.BigDecimal;

public record TransactionCustomer(String name,
                                  BigDecimal oldBalance,
                                  BigDecimal newBalance) {
}
