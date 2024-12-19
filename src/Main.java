package src;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.List;
import java.io.File;

public class Main {
    static final String PATH = "src/Resources/words.txt";
    static Scanner userEnters = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException {
        int state = 0;
        while (state == 0) {
            System.out.println("Введи N для новой игры или E для выхода");
            switch (userEnters.nextLine()) {
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
                    System.out.println("Такой опции нету, попробуй еще раз");
            }
        }
    }

    public static void gameCycle() throws FileNotFoundException {
        List <Character> validChars = new ArrayList<>();
        List <Character> allChars = new ArrayList<>();
        char letter;
        int mistCnt = 0;
        int winCnt = 0;m
        String word = wordsBank(PATH);
        while (true) {
            winCnt = wordPrint(word, validChars);
            if (winCnt == word.length()) {
                System.out.println("Ты отгадал слово! Сыграем еще раз?");
                break;
            }
            if (mistCnt == 6) {
                System.out.println("Ты проиграл! Было загадано слово: " + word + "\n Хочешь попробовать еще раз?");
                break;
            }
            if (mistCnt != 0 && winCnt != 0) {
                //printUsedLetters //TODO написать эту функцию
            }
            System.out.println("\nВведи букву");
            letter = userEnters.nextLine().charAt(0);
            if ((int)letter < 1040 && (int)letter > 1103) {
                if ((int)letter == 1025 || (int)letter == 1105) {
                    System.out.println("Вместо ё используй е");
                }
                System.out.println("Некорректный ввод, попробуй еще раз");
            }
        }
    }

    public static String wordsBank(String filepath) throws FileNotFoundException {
        String word;
        Random rand = new Random();
        List <String> allWords = new ArrayList<>();
        File file = new File(filepath);
        Scanner scanWords = new Scanner(file);
        while (scanWords.hasNextLine()) {
            allWords.add(scanWords.nextLine().trim());
        }
        word = allWords.get(rand.nextInt(allWords.size()));
        scanWords.close();
        return word;
    }

    public static int wordPrint(String word, List<Character> validChars) {
        int cnt = 0;
        for (int i = 0; i < word.length(); i++) {
            if (validChars.contains(word.charAt(i))) {
                System.out.print(word.charAt(i));
                cnt++;
            } else {
                System.out.print("*");
            }
        }
        return cnt;
    }
}
