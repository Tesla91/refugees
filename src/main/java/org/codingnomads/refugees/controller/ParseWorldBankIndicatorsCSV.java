package org.codingnomads.refugees.controller;

import org.codingnomads.refugees.model.WorldbankIndicators;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by nicola on 7/19/17.
 */
public class ParseWorldBankIndicatorsCSV {


    public static ArrayList< WorldbankIndicators> parseFile(){
        System.out.println("parseFile() called...");

        // location of file
        //TODO: this should be a command line argument of flexibility
        String csvFile = "/Users/nicola/Documents/codingnomads/databases/nrippner-refugee-host-nations/" +
                "worldbank_indicators.csv";

        // create arraylist to hold WorldbankIndicators objects that are created in while loop below
        ArrayList<WorldbankIndicators> worldbank = new ArrayList();

        //instantiate "line" which will hold each line read from the csv file
        String line = "";
        String cvsSplitBy = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";

        //use Try-with-resources to open csv file
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            // discard first line of file which is headers/column names
            line = br.readLine();


            int count =0;
            // this while loop assigns the next line of the csv file to the variable
            //"line" and while it is not null it continues looping
            while ((line = br.readLine()) != null) {

                //create empty pojo of WorldbankIndicators to populate
                //from csv file - each line in file is a new object
                WorldbankIndicators wb = new WorldbankIndicators();

                // use the "split" method to split the line on each comma
                //the results will automatically be stored in an array called data
                String[] data = line.split(cvsSplitBy);

                //begin setting  WorldbankIndicators (rwb) instance variables
                //using the data read from the csv file, which is now the data array
                wb.setSeries_name(data[0]);
                wb.setSeries_code(data[1]);
                wb.setCountry_name(data[2]);
                wb.setCountry_code(data[3]);
                wb.setYR2000(data[4].equalsIgnoreCase("..") ? 0 : (Double.parseDouble(data[4])));
                wb.setYR2001(data[5].equalsIgnoreCase("..") ? 0 : (Double.parseDouble(data[5])));
                wb.setYR2002(data[6].equalsIgnoreCase("..") ? 0 : (Double.parseDouble(data[6])));
                wb.setYR2003(data[7].equalsIgnoreCase("..") ? 0 : (Double.parseDouble(data[7])));
                wb.setYR2004(data[8].equalsIgnoreCase("..") ? 0 : (Double.parseDouble(data[8])));
                wb.setYR2005(data[9].equalsIgnoreCase("..") ? 0 : (Double.parseDouble(data[9])));
                wb.setYR2006(data[10].equalsIgnoreCase("..") ? 0 : (Double.parseDouble(data[10])));
                wb.setYR2007(data[11].equalsIgnoreCase("..") ? 0 : (Double.parseDouble(data[11])));
                wb.setYR2008(data[12].equalsIgnoreCase("..") ? 0 : (Double.parseDouble(data[12])));
                wb.setYR2009(data[13].equalsIgnoreCase("..") ? 0 : (Double.parseDouble(data[13])));
                wb.setYR2010(data[14].equalsIgnoreCase("..") ? 0 : (Double.parseDouble(data[14])));
                wb.setYR2011(data[15].equalsIgnoreCase("..") ? 0 : (Double.parseDouble(data[15])));
                wb.setYR2012(data[16].equalsIgnoreCase("..") ? 0 : (Double.parseDouble(data[16])));
                wb.setYR2013(data[17].equalsIgnoreCase("..") ? 0 : (Double.parseDouble(data[17])));
                wb.setYR2014(data[18].equalsIgnoreCase("..") ? 0 : (Double.parseDouble(data[18])));
                wb.setYR2015(data[19].equalsIgnoreCase("..") ? 0 : (Double.parseDouble(data[19])));

                //add  RefugeesByYearCountry object created in this loop to the
                //ArrayList called "worldbank"
                worldbank.add(wb);

                //print out a count to check at what line an exception is thrown
                System.out.println(count);
                count++;

                //print out the toString every 10th object
                if (count % 10 == 0){
                    System.out.println(wb.toString());
                }


            }

            //loop through until all lines of csv file have been processed

        }
        //catch IOException
        catch (IOException e) {
            e.printStackTrace();
        }
        return worldbank;
    }
}
