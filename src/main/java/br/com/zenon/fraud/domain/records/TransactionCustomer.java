package br.com.zenon.fraud.domain.records;
import java.math.BigDecimal;
import java.util.Objects;

public record TransactionCustomer(String name,
                                  BigDecimal oldBalance,
                                  BigDecimal newBalance) {

    public TransactionCustomer {
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
