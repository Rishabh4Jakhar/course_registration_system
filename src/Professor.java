import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Professor extends User {
    ArrayList<String> data;
    @Override
    void get_info() {
        System.out.println("Professor ID: " + username);
    }

    // { "Username/ID", ["Real Name", "Password", "Course Taught", "Sem No"] }
    public Professor(String username, ArrayList<String> data_s) {
        this.username = username;
        this.password = data_s.get(1);
        this.data = data_s;
        this.permission = 1;
    }
    public void viewFeedback(ArrayList<Student> students, String CourseID) {
        for (Student student : students) {
            String feedback = student.getFeedback(CourseID);
            if (feedback != null) {
                System.out.println("Student ID: " + student.username);
                System.out.println("Feedback: " + feedback);
            }
            System.out.println("\n");
        }
    }
    public void viewCourses() {
        System.out.println("Course List this semester:");
        ArrayList<String> course = Data.getCourseData(data.get(2));
        System.out.println(course);
    }
    public void viewCourseDetails(String CourseID) {
        ArrayList<String> courseData = Data.getCourseData(CourseID);
        if (courseData == null) {
            System.out.println("Course not found.");
            return;
        }
        System.out.println("Course ID: " + CourseID);
        System.out.println("Course Name: " + courseData.get(0));
        System.out.println("Course Description: " + courseData.get(1));
        System.out.println("Course Professor: " + courseData.get(2));
        System.out.println("Course Schedule: " + courseData.get(3));
        System.out.println("Course Room: " + courseData.get(5));
        System.out.println("Course Capacity: " + courseData.get(6));
        System.out.println("Course Enrolled: " + courseData.get(7));
        System.out.println("Course Waitlist: " + courseData.get(8));
        System.out.println("Course Status: " + courseData.get(9));
    }
    public void updateCourseDetails(String CourseID) {
        System.out.println("Enter Course Name:");
        Scanner scanner = new Scanner(System.in);
        String courseName = scanner.next();
        System.out.println("Enter Course Description:");
        String courseDescription = scanner.next();
        System.out.println("Enter Course Schedule:");
        String courseSchedule = scanner.next();
        System.out.println("Enter Course Room:");
        String courseRoom = scanner.next();
        System.out.println("Enter Course Capacity:");
        String courseCapacity = scanner.next();
        System.out.println("Enter Course Enrolled:");
        String courseEnrolled = scanner.next();
        System.out.println("Enter Course Waitlist:");
        String courseWaitlist = scanner.next();
        System.out.println("Enter Course Status:");
        String courseStatus = scanner.next();
        System.out.println("Enter Course Credits:");
        String courseCredits = scanner.next();
        System.out.println("Enter Course Semester:");
        String courseSemester = scanner.next();
        ArrayList<String> data = new ArrayList<>(Arrays.asList(courseName, courseDescription, this.username, courseSchedule, courseRoom, courseCapacity, courseEnrolled, courseWaitlist, courseStatus, courseCredits, courseSemester));

        Data.updateCourseData(CourseID, data);
    }
    public void viewEnrolledStudents() {
        Data.getEnrolledStudent(data.get(2));
    }
    public void viewGrades(String studentID) {
        try {
            Data.getGrades(studentID, data.get(2));
        } catch (Exception e) {
            System.out.println("Student not found.");
        }
    }
    public void addTA(String studentID) {
        try {
            Data.addTA(data.get(2), studentID);
        } catch (Exception e) {
            System.out.println("Student not found.");
        }
    }
    public void removeTA(String studentID) {
        try {
            Data.removeTA(studentID);
        } catch (Exception e) {
            System.out.println("Student not found.");
        }
    }
    public void viewTAs() {
        // For all students, check if current course is in their TA course lsit
        try {
            Data.viewTA(data.get(2));
        } catch (Exception e) {
            System.out.println("No TAs found.");
        }
    }
    public void enterGrades(String studentID) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Grade:");
        String grade = scanner.next();
        try {
            Data.enterGrades(data.get(2), grade, studentID);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Student not found.");
        }
    }

}
