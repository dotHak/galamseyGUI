// The package name
package monitoring.gui.model;

// The program dependencies

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Definition of the <strong>Observatory</strong> class
 * @author Hubert, Bobie, Lisa, Jones
 * @version 2.0.0
 */
public class Observatory {
    // Instant variables
    private int observatoryID;
    private String observatoryName; // The name of the observatory
    private String countryName; // The country the observatory is located
    private int yearStarted; // The year the observatory was started
    private double areaCovered; // The area covered in kilometers square by the observatory
    private ObservableList<Galamsey> galamseyList; // The list of galamseys in the observatory
    private String createAt;
    private String UpdatedAt;
    private double largestAverageGalamseyEver;

    /**
     * Overloaded Constructor for the observatory
     * @param observatoryName the name of the observatory
     * @param countryName the name of country the observatory occurred
     * @param yearStarted the year the galamsey started
     * @param areaCovered the area covered in kilometers square
     * @param observatoryID the id of the observatory
     */
    public Observatory(int observatoryID,String observatoryName, String countryName, int yearStarted, double areaCovered) {
        this.observatoryID = observatoryID;
        this.observatoryName = observatoryName;
        this.countryName = countryName;
        this.yearStarted = yearStarted;
        this.areaCovered = areaCovered;
        this.galamseyList = FXCollections.observableArrayList();
    }

    /**
     * Accessor method for the observatory name
     * @return the observatory name
     */
    public String getObservatoryName() {
        return observatoryName;
    }

    /**
     * Mutator method for the observatory name
     * @param observatoryName the new observatory name
     */
    public void setObservatoryName(String observatoryName) {
        this.observatoryName = observatoryName;
    }

    /**
     * Accessor method for the name of country
     * @return the name of the country of the observatory
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Mutator method for the name of the country
     * @param countryName the new name of the country
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * Accessor method for year the observatory started
     * @return the year the observatory started
     */
    public int getYearStarted() {
        return yearStarted;
    }

    /**
     * Mutator method for the year of the observatory started
     * @param yearStarted the new year of the observatory started
     */
    public void setYearStarted(int yearStarted) {
        this.yearStarted = yearStarted;
    }

    /**
     * Accessor method the area covered by the galamsey
     * @return the area covered by the galamsey
     */
    public double getAreaCovered() {
        return areaCovered;
    }

    /**
     * Mutator method for the area covered by the galamsey
     * @param areaCovered the new area covered
     */
    public void setAreaCovered(double areaCovered) {
        this.areaCovered = areaCovered;
    }

    /**
     * Accessor method for the list of galamseys in the observatory
     * @return the list of galamseys in the observatory
     */
    public ObservableList<Galamsey> getGalamseyList() {
        return galamseyList;
    }

    /**
     * Mutator method for the list of galamseys in the observatory
     * @param galamseyList the new list of galamseys
     */
    public void setGalamseyList(ObservableList<Galamsey> galamseyList) {
        this.galamseyList = galamseyList;
    }


    /**
     * Accessor for observatoryID
     * @return the observatoryID
     */
    public int getObservatoryID() {
        return observatoryID;
    }

    /**
     * Mutator method for the observatory ID
     * @param observatoryID the new observatory ID
     */
    public void setObservatoryID(int observatoryID) {
        this.observatoryID = observatoryID;
    }

    /**
     * Accessor method for the date created
     * @return the date created
     */
    public String getCreateAt() {
        return createAt;
    }

    /**
     * Mutator method for the date created
     * @param createAt the new date
     */
    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    /**
     * Accessor method for last updated
     * @return the last updated
     */
    public String getUpdatedAt() {
        return UpdatedAt;
    }

    /**
     * Mutator method for the last updated
     * @param updatedAt the new last updated
     */
    public void setUpdatedAt(String updatedAt) {
        UpdatedAt = updatedAt;
    }

    /**
     * Accessor method for the largest average ever
     * @return the largest average ever
     */
    public double getLargestAverageGalamseyEver() {
        return largestAverageGalamseyEver;
    }

    /**
     *  Method for updating the last ever average galamsey value
     * @param largestAverageGalamseyEver the new average colour value
     */
    public void setLargestAverageGalamseyEver(double largestAverageGalamseyEver) {
        if(largestAverageGalamseyEver > this.largestAverageGalamseyEver){
            this.largestAverageGalamseyEver = largestAverageGalamseyEver;
        }
    }

    /**
     * Method to return the largest colour value
     * @return the largest colour value
     */
    public int largestColourValue(){
        int largestColourValue = 1;
        for(Galamsey galamsey: galamseyList){
            if(galamsey.getColourValue() > largestColourValue){
                largestColourValue = galamsey.getColourValue();
            }
        }
        return largestColourValue;
    }


    /**
     * Method to calculate the average of the colour values
     * @return the average of the colour values
     */
    public double averageColourValue(){
        double average = 0;
        int number = 0;
        for(Galamsey galamsey: galamseyList){
           average += galamsey.getColourValue();
           number++;
        }
        return  average/number;
    }

    /**
     * Method to return a list of galamseys with colour value greater than the input number
     * @param number the colour
     * @return the list of galamseys with colour value greater than the input number
     */
    public ArrayList<Galamsey> listGalamseys(int number){
        ArrayList<Galamsey> newList = new ArrayList<>();
        for(Galamsey galamsey: galamseyList){
            if(galamsey.getColourValue() > number){
                newList.add(galamsey);
            }
        }
        return newList;
    }

    /**
     *  Method for update the galamsey list from the galamsey hash table
     * @param hashMap galamsey hash table
     */
    public void updateGalamseyList( HashMap<Galamsey> hashMap){
        galamseyList = FXCollections.observableArrayList();
        if(hashMap.getSize() == 0 ){
            System.out.println("No items");
        }
        else{
            for(int i =0; i <= hashMap.getSize(); i++) {
                LinkedHashEntry<Galamsey> entry = hashMap.table[i];
                while (entry != null ){
                    if(entry.getValue().getObservatoryId() == this.observatoryID){
                        galamseyList.add(entry.getValue());
                        setLargestAverageGalamseyEver(averageColourValue());
                    }
                    entry = entry.getNext();
                }
            }
        }
    }

    /**
     * toString method
     * @return the string of the observatory object
     */
    @Override
    public String toString() {
        return observatoryID+" "+observatoryName;
    }

    /**
     * equals method for the observatory class
     * @param object the object to compare
     * @return boolean
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Observatory that = (Observatory) object;
        return yearStarted == that.yearStarted &&
                Double.compare(that.areaCovered, areaCovered) == 0 &&
                Objects.equals(observatoryName, that.observatoryName) &&
                Objects.equals(countryName, that.countryName);
    }



}
