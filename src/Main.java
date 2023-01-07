import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        //************************************************* Part file operation
        File file = new File("movies.txt");
        Scanner scanner = new Scanner(file);
        //Counter of lines
        int countTitles = 0;
        String title = "";
        //unless the file has no more lines:
        while (scanner.hasNextLine()) {
            //scan a line and store it in line variable
            String line = scanner.nextLine();
            // count line
            countTitles++;
        }
        // pick a random int number from the range of lines in file
        int random = (int) (Math.random() * countTitles) + 1;
        // moving scanner to the beginning of the file
        scanner = new Scanner(file);
        // setting title to randomly picked value to be guessed in the game
        for (int i = 0; i < random; i++) {
            title = scanner.nextLine();
        }
        // encrypting the title in a new variable that will be a place to store and change the answers
        String encrypted = "";
        int titleLength = (title.length());
        for (int i = 0; i < titleLength; i++) {
            if (title.charAt(i) != ' ')
                encrypted = encrypted + '_';
            else
                encrypted = encrypted + title.charAt(i);
        }

        //*************************************************Part responsible for guessing
        // set of variables for a guessing part
        int scoreToloose = 10;
        int wrongLetter = 0;
        String wrongLetters = "";
        int indexOf = 0;
        Scanner guessScanner = new Scanner(System.in);
        while (!encrypted.equals(title)) {
            System.out.println("You are guessing: " + encrypted);
            System.out.println("You have guessed " + wrongLetter + " wrong letters:" + wrongLetters);
            System.out.println("Guess a letter:");
            char letter = guessScanner.next().charAt(0);
            //is the letter in title:
            if (title.indexOf(letter) < 0) {
                wrongLetter++;
                wrongLetters = wrongLetters + ' ' + letter;
                //if we are out of score to loose
                if (wrongLetter >= scoreToloose)
                    break;
            } else {
                // untill the indexof returns not negative value (means there is still such letter in the word),
                // replace "_" with that letter in encrypted
                while (title.indexOf(letter, indexOf) >= 0) {
                    indexOf = title.indexOf(letter, indexOf);
                    encrypted = encrypted.substring(0, indexOf) + letter + encrypted.substring(indexOf + 1);
                    indexOf++;
                }
                // need to reset the index for next search
                indexOf = 0;
            }
        }
        // winning conditions check
        if (wrongLetter < scoreToloose)
            System.out.println("Congratulations, you win!");
        else
            System.out.println("You loose, Looser");
        System.out.println("The movie title is:" + title);
    }
}
