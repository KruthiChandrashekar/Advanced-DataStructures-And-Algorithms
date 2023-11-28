import java.io.*;
import java.util.*;

class Student {
    String name;
    List<String> enrolledCourses = new ArrayList<>();

    public Student(String name) {
        this.name = name;
    }
}

class Course {
    String name;
    String time;
    String dayOfWeek;
    List<String> enrolledStudents = new ArrayList<>();

    public Course(String name, String time, String dayOfWeek) {
        this.name = name;
        this.time = time;
        this.dayOfWeek = dayOfWeek;
    }
}

class StudentCourseManager {
    private static final String DATABASE_FILE = "database.txt";
    private Map<String, Student> students = new HashMap<>();
    private Map<String, Course> courses = new HashMap<>();

    public static void main(String[] args) {
        StudentCourseManager manager = new StudentCourseManager();
        manager.loadDatabase();

        // Example usage
        manager.enrollStudent("John Doe");
        manager.introduceCourse("Math 101", "9:00 AM", "Monday");
        manager.enrollStudentInCourse("John Doe", "Math 101");
        manager.printEnrolledStudents("Math 101");
        manager.printEnrolledCourses("John Doe");
        manager.printStudentSchedule("John Doe", "Monday");

        manager.saveDatabase();
    }

    public void enrollStudent(String studentName) {
        Student student = new Student(studentName);
        students.put(studentName, student);
    }

    public void introduceCourse(String courseName, String time, String dayOfWeek) {
        Course course = new Course(courseName, time, dayOfWeek);
        courses.put(courseName, course);
    }

    public void enrollStudentInCourse(String studentName, String courseName) {
        if (students.containsKey(studentName) && courses.containsKey(courseName)) {
            Student student = students.get(studentName);
            Course course = courses.get(courseName);

            student.enrolledCourses.add(courseName);
            course.enrolledStudents.add(studentName);
        } else {
            System.out.println("Student or course not found.");
        }
    }

    public void printEnrolledStudents(String courseName) {
        if (courses.containsKey(courseName)) {
            Course course = courses.get(courseName);
            System.out.println("Students enrolled in " + courseName + ": " + course.enrolledStudents);
        } else {
            System.out.println("Course not found.");
        }
    }

    public void printEnrolledCourses(String studentName) {
        if (students.containsKey(studentName)) {
            Student student = students.get(studentName);
            System.out.println(studentName + " is enrolled in courses: " + student.enrolledCourses);
        } else {
            System.out.println("Student not found.");
        }
    }

    public void printStudentSchedule(String studentName, String dayOfWeek) {
        if (students.containsKey(studentName)) {
            Student student = students.get(studentName);
            System.out.println("Schedule for " + studentName + " on " + dayOfWeek + ":");
            for (String courseName : student.enrolledCourses) {
                Course course = courses.get(courseName);
                if (course.dayOfWeek.equals(dayOfWeek)) {
                    System.out.println(courseName + " at " + course.time);
                }
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    public void loadDatabase() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATABASE_FILE))) {
            students = (Map<String, Student>) ois.readObject();
            courses = (Map<String, Course>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No previous database found. Starting with an empty database.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveDatabase() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATABASE_FILE))) {
            oos.writeObject(students);
            oos.writeObject(courses);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
