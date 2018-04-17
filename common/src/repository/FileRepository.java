package repository;

import model.Grade;
import model.Problem;
import model.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

public class FileRepository implements IRepository{
    private List<Student> students;
    private List<Problem> problems;
    private String studentFile;
    private String problemFile;
    private String assignmentFile;

    public FileRepository(String stud_f,String prob_f){
        this.studentFile=stud_f;
        this.problemFile=prob_f;
        this.readStudents();
        this.readProblems();
    }

    public FileRepository(String stud_f,String prob_f, String asign_f){
        this.studentFile=stud_f;
        this.problemFile=prob_f;
        this.assignmentFile=asign_f;
        this.readStudents();
        this.readProblems();
        this.assignProblemsWithGrades();
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Problem> getProblems() {
        return problems;
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
    }

    public Student getStudentById(int id)
    {
        for (Student s: students)
            if (s.getId() == id)
                return s;
        return null;
    }

    public Problem getProblemById(int id)
    {
        for(Problem p: problems)
            if (p.getId() == id)
                return p;
        return null;
    }
    private void readStudents(){
        try {
            BufferedReader in = new BufferedReader(new FileReader(this.studentFile));
            students=in.lines().map(line->{
                                            Student s=new Student();
                                            String args[]=line.split(" ");
                                            s.setId(Long.parseLong(args[0]));
                                            s.setName(args[1]);
                                            s.setGroup(Integer.parseInt(args[2]));
                                            return s;}).collect(Collectors.toList());

        } catch (IOException error1) {
            System.out.println("File Not Found");
        }

    }

    private void readProblems(){
        try {

            BufferedReader in = new BufferedReader(new FileReader(this.problemFile));
            this.problems=in.lines().map(line->{
                Problem p=new Problem();
                String args[]=line.split("-");
                p.setId(Long.parseLong(args[0]));
                p.setDescription(args[1]);
                return p;}).collect(Collectors.toList());

        } catch (IOException error1) {
            System.out.println("File Not Found");
        }

    }

    private void assignProblems(){
        try (BufferedReader br = new BufferedReader(new FileReader(this.assignmentFile))) {

            String line;
            String[] args;
            while ((line = br.readLine()) != null) {
                args = line.split(" ");
                getStudentById(Integer.parseInt(args[0])).addProblem(getProblemById(Integer.parseInt(args[1])));
            }
        }catch (IOException error1) {
            System.out.println("File Not Found");
        }
    }

    private void assignProblemsWithGrades(){
        try (BufferedReader br = new BufferedReader(new FileReader("src\\assignmentswithgrades.txt"))) {

            String line;
            String[] args;
            int pid;
            while ((line = br.readLine()) != null) {
                args = line.split(" ");
                pid = Integer.parseInt(args[1]);
                getStudentById(Integer.parseInt(args[0])).addProblem(getProblemById(pid));
                getStudentById(Integer.parseInt(args[0])).addGrade(new Grade(pid, Double.parseDouble(args[2])));
            }
        }catch (IOException error1) {
            System.out.println("File Not Found");
        }
    }


    public void addStudent(Student st)
    {
        students.add(st);
    }
    public void addProblem(Problem pr) {problems.add(pr); }

    public void toFile(){
        try(PrintWriter writer = new PrintWriter("src\\students.txt", "UTF-8")) {
            for(Student s : this.students){ writer.println(s.toFile());}
            writer.close();
        }
        catch (IOException error1) {
            System.out.println(error1);
        }
        try(PrintWriter writer = new PrintWriter("src\\problems.txt", "UTF-8")) {
            for(Problem p : this.problems){ writer.println(p.toFile());}
            writer.close();
        }
        catch (IOException error1) {
            System.out.println(error1);
        }
        try(PrintWriter writer = new PrintWriter("src\\assignmentswithgrades.txt", "UTF-8")) {
            for(Student s : this.students){
                for(Problem p: s.getProblems()) {
                    for (Grade g : s.getGrades()) {
                        writer.println(s.getId() + " " + g.getProblemId() + " " + g.getGrade());
                    }
                }
            }
            writer.close();
        }
        catch (IOException error1) {
            System.out.println(error1);
        }
    }

}
