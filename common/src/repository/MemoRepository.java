package repository;

import model.Problem;
import model.Student;

import java.util.ArrayList;
import java.util.List;

public class MemoRepository implements IRepository{

    private List<Student> students;
    private List<Problem> problems;

    public MemoRepository(){
        this.problems=new ArrayList<>();
        this.students=new ArrayList<>();
        //this.generateData();
    }

    private void generateData(){
        Problem p1=new Problem(Long.valueOf(1),"A faculty manages information about students and disciplines.");
        Problem p2=new Problem(Long.valueOf(2),"A teacher manages information about students and lab problems.");
        Problem p3=new Problem(Long.valueOf(3),"A movie rental shop manages information about movies and clients.");
        Problem p4=new Problem(Long.valueOf(4),"4-A book publisher manages information about books and clients.");
        this.problems.add(p1);
        this.problems.add(p2);
        this.problems.add(p3);
        this.problems.add(p4);
        Student s1=new Student(Long.valueOf(2), "George", 926);
        Student s2= new Student(Long.valueOf(2) ,"Paul" ,926);
        Student s3=new Student(Long.valueOf(3), "Andrei", 921);
        this.students.add(s1);
        this.students.add(s2);
        this.students.add(s3);
    }

    @Override
    public List<Student> getStudents() {
        return this.students;
    }

    @Override
    public void setStudents(List<Student> students) {
        this.students=students;
    }

    @Override
    public List<Problem> getProblems() {
        return this.problems;
    }

    @Override
    public void setProblems(List<Problem> problems) {
        this.problems=problems;
    }

    @Override
    public Student getStudentById(int id) {
        for (Student s: students)
            if (s.getId() == id)
                return s;
        return null;
    }

    @Override
    public Problem getProblemById(int id) {
        for(Problem p: problems)
            if (p.getId() == id)
                return p;
        return null;
    }

    @Override
    public void addStudent(Student st) {
        this.students.add(st);
    }

    @Override
    public void addProblem(Problem pr) {
        this.problems.add(pr);
    }
}
