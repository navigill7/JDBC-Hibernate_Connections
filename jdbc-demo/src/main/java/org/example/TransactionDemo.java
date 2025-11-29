package org.example;

import java.sql.*;

public class TransactionDemo {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/myappdb?serverTimezone=UTC&useSSL=false";

    private static final String USER = "appuser";
    private static final String PASSWORD = "AppPass123!";
    static void main() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connected to database");
            // turned off the auto save

          try{
              conn.setAutoCommit(false);

              // orders , order_items
              // insert into orders

              int orderid = insertOrders(conn , 102 , "Navi" , 2000.0 );

              // insert into order_items
              insertOrderItems(conn , orderid , "Laptop01" , 1  , 2000.0  );

              conn.commit();

              System.out.println("transactions commited sucessfully");
          }catch (Exception e){
              e.printStackTrace();
              conn.rollback();
              System.out.println("tranaction rollback successfully ");
          }

          finally {
              conn.setAutoCommit(true);
          }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertOrderItems(Connection conn, int orderid, String item, int quantity, double price) {
        String sql = "insert into order_items (order_id , product_name , quantity , price   ) values (? , ? , ?  , ?)";

        try(PreparedStatement ptmt = conn.prepareStatement(sql)){
            ptmt.setInt(1 , orderid);
            ptmt.setString(2 , item);
            ptmt.setInt(3 , quantity);
            ptmt.setDouble(4 , price);
//            int num = 10/0;
            int rows = ptmt.executeUpdate();

            System.out.println("Inserted into orders_items : " + rows);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static int insertOrders(Connection conn, int user_id, String customerName, double total) {

        String sql = "insert into orders (user_id , customer_name , total_amount ) values (? , ? , ? )";

        try(PreparedStatement ptmt = conn.prepareStatement(sql   , Statement.RETURN_GENERATED_KEYS)){
            ptmt.setInt(1 , user_id);
            ptmt.setString(2 ,customerName);
            ptmt.setDouble(3 , total);

            int rows = ptmt.executeUpdate();

            System.out.println("Inserted into orders : " + rows);


            try (ResultSet rs = ptmt.getGeneratedKeys()) {
                if(rs.next()){
                    int orderId = rs.getInt(1);
                    return orderId;
                }else{
                    throw new SQLException("OrderId id not generated ");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
