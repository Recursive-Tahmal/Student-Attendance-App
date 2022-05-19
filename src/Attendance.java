import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Attendance extends Date {
    private static int day, month, year;

    /**
     * Constructor to set the date
     * @param day
     * @param month
     * @param year
     */
    public Attendance(int day, int month, int year) {
        super(day, month, year); // inherited from class Date
    }


    /**
     * This method will take the attendance and add them in a file called Attendance.txt
     * @throws IOException
     */
    protected void addAttendance() throws IOException {
        char attend;

        FileWriter write_file = new FileWriter("Attendance.txt", true);
        PrintWriter print_writer = new PrintWriter(write_file);
        Date date = new Date();

        Scanner keyboard = new Scanner(System.in);
        try {

            System.out.println("Please enter 'p' to present him 'a' to absent and 'e' to excuse him");
            print_writer.printf("----------------------------------------------\n");
            print_writer.printf("%4s%20s%21s\n", "ID", "Name", date.toString());


            for (int i = 0; i < Student.listStudent().size(); i += 2) {
                String id = Student.listStudent().get(i);
                String name = Student.listStudent().get(i + 1);
                System.out.printf("%s %s\n", id, name);
                attend = keyboard.next().charAt(0);

                if (attend == 'p' | attend == 'P') {
                    print_writer.printf("%5s%20s%17s\n", id, name, "Present");
                } else if (attend == 'a' | attend == 'A') {
                    print_writer.printf("%5s%20s%17s\n", id, name, "Absent");
                } else if (attend == 'e' | attend == 'E') {
                    print_writer.printf("%5s%20s%17s\n", id, name, "Excused");
                } else {
                    System.out.println("Error");
                }
                System.out.println("\n");
            }
            System.out.println("\n");
            print_writer.close();
        }catch (InputMismatchException e) {
            System.out.println("Wrong input");
        }
    }


    /**
     * This method will list all attendance in an arraylist
     * @return arraylist of attendance
     */
    protected static ArrayList<String> listAttendance() {
        ArrayList Attendance = new ArrayList<String>();
        File file = new File("Attendance.txt");
        Scanner scanfilename = null;
        try {
            scanfilename = new Scanner(file);
            while (scanfilename.hasNext()) {
                Attendance.add(scanfilename.nextLine());
            }
            return Attendance;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return null;
    }


    /**
     * This method will edit the attendance in Attendance.txt in case a student has an excuse for his absent or such.
     */

    protected static void editAttendance() {
        String line;
        String studentname;
        int id;
        String status = null;
        char attend;
        boolean found = false;
        boolean error = false;

        try {
            File fileT = new File("temp.txt");
            fileT.createNewFile(); // create new file if it doesn't exist
            Scanner sfilenameTmp = new Scanner(fileT);
            FileWriter write_fileT = new FileWriter("temp.txt");

            PrintWriter print_writerT = new PrintWriter(write_fileT);

            Scanner keyboard = new Scanner(System.in);

            //copy file to temp.txt
            ArrayList<String> attendance = Attendance.listAttendance();
            for (String i : attendance) {
                print_writerT.printf("%s\n", i);
            }
            print_writerT.close();
            try {
                do {
                    System.out.println("Please enter the day");
                    day = keyboard.nextInt();
                } while (day >= 31);

                do {
                    System.out.println("Please enter the month");
                    month = keyboard.nextInt();
                } while (month >= 12);

                System.out.println("Please enter the year");
                year = keyboard.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Wrong input");
                error = true;
            }
            if (!error) {
                System.out.println("Please enter the ID and the student's name");
                id = keyboard.nextInt();
                keyboard.nextLine(); // consume the next line
                studentname = keyboard.nextLine();

                System.out.println("Please enter 'p' to present him 'a' to absent and 'e' to excuse him");
                attend = keyboard.nextLine().charAt(0);
                switch (attend) {
                    case 'p', 'P' -> status = "present";
                    case 'a', 'A' -> status = "absent";
                    case 'e', 'E' -> status = "excused";
                    default -> System.out.println("Error!");
                }
                System.out.println("\n");


                FileWriter write_file = new FileWriter("attendance.txt");
                PrintWriter print_writer = new PrintWriter(write_file);

                while (sfilenameTmp.hasNext()) {
                    line = sfilenameTmp.nextLine();
                    if (line.contains(String.format("%d-%d-%d", day, month, year))) {
                        print_writer.printf("%s\n", line);
                        while (sfilenameTmp.hasNext() & !found) {
                            line = sfilenameTmp.nextLine();
                            if (line.contains(String.valueOf(id)) & line.contains(studentname)) {
                                print_writer.printf("%5s%20s%17s\n", id, studentname, status);
                                found = true;
                            } else {
                                print_writer.printf("%s\n", line);
                            }
                        }
                    } else {
                        print_writer.printf("%s\n", line);
                    }
                }
                if (!found) {
                    System.out.printf("%s %s has not been found", id, studentname);
                }

                print_writer.close();
            }
        }catch (Exception e){}
    }

}


