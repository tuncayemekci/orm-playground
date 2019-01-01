package hello;

import java.sql.*;
import java.util.HashMap;

public class ConnectionManager {

    private static String url = "jdbc:mysql://localhost:3306/test";
    private static String driverName = "com.mysql.jdbc.Driver";
    private static String username = "root";
    private static String password = "";
    private static Connection con = getConnection();

    public static Connection getConnection(){
        try {
            Class.forName(driverName);
            try {
                con = DriverManager.getConnection(url,username,password);
            } catch (SQLException e) {
                System.out.println("Datebase connection is failed");
            }
        }catch (ClassNotFoundException ex){
            System.out.println("Driver is not found.");
        }

        if (con != null) {
            System.out.println("DB Connection is succesful.");
        } else {
            System.out.println("DB Connection is failed.");
        }

        return con;
    }


    public static HashMap<Integer, User> getUsersTableFromDBToMap(){

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = con.prepareStatement("select * from users");
            ResultSet rs = preparedStatement.executeQuery();

            User.users.clear();

            while (rs.next()) {
                User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                User.users.put(rs.getInt(1), user);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        System.out.println("Users have been updated from Database to HashMap.");
        return (HashMap<Integer, User>) User.users;
    }

    public static HashMap<Integer, User> addRandomUsersToDB(int s){
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = con.prepareStatement("INSERT INTO users (username, name, surname, status) values (?,?,?,?)");

            for (int i = 0; i < s; i++) {
                preparedStatement.setString(1, Tusable.randomStr(5));
                preparedStatement.setString(2, Tusable.randomStr(5));
                preparedStatement.setString(3, Tusable.randomStr(5));
                preparedStatement.setString(4, "1");
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        getUsersTableFromDBToMap();
        
        System.out.println(s + " random users have been added to DB and HashMap has been updated from DB.");
        return (HashMap<Integer, User>) User.users;
    }

}
