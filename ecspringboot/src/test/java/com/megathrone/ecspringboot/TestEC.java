package com.megathrone.ecspringboot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestEC {
  public static void main(String... args) {
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    try (Connection connection =
        DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/EC_springboot?useUnicode=true&characterEncoding=utf8&useSSL=false",
            "root",
            "123456")) {
      Statement statement = connection.createStatement();
      for (int i = 0; i <= 10; i++) {
        String sqlFormat = "insert into category values (null, 'test category %d')";
        String sql = String.format(sqlFormat, i);
        statement.execute(sql);
      }
      System.out.println("Test data Done.");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
