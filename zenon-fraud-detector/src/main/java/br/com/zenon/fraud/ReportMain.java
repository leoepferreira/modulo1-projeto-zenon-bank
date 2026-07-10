import br.com.zenon.fraud.service.TransactionReport;

void main() {
    long maxMemory = Runtime.getRuntime().maxMemory();
    System.out.println("Memória Máxima da JVM: " + (maxMemory / 1024 / 1024) + "MB");

    String fileNameIO = "zenon-fraud-detector/data/PS_20174392719_1491204439457_log.csv";

    TransactionReport transactionReport = new TransactionReport();
    transactionReport.createReport(fileNameIO);
}


