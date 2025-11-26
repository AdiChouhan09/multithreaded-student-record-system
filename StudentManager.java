import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class StudentManager {

    private final List<Student> students = new ArrayList<>();
    private final String fileName;

    public StudentManager(String fileName) {
        this.fileName = fileName;
    }

    public void loadFromFile() {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("Data file not found. A new file will be created on save.");
            return;
        }
        LoadingThread loader = new LoadingThread("Loading data");
        loader.start();
        students.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                Student s = Student.fromCsv(line);
                if (s != null) {
                    students.add(s);
                }
            }
            loader.join();
            System.out.println("Loaded " + students.size() + " student(s) from file.");
        } catch (IOException | InterruptedException e) {
            System.out.println("Error while loading data: " + e.getMessage());
        }
    }

    public void saveToFile() {
        LoadingThread loader = new LoadingThread("Saving data");
        loader.start();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Student s : students) {
                bw.write(s.toCsv());
                bw.newLine();
            }
            loader.join();
            System.out.println("Student records saved to file.");
        } catch (IOException | InterruptedException e) {
            System.out.println("Error while saving data: " + e.getMessage());
        }
    }

    public void addStudent(Student s) {
        LoadingThread loader = new LoadingThread("Adding student");
        loader.start();
        students.add(s);
        try {
            loader.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Student added successfully.");
    }

    public void updateStudent(int rollNo, double newMarks) throws StudentNotFoundException {
        Student s = findStudent(rollNo);
        LoadingThread loader = new LoadingThread("Updating student");
        loader.start();
        s.setMarks(newMarks);
        try {
            loader.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Marks updated for roll no " + rollNo + ".");
    }

    public void deleteStudent(int rollNo) throws StudentNotFoundException {
        Student s = findStudent(rollNo);
        LoadingThread loader = new LoadingThread("Deleting student");
        loader.start();
        students.remove(s);
        try {
            loader.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Student with roll no " + rollNo + " deleted.");
    }

    public Student findStudent(int rollNo) throws StudentNotFoundException {
        for (Student s : students) {
            if (s.getRollNo() == rollNo) {
                return s;
            }
        }
        throw new StudentNotFoundException("Student with roll no " + rollNo + " not found.");
    }

    public void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records available.");
            return;
        }
        System.out.println("---- Student Records ----");
        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            it.next().displayInfo();
        }
    }

    public void sortByMarks() {
        if (students.isEmpty()) {
            System.out.println("No student records to sort.");
            return;
        }
        Collections.sort(students, new StudentComparator());
        System.out.println("Students sorted by marks (highest first).");
    }
}
