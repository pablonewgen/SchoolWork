// to run this code enter    java jdbc0Ora usrname passwd
// This is jdbc0Ora.java.   It connects JDBC to an ORACLE database.
// The database is called cfedb and is located on cslabdb.
// The driver used is an Oracle 'thin' driver.   The other common driver
// is Oracle OCI, where OCI is Oracle Call Interface.  The drivers most
// commonly used are 'thin', 'oci7', and 'oci8'.  The 'thin' one is used
// a lot for applets.
// To run this you want to have usrname and passwd as command line params.

// NOTE, connect.setAutoCommit(true) is legal for a connection called connect

/*
 2. Write a java program that will have as input an employee number, and
      will output the following as appropriate:
        employee with eid ddd has no batting average

        employee with eid ddd has an average below the Mendoza line
         (i.e. below 200)

        employee with eid ddd has a batting average of nnn
         (give the correct average)
        Your java program should of course use jdbc to query the database.

*/

import java.io.*;
import java.util.*;
import java.sql.*;

public class Homework {
  public static void main(String [] aa) {
    String url;
    url = "jdbc:oracle:thin:@moria.sdsu.edu:1521:cs514"; 
                  // jdbc is 'protocol',
                   //oracle is 'subprotocol',
                   // and thin is the driver; cs514 is the data base
                   // instance; the 'database' format is host:port:sid
    Statement statement;
    Connection connect;
    String employeeNumber = aa[2];
    String query = "SELECT EMPNO, AVERAGE FROM infobb02 where EMPNO=\'" +  employeeNumber + "\'";
    try { 
      // invoke the oracle thin driver; register it with DriverManager
      Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
    }   // this step 'loads' the drivers for Oracle that are 'found'
    catch (Exception e) {
      System.out.println("MR.UnitSitQueries.constructor.Exception: " + e);
    }
    try {
      connect = DriverManager.getConnection(url,aa[0],aa[1]); // establish
            // connection to DBMS or database
      statement = connect.createStatement(); // creates object from which SQL commands
            // can be sent to the DBMS
      ResultSet queryResults = statement.executeQuery(query); //create result object to hold
			// information returned by the DBMS
      boolean empty = true;
      while (queryResults.next()) {
        empty = false;
        // get strings
        String curEMPNO = queryResults.getString("EMPNO");
        String curAvg = queryResults.getString("AVERAGE");
		// if player doesnt exist
        if(queryResults.wasNull()){
                System.out.println("Employee with eid " + curEMPNO + " has no batting average"); 
        } else {	
             // if no batting average
            if(curAvg == null) {
                System.out.println("Employee with eid " + curEMPNO + " has no batting average"); 
            }
            // if batting average below 200
            if(Integer.parseInt(curAvg) < 200) {
                System.out.println("Employee with eid " + curEMPNO + " has an average below the Mendoza line");
            } else {
                  System.out.println("Employee with eid " + curEMPNO + " has a batting average of " + curAvg);
            }
        }
      }

      if(empty) {
	System.out.println("There is no employee with that employee number");

      }
      ResultSetMetaData rsmd = queryResults.getMetaData();
      statement.close();
      connect.close();
    }
    catch (SQLException e){System.out.println("OOPS" + e.getMessage());}
  }
}
