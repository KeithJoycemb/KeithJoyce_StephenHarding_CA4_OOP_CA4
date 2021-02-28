package dkit.oop;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * CoursesManager
 * This software component Encapsulates the storage and management of
 * all courses available through the CAO system.
 * Only administrators would typically be allowed to update this data,
 * but other users can get a COPY of all the courses by calling getAllCourses().
 * The Web Client would need this data to display the course codes,
 * course titles and institutions to the student.
 */

public class CourseManager
{

    // Store all the Course details.
    // Requires fast access given courseId.
    private ArrayList<Course> CourseInfo;

    protected void loadCoursesFromFile()
    {
        try(Scanner coursesFile = new Scanner(new BufferedReader(new FileReader("courses.txt"))))
        {
            String input;
            while(coursesFile.hasNextLine())
            {
                input = coursesFile.nextLine();
                String[] data = input.split(",");
                String courseId = data[0];
                String level = data[1];
                String title = data[2];
                String institution = data[3];

                Course readInCourse = new Course(courseId,level,title,institution);
                this.CourseInfo.add(readInCourse);
            }
        }
        catch(FileNotFoundException fne)
        {
            System.out.println(FontColours.RED + "System could not load in course information" + FontColours.RESET);
        }
    }

    public void saveCoursesToFile()
    {
        try(BufferedWriter saveCoursesToFile = new BufferedWriter(new FileWriter("courses.txt")))
        {
            for(Course course : CourseInfo)
            {
                saveCoursesToFile.write(course.getCourseId() + "," + course.getLevel() + "," + course.getTitle() + "," + course.getInstitution());
                saveCoursesToFile.write("\n");
            }
        }
        catch(FileNotFoundException fne)
        {
            System.out.println(FontColours.RED + "System could not load in course information to file" + FontColours.RESET);
        }
    }

    public CourseManager()
    {
        // Hardcode some values to get started
        // load from text file "courses.dat" and populate coursesMap
    }

//    public  getCourse( ) {
//    }
//
//
//    public  getAllCourses() {
//    }
//
//    public addCourse() {
//    }
//
//    public removeCourse() {
//    }

    // editCourse(courseId);       // not required for this iteration

}







