package org.codingnomads.refugees.controller;

import org.codingnomads.refugees.model.RefugeesByYearCountry;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class is designed to parse refugees_all_years.csv file from the UN this file can be found here
 * https://data.world/nrippner/refugee-host-nations
 * Created by nicola on 7/18/17.
 */
public class ParseCSV {

    public static void main(String[] args) {

        System.out.println("Starting application...");
        ArrayList<RefugeesByYearCountry> refugees = parseFile();
        System.out.println("Parsing complete...");
        System.out.println("Calling");
        ConvertToDB db = new ConvertToDB();
        boolean success = db.writeToDB(refugees);

    }

    public static ArrayList< RefugeesByYearCountry> parseFile(){
        System.out.println("parseFile() called...");

        // location of file
        //TODO: this should be a command line argument of flexibility
        String csvFile = "/Users/nicola/Documents/codingnomads/databases/nrippner-refugee-host-nations/" +
                "refugees_all_years.csv";

        // create arraylist to hold RefuggesByYearCountry objects that are created in while loop below
        ArrayList<RefugeesByYearCountry> refugees = new ArrayList();

        //instantiate "line" which will hold each line read from the csv file
        String line = "";
        String cvsSplitBy = ",";

        //use Try-with-resources to open csv file
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // discard first line of file which is headers/column names
            line = br.readLine();



            // this while loop assigns the next line of the csv file to the variable
            //"line" and while it is not null it continues looping
            while ((line = br.readLine()) != null) {

                //create empty pojo of RefugeesByYearCountry to populate
                //from csv file - each line in file is a new object
                RefugeesByYearCountry ref = new RefugeesByYearCountry();

                // use the "split" method to split the line on each comma
                //the results will automatically be stored in an array called data
                String[] data = line.split(cvsSplitBy);

                //begin setting  RefugeesByYearCountry (ref) instance variables
                //using the data read from the csv file, which is now the data array
                ref.setId(Integer.parseInt(data[0]));
                ref.setYear(Integer.parseInt(data[1]));
                ref.setAsylum_country(data[2]);
                ref.setOrigin_country(data[3]);

                //nested try catch to catch rows that have nothing but empty values after
                //index 3. The split method does not store the empty values if all are empty
                //after a given index
                try {
                    ref.setRefugees(data[4].equalsIgnoreCase("") ? 0 : (Double.parseDouble(data[4])));
                    ref.setAsylum_seekers(data[5].equalsIgnoreCase("") ? 0 : (Double.parseDouble(data[5])));
                    ref.setReturned_refugees(data[6].equalsIgnoreCase("") ? 0 : (Double.parseDouble(data[6])));
                    ref.setIdps(data[7].equalsIgnoreCase("") ? 0 : (Double.parseDouble(data[7])));
                    ref.setreturned_idps(data[8].equalsIgnoreCase("") ? 0 : (Double.parseDouble(data[8])));
                    ref.setStateless_persons(data[9].equalsIgnoreCase("") ? 0 : (Double.parseDouble(data[9])));
                    ref.setOthers_of_concern(data[10].equalsIgnoreCase("") ? 0 : (Double.parseDouble(data[10])));
                    ref.setTotal_population(data[11].equalsIgnoreCase("") ? 0 : (Double.parseDouble(data[11])));
                } catch(IndexOutOfBoundsException e){
                    // if exception is caught set all temaining values to default of 0
                    ref.setRefugees(0);
                    ref.setAsylum_seekers(0);
                    ref.setReturned_refugees(0);
                    ref.setIdps(0);
                    ref.setreturned_idps(0);
                    ref.setStateless_persons(0);
                    ref.setOthers_of_concern(0);
                    ref.setTotal_population(0);

                    }
                    //add  RefugeesByYearCountry object created in this loop to the
                    //ArrayList called "refugees"
                    refugees.add(ref);
                }

                //loop through until all lines of csv file have been processed

        }
        //catch IOException
        catch (IOException e) {
            e.printStackTrace();
        }
        return refugees;
    }

}







