import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * things to do:
 * error log file
 * output file
 * password hashing to validate
 */
public class main {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args){
        nameInput();
        intInput();
        fileInput();
        passwordInput();
    }

    public static void nameInput(){
        System.out.println("First and Last Name must consist of only alphabetical symbols. Upper and lower case is fine. Maximum 50 symbols.");

        System.out.println("Enter First Name: ");
        boolean correctFName = false;
        while(!correctFName) {
            String fName = input.next();
            correctFName = patternMatcherHelper(fName, "^[A-Za-z]{1,50}$");
            if(correctFName)
                System.out.println("User First Name Input: " + fName);
            else //there was an error in the input
                System.out.println("Enter First Name: ");
        }

        System.out.println("Enter Last Name: ");
        boolean correctLName = false;
        while(!correctLName) {
            String lName = input.next();
            correctLName = patternMatcherHelper(lName, "^([A-Za-z] ?){1,50}$");
            if(correctLName)
                System.out.println("User Last Name Input: " + lName);
            else //there was an error in the input
                System.out.println("Enter Last Name: ");
        }
    }

    public static void intInput(){
        System.out.println("Integers can be positive or negative within the range of -2147 and 2147.");

        System.out.println("Enter an Integer: ");
        boolean correctFirstInteger = false;
        boolean correctBounds = false;
        while(!correctFirstInteger) {
            String firstInt = input.next();
            correctFirstInteger = patternMatcherHelper(firstInt, "^-?\\d{1,10}$");
            correctBounds = checkBounds(firstInt);
            if(correctFirstInteger && correctBounds)
                System.out.println("First Integer Input: " + correctFirstInteger);
            else //there was an error in the input
                System.out.println("Enter an Integer: ");
        }

        System.out.println("Enter a second Integer: ");
        boolean correctSecondInteger = false;
        while(!correctSecondInteger) {
            String secondInt = input.next();
            correctSecondInteger = patternMatcherHelper(secondInt, "^-?\\d{1,10}$");
            correctBounds = checkBounds(secondInt);
            if(correctSecondInteger && correctBounds)
                System.out.println("Second Integer Input: " + correctSecondInteger);
            else //there was an error in the input
                System.out.println("Enter a second Integer: ");
        }
    }

    /**
     *checks if an integer is in the correct bounds
     */
    public static boolean checkBounds(String theInt){
        long num = Long.parseLong(theInt);
        if(num > 2147483647){
            System.out.println("Given integer exceeds maximum allowed value.");
            return false;
        }else if(num < -2147483648){
            System.out.println("Given integer exceeds minimum allowed value.");
            return false;
        }

        return true;
    }

    public static void fileInput(){
        System.out.println("File Names not to exceed 20 symbols. Only alphabet, numbers, underscore accepted. Case insensitive. Only .txt files accepted");

        System.out.println("Enter an Input File Name: ");
        boolean correctInputFileName = false;
        while(!correctInputFileName) {
            String inputName = input.next();
            correctInputFileName = patternMatcherHelper(inputName, "^\\w{1,20}\\.txt$");
            if (correctInputFileName)
                System.out.println("Input file name: " + correctInputFileName);
            else //there was an error in the input
                System.out.println("Enter an Input File Name: ");
        }

        System.out.println("Enter an Output File Name: ");
        boolean correctOutputFileName = false;
        while(!correctOutputFileName) {
            String outputName = input.next();
            correctOutputFileName = patternMatcherHelper(outputName, "^\\w{1,20}\\.txt$");
            if (correctOutputFileName)
                System.out.println("Output file name: " + correctOutputFileName);
            else //there was an error in the input
                System.out.println("Enter an Output File Name: ");
        }
    }

    public static void passwordInput(){
        System.out.println("Password should contain at least one uppercase letter, at least one lowercase letter, at least one digit, at least one symbol, and no more than three consecutive lowercase letters.");

        System.out.println("Enter Password: ");
        boolean correctPassword = false;
        String password = "";
        while(!correctPassword) {
            password = input.next();
            correctPassword = patternMatcherHelper(password, "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-])(?![a-z]{4,}).{10,}$");
            if (correctPassword)
                System.out.println("The Password: " + correctPassword);
            else //there was an error in the input
                System.out.println("Enter Password: ");
        }

        System.out.println("Please re-enter Password");
        Scanner newInput = new Scanner(System.in);
        String verifyPassword = newInput.next();

        while(!Objects.equals(password, verifyPassword)){
            System.out.println("Passwords do not match");
            System.out.println("Please re-enter Password");
        }

        System.out.println("The Passwords match");

    }

    public static boolean patternMatcherHelper(String str, String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        boolean found = false;
        while (matcher.find()) {
            found = true;
        }

        return found;
    }
}

