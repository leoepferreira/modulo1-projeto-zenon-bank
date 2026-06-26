import br.com.zenon.fraud.enums.Type;
import br.com.zenon.fraud.io.TransactionIngestor;
import br.com.zenon.fraud.records.Transaction;
import br.com.zenon.fraud.records.TransactionCostumer;

import static java.lang.IO.println;

void main() {
    Transaction transaction1 = new Transaction(1,
            Type.PAYMENT,
            BigDecimal.valueOf(9839.64),
            new TransactionCostumer("C123100615",
                    BigDecimal.valueOf(170136.0),
                    BigDecimal.valueOf(160296.36)),
            new TransactionCostumer("M197978155",
                    BigDecimal.valueOf(0.0),
                    BigDecimal.valueOf(0.0)),
            false,
            false);

    Transaction transaction2 = new Transaction(743,
            Type.CASH_OUT,
            BigDecimal.valueOf(850002.52),
            new TransactionCostumer("C1280323807",
                    BigDecimal.valueOf(850002.52),
                    BigDecimal.valueOf(0.0)),
            new TransactionCostumer("C873221189",
                    BigDecimal.valueOf(6510099.11),
                    BigDecimal.valueOf(7360101.63)),
            true,
            false);

    println("Transação 1:" + transaction1);
    println("Transação 2:" + transaction2);


    println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");

    TransactionIngestor transactionIngestor = new TransactionIngestor("zenon-fraud-detector/data/PS_20174392719_1491204439457_log.csv");

    transactionIngestor.readLines(1000);
    transactionIngestor.printLines(10);


    println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    TransactionIngestor transactionIngestor2 = new TransactionIngestor("zenon-fraud-detector/data/paysim_with_bad_data.csv");

    transactionIngestor2.readLines(100);
    println(transactionIngestor2.size());
    transactionIngestor2.printLines(100);

}


