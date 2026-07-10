import br.com.zenon.fraud.domain.enums.Type;
import br.com.zenon.fraud.api.io.TransactionIngestor;
import br.com.zenon.fraud.domain.records.Transaction;
import br.com.zenon.fraud.domain.records.TransactionCostumer;
import br.com.zenon.fraud.service.FraudAnalyzer;
import br.com.zenon.fraud.service.TransactionRepositoryList;
import br.com.zenon.fraud.service.TransactionRepositoryMap;

import static java.lang.IO.print;
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



    println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    TransactionIngestor transactionIngestor3 = new TransactionIngestor("zenon-fraud-detector/data/PS_20174392719_1491204439457_log.csv");
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


    println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");

    TransactionRepositoryList transactionRepositoryList = new TransactionRepositoryList();

    transactionRepositoryList.ReadLines("zenon-fraud-detector/data/PS_20174392719_1491204439457_log.csv", 100_000);

    String costumerOriginName1 = "C12345";
    String costumerOriginName2 = "C1231006815";

    Optional<Transaction> transactionFinded1 = transactionRepositoryList.FindTransaction(costumerOriginName1 );
    if (transactionFinded1.isPresent()) {
        System.out.println(transactionFinded1.get());
    } else {
        System.out.println("Transação não encontrada para o cliente " +  costumerOriginName1);
    }

    Optional<Transaction> transactionFinded2 = transactionRepositoryList.FindTransaction(costumerOriginName2 );
    if (transactionFinded2.isPresent()) {
        System.out.println(transactionFinded2.get());
    } else {
        System.out.println("Transação não encontrada para o cliente " +  costumerOriginName2);
    }

    println("--------Benchmark----------------------------------------------------------------------------------------------------------------------------------------------------------");
    String costumerOriginName = "C1868032458";
    String fileName = "zenon-fraud-detector/data/PS_20174392719_1491204439457_log.csv";
    Optional<Transaction> transactionFinded;

    TransactionRepositoryList benchmarkTransactionRepositoryList = new TransactionRepositoryList();
    long startListLoadTime = System.nanoTime();
    benchmarkTransactionRepositoryList.ReadLines(fileName, 100_000);
    long finalListLoadTime = System.nanoTime();
    println("Tempo total para carregar o 100.000 registros em lista: " + (finalListLoadTime - startListLoadTime));
    long startListSearchTime = System.nanoTime();
    transactionFinded = transactionRepositoryList.FindTransaction(costumerOriginName );
    long finalListSearchTime = System.nanoTime();
    println("Tempo total para pesquisar nos 100.000 registros em lista: " + (finalListSearchTime - startListSearchTime));
    println(transactionFinded);
    benchmarkTransactionRepositoryList = null;


    TransactionRepositoryMap benchmarkTransactionRepositoryMap = new TransactionRepositoryMap();
    long startMapLoadTime = System.nanoTime();
    benchmarkTransactionRepositoryMap.ReadLines(fileName, 100_000);
    long finalMapLoadTime = System.nanoTime();
    println("\nTempo total para carregar o 100.000 registros em map: " + (finalMapLoadTime - startMapLoadTime));
    long startMapSearchTime = System.nanoTime();
    transactionFinded = benchmarkTransactionRepositoryMap.FindTransaction(costumerOriginName );
    long finalMapSearchTime = System.nanoTime();
    println("Tempo total para pesquisar nos 100.000 registros em map: " + (finalMapSearchTime - startMapSearchTime));
    println(transactionFinded);
    benchmarkTransactionRepositoryMap = null;

    println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");
   /* REMOVIDO, ERA APENAS PARA TESTES DE ESTOURO DE MEMÓRIA (NÃO APAGADO PARA FICAR COMO REFERÊNCIA)

   long maxMemory = Runtime.getRuntime().maxMemory();
    System.out.println("Memória Máxima da JVM: " + (maxMemory / 1024 / 1024) + "MB");

    String fileNameIO = "zenon-fraud-detector/data/PS_20174392719_1491204439457_log.csv";

    TransactionRepositoryList ioTransactionRepositoryList = new TransactionRepositoryList();
    ioTransactionRepositoryList.ReadAllLines(fileName);

        println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");

*/


}


