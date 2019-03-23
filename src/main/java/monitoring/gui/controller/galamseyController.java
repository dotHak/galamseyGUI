// Package name
package monitoring.gui.controller;

// Dependencies
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import monitoring.gui.model.Galamsey;
import monitoring.gui.model.ObservatoryData;
import java.util.Calendar;

/**
 * @author Hubert, Bobie, Lisa, Jones
 * @version 2.0.0
 *  Controller for managing the the galamsey form
 */
public class galamseyController {

    //Instant variables
    @FXML
    private Button addButton, cancelButton;

    @FXML
    private TextField latText, longText, yearText;

    @FXML
    private RadioButton radio2, radio3, radio1, radioGreen, radioYellow, radioBrown;
    private int colourValue = 1;
    private String vegColour = "green";

    /**
     * Initialize method for starting the controller class
     */
    public void initialize(){
        addButton.setDisable(true);
        radio2.setDisable(true);
        radio1.setSelected(true);
        radio3.setDisable(true);
        radioGreen.setSelected(true);

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
     * Method for validating the form field on key type
     */
    public void handleKeyRelease(){
        Boolean valLat = false;
        if(!(latText.getText().trim().isEmpty())){
            double latitude = Double.parseDouble(latText.getText().trim());
            valLat = latitude >= 0;
        }
        Boolean valLong = false;
        if(!(longText.getText().trim().isEmpty())){
            double longitude = Double.parseDouble(longText.getText().trim());
            valLong = longitude >= 0;
        }
        Boolean valYear = false;
        if(!(yearText.getText().trim()).isEmpty()){
            int year = Integer.parseInt(yearText.getText().trim());
            valYear = String.valueOf(year).length() == 4 && year <= Calendar.getInstance().get(Calendar.YEAR) && year > 1901;
        }

        addButton.setDisable(!(valLat && valLong && valYear));
    }


    /**
     * Method for toggle the radio buttons
     */
    public void handleRadioToggle(){

        if(radioBrown.isArmed()){
            radio3.setDisable(false);
            radio3.setSelected(true);
            radio2.setDisable(true);
            radio1.setDisable(true);
            colourValue = 3;
            vegColour = "brown";
        }else if(radioGreen.isArmed()){
            radio1.setSelected(true);
            radio1.setDisable(false);
            radio2.setDisable(true);
            radio3.setDisable(true);
            colourValue = 1;
            vegColour = "green";
        }else if(radioYellow.isArmed()){
            radio2.setDisable(false);
            radio2.setSelected(true);
            radio1.setDisable(true);
            radio3.setDisable(true);
            colourValue = 2;
            vegColour = "yellow";
        }
    }

    /**
     * Method for processing the all form information
     */
    public void processResults(){
        double latitude = Double.parseDouble(latText.getText().trim());
        double longitude = Double.parseDouble(longText.getText().trim());
        int year = Integer.parseInt(yearText.getText().trim());

        int observatoryId = MainController.observatoryID;

        Runnable task = new Runnable() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        int id = ObservatoryData.getInstance().getDatabase().getLastGalamseyEntry() + 1;
                        Galamsey newest = new Galamsey(id,observatoryId,longitude,latitude,year, Galamsey.vegColour.getColour(colourValue));
                        ObservatoryData.getInstance().getGalamseyHashMap().add(id,newest);
                        ObservatoryData.getInstance().getDatabase().insertIntoGalamseyDB(vegColour,colourValue, latitude,longitude,year,observatoryId);
                        ObservatoryData.getInstance().buildObservatoryList();
                    }
                });

            }
        };

        new Thread(task).start();
    }

}



