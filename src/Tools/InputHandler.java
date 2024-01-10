/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author asus
 */
public class InputHandler {

    static Scanner sc = new Scanner(System.in);

    /**
     * Prompts the user for a yes/no confirmation.
     *
     * @param msg The message to display to the user.
     * @return True if the user confirms with "y", false if the user declines
     * with "n".
     */
    public static boolean yesNoConfirm(String msg) {
        String choice;
        boolean confirm = false;
        boolean quit = false;
        do {
            System.out.print(msg);
            choice = sc.nextLine();
            if (choice.equalsIgnoreCase("y")) {
                confirm = true;
                quit = true;
            } else if (choice.equalsIgnoreCase("n")) {
                confirm = false;
                quit = true;
            } else {
                System.out.println("Invalid Choice");
            }
        } while (!quit);
        return confirm;
    }

    /**
     * Prompts the user for a valid string input.
     *
     * @param msg The message to display to the user.
     * @return The valid string input.
     */
    public static String getValidString(String msg) {
        String input = null;
        boolean quit = false;
        try {
            do {
                System.out.print(msg);
                input = sc.nextLine();
                if (input.isEmpty()) {
                    System.out.println("Value can not null");

                } else {
                    quit = true;
                }

            } while (!quit);
        } catch (Exception e) {
            System.out.println("invalid input");
        }

        return input;
    }

    private static final String dateFormat = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)[0-9]{2,2}$";

    /**
     * Prompts the user for a valid date input.
     *
     * @param msg The message to display to the user.
     * @return The valid date input.
     */
    public static String getDate(String msg) {
        boolean quit = false;
        String date = null;
        while (!quit) {
            try {
                System.out.print(msg);
                date = sc.nextLine();
                Pattern pt = Pattern.compile(dateFormat);
                if (pt.matcher(date).find() && isValidDate(date)) {
                    quit = true;
                    return date;
                }
                throw new Exception();
            } catch (Exception ex) {
                System.out.println("Wrong date format!");
            }
        }
        return date;
    }

    public static boolean isValidDate(String date) {
        String[] split = date.split("[-/. ]");
        int day = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        int year = Integer.parseInt(split[2]);
        int maxDay = 30;
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            maxDay = 31;
        }
        if (month == 2) {
            if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
                maxDay = 29;
            } else {
                maxDay = 28;
            }
        }
        return day <= maxDay;
    }

    /**
     * Prompts the user for a valid gender input.
     *
     * @param message The message to display to the user.
     * @return The valid gender input.
     */
    public static String getGender(String message) {
        String input;
        do {
            System.out.print(message);
            input = sc.nextLine().trim().toLowerCase();
        } while (!input.equals("male") && !input.equals("female") && !input.equals("others"));
        return input;
    }

    /**
     * Prompts the user for a valid shift input.
     *
     * @param message The message to display to the user.
     * @return The valid shift input.
     */
    public static String getShift(String message) {
        String input;
        do {
            System.out.print(message);
            input = sc.nextLine().trim().toLowerCase();
        } while (!input.equals("day") && !input.equals("night") && !input.equals("both"));
        return input;
    }
    static String timeFormat = "^(?i)(January|March|May|July|September|November)$";

    /**
     * Prompts the user for a valid time input.
     *
     * @param msg The message to display to the user.
     * @return The valid time input.
     */
    public static String getValidTime(String msg) {

        String input = null;
        boolean quit;
        Pattern pt = Pattern.compile(timeFormat);
        do {

            quit = false;
            input = getValidString(msg);
            if (pt.matcher(input).find()) {
                quit = true;
            } else {
                System.out.println("Time only accept as: January, March, May, July, September, November");
            }

        } while (!quit);
        return input;

    }

    /**
     * Prompts the user for a valid integer input within the specified range.
     *
     * @param start The start of the range (inclusive).
     * @param end The end of the range (inclusive).
     * @param msg The message to display to the user.
     * @return The valid integer input within the specified range.
     */
    public static int getIntInRange(int start, int end, String msg) {

        int input;
        do {
            System.out.print(msg);
            input = sc.nextInt();
            if (input < start || input > end) {
                System.out.println("Value must be from " + start + " to " + end);
            }
        } while (input < start || input > end);
        sc.nextLine();
        return input;
    }

    public static boolean checkBeforeAfter(String startDate, String endDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        if (sdf.parse(endDate).before(sdf.parse(startDate))) {

            return false;
        }
        return true;
    }

    /**
     * Prompts the user for a positive integer value.
     *
     * @param message The message to display to the user.
     * @return The positive integer value.
     */
    public static int getPositiveValue(String message) {
        int input = 0;

        boolean isValidInput;
        do {
            System.out.print(message);
            try {
                String userInput = sc.nextLine();

                input = Integer.parseInt(userInput);
                if (input > 0) {
                    isValidInput = true;
                } else {
                    System.out.println("Value must be a positive number");
                    isValidInput = false;
                }
            } catch (Exception e) {
                System.out.println("Invalid value");
                isValidInput = false;

            }
        } while (!isValidInput);

        return input;
    }

    /**
     * Prompts the user for a valid file path ending with ".doc" or ".pdf".
     *
     * @param msg The message to display to the user.
     * @return The valid file path.
     */
    public static String getContent(String msg) {
        String input;
        do {
            input = getValidString(msg);
            if (!input.endsWith(".doc") && !input.endsWith(".pdf")) {
                System.out.println("only enter the path of the file( .doc or .pdf)");
            }
        } while (!input.endsWith(".doc") && !input.endsWith(".pdf"));
        return input;
    }
    private static final String majorFormat = "^([Ss][EeBb])|([Gg][Dd])|([Mm][Cc])$";

    /**
     * Prompts the user for a valid major input.
     *
     * @param message The message to display to the user.
     * @return The valid major input.
     */
    public static String getValidMajor(String message) {
        String input;
        boolean quit = true;
        Pattern pt = Pattern.compile(majorFormat);
        do {

            input = getValidString(message);
            if (!pt.matcher(input).find()) {
                System.out.println("Input Invalid");
                quit = false;
            }

        } while (!quit);
        return input;
    }

    /**
     * Prompts the user for a valid email input with the specified domain.
     *
     * @param message The message to display to the user.
     * @param errMessage The error message to display if the input is invalid.
     * @param domain The required domain for the email.
     * @return The valid email input.
     */
    public static String getValidEmail(String message, String errMessage, String domain) {
        String input;
        do {
            input = getValidString(message);
            if (!input.endsWith(domain)) {
                System.out.println(errMessage);
            }
        } while (!input.endsWith(domain));
        return input;

    }

    /**
     * Checks if the given date is between the start date and end date
     * (inclusive).
     *
     * @param startDate The start date.
     * @param day The date to check.
     * @param endDate The end date.
     * @return True if the date is within the range, false otherwise.
     * @throws ParseException If there is an error parsing the dates.
     */
    public static boolean checkDateInRange(String startDay, String day, String endDay) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        if (sdf.parse(startDay).before(sdf.parse(day)) && sdf.parse(endDay).after(sdf.parse(day))) {

            return true;
        }
        return false;
    }

}
