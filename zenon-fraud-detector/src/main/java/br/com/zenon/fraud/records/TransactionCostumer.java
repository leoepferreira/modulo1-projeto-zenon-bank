package br.com.zenon.fraud.records;
import java.math.BigDecimal;
import java.util.Objects;

public record TransactionCostumer(String name,
                                  BigDecimal oldBalance,
                                  BigDecimal newBalance) {

    public TransactionCostumer {
        Objects.requireNonNull(name);
        Objects.requireNonNull(oldBalance);
        Objects.requireNonNull(newBalance);

    validateIsPositive("oldBalance",oldBalance);
    validateIsPositive("newBalance",newBalance);

    validateName(name);

    }
    private void validateName(String name){
        if(name.isEmpty()){
        throw new IllegalArgumentException("name should not be empty");
        }
    }

    private void validateIsPositive(String field, BigDecimal  number){
        if(number.signum() < 0){
            throw new IllegalArgumentException(field + " should be positive: " + number);
        }}
}
