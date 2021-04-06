package za.co.wethinkcode.mastermind;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CodeGeneratorTest {

    @Test
    void testValidCodeLength(){
        CodeGenerator generator = new CodeGenerator();
        String code = generator.generateCode();
        assertEquals(4, code.length());
    }

    @Test
    void testGeneratesDigitsOnly(){
        CodeGenerator generator = new CodeGenerator();
        String code = generator.generateCode();
        assertTrue(code.chars().allMatch(Character::isDigit));
    }

    @Test
    void testGeneratesValidCodeRange(){
        CodeGenerator generator = new CodeGenerator();
        String code = generator.generateCode();
        for (int i = 0; i < code.length(); i++){
            int digit = Integer.parseInt(""+code.charAt(i));
            assertTrue(digit >= 1 && digit <= 8);
        }
    }
}
