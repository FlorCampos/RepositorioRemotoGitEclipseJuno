package com.belatrix;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BdUtilitarios {
	
	  static final String DATABASE_URL = "jdbc:mysql://localhost/bd_examen";
	    Connection connection = null;
	    Statement statement = null;
	    ResultSet resultSet = null;
	 
	    public BdUtilitarios() throws SQLException {
	        // establish connection to database  
	        try {
	            connection = DriverManager.getConnection("jdbc:mysql://localhost/bd_examen", "root", "mysql");
	 
	        } catch (SQLException ex) {
	            System.out.println("The following error has occured: " + ex.getMessage());
	        }
	    }
	 
	    public void DisconnectFromDB() {
	 
	        try {
	            resultSet.close();
	            statement.close();
	            connection.close();
	        } // end try                                              
	        catch (Exception ex) {
	            System.out.println("The following error has occured: " + ex.getMessage());
	        } // end catch  
	    }
	 
	    public ResultSet ReadRecords(String sql_stmt) {
	        try {
	 
	            statement = connection.createStatement();
	                                    
	            resultSet = statement.executeQuery(sql_stmt);
	 
	            return resultSet;
	 
	        }
	        catch (SQLException ex) {
	            System.out.println("The following error has occured: " + ex.getMessage());
	        }                                                    
	 
	        return resultSet;
	    }
	 
	    public void ExecuteSQLStatement(String sql_stmt) {
	        try {
	            statement = connection.createStatement();
	                                    
	            statement.executeUpdate(sql_stmt);
	        }
	        catch (SQLException ex) {
	            System.out.println("The following error has occured: " + ex.getMessage());
	        }
	    }




}
