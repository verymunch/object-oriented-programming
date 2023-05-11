import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Morse {
    private char[] alpha;
    private String[] morse;

    /**
     * Default constructor that creates a new Morse object that
     * instantiates a char array and string array with 26 elements.
     */
    public Morse() {
        alpha = new char[26];
        morse = new String[26];
    }

    /**
     * Single argument constructor that populates a char array
     * and string array with data from a text file.
     * @param file: the text file passed in by name.
     */
    public Morse(File file) {
        try {
            File myFile = new File("morse.txt");
            Scanner scan = new Scanner(myFile);
            alpha = new char[26];
            morse = new String[26];
            int index = 0;
            while (scan.hasNext()) {
                // populate char array
                alpha[index] = scan.next().charAt(0);
                // populate string array
                morse[index] = scan.next();
                index++;
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Translates English characters to Morse code.
     * @param character: the letter to be translated to Morse code.
     * @return a string representation of Morse code.
     */
    public String getMorse(char character) {
        int index = 0;
        boolean found = false;
        String someString = "";
        while (!found && index < alpha.length) {
            // when character in alpha array matches input character
            if (alpha[index] == character) {
                found = true;
                someString = morse[index];
            }
            index++;
        }
        return someString;
    }

    /**
     * Translates Morse code to English characters.
     * @param someString: the Morse code to be translated to English.
     * @return a character representation of English letters.
     */
    public char getChar(String someString) {
        int index = 0;
        boolean found = false;
        char character = 0;
        while (!found && index < morse.length) {
            // when string in morse array matches string input
            if (morse[index].equals(someString)) {
                found = true;
                character = alpha[index];
            }
            index++;
        }
        return character;
    }
}

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);

        // handle file
        System.out.print("Input filename: ");
        String fileName = scan.nextLine();
        File file = new File(fileName);
        Morse translator = new Morse(file);

        // display menu
        System.out.println("1: Convert Morse to alpha.");
        System.out.println("2: Convert alpha to Morse.");
        System.out.println("3: Exit the program.");

        // sentinel loop with 3 options
        int userChoice = 0;
        while (userChoice != 3) {
            System.out.print("Make your choice: ");
            userChoice = scan.nextInt();

            if (userChoice != 1 && userChoice != 2 && userChoice != 3) {
                System.out.println("Invalid input. Please try again.");
            }

            if (userChoice == 1) {
                System.out.print("Enter Morse code (with spaces): ");
                String code = scan.next();
                System.out.print("Result: ");
                for (int i = 0; i < code.length(); i++) {
                    System.out.print(translator.getChar(code));
                }
                System.out.println();
            }

            if (userChoice == 2) {
                System.out.print("Enter alpha characters (without spaces): ");
                String letters = scan.next().toUpperCase();
                System.out.print("Result: ");
                for (int i = 0; i < letters.length(); i++) {
                    char userInput = letters.charAt(i);
                    System.out.print(translator.getMorse(userInput));
                }
                System.out.println();
            }

            if (userChoice == 3) {
                System.out.println("Thank you!");
                break;
            }
        }
    }
}