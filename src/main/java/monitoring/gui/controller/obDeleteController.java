// Package name
package monitoring.gui.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import monitoring.gui.model.Observatory;
import monitoring.gui.model.ObservatoryData;

/**
 * @author Hubert, Lisa, Bobie, Jones
 * @version  2.0.0
 * Controller class for managing the deletion of the observatories
 */
public class obDeleteController {

    // Instant variables
    @FXML
    private Button observCancel, observConfirm;

    @FXML
    Text deleteObservId;

    /**
     * Initialize method starting the controller class
     */
    public void initialize(){

        deleteObservId.setText(Integer.toString(MainController.observatoryID));

        observConfirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) observConfirm.getScene().getWindow();
                processDeletion();
                stage.close();
            }
        });

        observCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) observCancel.getScene().getWindow();
                stage.close();
            }
        });

    }

    /**
     * Method for processing the deleting of the observatory object
     */
    public void processDeletion(){
        Runnable task = new Runnable() {
            @Override
            public void run() {

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Observatory observatory = ObservatoryData.getInstance().getObservatoryHashMap().get(MainController.observatoryID);
                        ObservatoryData.getInstance().getObservatoryHashMap().remove(MainController.observatoryID);
                        ObservatoryData.getInstance().getDatabase().deleteEntryFromObsevatoryDB(MainController.observatoryID);
                        ObservatoryData.getInstance().getObservatories().remove(observatory);
                        ObservatoryData.getInstance().updateDatabase();
                    }
                });

            }
        };

        new Thread(task).start();

    }
}
