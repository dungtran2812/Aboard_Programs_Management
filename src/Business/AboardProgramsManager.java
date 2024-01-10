/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Model.AboardPrograms;
import Tools.InputHandler;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author asus
 */
public class AboardProgramsManager extends HashMap<String, AboardPrograms> {

    public void displaysAll() {
        int count = 0;
        if (this.isEmpty()) {
            System.out.println("Theres no aboard program in list.");
            return;
        }
        for (AboardPrograms ap : this.values()) {
            count++;
            System.out.println("Program No." + count);
            displayAboardPrograms(ap);
        }
    }

    public void displayAboardPrograms(AboardPrograms ap) {
        int count = 0;
        System.out.println("0. ID and Name: " + ap.getId() + "-" + ap.getName());
        System.out.println("1. Time : " + ap.getTime());
        System.out.println("2. Day : " + ap.getDays());
        System.out.println("3. Location : ");
        for (String location : ap.getLocation()) {
            count++;
            System.out.println("\t" + count + ". " + location);
        }
        System.out.println("4. Cost : " + ap.getCost());
        System.out.println("5. Content : " + ap.getContent());
    }

    public void addNewAboardProgram() throws ParseException {
        String id;
        do {
            id = InputHandler.getValidString("Enter id: ");
            if (this.containsKey(id)) {
                System.out.println("Id already exist");
            }
        } while (this.containsKey(id));
        String name = InputHandler.getValidString("Enter Name: ");
        String time = InputHandler.getValidTime("Enter Time(January, March, May, July, September, November): ");
        String fromRegistrationDate;
        String endRegistrationDate;
        do {
            fromRegistrationDate = InputHandler.getDate("Enter From Registration Date : ");
            endRegistrationDate = InputHandler.getDate("Enter End Registration Date : ");
            if (!InputHandler.checkBeforeAfter(fromRegistrationDate, endRegistrationDate)) {
                System.out.println("End date must be after start date.");
            }
        } while (!InputHandler.checkBeforeAfter(fromRegistrationDate, endRegistrationDate));
        int days = InputHandler.getIntInRange(30, 40, "Enter Days : ");
        
        ArrayList<String> locationList = new ArrayList<>();
        String location;
        do {
            location = InputHandler.getValidString("Enter Location : ");
            locationList.add(location);
            
        } while (InputHandler.yesNoConfirm("Do you want to add more location(y/n)?"));

        int cost = InputHandler.getPositiveValue("Enter Cost : ");
        String content = InputHandler.getContent("Enter Content : ");
        AboardPrograms ap = new AboardPrograms(id, name, time, fromRegistrationDate, endRegistrationDate, days, locationList, cost, content);
        this.put(id, ap);
    }

    public void editInfoById(String id) throws ParseException {
        AboardPrograms ap = this.get(id);
        ap.setName(InputHandler.getValidString("Enter Name: "));
        ap.setTime(InputHandler.getValidTime("Enter Time: "));
        String fromRegistrationDate;
        String endRegistrationDate;
        do {
            fromRegistrationDate = InputHandler.getDate("Enter From Registration Date : ");
            endRegistrationDate = InputHandler.getDate("Enter End Registration Date : ");
            if (InputHandler.checkBeforeAfter(fromRegistrationDate, endRegistrationDate)) {
                System.out.println("End date must be after start date.");
            }
        } while (InputHandler.checkBeforeAfter(fromRegistrationDate, endRegistrationDate));
        ap.setFromRegistrationDate(fromRegistrationDate);
        ap.setEndRegistrationDate(endRegistrationDate);
        ap.setDays(InputHandler.getIntInRange(30, 40, "Enter Days: "));
        ArrayList<String> locationList = null;
        do {
            String location = InputHandler.getValidString("Enter Location : ");
            locationList.add(location);
        } while (InputHandler.yesNoConfirm("Do you want to add more location(y/n)?"));
        ap.setLocation(locationList);
        ap.setCost(InputHandler.getPositiveValue("Enter Cost: "));
        ap.setContent(InputHandler.getContent("Enter Content: "));
    }

    public void searchById(String id) {
        AboardPrograms ap = this.get(id);
        displayAboardPrograms(ap);
    }
}
