package controllers;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.*;

public class SQLControllerTest {
    @BeforeAll
    static SQLController getSqlController() {
        return new SQLController();
    }
    @Test
    public void testIsNotInputInDB(){
        assertFalse(getSqlController().isUserDataCorrect("kfdoko","dffdfd"));
    }
    @Test
    public void testIsInputInDB(){
        assertTrue(getSqlController().isUserDataCorrect("Szczepan","123"));
    }
    @Test
    public void testIsUserTypeCorrect(){
        assertEquals(getSqlController().getUserType("Szczepan"),"Manager");
    }
    @Test
    public void testIsUserTypeNotCorrect(){
        assertNull(getSqlController().getUserType("fkdofkdofkd"));
    }
    @Test
    public void testGetIdByLoginCorrect(){
        assertEquals(getSqlController().getIdByLogin("Szczepan"),1);
    }
    @Test
    public void testGetIdByLoginNotCorrect(){
        assertEquals(getSqlController().getIdByLogin("fdsfdf"),0);
    }
}