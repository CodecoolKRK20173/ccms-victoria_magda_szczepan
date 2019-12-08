package view;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ViewTest {

    @Test
    void printErrorMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        View.printMessage("Hello");
        String expectedOutput  = "Hello\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void TestDisplayMenu() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        View.displayMenu(new String[] {"hello"});
        String expectedOutput  = "(1) hello\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testValidateUserChoiceMoreThenLength () {
        assertFalse(View.validateUserChoice("5", 1));
    }

    @Test
    public void testValidateUserChoiceLessThenLength() {
        assertFalse(View.validateUserChoice("0", 1));
    }

    @Test
    public void testValidateUserChoiceInvalid() {
        assertFalse(View.validateUserChoice("fjfjfj", 1));
    }

    @Test
    public void testValidateUserTrue() {
        assertTrue(View.validateUserChoice("1", 1));
    }

    @Test
    public void testDisplayGrades() {
        Map<String, Integer> grades = Map.of("online shop", 2);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        View.displayGrades(grades);
        String expectedOutput  = "online shop: 2\n";
        assertEquals(expectedOutput, outContent.toString());
    }

}