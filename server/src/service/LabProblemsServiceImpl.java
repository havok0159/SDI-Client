package service;

import javafx.util.Pair;
import jdk.nashorn.api.tree.AssignmentTree;
import model.BaseEntity;
import model.Student;
import model.Problem;
import model.Assigment;
import repository.Repository;

import java.time.Year;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class LabProblemsServiceImpl implements LabProblemsService{

    /**
     * the executorService to which to submit the tasks
     */
    private ExecutorService executorService;
    /**
     * the Students Repository
     */
    private Repository<Long, Student> students;
    /**
     * the Problems Repository
     */
    private Repository<Long, Problem> problems;
    /**
     * the Assignments Repository
     */
    private Repository<Long, Assigment> assignments;


    /**
     * Constructor for BookstoreServiceImpl
     *
     * @param executorService the ExecutorService
     * @param students           the StudentDBRepository
     * @param problems         the ProblemDBRepository
     * @param assignments    the AssignmentsDBRepository
     */
    public LabProblemsServiceImpl(ExecutorService executorService, Repository<Long, Student> students, Repository<Long, Problem> problems, Repository<Long, Assigment> assignments) {
        this.executorService = executorService;
        this.students = students;
        this.problems = problems;
        this.assignments = assignments;
    }


    /**
     * Function to retrieve all Students from the database
     * It submits the task of calling the findAll() method on the Students repo and
     * returning the result in String form, as computed by the getPrintable() method
     * implemented below, to the executorService
     *
     * @return the CompletableFuture containing the String representation of all the Books int the DB
     */
    @Override
    public CompletableFuture<String> getAllStudents() {
        return CompletableFuture.supplyAsync(() -> {
            Iterable<Student> studentIterable = students.findAll();
            return getPrintable(StreamSupport.stream(studentIterable.spliterator(), false).collect(Collectors.toSet()));
        }, executorService);
    }


    /**
     * Function to retrieve all Problems from the database
     * It submits the task of calling the findAll() method on the Problems repo and
     * returning the result in String form, as computed by the getPrintable() method
     * implemented below, to the executorService
     *
     * @return the CompletableFuture containing the String representation of all the Books int the DB
     */
    @Override
    public CompletableFuture<String> getAllProblems() {
        return CompletableFuture.supplyAsync(() -> {
            Iterable<Problem> problemIterable = problems.findAll();
            return getPrintable(StreamSupport.stream(problemIterable.spliterator(), false).collect(Collectors.toSet()));
        }, executorService);
    }


    /**
     * Function to retrieve all Assignments from the database
     * It submits the task of calling the findAll() method on the Assignments repo and
     * returning the result in String form, as computed by the getPrintable() method
     * implemented below, to the executorService
     *
     * @return the CompletableFuture containing the String representation of all the Books int the DB
     */
    @Override
    public CompletableFuture<String> getAllAssignments() {
        return null;
    }


    /**
     * Function that adds a Student to the database
     * It submits the task of calling the save() method on the Students repo and
     * returning a String stating whether the Student was successfully added or not, to the executorService
     *
     * @param studentString: String representation of the Student to be added
     * @return CompletableFuture with the String containing the
     * message stating whether the save was successful or not
     */
    @Override
    public CompletableFuture<String> addStudent(String studentString) {
        return CompletableFuture.supplyAsync(() -> {
            Student student = parseStudentString(studentString);
            try {
                if (!students.save(student).isPresent())
                    return "Student saved successfully!";
                else return "Student save failed!";
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }, executorService);
    }


    /**
     * Function that adds a Problem to the database
     * It submits the task of calling the save() method on the Problems repo and
     * returning a String stating whether the Problem was successfully added or not, to the executorService
     *
     * @param problemString: String representation of the Problem to be added
     * @return CompletableFuture with the String containing the
     * message stating whether the save was successful or not
     */
    @Override
    public CompletableFuture<String> addProblem(String problemString) {
        return CompletableFuture.supplyAsync(() -> {
            Problem problem = parseProblemString(problemString);
            try {
                if (!problems.save(problem).isPresent())
                    return "Problem saved successfully!";
                else return "Problem save failed!";
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }, executorService);
    }


    /**
     * Function that updates the Student from the database with the ID of the
     * Student whose String representation is passed as parameter; the fields of the Student in
     * the database are replaced by the fields of the Student passed as parameter
     * It submits the task of calling the update() method on the Students repo and
     * returning a String stating whether the Student was successfully updated or not, to the executorService
     *
     * @param studentString: String representation of the Student to which to update
     * @return CompletableFuture with the String containing the
     * message stating whether the change was successful or not
     */
    @Override
    public CompletableFuture<String> updateStudent(String studentString) {
        return CompletableFuture.supplyAsync(() -> {
            Student student = parseStudentString(studentString);
            try {
                if (!students.update(student).isPresent())
                    return "Student updated successfully!";
                else return "Student update failed!";
            } catch (Exception ex) {
                throw new RuntimeException(ex.getMessage());
            }
        }, executorService);
    }


    /**
     * Function that updates the Problem from the database with the ID of the
     * Problem whose String representation is passed as parameter; the fields of the Problem in
     * the database are replaced by the fields of the Problem passed as parameter
     * It submits the task of calling the update() method on the Problems repo and
     * returning a String stating whether the Problem was successfully updated or not, to the executorService
     *
     * @param problemString: String representation of the Student to which to update
     * @return CompletableFuture with the String containing the
     * message stating whether the change was successful or not
     */
    @Override
    public CompletableFuture<String> updateProblem(String problemString) {
        return CompletableFuture.supplyAsync(() -> {
            Problem problem = parseProblemString(problemString);
            try {
                if (!problems.update(problem).isPresent())
                    return "Problem updated successfully!";
                else return "Problem update failed!";
            } catch (Exception ex) {
                throw new RuntimeException(ex.getMessage());
            }
        }, executorService);
    }


    /**
     * Function that deletes the Student from the database with the given ID
     * It submits the task of calling the delete() method on the Students repo and
     * returning a String stating whether the Student was successfully deleted or not, to the executorService
     *
     * @param ID: String representation of the ID of the Student to be deleted
     * @return CompletableFuture with the String containing the
     * message stating whether the remove was successful or not
     */
    @Override
    public CompletableFuture<String> deleteStudent(String ID) {
        return CompletableFuture.supplyAsync(() -> {
            if (!students.delete(Long.valueOf(ID)).isPresent())
                return "Student deleted successfully!";
            else return "Student delete failed!";
        }, executorService);
    }


    /**
     * Function that deletes the Problem from the database with the given ID
     * It submits the task of calling the delete() method on the Problems repo and
     * returning a String stating whether the Problem was successfully deleted or not, to the executorService
     *
     * @param ID: String representation of the ID of the Problem to be deleted
     * @return CompletableFuture with the String containing the
     * message stating whether the remove was successful or not
     */
    @Override
    public CompletableFuture<String> deleteProblem(String ID) {
        return CompletableFuture.supplyAsync(() -> {
            if (!problems.delete(Long.valueOf(ID)).isPresent())
                return "Problem deleted successfully!";
            else return "Problem delete failed!";
        }, executorService);
    }



    /**
     * Function that assigns a Problem to a Student
     * It submits the task of calling the save() method on the Assignments repo and
     * returning a String stating whether the Problem was successfully added or not, to the executorService
     *
     * @param assignmentString: String representation of the assignment to be added
     * @return CompletableFuture with the String containing the
     * message stating whether the save was successful or not
     */
    @Override
    public CompletableFuture<String> assignProblemToStudent(String assignmentString) {
        return CompletableFuture.supplyAsync(() -> {
            Assigment assigment = parseAssignmentString(assignmentString);
            try {
                if (!assignments.save(assigment).isPresent())
                    return "Assignment saved successfully!";
                else return "Assignment save failed!";
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }, executorService);
    }

    //TODO
    @Override
    public CompletableFuture<String> assignProblemToGroup(String assignment) {
        return null;
    }

    @Override
    public CompletableFuture<String> gradeStudent(String studentGrade) {
        return null;
    }

    @Override
    public CompletableFuture<String> studentsWithMeanGreaterThan(String mean) {
        return null;
       // return CompletableFuture.supplyAsync(() ->
      //                  getPrintable(StreamSupport.stream(students.findAll().spliterator(), false).filter(student -> student.getAge() == Integer.parseInt(age)).collect(Collectors.toSet()))
      //          , executorService);
    }

    @Override
    public CompletableFuture<String> problemAssignedMoreTimes(String n) {
        return null;
    }

    @Override
    public CompletableFuture<String> problemReports() {
        return null;
    }


    /**
     * Function that returns a set containing top three Students, sorted descending based on mean
     * It submits the task of calling the findAll() method on the Client repo, filtering them by the specified criteria
     * and returning the result in String form, as computed by the getPrintable() method
     * implemented below, to the executorService
     *
     * @return CompletableFuture with the String representation of the
     * set of desired Students
     */
    @Override
    public CompletableFuture<String> topThreeStudents() {
        return null;
    }

    /**
     * Function that converts a Set of entities to a String form that can be sent to the Client(of hte Server)
     *
     * @param set the Set to be converted
     * @return the string representation of that Set
     */
    private String getPrintable(Set<? extends BaseEntity<Long>> set) {
        StringBuffer buff = new StringBuffer();

        set.forEach(el -> {
            buff.append(el);
            buff.append("--");
        });

        return buff.toString();
    }

    /**
     * Function that creates a Student entity from the String representation provided
     *
     * @param studentString the String representation to be converted
     * @return A new Student instance created from the String representation passed as parameter
     */
    private Student parseStudentString(String studentString) {
        List<String> items = Arrays.asList(studentString.split(";"));

        Long id = Long.valueOf(items.get(0));
        String nume = items.get(1);
        int grupa = Integer.valueOf(items.get(2));

        Student student = new Student(id, nume, grupa);

        return student;
    }


    /**
     * Function that creates a Problem entity from the String representation provided
     *
     * @param problemString the String representation to be converted
     * @return A new Problem instance created from the String representation passed as parameter
     */
    private Problem parseProblemString(String problemString) {
        List<String> items = Arrays.asList(problemString.split(";"));

        Long id = Long.valueOf(items.get(0));
        String desc = items.get(1);

        Problem problem = new Problem(id, desc);

        return problem;
    }


    /**
     * Function that creates an Assignment entity from the String representation provided
     *
     * @param assignmentString the String representation to be converted
     * @return A new Assignment instance created from the String representation passed as parameter
     */
    private Assigment parseAssignmentString(String assignmentString) {
        List<String> items = Arrays.asList(assignmentString.split(";"));

        Long id = Long.valueOf(items.get(0));
        Long studentId = Long.valueOf(items.get(1));
        Long problemId = Long.valueOf(items.get(2));
        double grade = Double.valueOf(items.get(3));

        Assigment assignment = new Assigment(studentId, problemId, grade);
        assignment.setId(id);

        return assignment;
    }
}
