/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconnect {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            "jdbc:derby://localhost:1527/Fruit_Store_database", "root", "root123"
        );
    }
}


