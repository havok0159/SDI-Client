

import java.util.Scanner;

public class UI {
    private int code;
    private String cmd;
    //private IRepository repository;
    private Controller controller;

    public UI (Controller cntr){
        //this.repository = repository;
        this.controller = cntr;
    }

    public int run(){
        Scanner scanner = new Scanner(System.in);
        code = 1;
        System.out.println("UI v0.1");


        while (code == 1){
            System.out.println("1. Print all students");
            System.out.println("2. Print all problems");
            System.out.println("3. Print assigned problems for student");
            System.out.println("4. Assign problem to group");
            System.out.println("5. Assign problem to student");
            System.out.println("6. Add Student");
            System.out.println("7. Add Problem");
            System.out.println("8. Show Grade");
            System.out.println("9. Add Grade");
            System.out.println("99. Load from file");
            System.out.println("100. Load from file, manual path");
            System.out.println("101. Save to txt file");
            System.out.println("0. Exit");
            System.out.println(">>");

            cmd = scanner.next();

            int sid, gid, pid, grade;
            String sname, pdesc;
            switch(cmd) {
                case "0":
                    code = 0;
                    return code;
                case "1":
                    System.out.print(controller.printAllStudents());
                    break;
                case "2":
                    System.out.print(controller.printAllProblems());
                    break;
                case "3":
                    System.out.println("Enter Student ID:");
                    try {
                        sid = scanner.nextInt();
                        System.out.print(controller.printProblemsByStudID(sid));
                    }
                    catch(java.util.InputMismatchException e){
                        System.out.print("Invalid character. Expected int.");
                    }
                    break;
                case "4":
                    System.out.println("Enter Group ID:");
                    gid = scanner.nextInt();
                    System.out.println("Enter Problem ID:");
                    pid = scanner.nextInt();
                    controller.assignProblemToGroup(gid, pid);
                    break;
                case "5":
                    System.out.println("Enter Student ID:");
                    sid = scanner.nextInt();
                    System.out.println("Enter Problem ID:");
                    pid = scanner.nextInt();
                    controller.assignProblemToStudent(sid, pid);
                    break;
                case "6":
                    System.out.println("Enter Student ID:");
                    sid = scanner.nextInt();
                    System.out.println("Enter Student Name:");
                    sname = scanner.next();
                    System.out.println("Enter Student Group:");
                    gid = scanner.nextInt();
                    controller.addStudent(sid, sname, gid);
                    break;
                case "7":
                    System.out.println("Enter Problem ID:");
                    pid = scanner.nextInt();
                    System.out.println("Enter Problem Description:");scanner.nextLine();
                    pdesc = scanner.nextLine();
                    controller.addProblem(pid, pdesc);
                    break;
                case "8":
                    System.out.println("Enter Student ID:");
                    sid = scanner.nextInt();
                    System.out.print(controller.printGradesByStudID(sid));
                    break;
                case "9":
                    System.out.println("Enter Student ID:");
                    try {
                        sid = scanner.nextInt();
                        System.out.print(controller.printProblemsByStudID(sid));
                        System.out.println("Enter Problem ID:");
                        pid = scanner.nextInt();
                        System.out.println("Enter Grade:");
                        grade = scanner.nextInt();
                        controller.gradeStudent(sid, pid, grade);
                    }
                    catch(java.util.InputMismatchException e){
                        System.out.print("Invalid character. Expected int.");
                    }
                    break;
                case "99":
                    controller.loadDatabaseFromFile();
                    break;
                case "100":
                    System.out.println("Enter path to students file:");
                    String pathStud = scanner.next();
                    System.out.println("Enter path to problems file:");
                    String pathProb = scanner.next();
                    controller.loadDatabaseFromFile(pathStud,pathProb);
                    break;
                case "101":
                    controller.saveDatabaseToFile();

            }
            System.out.println("\nPress Enter to continue...");
            try
            {
                System.in.read();
            }
            catch(Exception e)
            {}
        }
        return code;
    }
}
