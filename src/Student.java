import java.util.ArrayList;

public class Student extends User {
    ArrayList<String> data;

    // { "Username/ID", ["Real Name", "Password", "Sem No", "Current CG", "Current Courses/Registered", "Course IDs Done", "Grades in courses in order", "Complaints no", "Feedback"] }
    public Student(String username, ArrayList<String> data_s) {
        this.username = username;
        this.password = data_s.get(1);
        this.data = data_s;
        this.permission = 0;
    }

    @Override
    void get_info() {
        System.out.println("Student ID: " + username);
    }
    public ArrayList<String> getData() {
        return data;
    }
    public String getCourseID() {
        return data.get(11);
    }
    public String getUsername() {
        return username;
    }
    public void giveFeedback(String CourseID, String feedback) {
        ArrayList<String> courseData = Data.getCourseData(CourseID);
        if (courseData == null) {
            System.out.println("Course not found.");
            return;
        }
        // Check if course is completed or not
        if (!data.get(5).contains(CourseID)) {
            System.out.println("Course not completed.");
            return;
        }
        Feedback<String> feedback_obj = new Feedback<String>(CourseID, feedback);
        data.set(10, CourseID + " " + feedback_obj.getFeedback()+" delim ");
        Data.updateStudentData(username, data);
        System.out.println("Feedback submitted successfully.");
    }
    public void giveFeedback(String CourseID, int feedback) {
        ArrayList<String> courseData = Data.getCourseData(CourseID);
        if (courseData == null) {
            System.out.println("Course not found.");
            return;
        }
        // Check if course register or completed (either in 4 or 5 data)
        if (!data.get(4).contains(CourseID) && !data.get(5).contains(CourseID)) {
            System.out.println("Course not registered.");
            return;
        }
        // Check if course is completed or not
        // if (!data.get(5).contains(CourseID)) {
        //    System.out.println("Course not completed.");
        //    return;
        //}
        Feedback<Integer> feedback_obj = new Feedback<Integer>(CourseID, feedback);
        data.set(10, CourseID+ " " + Integer.toString(feedback_obj.getFeedback())+" delim ");
        Data.updateStudentData(username, data);
        System.out.println("Feedback submitted successfully.");
    }
    public String getFeedback(String CourseID) {
        // Split on " delim " to get the feedback
        // and then return the one having the CourseID
        String[] feedbacks = data.get(10).split(" delim ");
        for (String feedback : feedbacks) {
            if (feedback.contains(CourseID)) {
                // Return feedback after removing CourseID
                return feedback.replace(CourseID+" ", "");
            }
        }
        return "No feedback found.";
    }
    public void viewCourseGrade(String CourseID) {
        String[] courses = data.get(4).split(",");
        String[] grades = data.get(6).split(",");
        for (int i=0; i < courses.length; i++) {
            if (courses[i].equals(CourseID)) {
                System.out.println("Grade: " + grades[i]);
                return;
            }
        }
        System.out.println("Course not found in the given students course list.");
    }
    public void viewCourses() {
        System.out.println("Courses available this semester:");
        int sem_no = Integer.parseInt(data.get(2));
        System.out.println("Semester: " + sem_no);
        ArrayList<String> courses = Data.getCourseDatawithSem(sem_no);
        for (String course : courses) {
            System.out.println(course);
        }
    }
    public void viewComplaintStatus() {
        System.out.println("Complaint Status: " + data.get(9));
    }
    public void viewRegisteredCourses() {
        System.out.println("Registered Courses:");
        String[] courses = data.get(4).split(",");
        for (String course : courses) {
            System.out.println(course);
        }
    }
    public void viewDetails() {
        System.out.println("Name: " + data.get(0));
        System.out.println("Semester: " + data.get(2));
        System.out.println("Current CGPA: " + data.get(3));
    }
    public void submitComplaint(String complaint) {
        data.set(8, complaint);
        data.set(9, "Pending");
        int complaints = Integer.parseInt(data.get(7));
        complaints++;
        data.set(7, Integer.toString(complaints));
        Data.updateStudentData(username, data);
        System.out.println("Complaint submitted successfully.");
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
    public void checkSemProgress() {
        String[] courses = data.get(4).split(",");
        String[] courses_done = data.get(5).split(",");
        String[] grades = data.get(6).split(",");
        int grade_num = 0;
        boolean fail = false;
        for (String grade : grades) {
            switch (grade) {
                case "A":
                    grade_num += 10;
                    break;
                case "A-":
                    grade_num += 9;
                    break;
                case "B":
                    grade_num += 8;
                    break;
                case "B-":
                    grade_num += 7;
                    break;
                case "C":
                    grade_num += 6;
                    break;
                case "C-":
                    grade_num += 5;
                    break;
                case "D":
                    grade_num += 4;
                    break;
                case "I":
                    // Incomplete
                    fail = true;
                    break;
                case "F":
                    fail = true;
                    break;
                default:
                    fail = true;
                    System.out.println("\n");
                    return;
            }
        }
        int cgpa = grade_num / courses.length;
        if (fail) {
            data.set(3, Integer.toString(cgpa));
            Data.updateStudentData(username, data);
            return;
        }
        if (cgpa >= 4 && (courses.length)== 3) {
            System.out.println(data.get(4) + " erer " + data.get(5) + courses_done.length + courses.length);
            if (data.get(5).isEmpty()) {
                data.set(5, data.get(4));
            } else {
                data.set(5, data.get(5) + "," + data.get(4));
            }
            data.set(3, Integer.toString(cgpa));
            //data.set(5, data.get(4)+","+data.get(5));
            data.set(4, "");
            Data.updateStudentData(username, data);
            if ((courses_done.length+courses.length)>=6) {
                // Student has done all courses
                System.out.println("Congratulations! You have completed all courses.\n\n-----------------------");
                return;
            }
            //String[] courses_done = data.get(5).split(",");
                int sem_no = Integer.parseInt(data.get(2));
                // For all courses with sem = sem_no, decrease enrolled by 1
                ArrayList<String> coursesSem = Data.getCourseDatawithSem(sem_no);
                for (String course : coursesSem) {
                    ArrayList<String> courseData = Data.getCourseData(course);
                    int enrolled = Integer.parseInt(courseData.get(7));
                    enrolled--;
                    courseData.set(7, Integer.toString(enrolled));
                    Data.updateCourseData(course, courseData);
                }
                sem_no++;
                data.set(2, Integer.toString(sem_no));
                System.out.println("Semester updated successfully. Register for new courses.");
        }
        data.set(3, Integer.toString(cgpa));
        Data.updateStudentData(username, data);
        return;
    }
    public void registerCourses(String CourseID) throws CourseFullException {
        ArrayList<String> courseData = Data.getCourseData(CourseID);
        if (courseData == null) {
            System.out.println("Course not found.");
            return;
        }
        // Check if course is already registered
        if (data.get(4).contains(CourseID)) {
            System.out.println("Course already registered.");
            return;
        }

        if (Integer.parseInt(courseData.get(6)) > Integer.parseInt(courseData.get(7))) {
            // Check for prerequisites
            if (!courseData.get(4).equals("None")) {
                String[] prereq = courseData.get(4).split(",");
                for (String course : prereq) {
                    if (!data.get(5).contains(course)) {
                        System.out.println("Prerequisite not met.");
                        return;
                    }
                }
            }
            // Check for sem no
            if (Integer.parseInt(courseData.get(11)) != Integer.parseInt(data.get(2))) {
                System.out.println("Course not available this semester.");
                return;
            }
            data.set(4, data.get(4) + CourseID + ",");
            data.set(6, data.get(6) + "I,");
            Data.updateStudentData(username, data);
            System.out.println("Course registered successfully.");
            // Increase course enrolled +1
            int enrolled = Integer.parseInt(courseData.get(7));
            enrolled++;
            courseData.set(7, Integer.toString(enrolled));
        } else {
            try {
                throw new CourseFullException("Course is capped and full. Please contact the admin.");
            } catch (CourseFullException e) {
                System.out.println(e);
            }
            System.out.println("\n");
            //
            //throw new CourseFullException("Course is capped and full. Please contact the admin.");
            // System.out.println("Course is capped. Please contact the admin.");
        }
    }
    public void viewSchedule() {
        System.out.println("Schedule:");
        String[] courses = data.get(4).split(",");
        for (String course : courses) {
            ArrayList<String> courseData = Data.getCourseData(course);
            try {
                System.out.println(courseData.get(0) + " By " + courseData.get(2) + ":");
                System.out.println(courseData.get(3));
                System.out.println("Room: " + courseData.get(5));
            } catch (Exception e) {
                System.out.println("Course not found.");
            }
        }
    }
    public void dropCourses(String CourseID) throws DropDeadlinePassedException {
        ArrayList<String> courseData = Data.getCourseData(CourseID);
        // Check if add drop is enabled
        if (!Data.getCanAddDrop()) {
            try {
                throw new DropDeadlinePassedException("Drop deadline has passed. Please contact the admin.");
            } catch (DropDeadlinePassedException e) {
                System.out.println(e);
            }
            System.out.println("\n");
            return;
            //throw new DropDeadlinePassedException("Drop deadline has passed. Please contact the admin.");
        }
        if (courseData == null) {
            System.out.println("Course not found.");
            return;
        }
        if (!data.get(4).contains(CourseID)) {
            System.out.println("Course not registered.");
            return;
        }
        String[] courses = data.get(4).split(",");
        String newCourses = "";
        for (String course : courses) {
            if (!course.equals(CourseID)) {
                newCourses += course + ",";
            }
        }
        data.set(4, newCourses);
        Data.updateStudentData(username, data);
        System.out.println("Course dropped successfully.");
        // Reduce course enrolled by 1
        int enrolled = Integer.parseInt(courseData.get(7));
        enrolled--;
        courseData.set(7, Integer.toString(enrolled));
    }
    public void viewGrades() {
        // Check if user is in 2nd sem or 1st sem
        int sem_no = Integer.parseInt(data.get(2));
        System.out.println("Grades:");
        String[] grades = data.get(6).split(",");
        String[] courses = data.get(4).split(",");
        int grade_num = 0;
        int len = courses.length;
        if (len == 0) {
            System.out.println("No courses registered.");
            return;
        }
        if (sem_no == 2) {
            len += 3;
        }
        double cgpa;
        double sgpa = 0;
        // System.out.println(data.get(4) + " " + data.get(6));
        for (int i=0; i < len; i++) {
            String course = courses[i];
            if (len!=courses.length && i < 3) {
                course = data.get(5).split(",")[i];
            }
            if (i == 3) {
                sgpa = (double) grade_num/3;
                System.out.println("SGPA: " + (double) grade_num/3);
                System.out.println("CGPA: " + (double) grade_num/3);
                if (sem_no == 2) {
                    System.out.println("Semester 2");
                }
            }
            if (i == 0) {
                System.out.println("Semester 1");
            }
            switch (grades[i]) {
                case "A":
                    System.out.println(course + ": 10.0");
                    grade_num += 10;
                    break;
                case "A-":
                    System.out.println(course + ": 9.0");
                    grade_num += 9;
                    break;
                case "B":
                    System.out.println(course + ": 8.0");
                    grade_num += 8;
                    break;
                case "B-":
                    System.out.println(course + ": 7.0");
                    grade_num += 7;
                    break;
                case "C":
                    System.out.println(course + ": 6.0");
                    grade_num += 6;
                    break;
                case "C-":
                    System.out.println(course + ": 5.0");
                    grade_num += 5;
                    break;
                case "D":
                    System.out.println(course + ": 4.0");
                    grade_num += 4;
                    break;
                case "I":
                    System.out.println(course + ": No Grade");
                    break;
                case "F":
                    System.out.println(course + ": 0.0");
                    break;
                default:
                    System.out.println(course + ": No Grade");
            }
        }
        cgpa = (double) grade_num / len;
        if (courses.length < 3) {
            System.out.println("SGPA: " + cgpa);
            System.out.println("CGPA: " + cgpa);
            return;
        }
        System.out.println("SGPA: " + sgpa+(cgpa-sgpa)*2);
        System.out.println("CGPA: " + cgpa);
    }

    public void viewComplaints() {
        System.out.println("Complaints (Number):" + data.get(7));
    }
}
