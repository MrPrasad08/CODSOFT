import java.util.*;
import java.util.Scanner;

// === Student class ===
class Student {
    private String id;
    private String name;
    private List<Course> registeredCourses;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }
    public String getId() {return id;}
    public boolean registerCourse(Course course) {
        if (!registeredCourses.contains(course) && course.addStudent(this)) {
            registeredCourses.add(course);
            return true;
        }
        return false;
    }
    public boolean dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            course.removeStudent(this);
            return true;
        }
        return false;
    }
    public void showCourses() {
        if (registeredCourses.isEmpty()) {
            System.out.println("No courses registered.");
        } else {
            for (Course c : registeredCourses) {
                System.out.println(" - " + c.collectCode() + ": " + c.collectTitle());
}}}}
// === Course Database class ===
class CourseDatabase {
    private Map<String, Course> courses = new HashMap<>();

    public void addCourse(Course course) {
        courses.put(course.collectCode(), course);
    }
    public Course getCourse(String code) {
        return courses.get(code.toUpperCase());
    }
    public void showAllCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
            return;
        }
        for (Course c : courses.values()) {
            System.out.println(c);
}}}
// === Student Database class ===
class StudentDatabase {
    private Map<String, Student> students = new HashMap<>();

    public boolean addStudent(Student student) {
        if (students.containsKey(student.getId())) {
            return false;
        }
        students.put(student.getId(), student);
        return true;
    }
    public Student getStudent(String id) {
        return students.get(id);
}}
// Course
class Course {
    String code,title,schedule;
    int capacity;
    List<Student> enrolledStudents;

    public Course(String code, String title, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolledStudents = new ArrayList<>();
    }
    public String collectCode() {return code;}
    public String collectTitle() {return title;}
    public String collectSchedule() {return schedule;}
    public int collectCapacity() {return capacity;}
    public int collectEnrolledCount() {return enrolledStudents.size();}
    public boolean hasSlot() {return enrolledStudents.size() < capacity;}
    public boolean addStudent(Student student) {
        if (hasSlot() && !enrolledStudents.contains(student)) {
            enrolledStudents.add(student);
            return true;
        }
        else {return false;}
    }
    public void removeStudent(Student student) {
        enrolledStudents.remove(student);
    }
    @Override
    public String toString() {
        return code + " - " + title + " (" + collectEnrolledCount() + "/" + capacity + ") | " + schedule;
    }
}
// === Main Application ===
public class StudentCourseRegistration {
    private static final Scanner scanner = new Scanner(System.in);
    private static final CourseDatabase courseDB = new CourseDatabase();
    private static final StudentDatabase studentDB = new StudentDatabase();

    public static void main(String[] args) {
        setupCourses();

        while (true) {
            showMenu();
            int choice = readInt("Choose an option: ");
            switch (choice) {
                case 1 -> courseDB.showAllCourses();
                case 2 -> registerNewStudent();
                case 3 -> registerForCourse();
                case 4 -> dropCourse();
                case 5 -> viewStudentCourses();
                case 6 -> {
                    System.out.println("Exiting. Thank you!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
}}}
    // Preload some sample courses
    private static void setupCourses() {
        courseDB.addCourse(new Course("CS178", "Computer Science Basics", 12, "Mon/Wed 10:00 AM"));
        courseDB.addCourse(new Course("MAT116", "Mathematics", 10, "Tue/Thu 11:00 AM"));
        courseDB.addCourse(new Course("CMS102", "Communication Skills", 10, "Wed/Fri 1:00 PM"));
        courseDB.addCourse(new Course("PHY106", "Physics", 3, "Sat 10:00 AM"));
        courseDB.addCourse(new Course("JAVA301", "Java Basics", 10, "Mon/Tue/Wed 5:00 PM"));
        courseDB.addCourse(new Course("PY205", "Python Programming", 10, "Thu/Fro/Sat 5:00 PM"));
    }
    // Show main menu
    private static void showMenu() {
        System.out.println("\n===============================================");
        System.out.println("\n== STUDENT COURSE REGISTRATION SYSTEM ==");
        System.out.println("\n===============================================");
        System.out.println("1. Show All Courses");
        System.out.println("2. Register New Student");
        System.out.println("3. Register for a Course");
        System.out.println("4. Drop a Course");
        System.out.println("5. View My Courses");
        System.out.println("6. Exit");
    }
    private static void registerNewStudent() {
        String id = readLine("Enter student ID: ");
        String name = readLine("Enter student name: ");
        Student student = new Student(id, name);
        if (studentDB.addStudent(student)) {
            System.out.println("Student registered successfully!");
        } else {
            System.out.println("Student ID already exists.");
        }
    }
    private static void registerForCourse() {
        Student student = getStudentById();
        if (student == null) return;
        String courseCode = readLine("Enter course code to register: ");
        Course course = courseDB.getCourse(courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }
        if (student.registerCourse(course)) {
            System.out.println("Course registration successful!");
        } else {
            System.out.println("Could not register. Course might be full or already registered.");
        }
    }
    private static void dropCourse() {
        Student student = getStudentById();
        if (student == null) return;
        String courseCode = readLine("Enter course code to drop: ");
        Course course = courseDB.getCourse(courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }
        if (student.dropCourse(course)) {
            System.out.println("Course dropped successfully.");
        } else {
            System.out.println("You are not registered in this course.");
        }
    }
    private static void viewStudentCourses() {
        Student student = getStudentById();
        if (student == null) return;
        System.out.println("Courses for " + student.getId() + ":");
        student.showCourses();
    }
    private static Student getStudentById() {
        String id = readLine("Enter your student ID: ");
        Student student = studentDB.getStudent(id);
        if (student == null) {
            System.out.println("Student not found.");
        }
        return student;
    }
    // Input helpers
    private static String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
    private static int readInt(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(readLine(prompt));
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter again.");
            }
        }
    }
}