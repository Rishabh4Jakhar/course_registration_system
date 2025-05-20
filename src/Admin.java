import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Admin extends User {
    @Override
    void get_info() {
        System.out.println("Admin ID: " + username);
    }
    // Constructor
    public Admin(String username) {
        this.username = username;
        this.password = "0000";
        this.permission = 2;
    }
    public void viewCourses() {
        System.out.println("Course List:");
        System.out.println("C100: Introduction to Programming");
        System.out.println("C101: Linear Algebra");
        System.out.println("C103: Introduction to AI");
        System.out.println("C201:Data Structures");
        System.out.println("C202: Multivariate Calculus");
        System.out.println("C203: Machine Learning");
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
    }
    public void updateCourseDetails(String CourseID) {
        Data.removeCourse(CourseID);
        System.out.println("Course deleted successfully.");
    }
    public void addCourse(String CourseID) {
        System.out.println("Enter Course Name:");
        Scanner scanner = new Scanner(System.in);
        String courseName = scanner.next();
        System.out.println("Enter Assigned Prof:");
        String courseProf = scanner.next();
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
        ArrayList<String> data = new ArrayList<>(Arrays.asList(courseName, courseDescription, courseProf, courseSchedule, courseRoom, courseCapacity, courseEnrolled, courseWaitlist, courseStatus, courseCredits, courseSemester));
        Data.addNewCourse(CourseID, data);
    }
    public void setCanAddDrop(boolean val) {
        Data.setCanAddDrop(true);
    }
    public void endAddDrop() {
        Data.setCanAddDrop(false);
    }
    public void viewStudent(String studentID) {
        ArrayList<String> studentData = Data.getStudentData(studentID);
        if (studentData == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.println("Student ID: " + studentID);
        System.out.println("Student Name: " + studentData.get(0));
        System.out.println("Student Semester: " + studentData.get(2));
        System.out.println("Student CGPA: " + studentData.get(3));
        System.out.println("Student Complaints: " + studentData.get(7));
        System.out.println("Student Complaint Status: " + studentData.get(9));
    }
    public void updateStudentDetails(String studentID) {
        System.out.println("Enter Student Name:");
        Scanner scanner = new Scanner(System.in);
        String studentName = scanner.next();
        System.out.println("Enter Student Semester:");
        String studentSemester = scanner.next();
        System.out.println("Enter Student CGPA:");
        String studentCGPA = scanner.next();
        System.out.println("Enter Student Complaints:");
        String studentComplaints = scanner.next();
        System.out.println("Enter Student Complaint Status:");
        String studentComplaintStatus = scanner.next();
        ArrayList<String> data = new ArrayList<>(Arrays.asList(studentName, this.password, studentSemester, studentCGPA, "", "", studentComplaints, "", studentComplaintStatus));
        Data.updateStudentData(studentID, data);
    }
    public void assignProf(String CourseID, String ProfID) {
        ArrayList<String> courseData = Data.getCourseData(CourseID);
        if (courseData == null) {
            System.out.println("Course not found.");
            return;
        }
        courseData.set(2, ProfID);
        Data.updateCourseData(CourseID, courseData);
        System.out.println("Professor assigned successfully.");
    }
    public void handleComplaints() {
        System.out.println("Enter Student ID:");
        Scanner scanner = new Scanner(System.in);
        String studentID = scanner.next();
        System.out.println("Enter Complaint Status:");
        String complaintStatus = scanner.next();
        ArrayList<String> studentData = Data.getStudentData(studentID);
        if (studentData == null) {
            System.out.println("Student not found.");
            return;
        }
        studentData.set(9, complaintStatus);
        Data.updateStudentData(studentID, studentData);
        System.out.println("Complaint status updated successfully.");
    }
}
