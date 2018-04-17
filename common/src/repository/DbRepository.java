package repository;

import model.Problem;
import model.Student;

import java.util.List;

public class DbRepository implements IRepository{
    @Override
    public List<Student> getStudents() {
        return null;
    }

    @Override
    public void setStudents(List<Student> students) {

    }

    @Override
    public List<Problem> getProblems() {
        return null;
    }

    @Override
    public void setProblems(List<Problem> problems) {

    }

    @Override
    public Student getStudentById(int id) {
        return null;
    }

    @Override
    public Problem getProblemById(int id) {
        return null;
    }

    @Override
    public void addStudent(Student st) {

    }

    @Override
    public void addProblem(Problem pr) {

    }
}
