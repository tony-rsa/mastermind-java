package za.co.wethinkcode.mastermind;

public class Mastermind {
    private String guess = "";
    private final String code;
    private final Player player;

    public Mastermind(CodeGenerator generator, Player player){
        this.code = generator.generateCode();
        this.player = player;
    }

    public Mastermind(){ this(new CodeGenerator(), new Player()); }


    private void printEndOfGame(boolean playerWon) {
        String outcomeMassage = playerWon ?
           "Congratulations! You are a codebreaker!" : "No more turns left.";
        System.out.println(outcomeMassage);
        System.out.println("The code was: " + this.code);
    }

    private void printEvaluatedResults(int correctPlace, int notInCorrectPlace ) {
        System.out.println("Number of correct digits in correct place: "
                + correctPlace);
        System.out.println("Number of correct digits not in correct place: "
                + notInCorrectPlace);
    }

    private void evaluateInput(){
        int digitsInCorrectPlace = 0;
        int digitsNotInCorrectPlace = 0;

        for (int i = 0; i < this.guess.length(); i++)
            if (this.guess.charAt(i) == this.code.charAt(i))
                digitsInCorrectPlace++;
            else if (this.guess.indexOf(this.code.charAt(i)) > -1)
                digitsNotInCorrectPlace++;

        printEvaluatedResults(digitsInCorrectPlace, digitsNotInCorrectPlace);
    }

    public void runGame(){
        String firstMassage = "4-digit Code has been set. Digits in range 1 to 8."
                + " You have 12 turns to break it.";
        System.out.println(firstMassage);
        while (this.player.stillHasTurns()) {
            this.guess = this.player.getGuess();
            if (this.player.wantsToQuit())
                break;

            evaluateInput();
            if (this.player.isCorrect(this.guess,this.code) )
                break;

            this.player.lostTurn();
            if (this.player.stillHasTurns())
                System.out.println("Turns left: " + this.player.getTurns());
        }
        if (!player.wantsToQuit())
            printEndOfGame(this.player.isCorrect(this.guess, this.code));
    }

    public static void main(String[] args){
        Mastermind game = new Mastermind();
        game.runGame();
    }
}
