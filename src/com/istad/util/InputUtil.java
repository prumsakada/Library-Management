package com.istad.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputUtil {
    private static Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String getText(String label) {
        ViewUtil.print(label + "-> ", false);
        return scanner.nextLine();
    }

    public static String getGmail(String label) {
        // Gmail regex: user@gmail.com only
        String gmailRegex = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
        Pattern pattern = Pattern.compile(gmailRegex);

        while (true) {
            String input = getText(label); // use your existing scanner method
            if (pattern.matcher(input).matches()) {
                return input; // valid Gmail
            } else {
                System.out.println("Invalid Gmail! Please enter a valid Gmail address (example@gmail.com).");
            }
        }
    }

    public static String getGmailNullable(String label) {
        String gmailRegex = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
        Pattern pattern = Pattern.compile(gmailRegex);

        while (true) {
            String input = getText(label);
            input = input.trim();
            if (input.isEmpty()) {
                return null;
            } else if (pattern.matcher(input).matches()) {
                return input;
            } else {
                System.out.println("Invalid Gmail! Please enter a valid Gmail address (example@gmail.com) or leave empty to skip.");
            }
        }
    }


    public static String getTextNullable(String label) {
        ViewUtil.print(label + " (Enter to skip) -> ", false);
        String input = scanner.nextLine().trim();

        return input.isEmpty() ? null : input;
    }

    public static BigDecimal getMoney(String label) {
        do {
            ViewUtil.print(label + "-> ", false);
            try {
                return BigDecimal.valueOf(Double.parseDouble(scanner.nextLine()));
            } catch (NumberFormatException e) {
                ViewUtil.print(e.getMessage(), true);
            }
        } while(true);
    }

    public static BigDecimal getMoneyNullable(String label) {
        while (true) {
            ViewUtil.print(label + " (Enter to skip) -> ", false);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                return null;
            }

            try {
                return new BigDecimal(input);
            } catch (NumberFormatException e) {
                ViewUtil.print("Invalid money format. Try again.", true);
            }
        }
    }

    public static Integer getInteger(String label) {
        do {
            ViewUtil.print(label + "-> ", false);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                ViewUtil.print(e.getMessage(), true);
            }
        } while(true);
    }

    public static Integer getIntegerNullable(String label) {
        while (true) {
            ViewUtil.print(label + " (Enter to skip) -> ", false);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                return null;
            }

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                ViewUtil.print("❌ Please enter a valid integer or press Enter to skip.", true);
            }
        }
    }

    public static Double getDouble(String label) {
        do {
            ViewUtil.print(label + "-> ", false);
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                ViewUtil.print(e.getMessage(), true);
            }
        } while(true);
    }

    public static LocalDate getLocalDate(String label) {
        while (true) {
            try {
                ViewUtil.print(label + " (yyyy-MM-dd): " , false);
                String input = scanner.nextLine().trim();


                if (input.isEmpty()) {
                    ViewUtil.print("Date cannot be empty.",true);
                    continue;
                }


                return LocalDate.parse(input, DATE_FORMAT);


            } catch (DateTimeParseException e) {
                ViewUtil.print("Invalid date format. Example: 2026-01-24",true);
            }
        }
    }

    public static LocalDate getLocalDateNullable(String label) {
        while (true) {
            try {
                ViewUtil.print(label + " (yyyy-MM-dd, blank to skip): ",false);
                String input = scanner.nextLine().trim();


                if (input.isEmpty()) {
                    return null;
                }
                return LocalDate.parse(input, DATE_FORMAT);
            } catch (DateTimeParseException e) {
                ViewUtil.print("Invalid date format.",true);
            }
        }
    }
}
