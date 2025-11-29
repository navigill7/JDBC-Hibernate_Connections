package org.example;

import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/myappdb?serverTimezone=UTC&useSSL=false";

    private static final String USER = "appuser";
    private static final String PASSWORD = "AppPass123!";
    static void main() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connected to database");

//            insertStudent(conn , "Navi", "nnavigill784@gmail.com" );
            updateStudent(conn , 1 , "Navinder" , "gillnavi752@gmail.com");
            SelectStudent(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void insertStudent(Connection conn  , String name  , String email ){
        String sql = "insert into master (name , email ) values ('"+name+"' , '"+email+"') ";

        try(Statement stmt  = conn.createStatement()  ){
            int rows = stmt.executeUpdate(sql);

            System.out.println("Inserted " + rows );
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    private static void SelectStudent(Connection conn  ){
        String sql = "select  * from master ";

        try(Statement stmt  = conn.createStatement()  ){
            ResultSet resultSet  = stmt.executeQuery(sql);
            System.out.println("Student List : ");
            while(resultSet.next()){
                int id  = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");

                System.out.println(id+" : "+name+" : "+email);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // Here we are not using the exact same string with concatenation
    // we are using the placeholder in sql string
    // using the prepared statement prepare the sting while compline the code with parameters passed in method
    // this prevents the sql injection types attacks
    private static void updateStudent(Connection conn , int id , String name , String email ){

        String sql = "update master  set name=?  , email=? where id = ?  ";

        try(PreparedStatement ptmt = conn.prepareStatement(sql)){
            ptmt.setString(1 , name );
            ptmt.setString(2 , email);
            ptmt.setInt(3 , id );
            int row = ptmt.executeUpdate();
            System.out.println("Updated : " + row);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }





//        try {
//            conn = DriverManager.getConnection(URL, USER, PASSWORD);
//            System.out.println("Connected to database ");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                conn.close();
//                System.out.println("connection closed ");
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }

}



