import br.com.zenon.fraud.enums.Type;
import br.com.zenon.fraud.io.TransactionIngestor;
import br.com.zenon.fraud.records.Transaction;
import br.com.zenon.fraud.records.TransactionCustomer;

import static java.lang.IO.println;

void main() {
    Transaction transaction1 = new Transaction(1,
            Type.PAYMENT,
            BigDecimal.valueOf(9839.64),
            new TransactionCustomer("C123100615",
                    BigDecimal.valueOf(170136.0),
                    BigDecimal.valueOf(160296.36)),
            new TransactionCustomer("M197978155",
                    BigDecimal.valueOf(0.0),
                    BigDecimal.valueOf(0.0)),
            false,
            false);

    Transaction transaction2 = new Transaction(743,
            Type.CASH_OUT,
            BigDecimal.valueOf(850002.52),
            new TransactionCustomer("C1280323807",
                    BigDecimal.valueOf(850002.52),
                    BigDecimal.valueOf(0.0)),
            new TransactionCustomer("C873221189",
                    BigDecimal.valueOf(6510099.11),
                    BigDecimal.valueOf(7360101.63)),
            true,
            false);

    println("Transação 1:" + transaction1);
    println("Transação 2:" + transaction2);

    String file = new String("C:\\Users\\Leo\\GitUnipds\\modulo1-projeto-zenon-bank\\zenon-fraud-detector\\data\\PS_20174392719_1491204439457_log.csv");

    TransactionIngestor transactionIngestor =  new TransactionIngestor(file);
    transactionIngestor.readLines(1000);
//    transactionIngestor.printLines(10);

}


