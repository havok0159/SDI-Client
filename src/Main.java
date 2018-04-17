import Common.Service.LabProblemsService;
import TCP.TcpClient;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import Controller.Controller;

public class Main {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        TcpClient tcpClient = new TcpClient(executorService, LabProblemsService.SERVER_HOST, LabProblemsService.SERVER_PORT);
        LabProblemsService bookstoreService = new Controller(executorService, tcpClient);
        UI clientConsole = new UI(bookstoreService);
        try {
            clientConsole.run();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdownNow();

        System.out.println("bye - client");
    }
}
