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
    private static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args)
    {
        System.out.println("CAO Online - CA4");
        new App().start();

    }

    private void start()
    {
        // load students
        StudentManager studentManager = new StudentManager();
        studentManager.loadStudentsFromFile();



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
        mainMenuLoop(studentManager ,courseManager);

    }

    private void adminMenuLoop(StudentManager studentManager,CourseManager courseManager)
    {

        boolean loop = true;
        AdminMenu menuOption;
        int option = -1;
        while (loop) {
            printAdminMenu();
            try {
                option = keyboard.nextInt();
                keyboard.nextLine();
                menuOption = AdminMenu.values()[option];
                switch (menuOption) {
                    case SAVE_AND_EXIT:
                        loop = false;
                        break;
                    case ADD_COURSE:
                        courseManager.addCourse();
                        break;
                    case REMOVE_COURSE:
                        //;
                        break;
                    case DISPLAY_ALL_COURSES:
                        //  ;
                        break;
                    case COURSE_DETAILS:
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
            } catch (InputMismatchException ime)
            {
                System.out.println("please enter a valid option");
            }
        }

    }

    private void printAdminMenu()
    {
        System.out.println("\n Options to select:");
        for (int i = 0; i < AdminMenu.values().length; i++) {
            System.out.println("\t" + i + ". " + AdminMenu.values()[i].toString());
        }
        System.out.print("Enter a number to select the option (0 to quit):>");
    }

    private void printMainMenu()
    {
        System.out.println("\n Options to select:");
        for (int i = 0; i < MainMenu.values().length; i++) {
            System.out.println("\t" + i + ". " + MainMenu.values()[i].toString());
        }
        System.out.print("Enter a number to select the option (0 to quit):>");
    }

    private void printStudentMenu()
    {
        System.out.println("\n Options to select:");
        for (int i = 0; i < StudentMenu.values().length; i++) {
            System.out.println("\t" + i + ". " + StudentMenu.values()[i].toString());
        }
        System.out.print("Enter a number to select the option (0 to quit):>");
    }


    private void mainMenuLoop(StudentManager studentManager, CourseManager courseManager)
    {
        boolean loop = true;
        MainMenu menuOption;
        int option = -1;
        while (loop) {
            printMainMenu();
            try {
                option = keyboard.nextInt();
                keyboard.nextLine();
                menuOption = MainMenu.values()[option];
                switch (menuOption) {
                    case QUIT_APPLICATION:
                        loop = false;
                        break;
                    case STUDENT_MENU:
                        studentMenuLoop(studentManager,courseManager);
                        break;
                    case ADMIN_MENU:
                        adminMenuLoop(studentManager, courseManager);
                }
            } catch (InputMismatchException ime) {
                System.out.println("please enter a valid option");
            }
        }
    }
        private void studentMenuLoop(StudentManager studentManager, CourseManager courseManager)
        {
            boolean loop = true;
            StudentMenu menuOption;
            int option = -1;
            while (loop)
            {
                printStudentMenu();
                try {
                    option = keyboard.nextInt();
                    keyboard.nextLine();
                    menuOption = StudentMenu.values()[option];
                    switch (menuOption) {
                        case QUIT_APPLICATION:
                            loop = false;
                            break;
                        case LOGIN:
                            //;
                            break;
                        case DISPLAY_COURSE:
                            //;
                            break;
                        case DISPLAY_ALL_COURSES:
                            //;
                            break;
                        case DISPLAY_CURRENT_CHOICES:
                            //;
                            break;
                        case UPDATE_CHOICES:
                            //;
                            break;
                        case LOG_OUT:
                            //;
                            break;

                    }
                } catch (InputMismatchException ime) {
                    System.out.println("please enter a valid option");
                }
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
}

