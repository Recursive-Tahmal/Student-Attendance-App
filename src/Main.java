import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * This app is for teachers who which to take attendance for their student.
 * It can save a students information and their attendance in different files
 */

public class Main{
    public static void main(String[] args) throws IOException {
        Scanner keyboard = new Scanner(System.in);

        //Welcome
        System.out.printf("%s\n%s\n%s\n%s\n%s\n%s\n%s\n", "WELCOME TO ATTENDANCE APP!", "      ______ ______",
                "    _/      Y      \\_"
                , "   // ~~ ~~ | ~~ ~  \\\\", "  // ~ ~ ~~ | ~~~ ~~ \\\\",
                " //________.|.________\\\\", "`----------`-'----------'");


        //Choices
        int choice1 = 0; // to initialize
        while (true) {
            do {
                System.out.println("\nEnter \n1 for student\n2 for attendance\n3 to exist");
                try{
                    choice1 = keyboard.nextInt();}catch(InputMismatchException e){
                    System.out.println("Wrong input");
                    keyboard.nextLine(); // consume the next line
                }
            } while (choice1 < 1 || choice1 > 3);


            int choice2 = 0; // to initialize
            if (choice1 == 1) { //Student section
                do {
                    System.out.println("\nEnter \n1 to setup a new student\n2 to get a students information\n3 to go back");
                    try {
                        choice2 = keyboard.nextInt();
                    }catch(InputMismatchException e){
                        System.out.println("Wrong input");
                    }
                    keyboard.nextLine(); // consume next line
                } while (choice2 < 1 || choice2 > 4);

                //Choice 1 & 1
                if (choice2 == 1) {
                    int id;
                    try {
                        do{
                        System.out.println("Enter the ID: ");
                        id = keyboard.nextInt();
                        }while(id < 1000000 | id > 10000000);
                        System.out.println("Enter the student name: ");
                        keyboard.nextLine(); //consume next line
                        String studentname = keyboard.nextLine();
                        Student student = new Student(id, studentname);
                        Student.setStudent(student);
                    }catch(InputMismatchException e){}
                }


                //Choice 1 & 2
                if (choice2 == 2) {
                    ArrayList<String> students = Student.listStudent();
                    try {
                        for (int i = 0; i < students.size(); i += 2) {// this will increment by 2 because the first line is the ID and the second is the student's name
                            System.out.printf("%s %s\n", students.get(i), students.get(i + 1));
                        }
                    } catch (NullPointerException e) {}
                }
            }

            if (choice1 == 2) { //Attendance section
                do {
                    System.out.println("\nEnter \n1 to take attendance\n2 to get attendance\n3 to edit\n4 to go back");
                    try{
                        choice2 = keyboard.nextInt();
                    }catch(InputMismatchException e){
                        System.out.println("Wrong input");
                        keyboard.nextLine(); // Consume next line
                    }
                } while (choice2 < 1 || choice2 > 4);

                //Choice 2 & 1
                if (choice2 == 1) {
                    try {
                        do {
                            System.out.println("Please enter the day");
                            Date.day = keyboard.nextInt();
                        } while (Date.day >= 31);

                        do {
                            System.out.println("Please enter the month");
                            Date.month = keyboard.nextInt();

                        } while (Date.month >= 12);

                        System.out.println("Please enter the year");
                        Date.year = keyboard.nextInt();
                        Attendance attend = new Attendance(Date.day,Date.month,Date.year);
                        attend.addAttendance();
                    }catch(InputMismatchException e){}
                }

                //Choice 2 & 2
                if (choice2 == 2) {
                    ArrayList<String> attendance = Attendance.listAttendance();
                    try {
                        for (String i : attendance) {
                            System.out.printf("%s\n", i);
                        }
                    }catch (NullPointerException e){}
                }

                //Choice 2 & 3
                if (choice2 == 3) {
                    Attendance.editAttendance();
                }
            }


            //Choice 3
            if (choice1 == 3) {
                System.out.println("Farewell :) ");
                break;
            }
        }
    }
}
