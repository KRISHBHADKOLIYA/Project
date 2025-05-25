import java.util.*;

// ==================== MODEL ====================
class Course {
    String courseCode, title, description, schedule;
    int capacity;
    List<String> registeredStudentIds = new ArrayList<>();

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
    }

    public boolean isAvailable() {
        return registeredStudentIds.size() < capacity;
    }
}

class Student {
    String studentId, name, email;
    List<String> registeredCourses = new ArrayList<>();

    public Student(String studentId, String name, String email) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
    }
}

// ==================== CONTROLLER ====================
class RegistrationSystem {
    Map<String, Course> courses = new HashMap<>();
    Map<String, Student> students = new HashMap<>();

    public void addCourse(Course course) {
        courses.put(course.courseCode, course);
    }

    public void addStudent(Student student) {
        students.put(student.studentId, student);
    }

    public void listCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courses.values()) {
            System.out.printf("%s - %s | %s | Capacity: %d | Available: %d\n",
                    course.courseCode, course.title, course.schedule,
                    course.capacity, course.capacity - course.registeredStudentIds.size());
        }
    }

    public void registerCourse(String studentId, String courseCode) {
        Student student = students.get(studentId);
        Course course = courses.get(courseCode);

        if (student == null || course == null) {
            System.out.println("Invalid student or course.");
            return;
        }

        if (student.registeredCourses.contains(courseCode)) {
            System.out.println("Already registered for this course.");
            return;
        }

        if (!course.isAvailable()) {
            System.out.println("Course is full.");
            return;
        }

        student.registeredCourses.add(courseCode);
        course.registeredStudentIds.add(studentId);
        System.out.println("Registered successfully!");
    }

    public void dropCourse(String studentId, String courseCode) {
        Student student = students.get(studentId);
        Course course = courses.get(courseCode);

        if (student != null && student.registeredCourses.remove(courseCode)) {
            course.registeredStudentIds.remove(studentId);
            System.out.println("Course dropped successfully.");
        } else {
            System.out.println("You are not registered in this course.");
        }
    }

    public void viewStudentCourses(String studentId) {
        Student student = students.get(studentId);
        if (student == null) {
            System.out.println("Invalid student ID.");
            return;
        }
        System.out.println("\nCourses registered by " + student.name + ":");
        for (String code : student.registeredCourses) {
            Course course = courses.get(code);
            System.out.println("- " + course.title + " (" + course.courseCode + ")");
        }
    }
}

// ==================== VIEW (CLI) ====================
public class Main {
    public static void main(String[] args) {
        RegistrationSystem system = new RegistrationSystem();
        Scanner scanner = new Scanner(System.in);

        // Sample Data
        system.addCourse(new Course("CS101", "Intro to Java", "Basics of programming", 2, "Mon 10-12"));
        system.addCourse(new Course("CS102", "Data Structures", "Arrays, Lists, Trees", 3, "Wed 14-16"));

        system.addStudent(new Student("S001", "Alice", "alice@example.com"));
        system.addStudent(new Student("S002", "Bob", "bob@example.com"));

        // Menu
        while (true) {
            System.out.println("\n==== Student Course Registration System ====");
	    System.out.println("1. Add New Student");
            System.out.println("2. View Available Courses");
            System.out.println("3. Register for a Course");
            System.out.println("4. Drop a Course");
            System.out.println("5. View My Courses");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
		case 1:
		     System.out.print("Enter Student ID: ");
		     String newId = scanner.nextLine();
		     System.out.print("Enter Name: ");
	 	     String newName = scanner.nextLine();
 	    	     System.out.print("Enter Email: ");
  		     String newEmail = scanner.nextLine();
		     system.addStudent(new Student(newId, newName, newEmail));
      		     System.out.println("Student added successfully.");
		     break;
                case 2:
                    system.listCourses();
                    break;
                case 3:
                    System.out.print("Enter Student ID: ");
                    String sidReg = scanner.nextLine();
                    System.out.print("Enter Course Code: ");
                    String cidReg = scanner.nextLine();
                    system.registerCourse(sidReg, cidReg);
                    break;
                case 4:
                    System.out.print("Enter Student ID: ");
                    String sidDrop = scanner.nextLine();
                    System.out.print("Enter Course Code: ");
                    String cidDrop = scanner.nextLine();
                    system.dropCourse(sidDrop, cidDrop);
                    break;
                case 5:
                    System.out.print("Enter Student ID: ");
                    String sidView = scanner.nextLine();
                    system.viewStudentCourses(sidView);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option.");

            }
        }
    }
}
