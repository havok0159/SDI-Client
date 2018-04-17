package repository;

import model.Problem;
import model.Student;

import java.util.List;

public interface IRepository {
    List<Student> getStudents();

    void setStudents(List<Student> students);

    List<Problem> getProblems();

    void setProblems(List<Problem> problems);

    Student getStudentById(int id);

    Problem getProblemById(int id);

    void addStudent(Student st);
    void addProblem(Problem pr);
}
