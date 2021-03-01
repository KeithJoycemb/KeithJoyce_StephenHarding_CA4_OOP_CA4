package dkit.oop;
// StudentManager encapsulates the storage and ability
// to manipulate student objects
import java.awt.*;
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

    protected void loadStudentsFromFile()
    {
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
            System.out.println(FontColours.RED + "Could not find student" + FontColours.RESET);
        }
    }

    public void addStudent()
    {
        int caoNumber = isValid("caoNumber");
        String dateOfBirth = enterField("dateOfBirth");
        String password = enterField("password");
        String email = enterField("email");
        Student s = new Student(caoNumber, dateOfBirth, password, email);

        if (this.student != null)
        {
            if (s != null)
            {
                student.add(s);
            } else {
                System.out.println(FontColours.RED + "Error adding student " + FontColours.RESET);
            }
        }
    }

    public void saveStudentToFile()
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
        catch (IOException ioe)
        {
            System.out.println(FontColours.RED + "could not save Students to file" + FontColours.RESET);
        }
    }

    private int isValid(String intField)
    {
        boolean check = true;
        while (check)
        {
            try
            {
                if (intField.equals("caoNumber"))
                {
                    int caoNumber = Integer.parseInt(enterField(intField));
                    return caoNumber;
                }
            } catch (NumberFormatException nfe)
            {
                System.out.println(FontColours.RED + "Please enter a valid CAO number  " + FontColours.RESET);
            }
        }
        return -1;
    }

    private String enterField(String field)
    {
        String input;
        System.out.println("Please enter Students " + field + ":");
        input = keyboard.nextLine();
        return input;
    }


    public void deleteStudent()
    {
        if (this.student != null)
        {
            String studentToFind = enterField("StudentToRemove");
            Student studentToRemove = searchForStudent(Integer.parseInt(studentToFind));
            if (studentToRemove != null)
            {
                student.remove(studentToRemove);
                System.out.println(FontColours.GREEN + "Removed student from system" + FontColours.RESET);
            } else {
                System.out.println(FontColours.RED + "There is no student matching that ID" + FontColours.RESET);
            }
        }
    }

    public Student searchForStudent(int studentToFind)
    {
        for(Student student : student)
        {
            if(student.getCaoNumber() == studentToFind)
            {
                return student;
            }
        }
        return null;
    }

    public void getStudent()
    {
        String StudentInfoToPrint = enterField("student to finds cao number");
        Student studentToPrint = searchForStudent(Integer.parseInt(StudentInfoToPrint));
        if(studentToPrint != null)
        {
            System.out.println(studentToPrint);
        }
        else
        {
            System.out.println(FontColours.RED + "This student does not exist in the system" + FontColours.RESET);
        }
    }

    // log in not working - keith
//    public Student logIn()
//    {
//       Scanner inputCAONum = new Scanner(System.in);
//        System.out.println("Please enter your CAO number: ");
//        String caoNum = inputCAONum.next();
//
//        Scanner inputPW = new Scanner(System.in);
//        System.out.println("Please enter your password: ");
//        String userPW = inputPW.next();
//
//        if(caoNum.equals(caoNumber) && userPW.equals(password))
//        {
//            System.out.println(FontColours.GREEN + "You have successfully logged in " + FontColours.RESET);
//        }
//    }


}
