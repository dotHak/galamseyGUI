//Package name
package monitoring.gui.database;
/**
 * Dependencies
 */

import monitoring.gui.model.Galamsey;
import monitoring.gui.model.HashMap;
import monitoring.gui.model.Observatory;

import java.sql.*;

/**
 * @author  Hubert, Lisa, Jones, Bobie
 * @version 2.0.0
 * Database class for retrieving and inserting into database
 */
public class Database {

    private  Connection connection;
    private  Statement statement;
    private  ResultSet rs;

    /**
     * Database constructor
     */
    public Database() {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection=DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/65u5664pXs","65u5664pXs","dQsJG5hWui");
            statement = connection.createStatement();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public Statement getStatement() {
        return this.statement;
    }

    public ResultSet getRs() {
        return this.rs;
    }

    /**
     *  Method for inserting entry into galamsey databse
     * @param vegColour vegetation colour
     * @param colourValue the colour value
     * @param latitude the latitude of the position
     * @param longitude the longitude of the positon
     * @param year the year of the galamsey
     * @param obID the observatory ID the galamsey belongs to.
     */
    public void insertIntoGalamseyDB(String vegColour, int colourValue, double latitude,
                                     double longitude, int year, int obID){

        String values = "("+"'"+vegColour+"'"+","+colourValue+"," +latitude+","+longitude+","+year+","+obID+ "," + "'no'"+")";
        try{

            String sqlInsert = "INSERT INTO galamseydb(vegColour,colourValue,latitude,longitude,galamseyYear,observatoryID, deleted)" +
                    "VALUES" + values ;
            int countInserted = statement.executeUpdate(sqlInsert);
            System.out.println(countInserted + " records inserted.\n");

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    /**
     *  Method for inserting into the observatory database
     * @param name the observatory name
     * @param country the country name
     * @param year the year started
     * @param areaCovered the area covered
     */
    public void insertIntoObservatoryDB(String name, String country, int year, double areaCovered){

        String values = "("+"'"+name+"'"+","+"'" + country+ "'"+"," +year+","+areaCovered + "," + "'no'" + ")";
        try{

            String sqlInsert = "INSERT INTO observatorydb(name, country, yearStarted,areaCovered, deleted)" +
                    "VALUES" + values ;
            int countInserted = statement.executeUpdate(sqlInsert);
            System.out.println(countInserted + " records inserted.\n");

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    /**
     *  Method for deleting entry from the databse
     * @param id the id of the galamsey
     */
    public void deleteEntryFromGalamseyDB(int id){

        try {

            String sqlDelete = "UPDATE galamseydb SET deleted = 'yes' WHERE id = " + id;
            int countDeleted = statement.executeUpdate(sqlDelete);
            System.out.println(countDeleted + " records deleted.\n");

        }catch (SQLException e){
            e.printStackTrace();
        }


    }

    /**
     * Method for deleting entry from the observatory databse
     * @param id the id of the observatory to be deleted
     */
    public void deleteEntryFromObsevatoryDB(int id){

        try {
             ;
            String sqlDelete = "UPDATE observatorydb SET deleted = 'yes' WHERE id = " + id;
            int countDeleted = statement.executeUpdate(sqlDelete);
            System.out.println(countDeleted + " records deleted.\n");

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    /**
     * Method from converting the database to a hash table
     * @return the hash table of the observatory
     */
    public HashMap<Observatory> buildObservatoryHashTable(){
        HashMap<Observatory> observatoryHashMap = new HashMap<>();

        String strSelect = "SELECT * FROM observatorydb";
        try {
            ResultSet resultSet = statement.executeQuery(strSelect);
            while(resultSet.next()) {
                if(!(resultSet.getString("deleted").equals("yes"))) {
                    int id = Integer.parseInt(resultSet.getString("id"));
                    String name = resultSet.getString("name");
                    String country = resultSet.getString("country");
                    int yearStarted = resultSet.getDate("yearStarted").toLocalDate().getYear();
                    double areaCovered = Double.parseDouble(resultSet.getString("areaCovered"));
                    String createdAt = resultSet.getString("created_at");
                    String updatedAt = resultSet.getString("updated_at");

                    Observatory latest = new Observatory(id, name, country, yearStarted, areaCovered);
                    latest.setCreateAt(createdAt);
                    latest.setUpdatedAt(updatedAt);
                    observatoryHashMap.add(id, latest);
                }

            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return observatoryHashMap;
    }

    /**
     *  Method for retrieving the id of the last observatory entry in the database
     * @return the id of the last entry
     */
    public int getLastEntry(){
        int id = 0;
        String strSelect = "SELECT * FROM observatorydb";
        try {
            ResultSet resultSet = statement.executeQuery(strSelect);
            while(resultSet.next()) {
                if(resultSet.last()) {
                    id = Integer.parseInt(resultSet.getString("id"));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return id;
    }

    /**
     *  Method for retrieving the id of the last galamsey entry in the database
     * @return the id of the last entry
     */
    public int getLastGalamseyEntry(){
        int id = 0;
        String strSelect = "SELECT * FROM galamseydb";
        try {
            ResultSet resultSet = statement.executeQuery(strSelect);
            while(resultSet.next()) {
                if(resultSet.last()) {
                    id = Integer.parseInt(resultSet.getString("id"));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return id;
    }

    /**
     *  Method for converting the galamsey database into hash table
     * @return the hash table of the galamsey
     */
    public HashMap<Galamsey> buildGalamseyHashTable(){

        HashMap<Galamsey> galamseyHashMap = new HashMap<>();

        String strSelect = "SELECT * FROM galamseydb";
        try {
            ResultSet resultSet = statement.executeQuery(strSelect);
            while(resultSet.next()) {
                if(!(resultSet.getString("deleted").equals("yes"))) {
                    int id = Integer.parseInt(resultSet.getString("id"));
                    int colourValue = resultSet.getInt("colourValue");
                    double latitude = resultSet.getDouble("latitude");
                    double longitude = resultSet.getDouble("longitude");
                    int year = resultSet.getDate("galamseyYear").toLocalDate().getYear();
                    int obID = resultSet.getInt("observatoryID");

                    String createdAt = resultSet.getString("created_at");
                    String updatedAt = resultSet.getString("updated_at");

                    Galamsey latest = new Galamsey(id, obID, longitude, latitude, year, Galamsey.vegColour.getColour(colourValue));
                    latest.setCreatedAt(createdAt);
                    latest.setUpdatedAt(updatedAt);
                    galamseyHashMap.add(id, latest);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return galamseyHashMap;

    }


}