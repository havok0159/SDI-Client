package model;

public class Grade extends BaseEntity<Long>{
    private int problemId;
    private double grade;

    public Grade(int problemId, double grade)
    {
        this.problemId = problemId;
        this.grade = grade;
    }

    public Grade()
    {

    }

    public void setProblemId(int id)
    {
        this.problemId = id;
    }

    public void setGrade(double grade)
    {
        this.grade = grade;
    }

    public int getProblemId()
    {
        return this.problemId;
    }

    public double getGrade()
    {
        return this.grade;
    }

    public String toString()
    {
        return "ProblemId: " + this.problemId + " Grade: " + this.grade;
    }
}
