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
        courseManager.loadCoursesFromFile();
        // load manager to provide functionality to allow a student
        // to login and add/update their course selections
        // This CourseChoicesManager component depends on the
        // StudentManager and the CourseManager,
        // so we 'inject' or pass-in these objects.
        CourseChoicesManager mgr = new CourseChoicesManager(studentManager, courseManager);
        // display a menu to do things
        mainMenuLoop(studentManager ,courseManager);
    }

    private void adminMenuLoop(StudentManager studentManager,CourseManager courseManager)
    {
        boolean loop = true;
        AdminMenu menuOption;
        while (loop)
        {
            printAdminMenu();
            try
            {
                menuOption = AdminMenu.values()[Integer.parseInt(keyboard.nextLine().trim())];
                switch (menuOption)
                {
                    case QUIT_APPLICATION:
                        loop = false;
                        break;
                    case ADD_COURSE:
                        courseManager.addCourse();
                        break;
                    case REMOVE_COURSE:
                        courseManager.deleteCourse();
                        break;
                    case DISPLAY_ALL_COURSES:
                        courseManager.getAllCourses();
                        break;
                    case COURSE_DETAILS:
                        courseManager.getCourse();
                        break;
                    case ADD_STUDENT:
                        studentManager.addStudent();
                        break;
                    case REMOVE_STUDENT:
                        studentManager.deleteStudent();
                        break;
                    case DISPLAY_STUDENT:
                        studentManager.getStudent();
                        break;
                    case SAVE_AND_EXIT:
                        studentManager.saveStudentToFile();
                        courseManager.saveCoursesToFile();
                        loop = false;
                        break;
                }
            }
            catch (IllegalArgumentException e)
            {
                System.out.println(FontColours.RED + "Please enter a vaild option" + FontColours.RESET);
            }
            catch(ArrayIndexOutOfBoundsException e)
            {
                System.out.println(FontColours.RED + "Selection is out of range please try again " + FontColours.RESET);
            }
        }
    }

    private void mainMenuLoop(StudentManager studentManager, CourseManager courseManager)
    {
        boolean loop = true;
        MainMenu menuOption;
        while (loop)
        {
            printMainMenu();
            try
            {
                menuOption = MainMenu.values()[Integer.parseInt(keyboard.nextLine().trim())];
                switch (menuOption)
                {
                    case QUIT_APPLICATION:
                        loop = false;
                        break;
                    case STUDENT_MENU:
                        studentMenuLoop(studentManager,courseManager);
                        break;
                    case ADMIN_MENU:
                        adminMenuLoop(studentManager, courseManager);
                }
            }
            catch (IllegalArgumentException e)
            {
                System.out.println("please enter a valid option");
            }
            catch(ArrayIndexOutOfBoundsException e)
            {
                System.out.println("Selection out of rang. try again");
            }
        }
    }

    private void studentMenuLoop(StudentManager studentManager, CourseManager courseManager)
    {
        boolean loop = true;
        StudentMenu menuOption;
        while (loop)
        {
            printStudentMenu();
            try
            {

                menuOption = StudentMenu.values()[Integer.parseInt(keyboard.nextLine().trim())];
                switch (menuOption)
                {
                    case QUIT_APPLICATION:
                        loop = false;
                        break;
                    case LOGIN:
                        //;
                        break;
                    case DISPLAY_COURSE:
                        courseManager.getCourse();
                        break;
                    case DISPLAY_ALL_COURSES:
                        courseManager.getAllCourses();
                        break;
                    case DISPLAY_CURRENT_CHOICES:
                        //;
                        break;
                    case UPDATE_CHOICES:
                        //;
                        break;
                    case LOG_OUT:
                        loop = false;
                        break;
                }
            }
            catch (IllegalArgumentException e)
            {
                System.out.println("please enter a valid option");
            }
            catch(ArrayIndexOutOfBoundsException e)
            {
                System.out.println("Selection out of rang. try again");
            }
        }

    }

    private void printAdminMenu()
    {
        System.out.println("\n Options to select:");
        for (int i = 0; i < AdminMenu.values().length; i++)
        {
            System.out.println("\t" + i + ". " + AdminMenu.values()[i].toString());
        }
        System.out.print("Enter a number to select the option (0 to quit):>");
    }

    private void printMainMenu()
    {
        System.out.println("\n Options to select:");
        for (int i = 0; i < MainMenu.values().length; i++)
        {
            System.out.println("\t" + i + ". " + MainMenu.values()[i].toString());
        }
        System.out.print("Enter a number to select the option (0 to quit):>");
    }

    private void printStudentMenu()
    {
        System.out.println("\n Options to select:");
        for (int i = 0; i < StudentMenu.values().length; i++)
        {
            System.out.println("\t" + i + ". " + StudentMenu.values()[i].toString());
        }
        System.out.print("Enter a number to select the option (0 to quit):>");
    }
}
