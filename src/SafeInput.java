import java.util.Scanner;

public class SafeInput {


    /**
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a String response that is not zero length
     */
    //getNonZeroLenString
    public static String getNonZeroLenString(Scanner pipe, String prompt) {
        String getString = "";
        do {
            System.out.print("\n" + prompt + ": ");
            getString = pipe.nextLine();
        }
        while (getString.length() == 0);
        return getString;
    }


    /**
     * @param pipe
     * @param prompt
     * @return
     */
    //getInt
    public static int getInt(Scanner pipe, String prompt) {
        int result = 0;
        boolean done = false;
        String trash = "";
        do {
            System.out.println(prompt);
            if (pipe.hasNextInt()) {
                result = pipe.nextInt();
                pipe.nextLine(); // clear input
                done = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter an int: " + trash);
            }
        }
        while (!done);
        return result;
    }


    /**
     * @param pipe
     * @param prompt
     * @return
     */
    //getDouble
    public static double getDouble(Scanner pipe, String prompt) {
        double result = 0;
        boolean done = false;
        String trash = "";
        do {
            System.out.println(prompt);
            if (pipe.hasNextDouble()) {
                result = pipe.nextDouble();
                pipe.nextLine(); // clear input
                done = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter a double: " + trash);
            }
        }
        while (!done);
        return result;
    }


    /**
     * @param pipe
     * @param prompt
     * @return
     */
    //getRangedInt
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
        int result = 0;
        boolean done = false;
        String trash = "";
        do {
            System.out.println(prompt + "[" + low + " - " + high + "]: ");
            if (pipe.hasNextInt()) {
                result = pipe.nextInt();
                pipe.nextLine(); // clear input
                if (result >= low && result <= high) {
                    done = true;
                } else {
                    System.out.println("You must enter a value in range [" + low + " - " + high + "]: " + result);
                }
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter an int [" + low + " - " + high + "]: " + trash);
            }
        }
        while (!done);
        return result;
    }


    /**
     * @param pipe
     * @param prompt
     * @return
     */
    //getRangedDouble
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) {
        double result = 0;
        boolean done = false;
        String trash = "";
        do {
            System.out.println(prompt + "[" + low + " - " + high + "]: ");
            if (pipe.hasNextDouble()) {
                result = pipe.nextDouble();
                pipe.nextLine(); // clear input
                if (result >= low && result <= high) {
                    done = true;
                } else {
                    System.out.println("You must enter a value in range [" + low + " - " + high + "]: " + result);
                }
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter a double [" + low + " - " + high + "]: " + trash);
            }
        }
        while (!done);
        return result;
    }


    /**
     * @param pipe
     * @param prompt
     * @return
     */
    //getYNConfirm
    public static boolean getYNConfirm(Scanner pipe, String prompt) {
        String getString = "";
        boolean done = false;
        boolean yN = false;
            System.out.print("\n" + prompt + ": ");
        do {
            getString = pipe.nextLine();
            if (getString.equalsIgnoreCase("Y")) {
                done = true;
            } else if (getString.equalsIgnoreCase("N")) {
                done = true;
                yN = true;
            } else {
                System.out.println("\n" + getString + "must be 'Y' for yes or 'N' for no");
                System.out.println("Try again!");
                done = false;
            }
        }
        while (!done);
        return yN;
    }


    /**
     * @param pipe
     * @param prompt
     * @return
     */
    //getRegExString
    public static String getRegExString(Scanner pipe, String prompt, String regExPattern) {
        String response = "";
        boolean done = false;
        do {
            System.out.print("\n" + prompt + ": ");
            response = pipe.nextLine();
            if (response.matches(regExPattern)) {
                done = true;
            } else {
                System.out.println("\n" + response + " must match the pattern " + regExPattern);
                System.out.println("Try again!");
            }
        }
        while (!done);
        return response;
    }


    public static void prettyHeader(String prompt) {
        int length = prompt.length();
        System.out.print("*");
        for (int a = 0; a < 59; a++) {
            System.out.print("*");
        }
        System.out.print("\n*");
        for (int b = 0; b < 2; b++) {
            System.out.print("*");
        }
        for (int c = 0; c < (26 - (length / 2)); c++) {
            System.out.print(" ");
        }
        System.out.printf(prompt);
        if (length % 2 == 0) {
            for (int d = 0; d < (28 - (length / 2)); d++) {
                System.out.print(" ");
            }
        } else {
            for (int d = 0; d < (27 - (length / 2)); d++) {
                System.out.print(" ");
            }
        }
        System.out.print("*");
        for (int e = 0; e < 2; e++) {
            System.out.print("*");
        }
        System.out.print("\n*");
        for (int f = 0; f < 59; f++) {
            System.out.print("*");
        }
    }
}








