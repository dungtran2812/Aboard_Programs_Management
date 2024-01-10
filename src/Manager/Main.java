/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Business.GeneralManager;
import java.io.IOException;
import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author asus
 */
public class Main {

    public static void main(String[] args) throws ParseException, IOException, ClassNotFoundException {

        GeneralManager gm = new GeneralManager();
        gm.loadFromFileProgram("src\\Data\\AboardProgram.dat");
        gm.loadFromFileStudent("src\\Data\\Student.dat");
        Menu menu = new Menu();

        String generalChoice;
        do {
            generalChoice = menu.generalMenu();
            switch (generalChoice) {
                //Manage aboard programs menu 

                case "1":
                    String aboardProgramChoice;
                    do {
                        aboardProgramChoice = menu.aboardProgramMenu();
                        switch (aboardProgramChoice) {
                            case "1":
                                gm.displayAllProgram();
                                break;
                            case "2":
                                gm.addNewProgram();
                                break;
                            case "3":
                                gm.editProgram();
                                break;
                            case "4":
                                gm.searchProgram();
                                break;
                            case "5":
                                break;
                            default:
                                System.out.println("Invalid Option");
                                break;
                        }
                    } while (!aboardProgramChoice.equals("5"));

                    break;
                //Manage students menu
                case "2":
                    String studentChoice;
                    do {
                        studentChoice = menu.studentMenu();
                        switch (studentChoice) {
                            case "1":
                                gm.displayAllStudent();
                                break;
                            case "2":
                                gm.addStudent();
                                break;
                            case "3":
                                gm.editStudent();
                                break;
                            case "4":

                                break;
                            default:
                                System.out.println("Invalid Option");
                                break;
                        }
                    } while (!studentChoice.equals("4"));
                    break;
                case "3":
                    gm.registerProgramForStudent();
                    break;
                case "4":
                    String reportChoice;
                    do {
                        reportChoice = menu.reportMenu();
                        switch (reportChoice) {
                            case "1":
                                gm.printRegistrationById();
                                break;
                            case "2":
                                gm.printStudentMoreThan2program();
                                break;
                            case "3":
                                gm.countStudentOfProgram();
                                break;
                            case "4":

                                break;
                            default:
                                System.out.println("Invalid Option");
                                break;
                        }
                    } while (!reportChoice.equals("4"));
                    break;
            }
        } while (!generalChoice.equals("5"));
        if (generalChoice.equals("5")) {
            gm.saveToFileProgram("src\\Data\\AboardProgram.dat");
            gm.saveToFileStudent("src\\Data\\Student.dat");
        }

    }
}
