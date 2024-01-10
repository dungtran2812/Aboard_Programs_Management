/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import java.util.Scanner;

/**
 *
 * @author asus
 */
public class Menu {
    Scanner sc = new Scanner(System.in);
    public String generalMenu() {
        System.out.println("=======MENU======");
        System.out.println("1. Manage aboard programs");
        System.out.println("2. Manage students");
        System.out.println("3. Register a program for a student");
        System.out.println("4. Report");
        System.out.println("5 - Quit");
        System.out.println("=================");
        System.out.print("Enter Option: ");
        String choice = sc.nextLine();
        return choice;
    }
    public String aboardProgramMenu() {
        System.out.println("=======ABOARD PROGRAM MENU======");
        System.out.println("1.Displays all aboard programs");
        System.out.println("2.Add a new aboard program");
        System.out.println("3.Edit information a program by id ");
        System.out.println("4.Search and display a program by id");
        System.out.println("5.Back to main menu");
        System.out.println("================================");
        System.out.print("Enter Option: ");
        String choice = sc.nextLine();
        return choice;
    }
    public String studentMenu() {
        System.out.println("=======STUDENT MENU======");
        System.out.println("1.Displays all students");
        System.out.println("2.Add a new student");
        System.out.println("3.Edit information a student by id");
        System.out.println("4.Back to main menu");
        System.out.println("================================");
        System.out.print("Enter Option: ");
        String choice = sc.nextLine();
        return choice;
    }
    public String reportMenu() {
        System.out.println("=======REPORT MENU======");
        System.out.println("1.Print out the registration by student’s id");
        System.out.println("2.Print out the students that registered more than 2 programs");
        System.out.println("3.Count students that registered the program");
        System.out.println("4.Back to main menu");
        System.out.println("================================");
        String choice = sc.nextLine();
        return choice;
    }
}
