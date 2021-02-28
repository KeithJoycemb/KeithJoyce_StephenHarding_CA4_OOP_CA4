package dkit.oop;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * Notes:
 *  Synchronisation of multiple reads and writes to file is not considered here.
 *
 */



//TESTING COMMIT stephen 2
public class App 
{
    private static Scanner keyboard= new Scanner(System.in);
    public static void main( String[] args )
    {
        System.out.println( "CAO Online - CA4" );
        new App() .start();

    }

    private void start() {

        // load students
        StudentManager studentManager = new StudentManager();
        studentManager.loadStudentsFromFile();
        menuLoop(studentManager);




        // load courses
        CourseManager courseManager = new CourseManager();

        // load manager to provide functionality to allow a student
        // to login and add/update their course selections
        // This CourseChoicesManager component depends on the
        // StudentManager and the CourseManager,
        // so we 'inject' or pass-in these objects.
        //
        CourseChoicesManager mgr = new CourseChoicesManager(studentManager, courseManager);

        // display a menu to do things

    }
        private void menuLoop(StudentManager studentManager)
    {

        boolean loop = true;
        MainMenu menuOption;
        int option = -1;
        while(loop)
        {
            printMainMenu();
            try
            {
                option = keyboard.nextInt();
                keyboard.nextLine();
                menuOption = MainMenu.values()[option];
                switch(menuOption)
                {
                    case SAVE_AND_EXIT:
                        loop=false;
                        break;
                    case REMOVE_COURSE:
                        //;
                        break;
                    case DISPLAY_ALL_COURSES:
                      //  ;
                        break;
                    case DISPLAY_COURSE_DETAILS:
                       // ;
                        break;
                    case ADD_STUDENT:
                        studentManager.addStudent();
                        break;
                    case REMOVE_STUDENT:
                        studentManager.removeStudent();
                    case DISPLAY_STUDENT:
                        //;


                }
            }
            catch(InputMismatchException ime)
            {
                System.out.println("please enter a valid option");
            }
        }




    }
        private void printMainMenu()
        {
            System.out.println("\n Options to select:");
            for(int i=0; i < MainMenu.values().length;i++)
            {
                System.out.println("\t" + i + ". " + MainMenu.values()[i].toString());
            }
            System.out.print("Enter a number to select the option (0 to quit):>");
        }
        // manual testing of mgr public interface

//        if ( mgr.login(22224444, "xxxx","bbbb") )
//        {
//            Student student = mgr.getStudentDetails(22224444);
//
//            System.out.println("Student: " + student);
//        }
//        else
//            System.out.println("Not logged in - try again");


        //mgr.saveToFile();

}

