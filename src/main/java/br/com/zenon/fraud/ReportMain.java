import br.com.zenon.fraud.service.TransactionReport;

import static java.lang.IO.println;

void main() {
    long maxMemory = Runtime.getRuntime().maxMemory();
    println("Memória Máxima da JVM: " + (maxMemory / 1024 / 1024) + "MB");

    String fileNameIO = "data/PS_20174392719_1491204439457_log.csv";

    println("-------------pt_BR------------");


    TransactionReport transactionReport = new TransactionReport();
    transactionReport.createReport(fileNameIO);

    println("-------------en_US------------");

    TransactionReport transactionReportenUS = new TransactionReport(Locale.of("en","US"));
    transactionReportenUS.createReport(fileNameIO);
}


