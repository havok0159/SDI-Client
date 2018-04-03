import java.io.IOException;
import java.net.Socket;

public class Controller {

    private Socket clSocket;

    public Controller(){
        try {
            clSocket = new Socket("localhost", 44);
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

    public String printAllStudents(){
        String str = "";

        return str;
    }

    public String printAllProblems(){
        String str = "";

        return str;
    }

    public String printProblemsByStudID(int sid){
        String str = "";

        return str;
    }

    public String assignProblemToStudent(int sid, int pid){
        String str = "";

        return str;
    }

    public String assignProblemToGroup(int sid, int pid){
        String str = "";

        return str;
    }

    public void addStudent(int sid, String sname, int gid){

    }

    public void addProblem(int pid, String pdesc){

    }

    public String printGradesByStudID(int sid){
        String str = "";

        return str;
    }

    public void gradeStudent(int sid, int pid, int grade){

    }

    public void loadDatabaseFromFile(){

    }

    public void loadDatabaseFromFile(String pathStud, String pathProb){

    }

    public void saveDatabaseToFile(){

    }
}
