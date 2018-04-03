package Controller;

public interface IController {
    void addStudent(int id, String name, int grade);
    void addProblem(int id, String description);
    void assignProblemToStudent(int studentID, int problemID);
    void assignProblemToGroup(int groupID, int problemID);
    void gradeStudent(int studentID, int problemID, double grade);
    String printAllStudents();
    String printAllProblems();
    String printProblemsByStudID(int id);
    String printGradesByStudID(int id);
}
