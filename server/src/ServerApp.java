import model.Student;
import model.Problem;
import model.Message;
import model.Assigment;
import repository.StudentDBRepository;
import repository.ProblemDBRepository;
import repository.Repository;
import repository.AssigmentDBRepository;
import service.LabProblemsService;
import service.LabProblemsServiceImpl;
import tcp.TcpServer;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class ServerApp {
    public static void main(String args[])
    {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        TcpServer tcpServer = new TcpServer(executorService, LabProblemsService.SERVER_HOST, LabProblemsService.SERVER_PORT);

        Repository<Long, Student> srepo = new StudentDBRepository();
        Repository<Long, Problem> prepo = new ProblemDBRepository();
        Repository<Long, Assigment> arepo = new AssigmentDBRepository();

        LabProblemsService labProblemsService = new LabProblemsServiceImpl(executorService, srepo, prepo, arepo);


        tcpServer.addHandler(LabProblemsService.ALL_STUDENTS, (param) -> {
            Future<String> res = labProblemsService.getAllStudents();
            return processRequest(res);
        });

        tcpServer.addHandler(LabProblemsService.ALL_PROBLEMS, (param) -> {
            Future<String> res = labProblemsService.getAllProblems();
            return processRequest(res);
        });

        tcpServer.addHandler(LabProblemsService.ALL_ASSIGNMENTS, (param) -> {
            Future<String> res = labProblemsService.getAllAssignments();
            return processRequest(res);
        });

        tcpServer.addHandler(LabProblemsService.ADD_STUDENT, (param) -> {
            Future<String> res = labProblemsService.addStudent(param.getBody());
            return processRequest(res);
        });

        tcpServer.addHandler(LabProblemsService.ADD_PROBLEM, (param) -> {
            Future<String> res = labProblemsService.addProblem(param.getBody());
            return processRequest(res);
        });

        tcpServer.startServer();

    }

    private static Message processRequest(Future<String> res) {
        try {
            String result = res.get();
            return Message.builder()
                    .header(Message.OK)
                    .body(result)
                    .build();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            //return new Message(Message.ERROR, "");
            return Message.builder()
                    .header(Message.ERROR)
                    .build();
        }
    }
}
