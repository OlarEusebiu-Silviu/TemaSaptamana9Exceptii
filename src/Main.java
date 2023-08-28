import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Test the Student and StudentRepository classes
        try {
            Student student1 = new Student("Olar", "Eusebiu", "04-02-2000", "M", "1284567390");
            Student student2 = new Student("Mariana", "Popescu", "15-05-2002", "F", "9476548210");

            StudentRepository studentRepository = new StudentRepository();
            studentRepository.addStudent(student1);
            studentRepository.addStudent(student2);

            List<Student> allStudents = studentRepository.getAllStudents();
            System.out.println("All students:");
            for (Student student : allStudents) {
                System.out.println(student);
            }

            studentRepository.sortStudentsByName();
            System.out.println("\nStudents sorted by name:");
            for (Student student : allStudents) {
                System.out.println(student);
            }

            studentRepository.sortStudentsByBirthDate();
            System.out.println("\nStudents sorted by birth date:");
            for (Student student : allStudents) {
                System.out.println(student);
            }

            List<Student> ageFilteredStudents = studentRepository.getStudentsByAge(21);
            System.out.println("\nStudents with age 21:");
            for (Student student : ageFilteredStudents) {
                System.out.println(student);
            }

            studentRepository.deleteStudent("1284567390");
            System.out.println("\nRemaining students:");
            for (Student student : allStudents) {
                System.out.println(student);
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}