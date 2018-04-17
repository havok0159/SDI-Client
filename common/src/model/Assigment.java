package model;

public class Assigment extends BaseEntity<Long>{
    private Long id;
    private Long studentId;
    private Long problemId;
    private double grade;

    public Assigment(Long studentId, Long problemId, double grade)
    {
        this.studentId = studentId;
        this.problemId = problemId;
        this.grade = grade;
    }

    public void setId(Long id){this.id = id;}
    public  void setStudentId(Long id){this.studentId = id;}
    public void setProblemId(Long id){this.problemId = id;}
    public void setGrade(double grade){this.grade = grade;}

    public Long getId(){return this.id;}
    public Long getStudentId(){return this.studentId;}
    public Long getProblemId(){return this.problemId;}
    public double getGrade(){return this.grade;}
}
