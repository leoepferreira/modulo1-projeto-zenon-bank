package br.com.zenon.fraud.records;

import br.com.zenon.fraud.enums.Type;
import java.math.BigDecimal;

public record Transaction(int step,
                          Type type,
                          BigDecimal amount,
                          TransactionCustomer orign,
                          TransactionCustomer destination,
                          boolean isFraud,
                          boolean isFlaggedFraud){

}