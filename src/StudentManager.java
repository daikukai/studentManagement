import java.io.FileInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * The {@code StudentManager} class provides functionalities to manage a list
 * of students. It allows adding, viewing, updating, deleting, sorting, and
 * saving/retrieving student data from a file.
 */
public class StudentManager {
    /** List of students managed by this class. */
    private ArrayList<Student> students = new ArrayList<>();

    /** File name used for storing student data. */
    private static final String FILE_NAME = "students.dat";

    /**
     * Adds a new student to the list.
     *
     * @param s the {@link Student} object to be added.
     */
    public void addStudent(Student s) {
        students.add(s);
        System.out.println("Student added successfully!");
    }

    // Method to check for duplicate Student ID
    public boolean isDuplicateId(int id) {
        for (Student student : students) {
            if (student.getID() == id) {
                return true; // Duplicate found, exit method
            }
        }
        return false; // No duplicates, return false after full iteration
    }




    /**
     * Displays the list of students in the console.
     */
    public void viewStudents() {
        for (Student s : students) {
            System.out.println(s);
        }
    }

    /**
     * Saves the list of students to a file using serialization.
     * The file used for storage is determined by the constant {@code FILE_NAME}.
     */
    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
            System.out.println("Data saved to file.");
        } catch (Exception e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    /**
     * Loads the list of students from a file using deserialization.
     * If the file does not exist, it initializes the list as empty.
     */
    @SuppressWarnings("unchecked")
    public void loadFromFile() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
                students = (ArrayList<Student>) ois.readObject();
                System.out.println("Data loaded from file.");
            } catch (Exception e) {
                System.out.println("Error loading from file: " + e.getMessage());
            }
        } else {
            System.out.println("File does not exist. Starting refresh.");
        }
    }

    /**
     * Searches for a student by their unique ID.
     *
     * @param id the unique ID of the student.
     * @return the {@link Student} object if found, or {@code null} otherwise.
     */
    public Student searchStudentById(int id) {
        for (Student s : students) {
            if (s.getID() == id) {
                return s;
            }
        }
        return null;
    }

    /**
     * Updates the information of a student with the given ID.
     * @param name  the new name of the student.
     * @param age   the new age of the student.
     * @param marks the new marks of the student.
     * @return
     */
    public boolean updateStudent(int id, String name, int age, double marks) {
        Student s = searchStudentById(id);
        if (s != null) {
            s.SetName(name);
            s.SetAge(age);
            s.SetMarks(marks);
            System.out.println("Student record updated successfully.");

        } else {
            System.out.println("Student not found.");
        }
        return false;
    }

    /**
     * Deletes a student with the given ID from the list.
     *
     * @param id the unique ID of the student to delete.
     * @return
     */
    public boolean deleteStudent(int id) {
        Student s = searchStudentById(id);
        if (s != null) {
            students.remove(s);
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found.");
        }
        return false;
    }

    /**
     * Sorts the list of students by their names in alphabetical order,
     * from A to Z, and displays the sorted list.
     */
    public void sortByName() {
        students.sort((s1, s2) -> s1.getName().compareTo(s2.getName()));
        System.out.println("Sorted by name (A-Z):");
        viewStudents();
    }

    /**
     * Sorts the list of students by their marks in ascending order
     * (from lowest to highest), and displays the sorted list.
     */
    public void sortByMarks() {
        students.sort((s1, s2) -> Double.compare(s1.getMarks(), s2.getMarks()));
        System.out.println("Sorted by marks (highest to lowest):");
        viewStudents();
    }
}