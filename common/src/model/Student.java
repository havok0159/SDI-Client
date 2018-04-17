package model;

import java.util.ArrayList;
import java.util.List;

public class Student extends BaseEntity<Long>{
    private Long id;
    private String name;
    private int group;
    private List<Problem> problems;
    private List<Grade> grades;

    public Student(Long i,String name,int g){
        this.id=i;
        this.name=name;
        this.group=g;
        problems= new ArrayList<>();
        grades = new ArrayList<>();
    }
    public Student(){
        problems= new ArrayList<>();
        grades = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public List<Problem> getProblems() {
        return problems;
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
    }

    public void addProblem(Problem p) {
        this.problems.add(p);
    }

    public List<Grade> getGrades()
    {
        return this.grades;
    }

    public void setGrades(List<Grade> grades)
    {
        this.grades = grades;
    }

    public void addGrade(Grade g)
    {
        this.grades.add(g);
    }
    @Override
    public String toString() {
        return this.id+" "+this.name+" "+this.group+"\n";
    }

    public String toFile() {
        return this.id+" "+this.name+" "+this.group;
    }

    public String printProblems() { return this.problems.toString(); }

    public String printGrades() { return this.grades.toString(); }

}
