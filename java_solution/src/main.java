import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class main {
    public static void main(String[] args){
        nameInput();
        intInput();
        fileInput();
    }

    public static void nameInput(){
        Scanner input = new Scanner(System.in);
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
        Scanner input = new Scanner(System.in);
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
        int num = Integer.parseInt(theInt);
        if(num > 2147){
            System.out.println("Given integer exceeds maximum allowed value.");
            return false;
        }else if(num < -2147){
            System.out.println("Given integer exceeds minimum allowed value.");
            return false;
        }

        return true;
    }

    public static void fileInput(){

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

