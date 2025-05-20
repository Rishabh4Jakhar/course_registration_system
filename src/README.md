# Assignment 2

## Overview

This project demonstrates the use of generic programming, object-oriented classes, and exception handling in a Java application for managing a university system. The system includes functionalities for students, professors, and teaching assistants (TAs) to manage courses, feedback, and grades.

- Builds on code from Assignment 1 with additional features and improvements (slight modifications in old code to make new things work).

## Assumptions

- Each course is capped with 2/3 student limit
- Add/Drop deadline is on by default and ends when admin ends it. After which student, can neither register nor drop a course.
- Admin can end the add/drop deadline at any time. (Can also be restarted using same option)
- TA can only view grades of students in the course they are TA for.
- One student can only be TA for one course.
- One student (2023435) is prefed TA in C101 Course.
- Professors can add/remove TAs from their course.
- Students who are TA will see a TA option in their menu.

## Generic Programming

### Feedback Class

The `Feedback` class is a generic class that allows storing different types of feedback (e.g., numeric ratings, textual comments) for courses. This class is used to manage feedback provided by students and viewed by professors.


## Object-Oriented Classes

### TeachingAssistant Class

The `TeachingAssistant` class extends the `Student` class and adds additional functionalities for managing student grades. This class demonstrates inheritance by extending the capabilities of the `Student` class.


## Exception Handling

### Custom Exceptions

Custom exceptions are created to handle specific error scenarios in the system.

- `CourseFullException`: Thrown when a student tries to register for a course that is already full.
- `InvalidLoginException`: Thrown when a user tries to log in with incorrect credentials.
- `DropDeadlinePassedException`: Thrown when a student tries to drop a course after the deadline.

Methods in the `Student` and `Login` classes are updated to throw these custom exceptions, and the `Menu` class handles these exceptions using try-catch blocks.
