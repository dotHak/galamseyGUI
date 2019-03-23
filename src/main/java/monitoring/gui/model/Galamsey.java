// The package name
package monitoring.gui.model;

/**
 * Definition of the <strong>Galamasey</strong> class
 * @author Hubert , Lisa, Jones, Bobie
 * @version 2.0.0
 */
public class Galamsey {
    // Instant  variables
    private int galamseyId;
    private int observatoryId;
    private Position position; // The position of the galamsey
    private int galamseyYear; // The year the galamsey happened
    private int colourValue; // The colour value of the vegetation
    private vegColour vegetationColour; // The colour of the vegetation
    private String createdAt;
    private String updatedAt;

    /**
     * enum object for the vegetation colour
     */
    public enum vegColour{
        green, yellow, brown;

        public static  vegColour getColour(int  ordinal){
            vegColour titleValue = null;
            for(vegColour value : vegColour.values()){
                if(value.ordinal() == ordinal-1){
                    titleValue = value;
                    break;
                }
            }
            return  titleValue ;
        }
    }

    /**
     *  Overloaded method using the position object
     * @param position the position object of the galamsey
     * @param galamseyYear the year the galamsy happened
     * @param colour the colour of the vegetation
     * @param galamseyId the galamsey Id
     * @param observatoryId  the observatory id
     */
    public Galamsey(int galamseyId, int observatoryId, Position position, int galamseyYear, vegColour colour) {
        this.galamseyId = galamseyId;
        this.observatoryId = observatoryId;
        this.position = position;
        this.galamseyYear = galamseyYear;
        this.colourValue = colour.ordinal()+1;
        this.vegetationColour = colour;
    }

    /**
     * Overloaded constructor using the longitudes and latitudes
     * @param longitude the longitude of the galamsey
     * @param latitude the latitude of the galamsey
     * @param galamseyYear the year the galamsey happened
     * @param colour the colour of the vegetation
     * @param observatoryId the observatory ID
     * @param galamseyId the galamsey ID
     */
    public Galamsey(int galamseyId,int observatoryId,double longitude, double latitude, int galamseyYear, vegColour colour) {
        this.galamseyId = galamseyId;
        this.observatoryId = observatoryId;
        this.position = new Position(longitude, latitude);
        this.galamseyYear = galamseyYear;
        this.colourValue = colour.ordinal()+1;
        this.vegetationColour = colour;
    }

    /**
     * Accessor method for the position
     * @return the position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Mutator method for the position using the position object
     * @param position the new position
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Mutator method for the position using longitude and latitude
     * @param longitude the new longitude
     * @param latitude the new latitude
     */
    public void setPosition(double longitude, double latitude) {
        this.position = new Position(longitude, latitude);
    }

    /**
     * Accessor method for the year of galamsey
     * @return the year of galamsey
     */
    public int getGalamseyYear() {
        return galamseyYear;
    }

    /**
     * Mutator method for the year of galamsey
     * @param galamseyYear the new year of galamsey
     */
    public void setGalamseyYear(int galamseyYear) {
        this.galamseyYear = galamseyYear;
    }

    /**
     * Accessor method for the colour of the vegetation
     * @return the colour of the vegetation
     */
    public vegColour getVegetationColour() {
        return vegetationColour;
    }

    /**
     * Mutator method for the colour of vegetation
     * @param vegetationColour the new colour of vegetation
     */
    public void setVegetationColour(vegColour vegetationColour) {
        this.vegetationColour = vegetationColour;
        this.colourValue = vegetationColour.ordinal() + 1;
    }

    /**
     * Accessor method for the galamsey ID
     * @return the galamsey ID
     */
    public int getGalamseyId() {
        return this.galamseyId;
    }

    /**
     * Mutator method for the galamsey ID
     * @param galamseyId the galamsey ID
     */
    public void setGalamseyId(int galamseyId) {
        this.galamseyId = galamseyId;
    }

    /**
     * Accessor method for the observatory ID
     * @return the observatory ID
     */
    public int getObservatoryId() {
        return observatoryId;
    }

    /**
     * Mutator method for the observatory ID
     * @param observatoryId the bew observatory Id
     */
    public void setObservatoryId(int observatoryId) {
        this.observatoryId = observatoryId;
    }

    /**
     * Accessor method for date created
     * @return the date created
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * Mutator method for the date created
     * @param createdAt the new date created
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Accessor method for the last updated
     * @return the last updated
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Mutator method for the last updated
     * @param updateAt new date updated
     */
    public void setUpdatedAt(String updateAt) {
        this.updatedAt = updateAt;
    }


    /**
     * Accessor method for the value of the colour
     * @return the value of the colour
     */
    public int getColourValue(){
        return this.colourValue;
    }

    /**
     * Tostring method
     * @return string of the object
     */
    @Override
    public String toString() {
        return "Galamsey of ID " + galamseyId;
    }

    /**
     * equals method for the galamsey class
     * @param object the object to compare
     * @return boolean
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Galamsey galamsey = (Galamsey) object;
        return galamseyYear == galamsey.galamseyYear &&
                colourValue == galamsey.colourValue &&
                position.equals(galamsey.position) &&
                vegetationColour == galamsey.vegetationColour;
    }

}
