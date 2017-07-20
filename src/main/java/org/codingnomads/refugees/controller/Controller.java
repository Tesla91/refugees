package org.codingnomads.refugees.controller;

import org.codingnomads.refugees.model.QueriesPOJO;
import org.codingnomads.refugees.model.RefugeesByYearCountry;
import org.codingnomads.refugees.model.WorldbankIndicators;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by nicola on 7/19/17.
 */
public class Controller {
    public static void main(String[] args) {

        String country = "";
        boolean populate = false;
        DBAccess db = new DBAccess();

        System.out.println("Starting application...");

        // you need to manually change the populate boolean if you want to repopulate the databases
        if(populate) {
            ArrayList<WorldbankIndicators> worldbank = ParseWorldBankIndicatorsCSV.parseFile();
            System.out.println("Parsing complete...");
            System.out.println("Calling convertToDB.write()...");
            boolean success = db.writeToWorldbankIndicatorsDB(worldbank);

            System.out.println("Starting application...");
            ArrayList<RefugeesByYearCountry> refugees = ParseRefugeesByYearCountryCSV.parseFile();
            System.out.println("Parsing complete...");
            System.out.println("Calling convertToDB.write()...");
            boolean success2 = db.writeToRefugeesByYearCountryDB(refugees);
        } else
            System.out.println("Skipping database population.");

        // confirming that that a parameter has been set before running the first query
        if(args.length > 0) {

            //writes the user argument to the country variable
            country = args[0];

            //reading the query from the getCustomQuery method and the user
            ResultSet resultsOne = db.readDataBase(getCustomQuery(country));

            //populating the ArrayList with the queryOneToPojos
            ArrayList<QueriesPOJO> finalOne = db.queryOneToPojos(resultsOne);
            System.out.println("The size of the ArrayList is " + finalOne.size());
        }

        //reading the query from the getQueryTwo method
        ResultSet resultsTwo = db.readDataBase(getQueryTwo());

        //populating the ArrayList with the queryTwoToPojos
        ArrayList<QueriesPOJO> finalTwo = db.queryTwoToPojos(resultsTwo);
        System.out.println("The size of the ArrayList is " + finalTwo.size());

        //reading the query from the getQueryThree method
        ResultSet resultsThree = db.readDataBase(getQueryThree());

        //populating the ArrayList with the queryThreeToPojos
        ArrayList<QueriesPOJO> finalThree = db.queryThreeToPojos(resultsThree);
        System.out.println("The size of the ArrayList is " + finalThree.size());

    }

    public static String getCustomQuery(String country){

        System.out.println("Running queryOne...");
        return "SELECT SUM(refugees) as sumOfRefugees, " +
                "asylum_country as inAsylumCountry " +
                "FROM " +
                "immigrants.refugees_all " +
                "WHERE asylum_country = '" + country + "'";
    }
    public static String getQueryTwo(){

        System.out.println("Running queryTwo...");
        return "SELECT " +
                "count(asylum_seekers) as seekers, " +
                "asylum_country " +
                "FROM " +
                "immigrants.refugees_all im " +
                "GROUP BY asylum_country " +
                "HAVING seekers  > 1 " +
                "ORDER BY asylum_country asc";
    }
    public static String getQueryThree(){

        System.out.println("Running queryThree...");
        return "SELECT series_name, " +
                "country_name, " +
                "YR2015 " +
                "FROM worldbank.worldbank_indicators " +
                "WHERE series_name = 'GDP (current US$)' " +
                "ORDER BY country_name asc";
    }
}
