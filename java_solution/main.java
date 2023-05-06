import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Base64;
import java.util.Objects;

public class main {
    static Scanner input = new Scanner(System.in);
    private static String fName;
    private static String lName;
    private static int firstInt;
    private static int secondInt;
    private static int finalSum;
    private static int finalProduct;
    private static String inputName;
    private static String outputName;
    private static FileWriter errorLog;
    private static BufferedWriter errorWriter;

    public static void main(String[] args) throws IOException {
        try {
            errorLog = new FileWriter("errorLog.txt", false);
            errorWriter = new BufferedWriter(errorLog);
        } catch (Exception e) {
            System.err.println("Error creating the file: " + e.getMessage());
        }
        nameInput();
        intInput();
        fileInput();
        passwordInput();
        outputToFile();
        errorWriter.close();
        errorLog.close();
    }

    public static void nameInput() {
        System.out.println(
                "First and Last Name must consist of only alphabetical symbols. Upper and lower case is fine. Maximum 50 symbols.");

        System.out.println("Enter First Name: ");
        boolean correctFName = false;
        while (!correctFName) {
            fName = input.next();
            correctFName = patternMatcherHelper(fName, "^[A-Za-z]{1,50}$");
            if (correctFName)
                System.out.println("User First Name Input: " + fName);
            else { // there was an error in the input
                try {
                    writeErrorMessage("first name", fName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("Enter First Name: ");
            }
        }
        System.out.println();

        boolean correctLName = false;
        System.out.println("Enter Last Name: ");
        while (!correctLName) {
            lName = input.nextLine();
            correctLName = patternMatcherHelper(lName, "^([A-Za-z] ?){1,50}$");
            if (correctLName) {
                System.out.println("User Last Name Input: " + lName);
            } else if (!Objects.equals(lName, "")) {
                System.out.println("Enter Last Name: ");
                try {
                    writeErrorMessage("last name", lName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println();
    }

    public static void intInput() {
        System.out.println(
                "Integers can be positive or negative within the range of 2147483647 and -2147483648. The sum and product of the two integers must be between the bounds -2147483648 and 2147483647 also.");

        boolean sumOverflow = false, productOverflow = false;

        // Obtain first int and verify
        System.out.println("Enter an Integer: ");
        boolean correctFirstInteger = false;
        boolean correctBounds = false;
        while (!correctFirstInteger || !correctBounds) {
            String first = input.next();
            correctFirstInteger = patternMatcherHelper(first, "^-?\\d{1,10}$");
            if (correctFirstInteger) {
                correctBounds = checkBounds(first);
                if (correctBounds) { // checking bounds
                    System.out.println("First Integer Input: " + first);
                    // Assign Integer one to global variable
                    firstInt = Integer.parseInt(first);
                } else {
                    try {
                        writeSpecialErrorMessage("Integer inputted was out of bounds.");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("Enter an Integer: ");
                }
            } else { // there was an error in the input
                try {
                    writeErrorMessage("first integer", firstInt + "");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("Enter an Integer: ");
            }
        }
        System.out.println();

        // Obtain second int and verify
        System.out.println("Enter a second Integer: ");
        boolean correctSecondInteger = false;
        boolean checkSum = false;
        boolean checkProduct = false;
        while (!correctSecondInteger || !correctBounds || !checkSum || !checkProduct) {
            String strSecond = input.next();
            correctSecondInteger = patternMatcherHelper(strSecond, "^-?\\d{1,10}$");
            if (correctSecondInteger) {
                int second = Integer.parseInt(strSecond);
                correctBounds = checkBounds(strSecond); // checking bounds
                if (correctBounds) { 
                    checkSum = checkBoundsSum(second); //checking bounds of sum
                    if (checkSum) {
                        checkProduct = checkBoundsProduct(second); //checking bounds of product
                        if (checkProduct) {
                            System.out.println("Second Integer Input: " + second);
                            // Assign Integer two to global variable
                            secondInt = second;
                        } else {
                            System.out.println("The product of the two integers was out of bounds.");
                            System.out.println("Enter an Integer: ");
                        }
                    } else {
                        System.out.println("The sum of the two integers was out of bounds.");
                        System.out.println("Enter an Integer: ");
                    }
                } else {
                    try {
                        writeSpecialErrorMessage("Integer inputted was out of bounds.");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("Enter an Integer: ");
                }
            } else {// there was an error in the input
                try {
                    writeErrorMessage("second integer", secondInt + "");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("Enter a second Integer: ");
            }
        }
        System.out.println();

        finalSum = firstInt + secondInt;
        finalProduct = firstInt * secondInt;
        System.out.println("Final sum is: " + finalSum);
        System.out.println("Final product is: " + finalProduct);

    }

    /**
     * checks if an integer is in the correct bounds
     */
    public static boolean checkBounds(String theInt) {
        long num = Long.parseLong(theInt);
        if (num > 2147483647) {
            System.out.println("Given integer exceeds maximum allowed value.");
            return false;
        } else if (num < -2147483648) {
            System.out.println("Given integer exceeds minimum allowed value.");
            return false;
        }

        return true;
    }

    public static boolean checkBoundsSum(int value) {
        try {
            Math.addExact(firstInt, value);
        } catch (Exception e) {
            try {
                writeSpecialErrorMessage("The sum of the two integers exceeded the integer bounds.");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            return false;
        }
        return true;
    }

    public static boolean checkBoundsProduct(int value) {
        try {
            Math.multiplyExact(firstInt, value);
        } catch (Exception e) {
            try {
                writeSpecialErrorMessage("The product of the two integers exceeded the integer bounds.");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            return false;
        }
        return true;
    }

    public static void fileInput() {
        System.out.println();
        System.out.println(
                "File Names not to exceed 20 symbols. Only alphabet, numbers, underscore accepted. Case insensitive. Only .txt files accepted and .txt should be included in file name");

        boolean correctInputFileName = false;
        boolean fileExists = false;

        while (!correctInputFileName || !fileExists) {
            System.out.println("Enter an Input File Name: ");
            inputName = input.next();
            correctInputFileName = patternMatcherHelper(inputName, "^\\w{1,20}\\.txt$");

            if (correctInputFileName) {
                fileExists = Files.exists(Paths.get(inputName));
                if (!fileExists) {
                    try {
                        writeSpecialErrorMessage("File not found. Please enter a valid Input File Name. " + inputName);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.println("File not found. Please enter a valid Input File Name.");
                } else {
                    System.out.println("Input file name: " + inputName);
                }
            } else { // there was an error in the input
                try {
                    writeSpecialErrorMessage("Invalid input file name. Please follow the guidelines. " + inputName);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        System.out.println();

        System.out.println("Enter an Output File Name: ");
        boolean correctOutputFileName = false;
        while (!correctOutputFileName) {
            outputName = input.next();
            if (!outputName.equals(inputName)) { // making sure the input file does not equal the output file
                correctOutputFileName = patternMatcherHelper(outputName, "^\\w{1,20}\\.txt$");
                if (correctOutputFileName) {
                    System.out.println("Output file name: " + outputName);
                } else { // there was an error in the input
                    System.out.println("Enter an Output File Name: ");
                    try {
                        writeErrorMessage("Output file doesnt fit requirements: ", outputName);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                System.out.println("Output file name cannot be the same as the input file name.");
                System.out.println("Enter an Output File Name: ");
                try {
                    writeSpecialErrorMessage(
                            "Invalid input file name. Output file name equaled input file name: " + inputName);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        System.out.println();
    }

    /**
     * 
     */
    public static void passwordInput() {
        System.out.println(
                "Password should contain at least one uppercase letter, at least one lowercase letter, at least one digit, at least one symbol, must be 10 characters long, and no more than three consecutive lowercase letters.");

        System.out.println("Enter Password: ");
        boolean correctPassword = false;
        String password;
        String hashedPassword;
        byte[] salt = new byte[16];
        // While the entered password doesn't fit specifications loop
        while (!correctPassword) {
            password = input.next();
            correctPassword = patternMatcherHelper(password,
                    "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-])(?![a-z]{4,}).{10,}$");
            if (correctPassword) {// Password fit requirements
                try {
                    // Hash password with salt
                    SecureRandom.getInstance("SHA1PRNG").nextBytes(salt);
                    MessageDigest md = MessageDigest.getInstance("SHA-256");
                    md.update(salt);
                    hashedPassword = Base64.getEncoder().encodeToString(md.digest(password.getBytes()));

                    // Clearing file or creating file and Writing Hash to File appending the salt
                    Files.write(Paths.get("password_hash.txt"),
                            (hashedPassword + "," + Base64.getEncoder().encodeToString(salt)).getBytes(),
                            StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

                } catch (IOException | NoSuchAlgorithmException e) {
                    try {
                        writeSpecialErrorMessage("Error writing password hash to file.");
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    e.printStackTrace();
                }
            } else {// there was an error in the input
                System.out.println("Enter Password: ");
                try {
                    writeErrorMessage("Password doesnt fit requirements: ", password);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println();

        Scanner newInput = new Scanner(System.in);
        boolean passwordVerified = false;
        String hashedFilePassword = "";
        byte[] storedSalt = new byte[16];
        // Get hashed password from file and split Hashed password and the stored salt
        try {
            Scanner passwordFile = new Scanner(new File("password_hash.txt"));
            String[] storedHashAndSalt = passwordFile.nextLine().split(",");
            hashedFilePassword = storedHashAndSalt[0];
            storedSalt = Base64.getDecoder().decode(storedHashAndSalt[1]);
        } catch (FileNotFoundException e) {
            try {
                writeSpecialErrorMessage("Password File Not Found");
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            e.printStackTrace();
        }

        // Ask user to re-enter password tell hash from file matches the hashed password
        // from user
        do {
            // Prompt user to re-enter password and grab it
            System.out.println("Please re-enter Password");
            String verifyPassword = newInput.next();
            try {
                // Hash re-entered password using the stored salt from the files hashed password
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.update(storedSalt);
                String hashedConfirmPassword = Base64.getEncoder().encodeToString(md.digest(verifyPassword.getBytes()));

                // Check if entered is the same as files password
                passwordVerified = hashedFilePassword.equals(hashedConfirmPassword);
            } catch (NoSuchAlgorithmException e) {
                try {
                    writeSpecialErrorMessage("Error hashing password");
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }

            if (!passwordVerified) {
                System.out.println("Wrong Password");
                try {
                    writeSpecialErrorMessage("Passwords did not match");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } while (!passwordVerified);

        System.out.println("The Passwords match");
        newInput.close();
    }

    public static void outputToFile() {
        try {
            FileWriter fileWriter = new FileWriter(outputName, false);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("First Name: " + fName);
            bufferedWriter.newLine();
            bufferedWriter.write("Last Name: " + lName);
            bufferedWriter.newLine();
            bufferedWriter.newLine();

            bufferedWriter.write("First Integer: " + firstInt);
            bufferedWriter.newLine();
            bufferedWriter.write("Second Integer: " + secondInt);
            bufferedWriter.newLine();
            bufferedWriter.write("Sum of Integers: " + finalSum);
            bufferedWriter.newLine();
            bufferedWriter.write("Product of Integers: " + finalProduct);
            bufferedWriter.newLine();
            bufferedWriter.newLine();

            bufferedWriter.write("Input File Name: " + inputName);
            bufferedWriter.newLine();
            bufferedWriter.write("Inputs files Contents: ");
            bufferedWriter.newLine();

            Scanner fileInput = new Scanner(new File(inputName));
            while (fileInput.hasNextLine()) {
                bufferedWriter.write(fileInput.nextLine());
                bufferedWriter.newLine();
            }

            fileInput.close();
            bufferedWriter.close();
            fileWriter.close();

            System.out.println("Content successfully written to the file: " + outputName);

        } catch (IOException e) {
            try {
                writeSpecialErrorMessage("Error writing to the output file.");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    public static void writeErrorMessage(String inputType, String value) throws IOException {
        errorWriter.write("Error with " + inputType + " input, value: " + value);
        errorWriter.newLine();
    }

    public static void writeSpecialErrorMessage(String value) throws IOException {
        errorWriter.write(value);
        errorWriter.newLine();
    }

    public static boolean patternMatcherHelper(String str, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        boolean found = false;
        while (matcher.find()) {
            found = true;
        }

        return found;
    }
}
