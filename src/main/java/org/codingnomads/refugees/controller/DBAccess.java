package org.codingnomads.refugees.controller;

import org.codingnomads.refugees.model.QueriesPOJO;
import org.codingnomads.refugees.model.RefugeesByYearCountry;
import org.codingnomads.refugees.model.WorldbankIndicators;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by nicola on 7/19/17.
 */
public class DBAccess {

    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public boolean writeToWorldbankIndicatorsDB(ArrayList<WorldbankIndicators> worldbank){
        try {

            connection = getConnection();

            // using PreparedStatements to set the names of the columns that will be populated in the database
            preparedStatement = connection
                    .prepareStatement("insert into worldbank_indicators " +
                            "(series_name, series_code, country_name, country_code, YR2000, YR2001, YR2002, YR2003, YR2004, " +
                            "YR2005, YR2006, YR2007, YR2008, YR2009, YR2010, YR2011, YR2012, YR2013, YR2014, YR2015) " +
                            "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            for(int i = 0; i <worldbank.size(); i++) {
                // inserting the parameters to the corresponding items from the CSV into the SQL table
                preparedStatement.setString(1, worldbank.get(i).getSeries_name());
                preparedStatement.setString(2, worldbank.get(i).getSeries_code());
                preparedStatement.setString(3, worldbank.get(i).getCountry_name());
                preparedStatement.setString(4, worldbank.get(i).getCountry_code());
                preparedStatement.setDouble(5, worldbank.get(i).getYR2000());
                preparedStatement.setDouble(6, worldbank.get(i).getYR2001());
                preparedStatement.setDouble(7, worldbank.get(i).getYR2002());
                preparedStatement.setDouble(8, worldbank.get(i).getYR2003());
                preparedStatement.setDouble(9, worldbank.get(i).getYR2004());
                preparedStatement.setDouble(10, worldbank.get(i).getYR2005());
                preparedStatement.setDouble(11, worldbank.get(i).getYR2006());
                preparedStatement.setDouble(12, worldbank.get(i).getYR2007());
                preparedStatement.setDouble(13, worldbank.get(i).getYR2008());
                preparedStatement.setDouble(14, worldbank.get(i).getYR2009());
                preparedStatement.setDouble(15, worldbank.get(i).getYR2010());
                preparedStatement.setDouble(16, worldbank.get(i).getYR2011());
                preparedStatement.setDouble(17, worldbank.get(i).getYR2012());
                preparedStatement.setDouble(18, worldbank.get(i).getYR2013());
                preparedStatement.setDouble(19, worldbank.get(i).getYR2014());
                preparedStatement.setDouble(20, worldbank.get(i).getYR2015());

                preparedStatement.executeUpdate();
                System.out.println("record # " + i + " inserted in worldbank_indicators table");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
        System.out.println("Done! Go look at your database.");
        return true;
    }
    public boolean writeToRefugeesByYearCountryDB(ArrayList<RefugeesByYearCountry> refugees){
        try {
            connection = getConnection();

            // using PreparedStatements to set the names of the columns that will be populated in the database
            preparedStatement = connection
                    .prepareStatement("insert into refugees_all " +
                            "(year, asylum_country, origin_country, refugees, asylum_seekers, returned_refugees, " +
                            "idps, returned_idps, stateless_persons, others_of_concern, total_population) " +
                            "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            for(int i = 0; i <refugees.size(); i++) {
                // inserting the parameters to the corresponding items from the CSV into the SQL table
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

    public ResultSet readDataBase(String query){
        try {

            // Setup the connection with the DB
            connection = getConnection();

            // Statements allow to issue SQL queries to the database
            statement = connection.createStatement();

            // Result set get the result of the SQL query
            resultSet = statement.executeQuery(query);

            return resultSet;

            //catching any exceptions
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public ArrayList<QueriesPOJO> queryOneToPojos(ResultSet resultSet) {

        ArrayList<QueriesPOJO> queries = new ArrayList<>();
        try {

            while (resultSet.next()) {

                // creating objects and setting the names of the columns
                Double sumOfRefugees = resultSet.getDouble("sumOfRefugees");
                String inAsylumCountry = resultSet.getString("inAsylumCountry");

                // creating an object to to set the parameters of the columns
                QueriesPOJO q = new QueriesPOJO();

                // setting the parameters of the variables that are being called in the query
                q.setSumOfRefugees(sumOfRefugees);
                q.setInAsylumCountry(inAsylumCountry);

                // adding the objects to the ArrayList
                queries.add(q);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return queries;
    }
    public ArrayList<QueriesPOJO> queryTwoToPojos(ResultSet resultSet) {

        ArrayList<QueriesPOJO> queries = new ArrayList<>();
        try {

            while (resultSet.next()) {

                // creating objects and setting the names of the columns
                double seekers = resultSet.getDouble("seekers");
                String asylum_country = resultSet.getString("asylum_country");

                // creating an object to to set the parameters of the columns
                QueriesPOJO q = new QueriesPOJO();

                // setting the parameters of the variables that are being called in the query
                q.setSeekers(seekers);
                q.setAsylum_country(asylum_country);

                // adding the objects to the ArrayList
                queries.add(q);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return queries;
    }
    public ArrayList<QueriesPOJO> queryThreeToPojos(ResultSet resultSet) {

        ArrayList<QueriesPOJO> queries = new ArrayList<>();
        try {

            while (resultSet.next()) {

                // creating objects and setting the names of the columns
                String series_name = resultSet.getString("series_name");
                String country_name = resultSet.getString("country_name");
                double YR2015 = resultSet.getDouble("YR2015");

                // creating an object to to set the parameters of the columns
                QueriesPOJO q = new QueriesPOJO();

                // setting the parameters of the variables that are being called in the query
                q.setSeries_name(series_name);
                q.setCountry_name(country_name);
                q.setYR2015(YR2015);

                // adding the objects to the ArrayList
                queries.add(q);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return queries;
    }

    private Connection getConnection() {

        try{
            System.out.println("Creating connection...");
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connection = DriverManager.getConnection("jdbc:mysql://localhost?" +
                    "user=root&password=??????&useSSL=false");
            System.out.println("Connection succeeded...");
        } catch (ClassNotFoundException cnf){
            cnf.printStackTrace();
        } catch (SQLException se){
            se.printStackTrace();
        }
        return connection;
    }

}
