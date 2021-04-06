package za.co.wethinkcode.mastermind;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class MastermindTest {
    private final PrintStream standardOut = System.out;
    private final InputStream standardIn = System.in;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        System.setIn(standardIn);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
        System.setIn(standardIn);
    }

    @Test
    void testMainWins() {
        Random randomNumberMock = Mockito.mock(Random.class);
        when(randomNumberMock.nextInt(anyInt()))
                .thenReturn(5, 0, 6, 7);

        InputStream mockedInput = new ByteArrayInputStream("3236\n4234\n6178\n".getBytes());
        Mastermind mastermind = new Mastermind(new CodeGenerator(randomNumberMock), new Player(mockedInput));
        mastermind.runGame();

        assertEquals("4-digit Code has been set. Digits in range 1 to 8. You have 12 turns to break it.\n" +
                "Input 4 digit code:\n" +
                "Number of correct digits in correct place: 0\n" +
                "Number of correct digits not in correct place: 1\n" +
                "Turns left: 11\n" +
                "Input 4 digit code:\n" +
                "Number of correct digits in correct place: 0\n" +
                "Number of correct digits not in correct place: 0\n" +
                "Turns left: 10\n" +
                "Input 4 digit code:\n" +
                "Number of correct digits in correct place: 4\n" +
                "Number of correct digits not in correct place: 0\n" +
                "Congratulations! You are a codebreaker!\n" +
                "The code was: 6178", outputStreamCaptor.toString().trim());
    }

    @Test
    void testMainTurnsLosses() {
        Random randomNumberMock = Mockito.mock(Random.class);
        when(randomNumberMock.nextInt(anyInt()))
                .thenReturn(5, 0, 6, 7);

        InputStream mockedInput = new ByteArrayInputStream("1234\n1235\n1236\n2234\n2235\n2236\n3234\n3235\n3236\n4234\n4235\n4236\n".getBytes());
        Mastermind mastermind = new Mastermind(new CodeGenerator(randomNumberMock), new Player(mockedInput));
        mastermind.runGame();

        assertEquals("4-digit Code has been set. Digits in range 1 to 8. You have 12 turns to break it.\n" +
                "Input 4 digit code:\n" +
                "Number of correct digits in correct place: 0\n" +
                "Number of correct digits not in correct place: 1\n" +
                "Turns left: 11\n" +
                "Input 4 digit code:\n" +
                "Number of correct digits in correct place: 0\n" +
                "Number of correct digits not in correct place: 1\n" +
                "Turns left: 10\n" +
                "Input 4 digit code:\n" +
                "Number of correct digits in correct place: 0\n" +
                "Number of correct digits not in correct place: 2\n" +
                "Turns left: 9\n" +
                "Input 4 digit code:\n" +
                "Number of correct digits in correct place: 0\n" +
                "Number of correct digits not in correct place: 0\n" +
                "Turns left: 8\n" +
                "Input 4 digit code:\n" +
                "Number of correct digits in correct place: 0\n" +
                "Number of correct digits not in correct place: 0\n" +
                "Turns left: 7\n" +
                "Input 4 digit code:\n" +
                "Number of correct digits in correct place: 0\n" +
                "Number of correct digits not in correct place: 1\n" +
                "Turns left: 6\n" +
                "Input 4 digit code:\n" +
                "Number of correct digits in correct place: 0\n" +
                "Number of correct digits not in correct place: 0\n" +
                "Turns left: 5\n" +
                "Input 4 digit code:\n" +
                "Number of correct digits in correct place: 0\n" +
                "Number of correct digits not in correct place: 0\n" +
                "Turns left: 4\n" +
                "Input 4 digit code:\n" +
                "Number of correct digits in correct place: 0\n" +
                "Number of correct digits not in correct place: 1\n" +
                "Turns left: 3\n" +
                "Input 4 digit code:\n" +
                "Number of correct digits in correct place: 0\n" +
                "Number of correct digits not in correct place: 0\n" +
                "Turns left: 2\n" +
                "Input 4 digit code:\n" +
                "Number of correct digits in correct place: 0\n" +
                "Number of correct digits not in correct place: 0\n" +
                "Turns left: 1\n" +
                "Input 4 digit code:\n" +
                "Number of correct digits in correct place: 0\n" +
                "Number of correct digits not in correct place: 1\n" +
                "No more turns left.\n" +
                "The code was: 6178", outputStreamCaptor.toString().trim());

    }
}
