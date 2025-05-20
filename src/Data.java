import java.util.Enumeration;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

public class Data {
    // { "Username/ID", ["Real Name", "Password", "Sem No", "Current CG", "Current Courses/Registered", "Course IDs Done", "Grades in courses in order", "Complaints no", "Complaint", "Complaint Status", "Feedback", "Course ID for which TA"] }
    private static Map<String, ArrayList<String>> student_data = new HashMap<String, ArrayList<String>> () {{
        put("2023435", new ArrayList<String>(){{ add("Rishabh"); add("1234"); add("1"); add("0"); add(""); add(""); add(""); add("0"); add(""); add(""); add(""); add("C100");}});
        put("2022465", new ArrayList<String>(){{ add("Timmy"); add("5678"); add("1"); add("0"); add(""); add(""); add(""); add("0"); add(""); add(""); add(""); add("");}});
        put("2024052", new ArrayList<String>(){{ add("Yuku"); add("9101"); add("1"); add("0"); add(""); add(""); add(""); add("0"); add(""); add(""); add(""); add("");}});
    }};

    // { "Username/ID", ["Real Name", "Password", "Course Taught", "Sem No"] }
    private static Map<String, ArrayList<String>> prof_data = new HashMap<String, ArrayList<String>>() {{
        put("P01", new ArrayList<String>(){{ add("Satish"); add("5678"); add("C100"); add("1"); }});
        put("P02", new ArrayList<String>(){{ add("Rajesh"); add("9101"); add("C101"); add("1"); }});
        put("P03", new ArrayList<String>(){{ add("Samuel"); add("1901"); add("C103"); add("1"); }});
        put("P21", new ArrayList<String>(){{ add("Rahul"); add("1901"); add("C201"); add("2"); }});
        put("P22", new ArrayList<String>(){{ add("Raj"); add("1901"); add("C202"); add("2"); }});
        put("P23", new ArrayList<String>(){{ add("Suresh"); add("1901"); add("C203"); add("2"); }});
    }};

    // { "Course ID", ["Course Name", "Course Description", "Course Professor", "Course Schedule", "Course Pre-requisite", "Course Room", "Course Capacity", "Course Enrolled", "Course Waitlist", "Course Status", "Course Credits", "Course Semester"] }
    private static Map<String, ArrayList<String>> course_data = new HashMap<String, ArrayList<String>>() {{
        put("C100", new ArrayList<String>(){{ add("Introduction to Programming"); add("Syllabus - Python Basics"); add("P01"); add("Mon - 9:30-11:00\nWed - 9:30-11:00"); add("None"); add("LHC-101"); add("2"); add("0"); add("None"); add(""); add("2"); add("1"); }});
        put("C201", new ArrayList<String>(){{ add("Data Structures"); add("Syllabus - Arrays, Linked Lists, Stacks, Queues"); add("P01"); add("Tue - 9:30-11:00\nThu - 9:30-11:00"); add("C100"); add("LHC-101"); add("3"); add("0"); add("None"); add(""); add("2"); add("2"); }});
        put("C101", new ArrayList<String>(){{ add("Linear Algebra"); add("Syllabus - Matrices"); add("P02"); add("Tue - 9:30-11:00\nThu - 9:30-11:00"); add("None"); add("LHC-201"); add("3"); add("0"); add("None"); add(""); add("2"); add("1"); }});
        put("C202", new ArrayList<String>(){{ add("Multivariate Calculus"); add("Syllabus - Multivariate Calculus"); add("P02"); add("Mon - 9:30-11:00\nWed - 9:30-11:00"); add("C101"); add("LHC-201"); add("2"); add("0"); add("None"); add(""); add("2"); add("2"); }});
        put("C103", new ArrayList<String>(){{ add("Introduction to AI"); add("Syllabus - Basics of AI"); add("P03"); add("Mon - 11:00-12-30\nWed - 11:00-12:30"); add("None"); add("LHC-301"); add("3"); add("0"); add("None"); add(""); add("2"); add("1"); }});
        put("C203", new ArrayList<String>(){{ add("Machine Learning"); add("Syllabus - Basics of ML"); add("P03"); add("Tue - 11:00-12-30\nThu - 11:00-12:30"); add("C103"); add("LHC-301"); add("3"); add("0"); add("None"); add(""); add("2"); add("2"); }});
    }};
    private static boolean canAddDrop = true;
    public static boolean getCanAddDrop() {
        return canAddDrop;
    }
    public static void setCanAddDrop(boolean val) {
        canAddDrop = val;
    }
    public static ArrayList<String> getStudentData(String key) {
        return student_data.get(key);
    }
    public static ArrayList<String> getProfData(String key) {
        return prof_data.get(key);
    }
    public static ArrayList<String> getCourseData(String key) {
        return course_data.get(key);
    }
    public static void removeCourse(String key) {
        course_data.remove(key);
    }
    public static void addNewStudent(String key, ArrayList<String> data) {
        student_data.put(key, data);
    }
    public static void addNewProf(String key, ArrayList<String> data) {
        prof_data.put(key, data);
    }
    public static void removeStudent(String key) {
        student_data.remove(key);
    }
    public static void removeProf(String key) {
        prof_data.remove(key);
    }
    public static void addTA(String courseID, String key) {
        ArrayList<String> student = student_data.get(key);
        student.set(11, courseID);
        student_data.put(key, student);
    }
    public static void removeTA(String key) {
        ArrayList<String> student = student_data.get(key);
        student.set(11, "");
        student_data.put(key, student);
    }
    public static void viewTA(String courseID) {
        System.out.println("TAs for " + courseID + ":");
        for (Map.Entry<String, ArrayList<String>> entry : student_data.entrySet()) {
            if (entry.getValue().get(11).equals(courseID)) {
                System.out.println(entry.getKey());
            }
        }
    }
    public static void addNewCourse(String key, ArrayList<String> data) {
        course_data.put(key, data);
    }
    public static ArrayList<String> getCourseDatawithSem(int sem) {
        ArrayList<String> courses = new ArrayList<String>();
        for (Map.Entry<String, ArrayList<String>> entry : course_data.entrySet()) {
            if (Integer.parseInt(entry.getValue().get(11)) == sem) {
                courses.add(entry.getKey());
            }
        }
        return courses;
    }
    public static void getEnrolledStudent(String courseID) {
        System.out.println("Enrolled Students:");
        for (Map.Entry<String, ArrayList<String>> entry : student_data.entrySet()) {
            // System.out.println(entry.getValue().get(4));
            if (entry.getValue().get(4).contains(courseID)) {
                System.out.println(entry.getKey());
            }
        }
    }
    public static ArrayList<Student> getEnrolledStudentList(String courseID) {
        ArrayList<Student> students = new ArrayList<Student>();
        for (Map.Entry<String, ArrayList<String>> entry : student_data.entrySet()) {
            if (entry.getValue().get(5).contains(courseID)) {
                students.add(new Student(entry.getKey(), entry.getValue()));
            }
        }
        return students;
    }
    public static void updateCourseData(String courseID, ArrayList<String> data) {
        course_data.put(courseID, data);
    }
    public static void getCourseProfessor(String courseID) {
        System.out.println("Course Professor (" + courseID+ "):" + course_data.get(courseID).get(2));
    }
    public static void getGrades(String studentID, String courseID) {
        ArrayList<String> student = student_data.get(studentID);
        String[] courses = student.get(5).split(",");
        String[] grades = student.get(6).split(",");
        for (int i=0; i < courses.length; i++) {
            if (courses[i].equals(courseID)) {
                System.out.println("Grade: " + grades[i]);
                return;
            }
        }
    }
    public static void enterGrades(String courseID, String grade, String studentID) {
        ArrayList<String> student = student_data.get(studentID);
        String[] courses = student.get(4).split(",");
        String[] grades = student.get(6).split(",");
        //System.out.println(student.get(4));
        //System.out.println(student.get(6));
        //for (int i=0; i < courses.length; i++) {
        //    System.out.println(courses[i] + " : " + grades[i]);
        //}
        //for (int i=0; i < grades.length; i++) {
        //    System.out.println(grades[i]);
        //}
        for (int i=0; i < courses.length; i++) {
            if (courses[i].equals(courseID)) {

                // System.out.println(courses);
                grades[i] = grade;
                student.set(6, String.join(",", grades));
                student_data.put(studentID, student);
                System.out.println("Grade entered successfully.");
                return;
            }
        }
        System.out.println("Course not found.");
    }
    public static void updateStudentData(String studentID, ArrayList<String> data) {
        student_data.put(studentID, data);
    }
}