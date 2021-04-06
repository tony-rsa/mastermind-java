package za.co.wethinkcode.mastermind;

import java.io.InputStream;
import java.util.Scanner;

public class Player {
    private final Scanner inputScanner;
    private String guess = "";
    private int turns = 12;

    public Player(){
        this.inputScanner = new Scanner(System.in);
    }

    public Player(InputStream inputStream){
        this.inputScanner = new Scanner(inputStream);
    }

    /**
     * Gets a guess from user via text console.
     * This must prompt the user to re-enter a guess until a valid 4-digit string is entered, or until the user enters `exit` or `quit`.
     *
     * @return the value entered by the user
     */

    public void lostTurn(){
        if (stillHasTurns())
            this.turns--;
    }

    public int getTurns(){
        return this.turns;
    }

    public boolean stillHasTurns() {
        return getTurns() > 0;
    }

    public boolean isCorrect(String guess, String code) {
        return code.equals(guess);
    }

    public boolean wantsToQuit(){
        return this.guess.equalsIgnoreCase("exit") ||
               this.guess.equalsIgnoreCase("quit");
    }

    private boolean isValidGuess(String checkGuess){
        return checkGuess.length() == 4 &&
               checkGuess.chars().allMatch(Character::isDigit) &&
               !checkGuess.contains("0") && !checkGuess.contains("9");
    }

    public String getGuess(){
        while (true){
            System.out.println("Input 4 digit code:");
            if (this.inputScanner.hasNextLine())
                this.guess = this.inputScanner.nextLine();
            if (isValidGuess(this.guess) || wantsToQuit())
                break;
            System.out.println("Please enter exactly 4 digits (each from 1 to 8).");
        }
        return this.guess;
    }
}
