import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    // Student Class
    public static class Student {
        private String name;
        private String id;
        private ArrayList<Course> enrolledCourses;
        private Map<Course, Double> courseGrades;

        public Student(String name, String id) {
            this.name = name;
            this.id = id;
            this.enrolledCourses = new ArrayList<>();
            this.courseGrades = new HashMap<>();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public ArrayList<Course> getEnrolledCourses() {
            return enrolledCourses;
        }

        public void enrollInCourse(Course course) {
            if (!enrolledCourses.contains(course) && course.addStudent()) {
                enrolledCourses.add(course);
            } else {
                System.out.println("Cannot enroll in course. Either already enrolled or course is full.");
            }
        }

        public void assignGrade(Course course, double grade) {
            if (enrolledCourses.contains(course)) {
                courseGrades.put(course, grade);
            } else {
                System.out.println("Cannot assign grade. Student is not enrolled in the course.");
            }
        }

        public double getGrade(Course course) {
            return courseGrades.getOrDefault(course, 0.0);
        }

        public double calculateOverallGrade() {
            double totalGrades = 0;
            for (double grade : courseGrades.values()) {
                totalGrades += grade;
            }
            return courseGrades.size() > 0 ? totalGrades / courseGrades.size() : 0.0;
        }
    }

    // Course Class
    public static class Course {
        private String courseCode;
        private String courseName;
        private int maxCapacity;
        private int enrolledStudents;

        private static int totalEnrolledStudents = 0;

        public Course(String courseCode, String courseName, int maxCapacity) {
            this.courseCode = courseCode;
            this.courseName = courseName;
            this.maxCapacity = maxCapacity;
            this.enrolledStudents = 0;
        }

        public String getCourseCode() {
            return courseCode;
        }

        public String getCourseName() {
            return courseName;
        }

        public int getMaxCapacity() {
            return maxCapacity;
        }

        public boolean addStudent() {
            if (enrolledStudents < maxCapacity) {
                enrolledStudents++;
                totalEnrolledStudents++;
                return true;
            }
            return false;
        }

        public static int getTotalEnrolledStudents() {
            return totalEnrolledStudents;
        }
    }

    // CourseManagement Class
    public static class CourseManagement {
        private static ArrayList<Course> courses = new ArrayList<>();
        private static Map<String, Student> students = new HashMap<>();

        public static void addCourse(String courseCode, String courseName, int maxCapacity) {
            courses.add(new Course(courseCode, courseName, maxCapacity));
        }

        public static void enrollStudent(Student student, Course course) {
            student.enrollInCourse(course);
        }

        public static void assignGrade(Student student, Course course, double grade) {
            student.assignGrade(course, grade);
        }

        public static double calculateOverallGrade(Student student) {
            return student.calculateOverallGrade();
        }

        public static Course getCourseByCode(String courseCode) {
            for (Course course : courses) {
                if (course.getCourseCode().equals(courseCode)) {
                    return course;
                }
            }
            return null;
        }

        public static Student getStudentById(String id) {
            return students.get(id);
        }

        public static void addStudent(String name, String id) {
            students.put(id, new Student(name, id));
        }
    }

    // AdminInterface
    public static class AdminInterface {
        public static void run() {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Course Enrollment and Grade Management System");
                System.out.println("1. Add new course");
                System.out.println("2. Enroll student in a course");
                System.out.println("3. Assign grade to student");
                System.out.println("4. Calculate overall grade for a student");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter course code: ");
                        String courseCode = scanner.nextLine();
                        System.out.print("Enter course name: ");
                        String courseName = scanner.nextLine();
                        System.out.print("Enter maximum capacity: ");
                        int maxCapacity = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        CourseManagement.addCourse(courseCode, courseName, maxCapacity);
                        break;
                    case 2:
                        System.out.print("Enter student ID: ");
                        String studentId = scanner.nextLine();
                        System.out.print("Enter course code: ");
                        courseCode = scanner.nextLine();
                        Student student = CourseManagement.getStudentById(studentId);
                        Course course = CourseManagement.getCourseByCode(courseCode);
                        if (student != null && course != null) {
                            CourseManagement.enrollStudent(student, course);
                        } else {
                            System.out.println("Invalid student ID or course code.");
                        }
                        break;
                    case 3:
                        System.out.print("Enter student ID: ");
                        studentId = scanner.nextLine();
                        System.out.print("Enter course code: ");
                        courseCode = scanner.nextLine();
                        System.out.print("Enter grade: ");
                        double grade = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline
                        student = CourseManagement.getStudentById(studentId);
                        course = CourseManagement.getCourseByCode(courseCode);
                        if (student != null && course != null) {
                            CourseManagement.assignGrade(student, course, grade);
                        } else {
                            System.out.println("Invalid student ID or course code.");
                        }
                        break;
                    case 4:
                        System.out.print("Enter student ID: ");
                        studentId = scanner.nextLine();
                        student = CourseManagement.getStudentById(studentId);
                        if (student != null) {
                            double overallGrade = CourseManagement.calculateOverallGrade(student);
                            System.out.println("Overall grade: " + overallGrade);
                        } else {
                            System.out.println("Invalid student ID.");
                        }
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }

    public static void main(String[] args) {
        AdminInterface.run();
    }
}


