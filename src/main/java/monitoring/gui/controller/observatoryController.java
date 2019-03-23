//Package name
package monitoring.gui.controller;

//Dependencies
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import monitoring.gui.model.Observatory;
import monitoring.gui.model.ObservatoryData;
import java.util.Calendar;


/**
 * @version Lisa, Jones, Bobie, Hubert
 * @version 2.0.0
 * Controller class for managing and processing inputs from the observatory form
 */
public class observatoryController  {

    //Instant variables
    @FXML
    private Button addButton, cancelButton;

    @FXML
    private TextField nameField, yearField, countryField, areaField;

    /**
     * Initialize method for starting the controller class
     */
    public void initialize(){
        addButton.setDisable(true);
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) addButton.getScene().getWindow();
                processResults();
                stage.close();
            }
        });


        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) addButton.getScene().getWindow();
                stage.close();
            }
        });

    }

    /**
     * Method for processing the information from  the observatory form
     */
    public void processResults(){
        String name = nameField.getText().trim();
        String country = countryField.getText().trim();
        int year = Integer.parseInt(yearField.getText().trim());
        double areacovered = Double.parseDouble(areaField.getText().trim());
        int id = ObservatoryData.getInstance().getDatabase().getLastEntry() + 1;
        addButton.setText("Loading");
        Runnable task = new Runnable() {
            @Override
            public void run() {

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ObservatoryData.getInstance().getDatabase().insertIntoObservatoryDB(name,country,year, areacovered);
                        Observatory newest = new Observatory(id,name,country, year, areacovered);
                        ObservatoryData.getInstance().getObservatoryHashMap().add(id,newest);
                        ObservatoryData.getInstance().getObservatories().add(newest);
                    }
                });

            }
        };

        new Thread(task).start();

    }

    /**
     *  Method for validation form fields of the observatory form
     */

    @FXML
    public void handleKeyReleased(){
        String name = nameField.getText().trim();
        Boolean valName = !name.isEmpty() && name.length() >= 2;
        String country = countryField.getText().trim();
        Boolean valCountry =  !(country.isEmpty()) && country.length() >= 3;

        Boolean isValid = false;
        if(!(yearField.getText().trim()).isEmpty()){
            int year = Integer.parseInt(yearField.getText().trim());
            isValid = year > 1901 && String.valueOf(year).length() == 4 && year <= Calendar.getInstance().get(Calendar.YEAR);
        }

        Boolean valArea = false;
        if(!(areaField.getText().trim()).isEmpty()){
            double areaCovered = Double.parseDouble(areaField.getText().trim());
            valArea =areaCovered > 0;
        }

        addButton.setDisable(!(valArea && isValid && valName && valCountry));
    }




}
