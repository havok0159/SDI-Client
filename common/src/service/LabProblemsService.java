package service;

import java.util.concurrent.CompletableFuture;

/**
 * Interface for communication between client and server.
 * Exposes methods implemented by both the client and the server
 */

public interface LabProblemsService {
    /**
     * host
     */
    String SERVER_HOST = "172.30.115.191";
    /**
     * server port
     */
    int SERVER_PORT = 1234;

    /**
     * Message headers and
     * method names for TCPServer, corresponding to the specified methods
     */

    String ALL_STUDENTS = "getAllStudents";
    String ALL_PROBLEMS = "getAllProblems";
    String ALL_ASSIGNMENTS = "getAllAssignments";
    String ADD_STUDENT = "addStudent";
    String ADD_PROBLEM = "addProblem";
    String UPDATE_STUDENT = "updateStudent";
    String UPDATE_PROBLEM = "updateProblem";
    String DELETE_STUDENT = "deleteStudent";
    String DELETE_PROBLEM = "deleteProblem";
    String ASSIGN_PROBLEM_TO_STUDENT = "assignProblemToStudent";
    String ASSIGN_PROBLEM_TO_GROUP = "assignProblemToGroup";
    String GRADE_STUDENT = "gradeStudent";
    String STUDENTS_WITH_MEAN_GREATER_THAN = "studentsWithMeanGreaterThan";
    String PROBLEM_ASSIGNED_MORE_TIMES = "problemAssignedMoreTimes";
    String PROBLEM_REPORTS = "problemReports";
    String TOP_THREE_STUDENTS = "topThreeStudents";

    /**
     * Function to get all Students from the database
     *
     * @return CompletableFuture containing the String
     * representation of the set of all students in the database
     */
    CompletableFuture<String> getAllStudents();


    /**
     * Function to get all problems from the database
     *
     * @return CompletableFuture containing the String
     * representation of the set of all problems in the database
     */
    CompletableFuture<String> getAllProblems();


    /**
     * Function to get all Assignments from the database
     *
     * @return CompletableFuture containing the String
     * representation of the set of all Assignments in the database
     */
    CompletableFuture<String> getAllAssignments();


    /**
     * Function to add a Student to the database
     *
     * @param studentString: String representation of the Student to be added
     * @return CompletableFuture containing a String
     * specifying whether the Student was successfully added or not
     */
    CompletableFuture<String> addStudent(String studentString);


    /**
     * Function to add a Problem to the database
     *
     * @param problemString: String representation of the Problem to be added
     * @return CompletableFuture containing a String
     * specifying whether the Problem was successfully added or not
     */
    CompletableFuture<String> addProblem(String problemString);


    /**
     * Function to update the Student with
     * the ID of the Student from the studentString representation
     * with the new attributes from the studentString representation
     *
     * @param studentString: String representation of the new Book
     * @return CompletableFuture containing a String
     * specifying whether the Student was successfully updates or not
     */
    CompletableFuture<String> updateStudent(String studentString);


    /**
     * Function to update the Problem with
     * the ID of the Problem from the problemString representation
     * with the new attributes from the problemString representation
     *
     * @param problemString: String representation of the new Problem
     * @return CompletableFuture containing a String
     * specifying whether the Problem was successfully updates or not
     */
    CompletableFuture<String> updateProblem(String problemString);


    /**
     * Function to remove a Student from the database by unique ID
     *
     * @param ID: ID of the Student to be removed from the database
     * @return CompletableFuture containing a String
     * specifying whether the Student was successfully removed or not
     */
    CompletableFuture<String> deleteStudent(String ID);


    /**
     * Function to remove a Problem from the database by unique ID
     *
     * @param ID: ID of the Problem to be removed from the database
     * @return CompletableFuture containing a String
     * specifying whether the Problem was successfully removed or not
     */
    CompletableFuture<String> deleteProblem(String ID);

    /**
     * Function to assign a problem to a student
     *
     * @param assignment: The assignment to be added to the database
     * @return CompletableFuture containing a String
     * specifying whether the assignment was successfully added or not
     */
    CompletableFuture<String> assignProblemToStudent(String assignment);


    /**
     * Function to assign a problem to a group
     *
     * @param assignment: The assignment to be added to the database
     * @return CompletableFuture containing a String
     * specifying whether the assignment was successfully added or not
     */
    CompletableFuture<String> assignProblemToGroup(String assignment);


    /**
     * Function to grade a student
     *
     * @param studentGrade: A string containing the student, problem and the grade for that problem
     * @return CompletableFuture containing a String
     * specifying whether the Student was successfully graded or not
     */
    CompletableFuture<String> gradeStudent(String studentGrade);


    /**
     * Function to filter students by mean
     *
     * @param mean: The mean by which to filter
     * @return CompletableFuture containing a String
     * representation of the set of Students with the mean greater than the given one
     */
    CompletableFuture<String> studentsWithMeanGreaterThan(String mean);


    /**
     * Function to filter problems assigned more times
     *
     * @param n: The minimum number of assigned times
     * @return CompletableFuture containing a String
     * representation of the set of Problems where every problem was assigned more than n times
     */
    CompletableFuture<String> problemAssignedMoreTimes(String n);


    /**
     * Function to filter problems based on the number of assignments
     *
     * @return CompletableFuture containing a String
     * representation of the set of Problems and the number of times each problem was assigned, in descending order by
     * the number of assignments
     */
    CompletableFuture<String> problemReports();


    /**
     * Function to filter top three Students
     *
     * @return CompletableFuture containing a String
     * representation of the set of top three Students based on mean, in descending order
     */
    CompletableFuture<String> topThreeStudents();



}
