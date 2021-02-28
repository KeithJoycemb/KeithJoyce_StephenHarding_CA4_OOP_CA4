package dkit.oop;
import static java.util.stream.Collectors.toMap;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

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

    private static Scanner keyboard = new Scanner(System.in);

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

    public void saveCoursesToFile() throws IOException
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

    private static String enterInformation(String Information)
    {
        String input;
        System.out.print("Please enter the " + Information + " : ");
        input = keyboard.nextLine();
        return input;
    }

    public CourseManager()
    {
        // Hardcode some values to get started
        // load from text file "courses.dat" and populate coursesMap

        //Map<Course, Integer> coursesMap = new HashMap<>();
        //for(Course course : CourseInfo)
        //{
        //    coursesMap.put(course, course.length());
       // }
    }

   public Course searchForCourse(String courseToFind)
   {
       for(Course course : CourseInfo)
       {
           if(course.getCourseId().equals(courseToFind))
           {
               return course;
           }
       }
       return null;
   }

      public void getCourse()
      {
          String CourseInfoToPrint = enterInformation("Course ID to find");
          Course courseToPrint = searchForCourse(CourseInfoToPrint);
          if(courseToPrint != null)
          {
              System.out.println(courseToPrint);
          }
          else
          {
              System.out.println(FontColours.RED + "This course does not exist in the system" + FontColours.RESET);
          }
      }
      public void printCourseDetails()
      {
          System.out.println("input Course ");
      }

      public void getAllCourses()
      {
          for(Course allCourses : CourseInfo)
          {
              System.out.println(allCourses);
          }
      }

      public void addCourse()
      {
          String courseId = enterInformation("Course ID");
          String level = enterInformation("Course Level");
          String title = enterInformation("Course Title");
          String institution = enterInformation("Course Institution");

          Course addCourse = new Course(courseId,level,title,institution);
          this.CourseInfo.add(addCourse);
      }

      public void deleteCourse()
      {
          if(this.CourseInfo != null)
          {
              String courseToFind = enterInformation("Course ID to remove");
              Course courseToDelete = searchForCourse(courseToFind);
              if(courseToDelete != null)
              {
                  System.out.println(FontColours.GREEN + "Removed course from system" + FontColours.RESET);
              }
              else
              {
                  System.out.println(FontColours.RED + "There is no course matching that ID" + FontColours.RESET);
              }
          }
      }
}







