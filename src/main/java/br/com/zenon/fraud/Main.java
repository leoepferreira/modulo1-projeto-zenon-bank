import br.com.zenon.fraud.domain.enums.Type;
import br.com.zenon.fraud.api.io.TransactionIngestor;
import br.com.zenon.fraud.domain.records.Transaction;
import br.com.zenon.fraud.domain.records.TransactionCustomer;
import br.com.zenon.fraud.service.FraudAnalyzer;


import static java.lang.IO.print;
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


    println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");

    TransactionIngestor transactionIngestor = new TransactionIngestor("data/PS_20174392719_1491204439457_log.csv");

    transactionIngestor.readLines(1000);
    transactionIngestor.printLines(10);


    println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    TransactionIngestor transactionIngestor2 = new TransactionIngestor("data/paysim_with_bad_data.csv");

    transactionIngestor2.readLines(100);
    println(transactionIngestor2.size());
    transactionIngestor2.printLines(100);



    println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    TransactionIngestor transactionIngestor3 = new TransactionIngestor("data/PS_20174392719_1491204439457_log.csv");
    transactionIngestor3.readLines(50000);
    List<Transaction> transactionsList = transactionIngestor3.getTransactions();

    print("1. Total de Fraudes: ");
    FraudAnalyzer.printFraudsCount(transactionsList);
    println("2. Top 3 Fraudes de Maior Valor:");
    FraudAnalyzer.printBiggestAmountsFrauds(transactionsList,3);
    println("3. Clientes Suspeitos:");
    FraudAnalyzer.majorSuspects(transactionsList,5);
    print("4. Prejuízo Total: ");
    FraudAnalyzer.printFraudsSum(transactionsList);
    println("5. Fraudes por Tipo:");
    FraudAnalyzer.printFraudsPerTypeCount(transactionsList,Type.CASH_OUT);
    FraudAnalyzer.printFraudsPerTypeCount(transactionsList,Type.TRANSFER);

}


