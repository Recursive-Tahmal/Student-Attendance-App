import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Student {

	private String student;
	private int id;


	/**
	 * This Constructor will initialize student and id
	 * @param id student's ID
	 * @param student student's name
	 */
	public Student(int id,String student) {
		this.student = student;
		this.id = id;
	}


	/**
	 * getStudent
	 * @return student name
	 */
	public String getStudent() {
		return student;
	}


	/**
	 * getId
	 * @return Student's id
	 */
	public int getId() {
		return id;
	}


	/**
	 * A method that reads the file Students.txt and returns it as an array list
	 * @return An arraylist
	 */
	public static ArrayList<String> listStudent(){
		ArrayList students = new ArrayList<String>();
		File file = new File("Students.txt");
		Scanner scanfilename = null;
		try {
			scanfilename = new Scanner(file);
			while(scanfilename.hasNext()){
				students.add(scanfilename.nextLine());
			}
			return students;
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		return null;
	}


	/**
	 * This method will take student as an object of Student to add student to Students.txt file
	 * @param student an object that contains Student's ID and name
	 * @throws IOException
	 */
	public static void setStudent(Student student) throws IOException{
		FileWriter write_file = new FileWriter("Students.txt",true);
		PrintWriter print_writer = new PrintWriter(write_file);
		print_writer.printf("%d\n%s\n",student.getId(),student.getStudent());
		print_writer.close();
		System.out.printf("%s %s %s\n",student.getId(),student.getStudent(),"has been added");
	}
}
