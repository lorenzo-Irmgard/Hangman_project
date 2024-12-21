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
        int gameState = 0;
        while (gameState == 0) {
            System.out.println("Введите N для новой игры или E для выхода");
            switch (userEnters.nextLine().toLowerCase()) {
                case "e":
                    gameState = 1;
                    break;
                case "n":
                    gameState = gameCycle();
                    break;
                default:
                    System.out.println("Такой опции нету, попробуйте еще раз");
            }
        }
    }

    public static int gameCycle() throws FileNotFoundException {
        int gameState = 0;
        List <Character> allChars = new ArrayList<>();
        char letter;
        int mistCnt = 0;
        int winCnt;
        int code;
        String word = wordsBank(PATH);
        if (word == null) {
            gameState = 1;
            return gameState;
        }
        while (true) {
            hangPrint(mistCnt);
            if (mistCnt != 6) {
                winCnt = wordPrint(word, allChars);
            } else {
                System.out.println("Вы проиграли! Было загадано слово: " + word + "\n Попробуем еще раз?");
                break;
            }
            if (winCnt == word.length()) {
                System.out.println("Вы отгадали слово! Сыграем еще раз?");
                break;
            }
            if (mistCnt != 0 || winCnt != 0) {
                printUsedLetters(allChars);
            }
            System.out.println("Введите букву");
            letter = scanLetter();
            code = menuCheck(letter);
            switch (code) {
                case 0:
                    if (letter != '~') {
                        if (isMistake(letter, word, allChars) == 0) allChars.add(letter);
                        if (isMistake(letter, word, allChars) == 1) {
                            mistCnt++;
                            allChars.add(letter);
                            System.out.println("Вы ошиблись! Такой буквы в слове нет");
                        }
                    }
                    break;
                case 1:
                    gameState = 1;
                    return gameState;
                case 2:
                    return gameState;
                case 3:
            }
        }
        return gameState;
    }

    public static String wordsBank(String filepath) throws FileNotFoundException {
        String word;
        int cnt = 0;
        Random rand = new Random();
        List <String> allWords = new ArrayList<>();
        File file = new File(filepath);
        Scanner scanWords = new Scanner(file);
        while (scanWords.hasNextLine()) {
            allWords.add(scanWords.nextLine().trim());
            cnt++;
        }
        if (cnt == 0) {
            System.out.println("Ошибка! В банке слов нет ни одного слова!");
            return null;
        }
        word = allWords.get(rand.nextInt(allWords.size()));
        if (word.isEmpty()) {
            System.out.println("Ошибка! В качестве слова была выбрана пустая строка!");
            return null;
        }
        scanWords.close();
        return word;
    }

    public static int wordPrint(String word, List<Character> allChars) {
        int cnt = 0;
        System.out.println("Загаданное состоит из " + word.length() + " букв:");
        for (int i = 0; i < word.length(); i++) {
            if (allChars.contains(word.charAt(i))) {
                System.out.print(word.charAt(i));
                cnt++;
            } else {
                System.out.print("*");
            }
        }
        System.out.println("\n");
        return cnt;
    }

    public static char scanLetter() {
        char letter;
        String entry = userEnters.nextLine();
        if (entry.isEmpty()) {
            System.out.println("Некорректный ввод, попробуй еще раз\n");
            letter = '~';
            return letter;
        }
        letter = letterCheck(entry.charAt(0));
        if (letter >= 1072 && letter <= 1103) {
            letter = Character.toUpperCase(letter);
        }
        return letter;
    }

    public static char letterCheck(char letter) {
        if (letter < 1040 || letter > 1103) {
            if (letter == 1025 || letter == 1105) {
                System.out.println("Вместо ё используйте е");
            }
            if (letter == 'E' || letter == 'e' || letter == 'N' || letter == 'n') return letter;
            System.out.println("Некорректный ввод, попробуй еще раз\n");
            letter = '~';
        }
        return letter;
    }

    public static int menuCheck(char letter) {
        int code = 0;
        letter = Character.toLowerCase(letter);
        switch (letter) {
            case 'e':
                System.out.println("Вы точно хотите закончить игру? Введите Y для подтверждения или любой другой символ для отмены");
                if (operationVerify()) code = 1;
                break;
            case 'n':
                System.out.println("Вы точно хотите выйти в меню? Введите Y для подтверждения или любой другой символ для отмены");
                if (operationVerify()) code = 2;
                break;
        }
        return code;
    }

    public static boolean operationVerify() {
        char userAns;
        userAns = userEnters.nextLine().charAt(0);
        userAns = Character.toLowerCase(userAns);
        return userAns == 'y';
    }

    public static void printUsedLetters(List <Character> allChars) {
        System.out.print("Вы использовали буквы: ");
        for (Character allChar : allChars) {
            System.out.print(allChar + " ");
        }
        System.out.println();
    }

    public static void hangPrint(int mistCnt) {
        System.out.println("\n-------------------------------\n");
        switch (mistCnt) {
            case 0:
                break;
            case 1:
                System.out.println("|_______\n|/     |\n|\n|\n|\n|");
                System.out.println("У вас осталось " + (6 - mistCnt) + " ошибок");
                break;
            case 2:
                System.out.println("|_______\n|/     |\n|      O\n|      |\n|\n|");
                System.out.println("У вас осталось " + (6 - mistCnt) + " ошибки");
                break;
            case 3:
                System.out.println("|_______\n|/     |\n|      O\n|     /|\n|\n|");
                System.out.println("У вас осталось " + (6 - mistCnt) + " ошибки");
                break;
            case 4:
                System.out.println("|_______\n|/     |\n|      O\n|     /||\n|\n|");
                System.out.println("У вас осталось " + (6 - mistCnt) + " ошибки");
                break;
            case 5:
                System.out.println("|_______\n|/     |\n|      O\n|     /||\n|     /\n|");
                System.out.println("У вас осталось " + (6 - mistCnt) + " ошибка");
                break;
            case 6:
                System.out.println("|_______\n|/     |\n|      O\n|     /||\n|     /|\n|");
                break;
        }
    }

    public static int isMistake(char letter, String word, List <Character> allChars) {
        int res = 1;
        for (int i = 0; i < word.length(); i++) {
            if (allChars.contains(letter)) {
                res = 2;
                return res;
            }
            if (word.charAt(i) == letter) {
                res = 0;
                break;
            }
        }
        return res;
    }
}

/*
|_______
|/     |
|      O
|     /||
|     /|
|
 */