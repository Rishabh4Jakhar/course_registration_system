import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    // Constructor
    User user;
    public Menu(User user) throws CourseFullException, DropDeadlinePassedException, InvalidLoginException {
        this.user = user;
        int permission = user.permission;
        if (permission == 0) {
            Student student = (Student) user;
            studentMenu(student);
        } else if (permission == 1) {
            Professor professor = (Professor) user;
            professorMenu(professor);
        } else if (permission == 2) {
            Admin admin = (Admin) user;
            adminMenu(admin);
        }
    }
    // Student Menu
    public void studentMenu(Student student) throws CourseFullException, DropDeadlinePassedException, InvalidLoginException {
        while (true) {
            student.checkSemProgress();
            System.out.println("Welcome " + student.data.getFirst());
            System.out.println("1. View Courses");
            System.out.println("2. Register Courses");
            System.out.println("3. View Schedule");
            System.out.println("4. Track Academic Progress");
            System.out.println("5. Drop Courses");
            System.out.println("6. Complaint");
            System.out.println("7. Give Feedback");
            System.out.println("8. Logout");
            // Check if student data 11 has ta course (if he is ta)
            if (!student.data.get(11).isEmpty()) {
                System.out.println("9. TA Menu");
            }
            int input;
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextInt();

            if (input == 1) {
                student.viewCourses();
                System.out.println("Enter course title for more details:");
                String courseTitle = scanner.next();
                student.viewCourseDetails(courseTitle);
            } else if (input == 2) {
                // Check if add/drop period is over
                if (!Data.getCanAddDrop()) {
                    System.out.println("Add/Drop period is over. You can no longer add/drop courses.");
                    continue;
                }
                System.out.println("Enter your semester:");
                int sem_no = scanner.nextInt();
                String CourseID;
                System.out.println("Enter the Course ID:");
                CourseID = scanner.next();
                student.registerCourses(CourseID);
                student.viewRegisteredCourses();
            } else if (input == 3) {
                student.viewSchedule();
            } else if (input == 4) {
                student.viewDetails();
                System.out.println("View Grades (Y/N):");
                String viewGrades = scanner.next();
                if (viewGrades.equals("Y")) {
                    student.viewGrades();
                }
            } else if (input == 5) {
                System.out.println("Enter the Course ID:");
                String CourseID = scanner.next();
                System.out.println("Are you sure you want to drop this course? (Y/N)");
                String confirm = scanner.next();
                if (confirm.equals("Y")) {
                    student.dropCourses(CourseID);
                }
                else {
                    System.out.println("Course not dropped.");
                }
            } else if (input == 6) {
                System.out.println("1. Enter your complaint\n2.View Complaint Status");
                int complaintInput = scanner.nextInt();
                if (complaintInput == 1) {
                    System.out.println("Enter your complaint:");
                    String complaint = scanner.next();
                    student.submitComplaint(complaint);
                } else if (complaintInput == 2) {
                    student.viewComplaintStatus();
                }
            } else if (input == 7) {
                System.out.println("Enter Course ID:");
                String CourseID = scanner.next();
                System.out.println("Enter 1 for rating feedback and 2 for text feedback:");
                int feedbackType = scanner.nextInt();
                if (feedbackType == 1) {
                    System.out.println("Enter Rating (1-10):");
                    int rating = scanner.nextInt();
                    // check for rating to be between 1-10
                    if (rating < 1 || rating > 10) {
                        System.out.println("Invalid rating. Please enter a rating between 1-10.\nSetting feedback rating to 0 since invalid rating.");
                        rating = 0;
                    }
                    student.giveFeedback(CourseID, rating);
                } else if (feedbackType == 2) {
                    System.out.println("Enter Feedback:");
                    String feedback = scanner.next();
                    student.giveFeedback(CourseID, feedback);
                }
            } else if (input == 8) {
                System.out.println("Logging out...");
                Login.enterSystem();
            } else if (input==9 && !student.data.get(11).isEmpty()) {
                TeachingAssistant ta = new TeachingAssistant(student);
                taMenu(ta);

            }
            else {
                System.out.println("Invalid input. Logging out...");
                Login.enterSystem();
            }
        }
    }

    // TA Menu
    public void taMenu(TeachingAssistant TA) throws CourseFullException, DropDeadlinePassedException, InvalidLoginException {
        while (true) {
            System.out.println("Welcome " + TA.data.getFirst());
            System.out.println("1. View Course Details");
            System.out.println("2. View Grades");
            System.out.println("3. Update Grades");
            System.out.println("4. Back to Student Menu");
            int input;
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextInt();
            if (input == 1) {
                TA.viewTACourse();
            } else if (input == 2) {
                System.out.println("Enter Student ID to view Grades:");
                String studentID = scanner.next();
                Student student = new Student(studentID, Data.getStudentData(studentID));
                TA.viewStudentGrades(student);
            } else if (input == 3) {
                System.out.println("Enter Student ID:");
                String studentID = scanner.next();
                Student student = new Student(studentID, Data.getStudentData(studentID));
                System.out.println("Enter Grade:");
                String grade = scanner.next();
                TA.updateStudentGrades(student, grade);
            } else if (input == 4) {
                System.out.println("Returning to Student Menu...\n\n\n");
                Student student = new Student(TA.username, TA.data);
                studentMenu(student);

            } else {
                System.out.println("Invalid input. Retry...");
            }
        }
    }
    // Professor Menu
    public void professorMenu(Professor professor) throws CourseFullException, DropDeadlinePassedException, InvalidLoginException {
        while (true) {
            System.out.println("Welcome " + professor.data.getFirst());
            System.out.println("1. Manage Courses");
            System.out.println("2. View Enrolled Students");
            System.out.println("3. View Feedback");
            System.out.println("4. Manage TAs");
            System.out.println("5. Logout");
            int input;
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextInt();
            if (input == 1) {
                professor.viewCourses();
                System.out.println("View more course details (Y/N):");
                String viewDetails = scanner.next();
                if (viewDetails.equals("Y")) {
                    System.out.println("Enter course title for more details:");
                    String courseTitle = scanner.next();
                    professor.viewCourseDetails(courseTitle);
                    System.out.println("Update Course Details (Y/N):");
                    String updateDetails = scanner.next();
                    if (updateDetails.equals("Y")) {
                        professor.updateCourseDetails(courseTitle);
                    }
                }

            } else if (input == 2) {
                professor.viewEnrolledStudents();
                System.out.println("Want to enter/view grades of a student? (Y/N)");
                String viewGrades = scanner.next();
                if (viewGrades.equals("Y")) {
                    System.out.println("Enter Student ID:");
                    String studentID = scanner.next();
                    professor.viewGrades(studentID);
                    System.out.println("Enter grades for a student (Y/N):");
                    String enterGrades = scanner.next();
                    if (enterGrades.equals("Y")) {
                        professor.enterGrades(studentID);
                    }
                }

            } else if (input == 3) {
                String CourseID = professor.data.get(2);
                ArrayList<Student> students = Data.getEnrolledStudentList(CourseID);
                System.out.println("Feedback for course " + CourseID + ":");
                System.out.println("\n\n\n");
                professor.viewFeedback(students, CourseID);
            } else if (input == 4) {
                // Print all TAs
                professor.viewTAs();
                System.out.println("1. Add TA\n2. Remove TA");
                int taInput = scanner.nextInt();
                if (taInput == 1) {
                    System.out.println("Enter Student ID:");
                    String studentID = scanner.next();
                    professor.addTA(studentID);
                } else if (taInput == 2) {
                    System.out.println("Enter Student ID:");
                    String studentID = scanner.next();
                    professor.removeTA(studentID);
                }
            } else if (input == 5) {
                System.out.println("Logging out...");
                Login.enterSystem();
            } else {
                System.out.println("Invalid input. Logging out...");
                Login.enterSystem();
            }
        }
    }
    // Admin Menu
    public void adminMenu(Admin admin) throws CourseFullException, DropDeadlinePassedException, InvalidLoginException {
        while (true) {
            System.out.println("1. Manage Course Catalog");
            System.out.println("2. Manage Student Records");
            System.out.println("3. Assign Profs to Courses");
            System.out.println("4. Handle Complaints");
            System.out.println("5. End Add/Drop Period");
            System.out.println("6. Logout");
            int input;
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextInt();
            if (input == 1) {
                admin.viewCourses();
                System.out.println("View more course details (Y/N):");
                String viewDetails = scanner.next();
                if (viewDetails.equals("Y")) {
                    System.out.println("Enter course title for more details:");
                    String courseTitle = scanner.next();
                    admin.viewCourseDetails(courseTitle);
                    System.out.println("Delete this course ? (Y/N):");
                    String updateDetails = scanner.next();
                    if (updateDetails.equals("Y")) {
                        admin.updateCourseDetails(courseTitle);
                    }
                }
                System.out.println("Add a new course? (Y/N):");
                String addCourse = scanner.next();
                if (addCourse.equals("Y")) {
                    System.out.println("Enter Course ID:");
                    String CourseID = scanner.next();
                    admin.addCourse(CourseID);
                }
            } else if (input == 2) {
                System.out.println("Enter Student ID to view:");
                String studentID = scanner.next();
                admin.viewStudent(studentID);
                System.out.println("Update Student Details (Y/N):");
                String updateDetails = scanner.next();
                if (updateDetails.equals("Y")) {
                    admin.updateStudentDetails(studentID);
                }
            } else if (input == 3) {
                System.out.println("Enter Course ID:");
                String CourseID = scanner.next();
                System.out.println("Enter Professor ID:");
                String ProfID = scanner.next();
                admin.assignProf(CourseID, ProfID);
            } else if (input == 4) {
                admin.handleComplaints();
            } else if (input == 5) {
                admin.endAddDrop();
                System.out.println("Add/Drop period ended. Students can no longer add/drop courses.");
                System.out.println("Press Z to restart Add/Drop period.");
                String restart = scanner.next();
                if (restart.equals("Z")) {
                    admin.setCanAddDrop(true);
                    System.out.println("Add/Drop period restarted.");
                }
            } else if (input == 6) {
                System.out.println("Logging out...");
                Login.enterSystem();
            } else {
                System.out.println("Invalid input. Logging out...");
                Login.enterSystem();
            }
        }
    }
}
