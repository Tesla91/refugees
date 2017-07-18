package org.codingnomads.refugees.controller;

import org.codingnomads.refugees.model.RefugeesByYearCountry;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by nicola on 7/18/17.
 */
public class ConvertToDB {
    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public boolean writeToDB(ArrayList<RefugeesByYearCountry> refugees){
        try {
            System.out.println("creating connection...");
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connection = DriverManager.getConnection("jdbc:mysql://localhost/immigrants?" +
                    "user=root&password=???????&useSSL=false");
            System.out.println("Connection succeeded..");


            // Statements allow to issue SQL queries to the database
            System.out.println("Preparing statement...");
            statement = connection.createStatement();

            // PreparedStatements can use variables and are more efficient
            preparedStatement = connection
                    .prepareStatement("insert into refugees_all " +
                            "(year, asylum_country, origin_country, refugees, asylum_seekers, returned_refugees, " +
                            "idps, returned_idps, stateless_persons, others_of_concern, total_population) " +
                            "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            for(int i = 0; i <refugees.size(); i++) {
                // Parameters start with 1
                //preparedStatement.setInt(1, refugees.get(i).getId());
                preparedStatement.setInt(1, refugees.get(i).getYear());
                preparedStatement.setString(2, refugees.get(i).getAsylum_country());
                preparedStatement.setString(3, refugees.get(i).getOrigin_country());
                preparedStatement.setDouble(4, refugees.get(i).getRefugees());
                preparedStatement.setDouble(5, refugees.get(i).getAsylum_seekers());
                preparedStatement.setDouble(6, refugees.get(i).getReturned_refugees());
                preparedStatement.setDouble(7, refugees.get(i).getIdps());
                preparedStatement.setDouble(8, refugees.get(i).getreturned_idps());
                preparedStatement.setDouble(9, refugees.get(i).getStateless_persons());
                preparedStatement.setDouble(10, refugees.get(i).getOthers_of_concern());
                preparedStatement.setDouble(11, refugees.get(i).getTotal_population());

                preparedStatement.executeUpdate();
                System.out.println("record # " + i + " inserted in refugees_all table");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
        System.out.println("Done! Go look at your database.");
        return true;
    }

}
