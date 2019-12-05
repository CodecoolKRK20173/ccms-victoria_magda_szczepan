package controllers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SQLControllerTest {
    @BeforeAll
    public static void initializeDatabase(){
        RootController rootController = new RootController();
        String statement = rootController.getStatementsFromtxt("src/main/resources/dbInit.txt");
        getSqlController().initializeDB(statement);
    }
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
    @Test
    public void testGetIdByName(){
        assertEquals(getSqlController().getIdByName("Szczepan"), 1);
    }
    @Test
    public void testRemoveUser(){
        getSqlController().removeUser("Kamil");
        assertEquals(getSqlController().getIdByLogin("Kamil"),0);
    }
    @Test
    public void testAddUser(){
        getSqlController().addUser(new String[]{"Stefania", "Mentor"});
        assertEquals(getSqlController().getUserType("Stefania"),"Mentor");
    }

    @Test
    public void testFindTypeIdByTypeName() throws SQLException {
        assertEquals(getSqlController().findTypeIDbyTypeName("Mentor"),4);
    }
    @Test
    public void testFindTypeIdByTypeNameThrowsException() {
        assertThrows(SQLException.class, ()->{getSqlController().findTypeIDbyTypeName("jskadjksj");});
    }
    @Test
    public void testEditUser(){
        getSqlController().editUser("Magda", "NAME", "Magda2");
        assertEquals(getSqlController().getIdByName("Magda2"),2);
    }
    @Test
    public void testGetUsersNames(){
        assertEquals(getSqlController().getUsersNames("Manager"), new ArrayList<String>(Arrays.asList("Szczepan")));
    }



}