package dkit.oop;
// StudentManager encapsulates the storage and ability
// to manipulate student objects




import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class StudentManager
{
    private ArrayList<Student> student;
    private static Scanner keyboard = new Scanner(System.in);

    public StudentManager()
    {
        this.student = new ArrayList<>();
    }


    protected void loadStudentsFromFile() {
        try (Scanner studentsFile = new Scanner(new BufferedReader(new FileReader("students.txt"))))
        {
            String input;
            while (studentsFile.hasNextLine())
            {
                input = studentsFile.nextLine();
                String[] data = input.split(",");
                int caoNumber = Integer.parseInt(data[0]);
                String dateOfBirth = data[1];
                String password = data[2];
                String email = data[3];

                Student readInStudents = new Student(caoNumber, dateOfBirth, password, email);
                this.student.add(readInStudents);
            }
        }
        catch (FileNotFoundException fne)
        {
            System.out.println("Could not find students.txt file");
        }
    }

    public void addStudent() {
        int caoNumber = isValid("caoNumber");
        String dateOfBirth = enterField("dateOfBirth");
        String password = enterField("password");
        String email = enterField("email");

        Student s = new Student(caoNumber, dateOfBirth, password, email);

        if (this.student != null) {
            if (s != null) {
                student.add(s);
            } else {
                System.out.println("error adding student");
            }
        }
    }

    private void saveStudentToFile() throws IOException
    {
        try (BufferedWriter studentFile = new BufferedWriter(new FileWriter("students.txt")))
        {
            for (Student student : student)
            {
                studentFile.write(student.getCaoNumber() + "," + student.getDayOfBirth() + "," + student.getEmail()
                        + "," + student.getPassword());
                studentFile.write("\n");
            }
        }
        catch (FileNotFoundException fne)
        {
            System.out.println("could not save Students to file");
        }
    }

    private int isValid(String intField) {
        boolean check = true;
        while (check) {
            try {
                if (intField.equals("caoNumber")) {
                    int caoNumber = Integer.parseInt(enterField(intField));
                    return caoNumber;
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter a valid Cao Number");
            }
        }
        return -1;
    }

    private String enterField(String field) {
        String input;
        System.out.println("Please enter Students " + field + ":");
        input = keyboard.nextLine();
        return input;
    }


    public void removeStudent() {
        if (this.student != null) {
            int studentToDelete = isValid("StudentToRemove");
            Student studentToRemove = getStudent(studentToDelete);
            if (studentToRemove != null) {
                student.remove(studentToRemove);
            } else {
                System.out.println("student could not be found");
            }
        }
    }

    private Student getStudent(int studentToFind)
    {
        for(Student student: student)
        {
            if(student.getCaoNumber()==(studentToFind))
            {
                return student;
            }
        }
        return null;
    }

  //  isRegistered( caoNumber){

  //  }
   //     students.isValid()


}
