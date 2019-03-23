//Package name
package monitoring.gui.controller;

import com.jfoenix.controls.JFXListView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import monitoring.gui.model.Galamsey;
import monitoring.gui.model.Observatory;
import monitoring.gui.model.ObservatoryData;
import java.io.IOException;

/**
 * @author Hubert, Bobie, Jones, Lisa
 * @version  2.0.0
 * Main controller class for managing application behaviours
 */
public class MainController {
    //Instant Variables
    @FXML
    private JFXListView<Observatory> observatoryListView;
    @FXML
    private JFXListView<Galamsey> galamseyJFXListView;

    @FXML
    private Text idText, nameText,yearText, countryText, createdText, updatedText;

    @FXML
    private Text galamseyId, colourText,colourValueText,latText, longText,yearStartedText;

    @FXML
    private Text obbID, updateText, createText, largestAverage, largestColour, areaCovered;

    @FXML
    private AnchorPane mainAnchorPane;
    static int observatoryID;
    static int galamseyID;

    @FXML
    Button deleteGalamseybtn, deleteObservatorybtn;


    /**
     * Initialize method for starting the controller class
     */
    public void initialize(){

        deleteGalamseybtn.setDisable(true);
        deleteObservatorybtn.setDisable(true);
        observatoryListView.setItems(ObservatoryData.getInstance().getObservatories());
        observatoryListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        observatoryListView.getSelectionModel().selectFirst();

        observatoryListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Observatory>() {
            @Override
            public void changed(ObservableValue<? extends Observatory> observable, Observatory oldValue, Observatory newValue) {
                if(newValue != null){
                    Observatory observatory = observatoryListView.getSelectionModel().getSelectedItem();
                    observatoryID = observatory.getObservatoryID();
                    galamseyJFXListView.getItems().setAll(observatory.getGalamseyList());
                }
            }
        });



    }

    /**
     * Method for showing the galamsey form
     */
    @FXML
    public void showGalamseyForm(){
        Dialog dialog = new Dialog<>();
        dialog.initOwner(mainAnchorPane.getScene().getWindow());

        try{
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/galamseyForm.fxml"));
            dialog.getDialogPane().setContent(root);
        }catch (IOException e){
            e.printStackTrace();
            return;
        }

        dialog.showAndWait();

    }

    @FXML
    public void showObservatoryForm(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainAnchorPane.getScene().getWindow());

        try{
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/observatoryForm.fxml"));
            dialog.getDialogPane().setContent(root);
        }catch (IOException e){
            e.printStackTrace();
            return;
        }
        dialog.showAndWait();

    }

    /**
     * Method for displaying all the observatoring in database
     */

    public void handleListView() {
        Observatory observatory = observatoryListView.getSelectionModel().getSelectedItem();
        galamseyJFXListView.getItems().setAll(observatory.getGalamseyList());
        idText.setText(Integer.toString(observatory.getObservatoryID()));
        nameText.setText(observatory.getObservatoryName());
        yearText.setText(Integer.toString(observatory.getYearStarted()));
        countryText.setText(observatory.getCountryName());
        createdText.setText(observatory.getCreateAt());
        updatedText.setText(observatory.getUpdatedAt());
        largestAverage.setText(Double.toString(observatory.getLargestAverageGalamseyEver()));
        largestColour.setText(Double.toString(observatory.largestColourValue()));
        areaCovered.setText(Double.toString(observatory.getAreaCovered()));
        observatoryID = observatory.getObservatoryID();
        deleteObservatorybtn.setDisable(false);
    }

    /**
     * Method for displaying all the galamseys in the observatory list
     */
    public void handleGalamseyListView(){
        Galamsey galamsey = galamseyJFXListView.getSelectionModel().getSelectedItem();
        galamseyId.setText(Integer.toString(galamsey.getGalamseyId()));
        obbID.setText(Integer.toString(galamsey.getObservatoryId()));
        yearStartedText.setText(Integer.toString(galamsey.getGalamseyYear()));
        colourValueText.setText(Integer.toString(galamsey.getColourValue()));
        colourText.setText(galamsey.getVegetationColour().toString());
        latText.setText(Double.toString(galamsey.getPosition().getLatitude()));
        longText.setText(Double.toString(galamsey.getPosition().getLongitude()));
        createText.setText(galamsey.getCreatedAt());
        updateText.setText(galamsey.getUpdatedAt());
        deleteGalamseybtn.setDisable(false);
        galamseyID = galamsey.getGalamseyId();

    }

    /**
     * Method displaying the confirm delete dialog of the galamsey
     */
    @FXML
    public  void deleteGalamsey(){
       Galamsey galamsey=  galamseyJFXListView.getSelectionModel().getSelectedItem();
        Dialog dialog = new Dialog<>();
        dialog.initOwner(mainAnchorPane.getScene().getWindow());

        try{
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/galamseyDeleteDialog.fxml"));
            dialog.getDialogPane().setContent(root);
        }catch (IOException e){
            e.printStackTrace();
        }
        dialog.showAndWait();

    }


    /**
     * Method for displaying confirm delete dialog of the galamsey
     */
    @FXML
    public  void deleteObservatory(){
       Observatory observatory = observatoryListView.getSelectionModel().getSelectedItem();
        Dialog dialog = new Dialog<>();
        dialog.initOwner(mainAnchorPane.getScene().getWindow());

        try{
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/observatoryDeleteDialog.fxml"));
            dialog.getDialogPane().setContent(root);
        }catch (IOException e){
            e.printStackTrace();
        }

        dialog.showAndWait();

    }

}
