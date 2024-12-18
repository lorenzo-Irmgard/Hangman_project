package src;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.File;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int state = 0;
        String userAnswer;
        Scanner menu = new Scanner(System.in);
        while (state == 0) {
            System.out.println("Type N for starting a new game or E for exit");
            switch (userAnswer = menu.nextLine()) {
                case "E":
                    state = 1;
                    break;
                case "e":
                    state = 1;
                    break;
                case "N":
                    gameCycle();
                    break;
                case "n":
                    gameCycle();
                    break;
                default:
                    System.out.println("No such option, try again");
            }
        }
    }

    public static void gameCycle() {
        List <Character> validChars = new ArrayList<>();
        List <Character> invalidChars = new ArrayList<>();
        String word =
    }

    public static String wordsBank(String filepath) {

    }
}
