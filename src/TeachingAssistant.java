import java.util.ArrayList;

public class TeachingAssistant extends Student {
    private String courseID;
    public TeachingAssistant(String username, ArrayList<String> data_s) {
        super(username, data_s);
    }
    // Constructor to create a TA from a Student object
    public TeachingAssistant(Student student) {
        super(student.getUsername(), student.getData());
        this.courseID = student.getCourseID();
    }
    // Method to view grades of a specific student
    public void viewStudentGrades(Student student) {
        student.viewCourseGrade(courseID);
    }
    public void viewTACourse() {
        // Return course details of courseID
        ArrayList<String> courseData = Data.getCourseData(courseID);
        if (courseData == null) {
            System.out.println("Course not found.");
            return;
        }
        System.out.println("Course ID: " + courseID);
        System.out.println("Course Name: " + courseData.get(0));
        System.out.println("Course Description: " + courseData.get(1));
        System.out.println("Course Professor: " + courseData.get(2));
        System.out.println("Course Schedule: " + courseData.get(3));
        System.out.println("Course Room: " + courseData.get(5));
        System.out.println("Course Capacity: " + courseData.get(6));
        System.out.println("Course Enrolled: " + courseData.get(7));
    }
    // Method to update grades of a specific student
    public void updateStudentGrades(Student student, String grade) {
        try {
            Data.enterGrades(courseID, grade, student.username);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Student not found.");
        }
    }
}