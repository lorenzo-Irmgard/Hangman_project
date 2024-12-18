package src;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.List;
import java.io.File;

public class Main {
    static String path = "src/Resources/words.txt";
    static int errcode = 0;

    public static void main(String[] args) throws FileNotFoundException {
        int state = 0;
        String userAnswer;
        Scanner menu = new Scanner(System.in);
        while (state == 0 && errcode == 0) {
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

    public static void gameCycle() throws FileNotFoundException {
        List <Character> validChars = new ArrayList<>();
        List <Character> invalidChars = new ArrayList<>();
        String word = wordsBank(path);
        System.out.println(word);N
    }

    public static String wordsBank(String filepath) throws FileNotFoundException {
        String word;
        Random rand = new Random();
        List <String> allWords = new ArrayList<>();
        File file = new File(filepath);
        if (file == null) return ""; //TODO Добавить обработку краевых ситуаций и выброс ошибок если файла не существует
        Scanner scanWords = new Scanner(file);//TODO добавить слова в банк слов
        while (scanWords.hasNextLine()) {
            allWords.add(scanWords.nextLine().trim());
        }
        word = allWords.get(rand.nextInt(allWords.size()));
        scanWords.close();
        return word;
    }
}
