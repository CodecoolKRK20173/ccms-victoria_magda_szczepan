package controllers;


import models.users.User;
import models.users.UserFactory;
import view.View;

import view.View;

import java.sql.*;
import java.util.ArrayList;
import java.util.*;
public class SQLController implements DAO {
    private Connection c;
    private Statement stmt;

    public void initializeDB(String statement){
        connectToSQL();
        try {
            stmt.executeUpdate(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            closeConnection();
        }

    }

    private void closeConnection() {
        try {
            stmt.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void connectToSQL() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/main/resources/codecoolMS.db");
            c.setAutoCommit(true);

            stmt = c.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isUserDataCorrect(String login, String password) {
        connectToSQL();
        try {
            ResultSet rs = stmt.executeQuery(String.format("SELECT LOGIN, PASSWORD FROM LOGIN" +
                    " WHERE LOGIN = '%s' AND PASSWORD = '%s';",login,password));
            if (!rs.isClosed()){
                final boolean isCorrect = rs.getString("LOGIN").equals(login) && rs.getString("PASSWORD").equals(password);
                closeConnection();
                return isCorrect;}
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
        closeConnection();
        }
        return false;
    }

    public String getUserType(String login) {
        int Id = getIdByLogin(login);
        connectToSQL();
        try {
            ResultSet rs = stmt.executeQuery(String.format("SELECT USER.USER_ID,USER_TYPES.TYPE FROM USER" +
                    " INNER JOIN USER_TYPES ON  USER_TYPES.TYPE_ID = USER.TYPE_ID "+
                    "WHERE USER.USER_ID = %d;",Id));
            String type = rs.getString("TYPE");
            closeConnection();
            return type;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{closeConnection();}
        return null;
    }

    public void addUser(String[] data) {

        String name = data[0];
        String type = data[1];
        try {
            int typeID = findTypeIDbyTypeName(type);
            connectToSQL();
            String sql = String.format("INSERT INTO USER(NAME, TYPE_ID)" +
                    "VALUES('%s', %d);",name,typeID);
            stmt.executeUpdate(sql);
            int id = getIdByName(name);
            String sql2 = String.format("INSERT INTO LOGIN(LOGIN, PASSWORD, USER_ID)" +
                    "VALUES('%s', '%s', %d);",name, "123", id);
            stmt.executeUpdate(sql2);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{closeConnection();}
    }

    private int findTypeIDbyTypeName(String type) throws SQLException {
        connectToSQL();
        int ID = stmt.executeQuery("SELECT TYPE,TYPE_ID FROM USER_TYPES WHERE TYPE ='"+type+"';").getInt("TYPE_ID");
        closeConnection();
        return ID;
    }


    @Override
    public void editUser(String login, String columnName, String data) {
        int userId = getIdByLogin(login);
        connectToSQL();
        try {
            stmt.executeUpdate(String.format("UPDATE USERS SET %s = '%s' WHERE USER_ID = '%d'", columnName, data, userId));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            closeConnection();
        }

    }

    @Override
    public List<String> getUsersNames(String type){
        List<String> userNames = new ArrayList<>();
        try {
            int typeId = findTypeIDbyTypeName(type);
            connectToSQL();
            ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM USER WHERE TYPE_ID = %d;", typeId));
            while (rs.next()) {
                String name = rs.getString("NAME");
                userNames.add(name);
            }
            rs.close();
            closeConnection();
            return userNames;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{closeConnection();}

        return null;
    }

    @Override
    public void addAssignment(String assignment) {

    }

    @Override
    public void submitAssignment(int id) {

    }

    @Override
    public void removeUser(String login){
        int Id = getIdByLogin(login);
        connectToSQL();
        try {
            stmt.executeUpdate(String.format("DELETE FROM USER WHERE USER_ID = %d;",Id));
            stmt.executeUpdate(String.format("DELETE FROM LOGIN WHERE USER_ID = %d;",Id));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

    }

    private int getIdByLogin(String login) {
        connectToSQL();
        try {
            ResultSet rs = stmt.executeQuery(String.format("SELECT USER_ID FROM LOGIN WHERE login = '%s'", login));
            final int user_id = rs.getInt("USER_ID");
            rs.close();
            closeConnection();
            return user_id;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return 0;
    }

    private int getIdByName(String name) {
        connectToSQL();
        try {
            ResultSet rs = stmt.executeQuery(String.format("SELECT USER_ID FROM USER WHERE name = '%s'", name));
            final int user_id = rs.getInt("USER_ID");
            rs.close();
            closeConnection();
            return user_id;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return 0;
    }

    public Map<String,Integer> getStudentGrades(String login){
        Map<String, Integer> grades = new LinkedHashMap<>();
        int Id = getIdByLogin(login);
        connectToSQL();
        try {
            ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM STUDENT_ASSIGMENT" +
                    "INNER JOIN ASSIGMENT" +
                    "ON  STUDENT_ASSIGMENT.ASSIGMENT_ID = ASSIGMENT.ASSIGMENT_ID" +
                    "WHERE STUDENT_ID = '%d'",Id));
            while(rs.next()){
                grades.put(rs.getString("ASSIGMENT_NAME"),rs.getInt("GRADE"));
            }
            closeConnection();
            return grades;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return null;
    }

}
