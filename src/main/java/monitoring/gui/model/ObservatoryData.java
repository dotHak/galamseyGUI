// Package name
package monitoring.gui.model;

import javafx.collections.ObservableList;
import monitoring.gui.database.Database;

/**
 * @author Jones, Lisa, Bobie, Hubert
 * @version 2.0.0
 * Singleton method for managing the data storage
 */
public class ObservatoryData {
    //Instant Variables
    private static ObservatoryData instance = new ObservatoryData();
    private HashMap<Observatory> observatoryHashMap;
    private HashMap<Galamsey> galamseyHashMap;
    private ObservableList<Observatory> observatories;
    private Database database;

    /**
     * Accessor method for the observatory data
     * @return the instance of the observatory data
     */
    public static ObservatoryData getInstance(){
        return instance;
    }

    /**
     * Constructor for the observatory data class
     */
    private ObservatoryData(){
        database = new Database();
        observatoryHashMap =database.buildObservatoryHashTable();
        observatories = observatoryHashMap.getAllItems();
        galamseyHashMap = database.buildGalamseyHashTable();
        buildObservatoryList();
    }

    /**
     * Method for building the observatory list
     */
    public void buildObservatoryList(){
        for(Observatory ob: observatories){
            ob.updateGalamseyList(galamseyHashMap);
        }
    }

    /**
     * Method for updating he database
     */
    public void updateDatabase(){
        observatoryHashMap =database.buildObservatoryHashTable();
        observatories = observatoryHashMap.getAllItems();
        galamseyHashMap = database.buildGalamseyHashTable();
        buildObservatoryList();
    }


    /**
     * Accessor method for the observatory hash table
     * @return the observatory hash table
     */
    public HashMap<Observatory> getObservatoryHashMap() {
        return observatoryHashMap;
    }

    /**
     * Mutator method for hte observatory hash table
     * @param observatoryHashMap new hash table
     */
    public void setObservatoryHashMap(HashMap<Observatory> observatoryHashMap) {
        this.observatoryHashMap = observatoryHashMap;
    }

    /**
     * Accessor Method for the galamsey hash table
     * @return the galamsey hash table
     */
    public HashMap<Galamsey> getGalamseyHashMap() {
        return galamseyHashMap;
    }

    /**
     * Mutator method for the galamsey hash table
     * @param galamseyHashMap new hash table
     */
    public void setGalamseyHashMap(HashMap<Galamsey> galamseyHashMap) {
        this.galamseyHashMap = galamseyHashMap;
    }

    /**
     * Accessor method for the observatory list
     * @return the observatory list
     */
    public ObservableList<Observatory> getObservatories() {
        return observatories;
    }

    /**
     * Accessor method for the observatory list
     * @param observatories the new observatory list
     */
    public void setObservatories(ObservableList<Observatory> observatories) {
        this.observatories = observatories;
    }

    /**
     * Accessor method for the Database object
     * @return the databse
     */
    public Database getDatabase() {
        return database;
    }

    /**
     *  Mutator method for the database
     * @param database new database
     */
    public void setDatabase(Database database) {
        this.database = database;
        observatoryHashMap = database.buildObservatoryHashTable();
        observatories = observatoryHashMap.getAllItems();
        galamseyHashMap = database.buildGalamseyHashTable();
        buildObservatoryList();
    }

}
