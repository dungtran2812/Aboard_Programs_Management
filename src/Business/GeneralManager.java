/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Model.AboardPrograms;
import Model.Student;
import Tools.InputHandler;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * The GeneralManager class handles the management of aboard programs and students.
 */
public class GeneralManager {
    // Create a Scanner object for user input
    Scanner sc = new Scanner(System.in);
    // Create an instance of AboardProgramsManager to manage aboard programs
    AboardProgramsManager aboardProgramsManager = new AboardProgramsManager();
    
    /**
     * Displays information about all aboard programs.
     */
    public void displayAllProgram() {
        aboardProgramsManager.displaysAll();
    }

    /**
     * Adds a new aboard program.
     * @throws ParseException if there is an error in parsing input.
     */
    public void addNewProgram() throws ParseException {
        aboardProgramsManager.addNewAboardProgram();
    }

    /**
     * Edits an existing aboard program.
     * @throws ParseException if there is an error in parsing input.
     */
    public void editProgram() throws ParseException {
        String id = InputHandler.getValidString("Enter Id to edit: ");
        aboardProgramsManager.editInfoById(id);
    }

    /**
     * Searches for an aboard program by ID.
     */
    public void searchProgram() {
        String id = InputHandler.getValidString("Enter Id to display: ");
        aboardProgramsManager.searchById(id);
    }
    
    // Create an instance of StudentManager to manage students
    StudentManager studentManager = new StudentManager();

     /**
     * Displays information about all students.
     */
    public void displayAllStudent() {
        studentManager.displayAllStudent();
    }

    /**
     * Adds a new student.
     */
    public void addStudent() {
        studentManager.addStudent();
    }

    /**
     * Edits an existing student.
     */
    public void editStudent() {
        studentManager.editStudent();
    }

     /**
     * Registers a student for an aboard program.
     * @throws ParseException if there is an error in parsing input.
     * @throws IOException if an I/O error occurs.
     */
    public void registerProgramForStudent() throws ParseException, IOException {
        if (aboardProgramsManager.isEmpty()) {
            System.out.println("Theres no program in data");
            return;
        }
        if (studentManager.isEmpty()) {
            System.out.println("Theres no student in data");
            return;
        }
        String programId;
        System.out.println("Staff Enter: ");
        do {
            programId = InputHandler.getValidString("Enter program id: ");
            if (!aboardProgramsManager.containsKey(programId)) {
                System.out.println("program id doesnt exist");
            }
        } while (!aboardProgramsManager.containsKey(programId));

        String studentId;
        do {
            studentId = InputHandler.getValidString("Enter student id: ");
            if (!studentManager.containsKey(studentId)) {
                System.out.println("student id doesnt exist");
            }
        } while (!studentManager.containsKey(studentId));
        System.out.println("Student Enter: ");
        String registrationDate;
        String startDate = aboardProgramsManager.get(programId).getFromRegistrationDate();
        String endDate = aboardProgramsManager.get(programId).getEndRegistrationDate();
        do {
            registrationDate = InputHandler.getDate("Enter Registration date: ");
            if (!InputHandler.checkDateInRange(startDate, registrationDate, endDate)) {
                System.out.println("registration date must be between begin and end of the program’s registration date");
            }
        } while (!InputHandler.checkDateInRange(startDate, registrationDate, endDate));
        String parentsEmail = InputHandler.getValidEmail("Enter parents Email: ", "Email must have domain @gmail.com", "@gmail.com");
        String parentsPhone = InputHandler.getValidString("Enter parents phone: ");
        saveRegistrationForm(programId, studentId, registrationDate, parentsEmail, parentsPhone);
    }

    public void saveRegistrationForm(String programId, String studentId, String registrationDate, String parentsEmail, String parentsPhone) throws FileNotFoundException, IOException {
        AboardPrograms program = aboardProgramsManager.get(programId);
        Student student = studentManager.get(studentId);
        String path = "\\src\\RegistrationForm\\";
        String folderPath = new File("").getAbsolutePath();
        path = folderPath + path;
        String filePath = path + studentId + "_" + programId + ".doc";
        FileOutputStream f = new FileOutputStream(filePath);
        String content = "\t\tAboard Program Registration Form\n"
                + "Information Student:\n"
                + String.format("Student id: %s\tStudent name: %s\n", studentId, student.getName())
                + String.format("Major: %s\tEmail: %s\tPhone: %s\tPassport: %s\n", student.getMajor(), student.getEmail(), student.getPhone(), student.getPassport())
                + String.format("Address: %s\tEmail of the parents: %s\tPhone of the parents: %s\n", student.getAddress(), parentsEmail, parentsPhone)
                + "Information of the aboard program:\n"
                + String.format("Program’s id: %s\tProgram’s name: %s\n", program.getId(), program.getName())
                + String.format("Time: %s\tDays: %d\tLocation: %s\tCost: %d\n", program.getTime(), program.getDays(), program.getLocation(), program.getCost())
                + "Information of the registration:\n"
                + String.format("registration date:\t%s", registrationDate);
        f.write(content.getBytes());
        System.out.println("File created successfully!");
    }

    public void printRegistrationById() {
        System.out.println("Enter id to print: ");
        String id = sc.nextLine();
        String path = "\\src\\RegistrationForm";
        String folderPath = new File("").getAbsolutePath();
        path = folderPath + path;

        File directory = new File(path);
        if (directory.isDirectory()) {
            // Get an array of filenames in the directory
            File[] fileList = directory.listFiles();

            // Iterate over the filenames and print them
            if (fileList != null) {
                for (File file : fileList) {
                    String[] fileNameSubStrings = file.getName().split("_");
                    if (fileNameSubStrings[0].equalsIgnoreCase(id)) {
                        printFile(file);
                        return;
                    }
                }
            }
        } else {
            System.out.println("Invalid directory path.");
        }
    }

    public void printFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()))) {
            String line;

            // Read each line from the file and print its content
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    public HashMap<String, Integer> getStudentWithNumberOfProgram() {

        HashMap<String, Integer> studentMap = new HashMap<>();

        String path = "\\src\\RegistrationForm";
        String folderPath = new File("").getAbsolutePath();
        path = folderPath + path;
        File directory = new File(path);
        if (directory.isDirectory()) {
            // Get an array of filenames in the directory
            File[] fileList = directory.listFiles();

            // Iterate over the filenames and print them
            if (fileList != null) {
                for (File file : fileList) {
                    String[] fileNameSubStrings = file.getName().split("_");
                    int count = studentMap.getOrDefault(fileNameSubStrings[0], 0);
                    studentMap.remove(fileNameSubStrings[0]);
                    studentMap.put(fileNameSubStrings[0], count + 1);
                }

            }
        } else {
            System.out.println("Invalid directory path.");
        }
        return studentMap;
    }

    public void printStudentMoreThan2program() {
        HashMap<String, Integer> studentMap = getStudentWithNumberOfProgram();
        List<String> studenMore2 = new ArrayList<>();
        for (String studentId : studentMap.keySet()) {
            if (studentMap.get(studentId) >= 2) {
                studenMore2.add(studentId);
            }
        }
        if (studenMore2.isEmpty()) {
            System.out.println("Theres no student registration more than 2 program");
        } else {
            studentManager.dataTable();
        }
        for (String id : studenMore2) {

            Student student = studentManager.get(id);
            if (student != null) {
                studentManager.displayStudent(student);
            }
        }

    }

    public void countStudentOfProgram() {
        HashMap<String, Integer> programMap = new HashMap<>();

        String path = "\\src\\RegistrationForm";
        String folderPath = new File("").getAbsolutePath();
        path = folderPath + path;
        File directory = new File(path);
        if (directory.isDirectory()) {
            // Get an array of filenames in the directory
            File[] fileList = directory.listFiles();

            // Iterate over the filenames and print them
            if (fileList != null) {
                for (File file : fileList) {
                    String[] fileNameSubStrings = file.getName().split("_");
                    String[] fileNameSubStrings2 = fileNameSubStrings[1].split("\\.");
                    int count = programMap.getOrDefault(fileNameSubStrings2[0], 0);
                    programMap.remove(fileNameSubStrings2[0]);
                    programMap.put(fileNameSubStrings2[0], count + 1);
                }
            }
        } else {
            System.out.println("Invalid directory path.");
        }
        System.out.println(programMap.keySet());
        System.out.println("Enter program id: ");
        String programId = sc.nextLine();
        System.out.println("The number of student of this program : " + programMap.get(programId));
    }

    public void saveToFileProgram(String filePathProgram) {
        if (aboardProgramsManager.isEmpty()) {
            System.out.println("Program's list empty!");
            return;
        }
        try {
            FileOutputStream f = new FileOutputStream(filePathProgram);
            ObjectOutputStream fo = new ObjectOutputStream(f);
            Iterator iter = aboardProgramsManager.keySet().iterator();
            while (iter.hasNext()) {
                Object key = iter.next();
                fo.writeObject(aboardProgramsManager.get(key));
            }
            f.close();
            fo.close();
            System.out.println("Program's list has been saved!");
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

    public void saveToFileStudent(String filePathStudent) {
        if (studentManager.isEmpty()) {
            System.out.println("Student's list empty!");
            return;
        }
        try {
            FileOutputStream f = new FileOutputStream(filePathStudent);
            ObjectOutputStream fo = new ObjectOutputStream(f);
            Iterator iter = studentManager.keySet().iterator();
            while (iter.hasNext()) {
                Object key = iter.next();
                fo.writeObject(studentManager.get(key));
            }
            f.close();
            fo.close();
            System.out.println("Student's list has been saved!");
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void loadFromFileProgram(String filePathProgram) throws ClassNotFoundException {
        if (!aboardProgramsManager.isEmpty()) {
            aboardProgramsManager.clear();
        }
        try {
            File file = new File(filePathProgram);
            if (!file.exists()) {
                System.out.println("Program's list is empty!");
                return;
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            AboardPrograms aboardPrograms;
            boolean quit = false;
            while (!quit) {
                try {
                    aboardPrograms = (AboardPrograms) objectInputStream.readObject();
                    aboardProgramsManager.put(aboardPrograms.getId(), aboardPrograms);
                } catch (EOFException e) {
                    quit = true;
                    break; // Reached the end of the file, break out of the loop(End of File exception)
                }
            }
            fileInputStream.close();
            objectInputStream.close();
            System.out.println("Program's list has been loaded!");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void loadFromFileStudent(String filePathStudent) throws ClassNotFoundException {
        if (!studentManager.isEmpty()) {
            studentManager.clear();
        }
        try {
            File file = new File(filePathStudent);
            if (!file.exists()) {
                System.out.println("Student's list is empty!");
                return;
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Student student;
            boolean quit = false;
            while (!quit) {
                try {
                    student = (Student) objectInputStream.readObject();
                    studentManager.put(student.getId(), student);
                } catch (EOFException e) {
                    quit = true;
                    break; // Reached the end of the file, break out of the loop(End of File exception)
                }
            }
            fileInputStream.close();
            objectInputStream.close();
            System.out.println("Student's list has been loaded!");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
