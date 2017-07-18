package org.codingnomads.refugees.model;

/**
 * Created by nicola on 7/18/17.
 */
public class RefugeesByYearCountry {
    int id;
    int year;
    String asylum_country;
    String origin_country;
    double refugees;
    double asylum_seekers;
    double returned_refugees;
    double idps;
    double returned_idps;
    double stateless_persons;
    double others_of_concern;
    double total_population;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getAsylum_country() {
        return asylum_country;
    }

    public void setAsylum_country(String asylum_country) {
        this.asylum_country = asylum_country;
    }

    public String getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(String origin_country) {
        this.origin_country = origin_country;
    }

    public double getRefugees() {
        return refugees;
    }

    public void setRefugees(double refugees) {
        this.refugees = refugees;
    }

    public double getAsylum_seekers() {
        return asylum_seekers;
    }

    public void setAsylum_seekers(double asylum_seekers) {
        this.asylum_seekers = asylum_seekers;
    }

    public double getReturned_refugees() {
        return returned_refugees;
    }

    public void setReturned_refugees(double returned_refugees) {
        this.returned_refugees = returned_refugees;
    }

    public double getIdps() {
        return idps;
    }

    public void setIdps(double idps) {
        this.idps = idps;
    }

    public double getreturned_idps() {
        return returned_idps;
    }

    public void setreturned_idps(double returned_idps) {
        this.returned_idps = returned_idps;
    }

    public double getStateless_persons() {
        return stateless_persons;
    }

    public void setStateless_persons(double stateless_persons) {
        this.stateless_persons = stateless_persons;
    }

    public double getOthers_of_concern() {
        return others_of_concern;
    }

    public void setOthers_of_concern(double others_of_concern) {
        this.others_of_concern = others_of_concern;
    }

    public double getTotal_population() {
        return total_population;
    }

    public void setTotal_population(double total_population) {
        this.total_population = total_population;
    }

    @Override
    public String toString() {
        return "RefugeesByYearCountry{" +
                "id=" + id +
                ", year=" + year +
                ", asylum_country='" + asylum_country + '\'' +
                ", origin_country='" + origin_country + '\'' +
                ", refugees=" + refugees +
                ", asylum_seekers=" + asylum_seekers +
                ", returned_refugees=" + returned_refugees +
                ", idps=" + idps +
                ", returned_idps=" + returned_idps +
                ", stateless_persons=" + stateless_persons +
                ", others_of_concern=" + others_of_concern +
                ", total_population=" + total_population +
                '}';
    }
}
