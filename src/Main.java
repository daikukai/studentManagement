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
                    System.out.println("Enter Student ID, Name, Age, and Marks:");

                    int id = 0;
                    String name = "";
                    int age = 0;
                    double marks = 0;

                    boolean validInput = false; // Flag to manage re-prompting
                    try {
                        // Validate ID input
                        while (!validInput) {
                            System.out.print("Enter Student ID (positive integer): ");
                            if (sc.hasNextInt()) {
                                id = sc.nextInt();
                                if (id > 0) {
                                    validInput = true; // Valid ID
                                } else {
                                    System.out.println("ID must be a positive integer. Try again.");
                                }
                            } else {
                                System.out.println("Invalid input. Please enter a valid integer for ID.");
                                sc.next(); // Clear invalid input
                            }
                        }

                        // Consume the newline left by sc.nextInt()
                        sc.nextLine();

                        // Validate Name input
                        validInput = false;
                        while (!validInput) {
                            System.out.print("Enter Student Name: ");
                            name = sc.nextLine();
                            if (!name.trim().isEmpty()) {
                                validInput = true; // Valid name
                            } else {
                                System.out.println("Name cannot be empty. Try again.");
                            }
                        }

                        // Validate Age input
                        validInput = false;
                        while (!validInput) {
                            System.out.print("Enter Student Age (integer between 1 and 120): ");
                            if (sc.hasNextInt()) {
                                age = sc.nextInt();
                                if (age > 0 && age <= 120) {
                                    validInput = true; // Valid age
                                } else {
                                    System.out.println("Age must be between 1 and 120. Try again.");
                                }
                            } else {
                                System.out.println("Invalid input. Please enter a valid integer for Age.");
                                sc.next(); // Clear invalid input
                            }
                        }

                        // Validate Marks input
                        validInput = false;
                        while (!validInput) {
                            System.out.print("Enter Student Marks (double between 0 and 100): ");
                            if (sc.hasNextDouble()) {
                                marks = sc.nextDouble();
                                if (marks >= 0 && marks <= 100) {
                                    validInput = true; // Valid marks
                                } else {
                                    System.out.println("Marks must be between 0 and 100. Try again.");
                                }
                            } else {
                                System.out.println("Invalid input. Please enter a valid double for Marks.");
                                sc.next(); // Clear invalid input
                            }
                        }

                        // Once all inputs are valid, create Student and add to the manager
                        Student s = new Student(id, name, age, marks);
                        manager.addStudent(s);
                        System.out.println("Student added successfully!");

                    } catch (Exception e) {
                        System.out.println("An unexpected error occurred: " + e.getMessage());
                        sc.nextLine(); // Clear the scanner buffer
                    }
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