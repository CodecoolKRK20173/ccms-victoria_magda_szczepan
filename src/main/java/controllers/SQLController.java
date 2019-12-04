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
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
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
            final boolean isCorrect = rs.getString("LOGIN").equals(login) && rs.getString("PASSWORD").equals(password);
            closeConnection();
            return isCorrect;
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
        connectToSQL();
        String name = data[0];
        String type = data[1];

        try {
            int typeID = findTypeIDbyTypeName(type);
            String sql = "INSERT INTO USERS(NAME, TYPE_ID)" +
                    "VALUES('"+name+"', 'typeID');";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
    }

    private int findTypeIDbyTypeName(String type) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT TYPE,TYPE_ID FROM USER_TYPES WHERE TYPE ='"+type+"';");
        int ID = rs.getInt("TYPE_ID");
        rs.close();
        return ID;
    }

    public String getUserType(String login) {
        return null;
    }


    @Override
    public void editUser(String login, String columnName, String data) {
        int userId = getIdByLogin(login);
        connectToSQL();
        try {
            stmt.executeUpdate(String.format("UPDATE USERS SET %s = '%s' WHERE USER_ID = '%d'", columnName, data, userId));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection();
    }

    @Override
    public void removeUser(User user) {
        connectToSQL();

        closeConnection();
    }

    @Override
    public List<String> getUsersNames(String type){
        List<String> userNames = new ArrayList<>();
        connectToSQL();
        try {
            int typeId = findTypeIDbyTypeName(type);
            ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM USER WHERE TYPE_ID = %d;", typeId));
            while (rs.next()) {
                String name = rs.getString("NAME");
                userNames.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userNames;
    }

    @Override
    public void addAssignment(String assignment) {

    }

    @Override
    public void submitAssignment(int id) {

    }

    public void removeUser(String login){
        int Id = getIdByLogin(login);
        connectToSQL();
        try {
            stmt.executeUpdate(String.format("DELETE FROM USER WHERE USER_ID = %d;",Id));
            stmt.executeUpdate(String.format("DELETE FROM LOGIN WHERE USER_ID = %d;",Id));
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private int getIdByLogin(String login) {
        connectToSQL();
        try {
            ResultSet rs = stmt.executeQuery(String.format("SELECT USER_ID FROM LOGIN WHERE login = '%s'", login));
            final int user_id = rs.getInt("USER_ID");
            closeConnection();
            return user_id;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Map<String,Integer> getStudentGrades(String login){
        Map<String, Integer> grades = new LinkedHashMap<>();
        int Id = getIdByLogin(login);
        connectToSQL();
        try {
            ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM STUDENT_ASSIGMENT AS a" +
                    "INNER JOIN ASSIGMENT AS b" +
                    "ON a.ASSIGMENT_ID = b.ID" +
                    "WHERE STUDENT_ID = '%d'",Id));
            while(rs.next()){
                grades.put(rs.getString("ASSIGMENT_NAME"),rs.getInt("GRADE"));
            }
            closeConnection();
            return grades;
        } catch (SQLException e) {
            e.printStackTrace();
            closeConnection();
        }
        return null;
    }

}
