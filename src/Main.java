import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        Scanner sc = new Scanner(System.in);

        manager.loadFromFile();// load existing data

        while (true) {
            System.out.println("1. Add Student\n2. View Students\n3. Search \n4. Update Students \n5. " +
                    "Delete Students \n6. Sort by Name \n7. Exit" );
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter Student ID, Name, Age and Marks");
                    int id = sc.nextInt();
                    String name = sc.next();
                    int age = sc.nextInt();
                    double marks = sc.nextDouble();
                    Student s = new Student(id, name, age, marks);
                    manager.addStudent(s);
                    break;

                case 2: // View all students
                    manager.viewStudents();
                    break;

                case 3: // Search for a student
                    System.out.print("Enter Student ID to search: ");
                    int searchId = sc.nextInt();
                    Student foundStudent = manager.searchStudentById(searchId);
                    if (foundStudent != null) {
                        System.out.println("Student found: " + foundStudent);
                    } else {
                        System.out.println("No student found with ID: " + searchId);
                    }
                    break;

                case 4: // Update student details
                    System.out.print("Enter Student ID to update: ");
                    int updateId = sc.nextInt();
                    System.out.println("Enter new Name, Age, and Marks:");
                    String updatedName = sc.next();
                    int updatedAge = sc.nextInt();
                    double updatedMarks = sc.nextDouble();
                    boolean updateStatus = manager.updateStudent(updateId, updatedName, updatedAge, updatedMarks);
                    if (updateStatus) {
                        System.out.println("Student updated successfully.");
                    } else {
                        System.out.println("Update failed. Student with ID not found.");
                    }
                    break;

                case 5: // Delete a student
                    System.out.print("Enter Student ID to delete: ");
                    int deleteId = sc.nextInt();
                    boolean deleteStatus = manager.deleteStudent(deleteId);
                    if (deleteStatus) {
                        System.out.println("Student deleted successfully.");
                    } else {
                        System.out.println("Deletion failed. Student with ID not found.");
                    }
                    break;

                case 6: // Sort students by name
                    manager.sortByName();
                    System.out.println("Students sorted by name.");
                    break;


                case 7:
                System.out.println("Exiting program...");
                sc.close();
                return;
            }
        }
    }
}