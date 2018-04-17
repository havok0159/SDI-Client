package Controller;

import Common.Exceptions.ServiceException;
import Common.Service.LabProblemsService;
import TCP.TcpClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class Controller implements LabProblemsService {

    /**
     * the executorService to which to submit the tasks
     */
    private ExecutorService executorService;
    /**
     * TcpClass containing the TCP connection data and transfer method
     */
    private TcpClient tcpClient;

    /**
     * Constructor for the ClientService class
     *
     * @param executorService: executorService attribute
     * @param tcpClient:       tcpClient attribute
     */
    public Controller(ExecutorService executorService, TcpClient tcpClient) {
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }

    @Override
    public CompletableFuture<String> getAllStudents() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return ServiceMessageHandler.manage(LabProblemsService.ALL_STUDENTS, "", tcpClient);
            } catch (Exception e) {
                throw new ServiceException(e.getMessage());
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<String> getAllProblems() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return ServiceMessageHandler.manage(LabProblemsService.ALL_PROBLEMS, "", tcpClient);
            } catch (Exception e) {
                throw new ServiceException(e.getMessage());
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<String> getAllAssignments() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return ServiceMessageHandler.manage(LabProblemsService.ALL_ASSIGNMENTS, "", tcpClient);
            } catch (Exception e) {
                throw new ServiceException(e.getMessage());
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<String> addStudent(String studentString) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return ServiceMessageHandler.manage(LabProblemsService.ADD_STUDENT, studentString, tcpClient);
            } catch (Exception e) {
                throw new ServiceException(e.getMessage());
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<String> addProblem(String problemString) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return ServiceMessageHandler.manage(LabProblemsService.ADD_PROBLEM, problemString, tcpClient);
            } catch (Exception e) {
                throw new ServiceException(e.getMessage());
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<String> updateStudent(String studentString) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return ServiceMessageHandler.manage(LabProblemsService.UPDATE_STUDENT, studentString, tcpClient);
            } catch (Exception e) {
                throw new ServiceException(e.getMessage());
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<String> updateProblem(String problemString) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return ServiceMessageHandler.manage(LabProblemsService.UPDATE_PROBLEM, problemString, tcpClient);
            } catch (Exception e) {
                throw new ServiceException(e.getMessage());
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<String> deleteStudent(String ID) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return ServiceMessageHandler.manage(LabProblemsService.DELETE_STUDENT, ID, tcpClient);
            } catch (Exception e) {
                throw new ServiceException(e.getMessage());
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<String> deleteProblem(String ID) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return ServiceMessageHandler.manage(LabProblemsService.DELETE_PROBLEM, ID, tcpClient);
            } catch (Exception e) {
                throw new ServiceException(e.getMessage());
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<String> assignProblemToStudent(String assignment) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return ServiceMessageHandler.manage(LabProblemsService.ASSIGN_PROBLEM_TO_STUDENT, assignment, tcpClient);
            } catch (Exception e) {
                throw new ServiceException(e.getMessage());
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<String> assignProblemToGroup(String assignment) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return ServiceMessageHandler.manage(LabProblemsService.ASSIGN_PROBLEM_TO_GROUP, assignment, tcpClient);
            } catch (Exception e) {
                throw new ServiceException(e.getMessage());
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<String> gradeStudent(String studentGrade) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return ServiceMessageHandler.manage(LabProblemsService.GRADE_STUDENT, studentGrade, tcpClient);
            } catch (Exception e) {
                throw new ServiceException(e.getMessage());
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<String> studentsWithMeanGreaterThan(String mean) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return ServiceMessageHandler.manage(LabProblemsService.STUDENTS_WITH_MEAN_GREATER_THAN, mean, tcpClient);
            } catch (Exception e) {
                throw new ServiceException(e.getMessage());
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<String> problemAssignedMoreTimes(String n) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return ServiceMessageHandler.manage(LabProblemsService.PROBLEM_ASSIGNED_MORE_TIMES, n, tcpClient);
            } catch (Exception e) {
                throw new ServiceException(e.getMessage());
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<String> problemReports() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return ServiceMessageHandler.manage(LabProblemsService.PROBLEM_REPORTS, "", tcpClient);
            } catch (Exception e) {
                throw new ServiceException(e.getMessage());
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<String> topThreeStudents() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return ServiceMessageHandler.manage(LabProblemsService.TOP_THREE_STUDENTS, "", tcpClient);
            } catch (Exception e) {
                throw new ServiceException(e.getMessage());
            }
        }, executorService);
    }
}
