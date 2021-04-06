package za.co.wethinkcode.mastermind;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;


public class PlayerTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void testInvalidInput(){
        InputStream inputStream = new ByteArrayInputStream("ht5232t4\n1234\n".getBytes());
        Player player = new Player(inputStream);

        String guess = player.getGuess();
        assertEquals(guess, "1234");
        assertEquals(outputStreamCaptor.toString(),
                "Input 4 digit code:\nPlease enter exactly 4 digits (each from 1 to 8).\nInput 4 digit code:\n");
    }

    @Test
    void testValidInput(){
        InputStream inputStream = new ByteArrayInputStream("1234\n".getBytes());
        Player player = new Player(inputStream);

        String guess = player.getGuess();
        assertEquals(guess, "1234");
        assertEquals(outputStreamCaptor.toString(),
                "Input 4 digit code:\n");
    }

    @Test
    void testInvalidShortInputLength(){
        InputStream inputStream = new ByteArrayInputStream("12\n1234\n".getBytes());
        Player player = new Player(inputStream);

        String guess = player.getGuess();
        assertEquals(guess, "1234");
        assertEquals(outputStreamCaptor.toString(),
                "Input 4 digit code:\nPlease enter exactly 4 digits (each from 1 to 8).\nInput 4 digit code:\n");
    }

    @Test
    void testInvalidLongInput(){
        InputStream inputStream = new ByteArrayInputStream("1234567\n1234\n".getBytes());
        Player player = new Player(inputStream);

        String guess = player.getGuess();
        assertEquals(guess, "1234");
        assertEquals(outputStreamCaptor.toString(),
                "Input 4 digit code:\nPlease enter exactly 4 digits (each from 1 to 8).\nInput 4 digit code:\n");
    }

    @Test
    void testInvalidDigitRange(){
        InputStream inputStream = new ByteArrayInputStream("0099\n1234\n".getBytes());
        Player player = new Player(inputStream);

        String guess = player.getGuess();
        assertEquals(guess, "1234");
        assertEquals(outputStreamCaptor.toString(),
                "Input 4 digit code:\nPlease enter exactly 4 digits (each from 1 to 8).\nInput 4 digit code:\n");

    }

    @Test
    void testPlayerExits(){
        InputStream inputStream = new ByteArrayInputStream("exit\n".getBytes());
        Player player = new Player(inputStream);

        String guess = player.getGuess();
        assertEquals(guess.toLowerCase(), "exit");
    }

    @Test
    void testPlayerQuits(){
        InputStream inputStream = new ByteArrayInputStream("quit\n".getBytes());
        Player player = new Player(inputStream);

        String guess = player.getGuess();
        assertEquals(guess.toLowerCase(), "quit");
    }

    @Test
    void testGetTurns(){
        Player player = new Player();
        int turns = player.getTurns();
        assertEquals(turns, 12);
    }

    @Test
    void testLostTurns(){
        Player player = new Player();
        int expectedTurns = player.getTurns() - 1;
        player.lostTurn();
        int currentTurns = player.getTurns();
        assertEquals(expectedTurns,currentTurns);
    }

    @Test
    void testStillHasTurns(){
        Player player = new Player();
        for (int i = 0; i < 11; i++){
            player.lostTurn();
            assertTrue(player.stillHasTurns());
        }
        player.lostTurn();
        assertFalse(player.stillHasTurns());
    }

    @Test
    void testIsCorrect(){
        Player player = new Player();
        assertFalse(player.isCorrect("1234","1345"));
        assertTrue(player.isCorrect("1234","1234"));
    }

    @Test
    void testWantsToQuit(){
        InputStream mockedInput = new ByteArrayInputStream("quit\n".getBytes());
        Player player = new Player(mockedInput);
        assertFalse(player.wantsToQuit());
    }


}
