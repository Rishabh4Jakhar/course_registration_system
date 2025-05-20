import java.util.ArrayList;

interface Course_layout {
    void viewSchedule();
    void viewDetails();
}

public class Course implements Course_layout {
    String courseID;
    String courseName;
    String courseDescription;
    String courseProfessor;
    String courseSchedule;
    String coursePrerequisite;
    String courseRoom;
    String courseCapacity;
    String courseEnrolled;
    String courseWaitlist;
    String courseStatus;
    int courseCredits;
    int courseSemester;

    // Structure for data
    // { "Course ID", ["Course Name", "Course Description", "Course Professor", "Course Schedule", "Course Pre-requisite", "Course Room", "Course Capacity", "Course Enrolled", "Course Waitlist", "Course Status", "Course Credits", "Course Semester"] }
    public Course(String courseID, ArrayList<String> courseData) {
        this.courseID = courseID;
        this.courseName = courseData.get(0);
        this.courseDescription = courseData.get(1);
        this.courseProfessor = courseData.get(2);
        this.courseSchedule = courseData.get(3);
        this.coursePrerequisite = courseData.get(4);
        this.courseRoom = courseData.get(5);
        this.courseCapacity = courseData.get(6);
        this.courseEnrolled = courseData.get(7);
        this.courseWaitlist = courseData.get(8);
        this.courseStatus = courseData.get(9);
        this.courseCredits = Integer.parseInt(courseData.get(10));
        this.courseSemester = Integer.parseInt(courseData.get(11));
    }

    @Override
    public void viewSchedule() {
        System.out.println("Course Schedule: " + courseSchedule);
    }

    @Override
    public void viewDetails() {
        System.out.println("Course ID: " + courseID);
        System.out.println("Course Name: " + courseName);
        System.out.println("Course Description: " + courseDescription);
        System.out.println("Course Professor: " + courseProfessor);
        System.out.println("Course Room: " + courseRoom);
        System.out.println("Course Capacity: " + courseCapacity);
        System.out.println("Course Enrolled: " + courseEnrolled);
        System.out.println("Course Waitlist: " + courseWaitlist);
        System.out.println("Course Status: " + courseStatus);
    }
}
