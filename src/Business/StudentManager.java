/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Model.Student;
import Tools.InputHandler;
import java.util.HashMap;

/**
 *
 * @author asus
 */
public class StudentManager extends HashMap<String, Student>{
    public void dataTable() {
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        System.out.printf("|%-8s|%-12s|%-14s|%-30s|%-15s|%-30s|\n"
                , "ID", "NAME", "MAJOR", "EMAIL", "PHONE", "PASSPORT","ADDRESS");
        System.out.println("----------------------------------------------------------------------------------------------------------------");
    }
    public void displayStudent(Student student) {
        
        
        System.out.printf("|%-8s|%-12s|%-14s|%-30s|%-15s|%-30s|\n"
                , student.getId(), student.getName(), student.getMajor(), student.getEmail(), student.getPhone(), student.getPassport(),student.getAddress());
        
    }
    public void displayAllStudent() {
        if (this.isEmpty()) {
            System.out.println("Empty");
            return;
        }
        else {
            dataTable();
            for (Student student : this.values()) {
                displayStudent(student);
            }
        }
        
    }
    public void addStudent() {
        String id;
        do {
            id = InputHandler.getValidString("Enter id: ");
            if (this.containsKey(id)) {
                System.out.println("Id already exist");
            }
        } while (this.containsKey(id));
        String name = InputHandler.getValidString("Enter Name: ");
        String major = InputHandler.getValidMajor("Enter major: ");
        id = major+id;
        String email = InputHandler.getValidEmail("Enter Email: ", "Email have domain @fpt.edu.vn", "@fpt.edu.vn");
        String phone = InputHandler.getValidString("Enter phone: ");
        String passport = InputHandler.getValidString("Enter passport: ");
        String address = InputHandler.getValidString("Enter address: ");
        Student student = new Student(id, name, major, email, phone, passport, address);
        this.put(id, student);
    }
    public void editStudent() {
        if (this.isEmpty()) {
            System.out.println("There's no student in data");
            return;
        }
        Student studentFind;
        String id;
        do {
            id = InputHandler.getValidString("Enter id: ");
            if (!this.containsKey(id)) {
                System.out.println("Id doesnt exist");
            }
        } while (this.containsKey(id));
        studentFind = this.get(id);
        System.out.println("Student Info: ");
        displayStudent(studentFind);
        studentFind.setName(InputHandler.getValidString("Enter Name: "));
        studentFind.setMajor(InputHandler.getValidMajor("Enter major: "));
        studentFind.setEmail(InputHandler.getValidEmail("Enter Email: ", "Email have domain @fpt.edu.vn", "@fpt.edu.vn"));
        studentFind.setPhone(InputHandler.getValidString("Enter phone: "));
        studentFind.setPassport(InputHandler.getValidString("Enter passport: "));
        studentFind.setAddress(InputHandler.getValidString("Enter address: "));
        
    }
}
