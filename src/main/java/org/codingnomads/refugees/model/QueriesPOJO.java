package org.codingnomads.refugees.model;

/**
 * Created by nicola on 7/19/17.
 */
public class QueriesPOJO {

    String asylum_country;
    double sumOfRefugees;
    String inAsylumCountry;
    double seekers;
    String series_name;
    String country_name;
    double YR2015;


    public String getAsylum_country() {
        return asylum_country;
    }

    public void setAsylum_country(String asylum_country) {
        this.asylum_country = asylum_country;
    }

    public String getSeries_name() {
        return series_name;
    }

    public void setSeries_name(String series_name) {
        this.series_name = series_name;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }


    public double getYR2015() {
        return YR2015;
    }

    public void setYR2015(double YR2015) {
        this.YR2015 = YR2015;
    }

    public double getSeekers() {
        return seekers;
    }

    public void setSeekers(double seekers) {
        this.seekers = seekers;
    }

    public double getSumOfRefugees() {
        return sumOfRefugees;
    }

    public void setSumOfRefugees(double sumOfRefugees) {
        this.sumOfRefugees = sumOfRefugees;
    }

    public String getInAsylumCountry() {
        return inAsylumCountry;
    }

    public void setInAsylumCountry(String inAsylumCountry) {
        this.inAsylumCountry = inAsylumCountry;
    }
}
